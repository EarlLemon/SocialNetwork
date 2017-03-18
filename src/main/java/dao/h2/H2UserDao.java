package dao.h2;

import common.ConnectionPool;
import dao.UserDao;
import exeptions.UserAlreadyExistExeption;
import exeptions.UserNotFoundExeption;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import static security.EncryptUtil.encrypt;


public class H2UserDao implements UserDao {

    private static final Logger Log = LogManager.getLogger(H2UserDao.class);
    private static H2UserDao h2UserDao;
    private static ConnectionPool connectionPool;

    private static final String GET_ALL_USERS = "SELECT * FROM User";
    private static final String GET_USER_BY_ID = "SELECT * FROM User WHERE id=?";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM User WHERE email=?";
    private static final String ADD_USER = "INSERT INTO User (firstname, lastname, email, password, username, gropu_n)" +
            " VALUES (?,?,?,?,?,?)";

    // return the service instance

    public static H2UserDao getInstance() {
        if (h2UserDao == null) {
            h2UserDao = new H2UserDao();
            Log.debug("gettting instance of " + H2UserDao.class);
        }
        return h2UserDao;
    }



    @Override
    public void addUser(User user) throws UserAlreadyExistExeption {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
            String hash = encrypt(user.getPassword());

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, hash);
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.setString(6, user.getGroupN());

            preparedStatement.execute();

        } catch (InterruptedException | SQLException e) {
            Log.error("Can not add user", e);
        }

    }

    @Override
    public Collection<User> getAll() {
        Collection<User> allUsers = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
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
                                resultSet.getString("username"),
                                resultSet.getString("groupn")

                        )
                );
            }
        } catch (InterruptedException | SQLException e) {
            Log.error("Can not getAll users", e);
        }


        return allUsers;
    }

    @Override
    public User getUserById(int id) throws UserNotFoundExeption {
        User user = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStUser = connection.prepareStatement(GET_USER_BY_ID)) {

            prepStUser.setInt(1, id);
            try (ResultSet resultSet = prepStUser.executeQuery()) {
                while (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("username"),
                            resultSet.getString("groupn")
                    );
                }
            }

        } catch (InterruptedException | SQLException e) {
            Log.error("Cannot get user by id = " + id, e);
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundExeption {
        User user = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement prepStUser = connection.prepareStatement(GET_USER_BY_EMAIL)) {

            prepStUser.setString(1, email);
            try (ResultSet resultSet = prepStUser.executeQuery()) {
                while (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("username"),
                            resultSet.getString("groupn")
                    );
                }
            }
        } catch (InterruptedException | SQLException e) {
            Log.error("Cannot get user by email, where email = " + email, e);
        }
        return user;
    }
}
