package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UserException;

import java.math.BigDecimal;
import java.util.List;

public interface UserDao {

    User getUser(String login) throws DAOException, UserException;

    User getUser(String login, String password) throws DAOException, UserException;

    List<User> getAllUsers() throws DAOException;

    void updateUser(User user) throws DAOException;

    void updateProfile(User user) throws DAOException;

    User getUserById(Integer userId) throws DAOException;

    BigDecimal getBalanceByUserId(Integer userId) throws DAOException;

    BigDecimal getCreditByUserId(Integer userId) throws DAOException;

    void fillUpBalance(BigDecimal balance, Integer userId) throws DAOException;

    void delete(Integer userId) throws DAOException;

    void banUser(Integer userId) throws DAOException;

    void unBanUser(Integer userId) throws DAOException;

    void repayALoan(BigDecimal sum, Integer userId) throws DAOException;

    void updateIsDebtor(Boolean debtor, Integer userId) throws DAOException;

    Boolean isUserDebtor(Integer userId) throws DAOException;

    void changeUserRole(Integer userId, User.Role role) throws DAOException;

    List<User> getAllManagers() throws DAOException;

    void addUser(User user) throws DAOException;
}
