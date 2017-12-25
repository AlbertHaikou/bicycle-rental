package by.haikou.bicycle_rental.service.impl;

import java.util.List;

import by.haikou.bicycle_rental.dao.RentItemDao;
import by.haikou.bicycle_rental.dao.factory.DAOFactory;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UserServiceImpl implements UserService {

    private static Logger log = LogManager.getLogger(UserService.class);
    private UserDao userDao = DAOFactory.getFactory().getUserDao();
    private RentItemDao rentItemDao = DAOFactory.getFactory().getRentItemDao();

    @Override
    public User getUser(String login, String password) {
        User user = userDao.getUser(login, password);
        return user;
    }

  

    @Override
    public User getUser(String login) {
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
    public List<User> getAllSupports() {
        return userDao.getAllSupports();
    }

}