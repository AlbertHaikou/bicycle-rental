package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UserException;

import java.util.List;

public interface UserService {

    User login(String login, String password) throws UserException;

    User login(String login) throws UserException;

    List<User> getAllUsers();

    void deleteUser(Integer userId);

    User getUserById(Integer userId);

    void addUser(User user);

    void updateUser(User user);

    List<User> getAllSupports();
}
