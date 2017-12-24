package by.haikou.bicycle_rental.dao.mysql;

import by.haikou.bicycle_rental.dao.UserDao;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.dao.mysql.db.ConnectionPool;
import by.haikou.bicycle_rental.dao.mysql.db.ResultSetConverter;
import by.haikou.bicycle_rental.entity.UserEntity;
import by.haikou.bicycle_rental.util.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao implements UserDao {
    private final ConnectionPool pool = ConnectionPool.getPool();

    @Override
    public UserEntity getUser(String login) throws DAOException {
        UserEntity entity = null;
        List<String> roles = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = pool.getConnection();

            statement = connection.prepareStatement("SELECT users.id,users.firstName,users.lastName, "
                    + "users.email,users.password,roles.roleName from users \n"
                    + "join users_to_roles on users.id = users_to_roles.fk_user_id\n"
                    + "join roles on users_to_roles.fk_role_id= roles.id\n"
                    + "where email=?");
            statement.setString(1, login);
            set = statement.executeQuery();

            if (set != null) {
                while (set.next()) {
                    roles.add(set.getString("roleName"));
                }
                if (set.previous()) {
                    entity = ResultSetConverter.createUserEntity(set);
                    entity.setRoles(roles);
                }
            }

            return entity;

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement, set);
        }

    }

    @Override
    public UserEntity getUser(String login, String password) throws DAOException {
        UserEntity user = getUser(login);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                return user;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public List<UserEntity> getAllUsers() throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<UserEntity> result = new ArrayList<>();

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("SELECT users.id,users.firstName,users.lastName, users.email, users.password from users \n"
                    + "join users_to_roles on users.id = users_to_roles.fk_user_id\n"
                    + "join roles on users_to_roles.fk_role_id= roles.id\n"
                    + "where roles.roleName=\"user\"");
            set = statement.executeQuery();

            while (set.next()) {
                UserEntity entity = ResultSetConverter.createUserEntity(set);
                result.add(entity);
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement, set);
        }

    }

    @Override
    public void updateUser(UserEntity user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("update Users set firstName=?, lastName=?, email=? , password=?"
                    + "where id=?");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public UserEntity getUserById(Integer userId) throws DAOException {
        if (userId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("select * from Users where id=?");
            statement.setInt(1, userId);
            set = statement.executeQuery();

            if (set.next()) {
                UserEntity entity = ResultSetConverter.createUserEntity(set);
                return entity;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement, set);
        }

        return null;
    }

    @Override
    public void delete(Integer userId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("delete from users_to_roles where fk_user_id=?");
            statement.setInt(1, userId);
            statement.executeUpdate();
            statement = connection.prepareStatement("delete from Users where id=?");
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public List<UserEntity> getAllSupports() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<UserEntity> result = new ArrayList<>();
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("SELECT users.id,users.firstName,users.lastName, users.email,users.password,roles.roleName from users \n"
                    + "join users_to_roles on users.id = users_to_roles.fk_user_id\n"
                    + "join roles on users_to_roles.fk_role_id= roles.id\n"
                    + "where roleName=\"support\"");
            set = statement.executeQuery();

            while (set.next()) {
                UserEntity entity = ResultSetConverter.createUserEntity(set);
                result.add(entity);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement, set);
        }

        return result;
    }

    @Override
    public void addUser(UserEntity user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        Integer idUsers;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("insert into Users(firstName,lastName,email,password) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idUsers = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
                statement = connection.prepareStatement("insert into Users_to_Roles(fk_user_id,fk_role_id) values (?,?)");
                for (Role role : user.getRoles()) {
                    int roleId = role.getId();
                    statement.setInt(1, idUsers);
                    statement.setInt(2, roleId);
                }
                statement.execute();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

}
