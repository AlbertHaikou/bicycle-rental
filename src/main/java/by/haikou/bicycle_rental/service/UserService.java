package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.User;

import java.util.List;

public interface UserService {

    User getUser(String login, String password);

    User getUser(String login);

    List<User> getAllUsers();

    void deleteUser(Integer userId);

    User getUserById(Integer userId);

    void addUser(User user);

    void updateUser(User user);

    List<User> getAllSupports();
}
