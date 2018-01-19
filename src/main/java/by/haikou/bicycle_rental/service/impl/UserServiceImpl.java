package by.haikou.bicycle_rental.service.impl;

import by.haikou.bicycle_rental.dao.UserDao;
import by.haikou.bicycle_rental.dao.factory.DAOFactory;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UserException;
import by.haikou.bicycle_rental.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;


public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LogManager.getLogger(UserService.class);
    private UserDao userDao = DAOFactory.getFactory().getUserDao();

    @Override
    public User login(String login, String password) throws UserException {
        User user = userDao.getUser(login, password);
        return user;
    }

    @Override
    public User login(String login) {
        User user = null;
        try {
            user = userDao.getUser(login);
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void deleteUser(Integer userId) {
        userDao.delete(userId);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    public Boolean isLoginFree(String login) {
        boolean userNotFound = false;
        try {
            userNotFound = null == userDao.getUser(login);
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        return userNotFound;
    }

    public BigDecimal getBalanceByUserId(Integer userId) {
        return userDao.getBalanceByUserId(userId);
    }

    @Override
    public void fillUpUserBalance(BigDecimal balance, Integer id) {
        userDao.fillUpBalance(balance, id);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void updateProfile(User user) {
        userDao.updateProfile(user);
    }

    @Override
    public void banUser(Integer id) {
        userDao.banUser(id);
    }

    @Override
    public void appointUserAsManager(Integer id) {
        userDao.changeUserRole(id, User.Role.MANAGER);
    }

    @Override
    public void withdrawUserPrivileges(Integer id) {
        userDao.changeUserRole(id, User.Role.USER);
    }

    @Override
    public void unBanUser(Integer id) {
        userDao.unBanUser(id);
    }

    @Override
    public List<User> getAllSupports() {
        return userDao.getAllManagers();
    }

}
