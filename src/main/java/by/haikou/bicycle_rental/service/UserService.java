package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.UserEntity;
import java.util.List;

public interface UserService {

    UserEntity getUser(String login, String password);

    UserEntity getUser(String login);

    List<UserEntity> getAllUsers();

    void deleteUser(Integer userId);

    UserEntity getUserById(Integer userId);

    void addUser(UserEntity user);

    void updateUser(UserEntity user);

    List<UserEntity> getAllSupports();
}
