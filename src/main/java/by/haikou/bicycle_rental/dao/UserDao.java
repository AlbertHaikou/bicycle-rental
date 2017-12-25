package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.User;

import java.util.List;

public interface UserDao {
    
    User getUser(String login) throws DAOException;

    User getUser(String login, String password) throws DAOException;

    List<User> getAllUsers()throws DAOException;

    void updateUser(User user) throws DAOException;

    User getUserById(Integer userId)throws DAOException;

    void delete(Integer userId) throws DAOException;

    List<User> getAllSupports()throws DAOException;

    void addUser(User user) throws DAOException;
}
