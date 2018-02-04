package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UserException;
import by.haikou.bicycle_rental.util.PaginationObject;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    /**
     * Checks login and password for correspondence in the database and authorizes the user in case of success.
     *
     * @param login
     * @param password
     * @return
     * @throws UserException
     */
    User login(String login, String password) throws UserException;

    /**
     * @return List of all users.
     */
    List<User> getAllUsers();

    /**
     * Delete user from database by user id.
     *
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * Gets user from database by user id.
     *
     * @param userId
     * @return User
     */
    User getUserById(Integer userId);

    /**
     * Checks if the entered login is free.
     *
     * @param login
     * @return
     */
    Boolean isLoginFree(String login);

    /**
     * Gets balance of user by id.
     *
     * @param userId
     * @return the user's balance.
     */
    BigDecimal getBalanceByUserId(Integer userId);

    /**
     * Adds money to the account of the user.
     *
     * @param balance
     * @param userId
     */
    void fillUpUserBalance(BigDecimal balance, Integer userId);

    /**
     * Adds a new User into a database.
     *
     * @param user
     */
    void addUser(User user);

    /**
     * Registers the taking of a loan by the user.
     *
     * @param balance
     * @param id
     */
    void takeALoan(BigDecimal balance, Integer id);

    /**
     * Checks if user a debtor.
     *
     * @param id
     * @return
     */
    Boolean isUserDebtor(Integer id);

    /**
     * @param userId
     * @return Credit sum by user ID.
     */
    BigDecimal getCreditByUserId(Integer userId);

    /**
     * Updates user profile information in database.
     *
     * @param user
     */
    void updateProfile(User user);

    /**
     * Ban user by ID.
     *
     * @param id
     */
    void banUser(Integer id);

    /**
     * Makes user a manager.
     *
     * @param id
     */
    void appointUserAsManager(Integer id);

    /**
     * Makes manager a simply user.
     *
     * @param id
     */
    void withdrawUserPrivileges(Integer id);

    /**
     * Unban user by ID.
     *
     * @param id
     */
    void unBanUser(Integer id);

    /**
     * @return List of all managers.
     */
    List<User> getAllManagers();

    /**
     * @return List of all managers for the selected page.
     */
    PaginationObject<User> getAllManagers(Integer page);

    /**
     * @return List of all users for the selected page.
     */
    PaginationObject<User> getAllUsers(Integer page);
}
