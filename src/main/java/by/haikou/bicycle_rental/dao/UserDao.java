package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.UserEntity;

import java.util.List;

public interface UserDao {
    
    UserEntity getUser(String login) throws DAOException;

    UserEntity getUser(String login, String password) throws DAOException;

    List<UserEntity> getAllUsers()throws DAOException;

    void updateUser(UserEntity user) throws DAOException;

    UserEntity getUserById(Integer userId)throws DAOException;

    void delete(Integer userId) throws DAOException;

    List<UserEntity> getAllSupports()throws DAOException;

    void addUser(UserEntity user) throws DAOException;
}
