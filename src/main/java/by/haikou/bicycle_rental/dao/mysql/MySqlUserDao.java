package by.haikou.bicycle_rental.dao.mysql;

import by.haikou.bicycle_rental.dao.UserDao;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.dao.mysql.db.ConnectionPool;
import by.haikou.bicycle_rental.dao.mysql.db.ResultSetConverter;
import by.haikou.bicycle_rental.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao implements UserDao {
    private final ConnectionPool pool = ConnectionPool.getPool();

    @Override
    public User getUser(String login) throws DAOException {
        User entity = null;
        User.Role role = User.Role.USER;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = pool.getConnection();

            statement = connection.prepareStatement("SELECT id,firstName,lastName, "
                    + "email,password,role from user \n"
                    + "where email=?");
            statement.setString(1, login);
            set = statement.executeQuery();

            if (set != null) {
                while (set.next()) {
                    role = User.Role.valueOf(set.getString("role").toUpperCase());
                }
                if (set.previous()) {
                    entity = ResultSetConverter.createUserEntity(set);
                    entity.setRole(role);
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
    public User getUser(String login, String password) throws DAOException {
        User user = getUser(login);
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
    public List<User> getAllUsers() throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<User> result = new ArrayList<>();

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("SELECT id,firstName,lastName,email,password from user \n"
                    + "where role=\"user\"");
            set = statement.executeQuery();

            while (set.next()) {
                User entity = ResultSetConverter.createUserEntity(set);
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
    public void updateUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("update user set firstName=?, lastName=?, email=? , password=?, role=?, banned=?"
                    + "where id=?");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().getValue());
            statement.setBoolean(6, (user.getBanned()));
            statement.setInt(7, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public User getUserById(Integer userId) throws DAOException {
        if (userId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("select * from user where id=?");
            statement.setInt(1, userId);
            set = statement.executeQuery();

            if (set.next()) {
                User entity = ResultSetConverter.createUserEntity(set);
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
            statement = connection.prepareStatement("delete from user where id=?");
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
    public List<User> getAllSupports() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<User> result = new ArrayList<>();
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("SELECT id,firstName,lastName, email,password,role from user \n"
                    + "where role = \"manager\"");
            set = statement.executeQuery();

            while (set.next()) {
                User entity = ResultSetConverter.createUserEntity(set);
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
    public void addUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("insert into user(firstName,lastName,email,password, role) values (?,?,?,?,?)");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().getValue());
            statement.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

}
