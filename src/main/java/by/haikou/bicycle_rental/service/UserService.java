package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UserException;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    User login(String login, String password) throws UserException;

    User login(String login) throws UserException;

    List<User> getAllUsers();

    void deleteUser(Integer userId);

    User getUserById(Integer userId);

    BigDecimal getBalanceByUserId(Integer userId);

    void fillUpUserBalance(BigDecimal balance, Integer id);

    void addUser(User user);

    void updateUser(User user);

    void updateProfile(User user);

    void banUser(Integer id);

    void appointUserAsManager(Integer id);

    void withdrawUserPrivileges(Integer id);

    void unBanUser(Integer id);

    List<User> getAllSupports();
}
