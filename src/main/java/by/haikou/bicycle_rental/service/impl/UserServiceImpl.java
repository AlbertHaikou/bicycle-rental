package by.haikou.bicycle_rental.service.impl;

import by.haikou.bicycle_rental.dao.RentItemDao;
import by.haikou.bicycle_rental.dao.UserDao;
import by.haikou.bicycle_rental.dao.factory.DAOFactory;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UserException;
import by.haikou.bicycle_rental.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class UserServiceImpl implements UserService {

    private static Logger log = LogManager.getLogger(UserService.class);
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
            log.error(e);
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
