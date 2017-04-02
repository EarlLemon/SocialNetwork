package dao;

import exeptions.UserAlreadyExistExeption;
import exeptions.UserNotFoundExeption;
import model.User;

import java.util.Collection;

public interface UserDao {
    int addUser(User user) throws UserAlreadyExistExeption;

    Collection<User> getAll();

    User getUserById(int id) throws UserNotFoundExeption;

    User getUserByEmail(String email) throws UserNotFoundExeption;


}
