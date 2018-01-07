package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UserException;

import java.util.List;

public interface UserDao {

    User getUser(String login) throws DAOException, UserException;

    User getUser(String login, String password) throws DAOException, UserException;

    List<User> getAllUsers()throws DAOException;

    void updateUser(User user) throws DAOException;

    User getUserById(Integer userId)throws DAOException;

    void delete(Integer userId) throws DAOException;

    void banUser(Integer userId) throws DAOException;

    void unBanUser(Integer userId) throws DAOException;

    void changeUserRole(Integer userId, User.Role role) throws DAOException;

    List<User> getAllSupports()throws DAOException;

    void addUser(User user) throws DAOException;
}
