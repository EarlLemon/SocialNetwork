package dao.h2;


import dao.UserDao;
import exeptions.UserAlreadyExistExeption;
import exeptions.UserNotFoundExeption;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import static security.EncryptUtil.encrypt;


public class H2UserDao implements UserDao {

    private static final Logger Log = LogManager.getLogger(H2UserDao.class);
    private DataSource dataSource;


    private static final String GET_ALL_USERS = "SELECT firstname,lastname,username,password,email FROM User";
    private static final String GET_USER_BY_ID = "SELECT email, password, firstname, lastname FROM User WHERE id=?";
    private static final String GET_USER_BY_EMAIL = "SELECT id, password, firstname, lastname FROM User WHERE email=?";
    private static final String ADD_USER = "INSERT INTO User (firstname, lastname, email, password, username)" +
            " VALUES (?,?,?,?,?)";


    H2UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public int addUser(User user) throws UserAlreadyExistExeption {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS )) {
            String hash = encrypt(user.getPassword());
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, hash);
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            Log.error("Can not add user", e);
        }
        return 0;
    }

    @Override
    public Collection<User> getAll() {
        Collection<User> allUsers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_USERS)) {
            while (resultSet.next()) {
                allUsers.add(
                        new User(
                                resultSet.getInt("id"),
                                resultSet.getString("firstname"),
                                resultSet.getString("lastname"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getString("username")


                        )
                );
            }
        } catch (SQLException e) {
            Log.error("Can not getAll users", e);
        }


        return allUsers;
    }

    @Override
    public User getUserById(int id) throws UserNotFoundExeption {
        User user = new User();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement prepStUser = connection.prepareStatement(GET_USER_BY_ID)) {
            prepStUser.setInt(1, id);
            try (ResultSet resultSet = prepStUser.executeQuery()) {
                resultSet.next();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setId(id);
            }

        } catch (SQLException e) {
            Log.error("Cannot get user by id = " + id, e);
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundExeption {
        User user = new User();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement prepStUser = connection.prepareStatement(GET_USER_BY_EMAIL)) {

            prepStUser.setString(1, email);
            try (ResultSet resultSet = prepStUser.executeQuery()) {
                resultSet.next();
                user.setId(resultSet.getInt("id"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setEmail(email);
            }
        } catch (SQLException e) {
            Log.error("Cannot get user by email, where email = " + email, e);
        }
        return user;
    }
}
