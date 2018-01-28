package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UserException;
import by.haikou.bicycle_rental.util.PaginationObject;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    User login(String login, String password) throws UserException;

    User login(String login) throws UserException;

    List<User> getAllUsers();

    void deleteUser(Integer userId);

    User getUserById(Integer userId);

    Boolean isLoginFree(String login);

    BigDecimal getBalanceByUserId(Integer userId);

    void fillUpUserBalance(BigDecimal balance, Integer id);

    void addUser(User user);

    void takeALoan(BigDecimal balance, Integer id);

    Boolean isUserDebtor(Integer id);

    BigDecimal getCreditByUserId(Integer userId);

    void updateProfile(User user);

    void banUser(Integer id);

    void appointUserAsManager(Integer id);

    void withdrawUserPrivileges(Integer id);

    void unBanUser(Integer id);

    List<User> getAllSupports();

    PaginationObject<User> getAllSupports(Integer page);

    PaginationObject<User> getAllUsers(Integer page);
}
