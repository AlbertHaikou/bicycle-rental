package by.haikou.bicycle_rental.dao.mysql;

import by.haikou.bicycle_rental.dao.UserDao;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.dao.mysql.db.ConnectionPool;
import by.haikou.bicycle_rental.dao.mysql.db.ResultSetConverter;
import by.haikou.bicycle_rental.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(MySqlUserDao.class);

    private static final String SQL_FOR_GET_ALL_USERS = "SELECT `id`,`first_name`,`last_name`,`email`,`password`, `banned`, `balance` FROM `user` \n" +
            "WHERE `role`=\"user\"";
    private static final String SQL_FOR_GET_ALL_MANAGERS = "SELECT `id`,`first_name`,`last_name`,`email`,`password`, `banned`, `balance` FROM `user` \n" +
            "WHERE `role`=\"manager\"";
    private static final String SQL_FOR_GET_USER = "SELECT `id`,`first_name`,`last_name`,`email`,`password`,`role`, `banned`, `balance`" +
            "FROM `user` \n" +
            "WHERE `email`=?";
    private static final String SQL_FOR_UPDATE_USER = "UPDATE `user` SET `first_name`=?, `last_name`=?, `email`=? , `password`=?, `role`=?, `banned`=?, `balance`=?\n" +
            "WHERE `id`=?";
    private static final String SQL_FOR_UPDATE_PROFILE = "UPDATE `user` SET `first_name`=?, `last_name`=?, `email`=?\n" +
            "WHERE `id`=?";

    private static final String SQL_FOR_GET_USER_BY_ID = "SELECT `id`,`first_name`,`last_name`,`email`,`password`,`role`, `banned`, `balance`" +
            "FROM `user` \n" +
            "WHERE `id`=?";
    private static final String SQL_FOR_DELETE_USER = "DELETE " +
            "FROM `user` " +
            "WHERE `id` = ?";
    private static final String SQL_FOR_BUN_USER = "UPDATE `user` " +
            "SET `banned` = 1 " +
            "WHERE `id` = ?";
    private static final String SQL_FOR_UNBUN_USER = "UPDATE `user` " +
            "SET `banned` = 0 " +
            "WHERE `id` = ?";
    private static final String SQL_FOR_CHANGE_ROLE = "UPDATE `user` " +
            "SET `role`=? " +
            "WHERE `id`=?";
    private static final String SQL_FOR_ADD_USER = "INSERT INTO `user` (`first_name`,`last_name`,`email`,`password`, `role`," +
            " `banned`, `balance`, `credit_sum`, `debtor`) " +
            "VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_FOR_GET_BALANCE_BY_USER_ID = "SELECT `balance` " +
            "FROM `user` \n" +
            "WHERE `id`=?";
    private static final String SQL_FOR_FILL_UP_USER_BALANCE = "UPDATE `user` SET `balance`=? WHERE `id`=?";
    private static final String SQL_FOR_REPAY_A_LOAN = "UPDATE `user` SET `credit_sum`=? WHERE `id`=?";
    private static final String SQL_FOR_UPDATE_IS_DEBTOR = "UPDATE `user` SET `debtor`=? WHERE `id`=?";
    private static final String SQL_FOR_IS_USER_DEBTOR = "SELECT `debtor` FROM `user` WHERE `id`=?";
    private static final String SQL_FOR_GET_CREDIT_BY_USER_ID = "SELECT `credit_sum`" +
            "FROM `user` \n" +
            "WHERE `id`=?";

    private final ConnectionPool pool = ConnectionPool.getPool();

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
    public User getUser(String login) throws DAOException {
        User entity = null;
        User.Role role = User.Role.USER;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = pool.getConnection();

            statement = connection.prepareStatement(SQL_FOR_GET_USER);
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
    public User getUserById(Integer userId) throws DAOException {
        if (userId == null) {
            return null;
        }
        User entity = null;
        User.Role role = User.Role.USER;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_USER_BY_ID);
            statement.setInt(1, userId);
            set = statement.executeQuery();

            while (set.next()) {
                role = User.Role.valueOf(set.getString("role").toUpperCase());
            }
            if (set.previous()) {
                entity = ResultSetConverter.createUserEntity(set);
                entity.setRole(role);
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
    public List<User> getAllUsers() throws DAOException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<User> result = new ArrayList<>();

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_ALL_USERS);
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
    public void updateProfile(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_UPDATE_PROFILE);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public void updateUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_UPDATE_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().getValue());
            statement.setBoolean(6, (user.getBanned()));
            statement.setBigDecimal(7, user.getBalance());
            statement.setInt(8, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public BigDecimal getBalanceByUserId(Integer userId) throws DAOException {
        if (userId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_BALANCE_BY_USER_ID);
            statement.setInt(1, userId);
            set = statement.executeQuery();
            if (set.next()) {
                BigDecimal balance = set.getBigDecimal("balance");
                return balance;
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
    public BigDecimal getCreditByUserId(Integer userId) throws DAOException {
        if (userId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_CREDIT_BY_USER_ID);
            statement.setInt(1, userId);
            set = statement.executeQuery();
            if (set.next()) {
                BigDecimal creditSum = set.getBigDecimal("credit_sum");
                return creditSum;
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
    public void fillUpBalance(BigDecimal balance, Integer userId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_FILL_UP_USER_BALANCE);
            statement.setBigDecimal(1, getBalanceByUserId(userId).add(balance));
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public void delete(Integer userId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_DELETE_USER);
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
    public void banUser(Integer userId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_BUN_USER);
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
    public void unBanUser(Integer userId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_UNBUN_USER);
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
    public void changeUserRole(Integer userId, User.Role role) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_CHANGE_ROLE);
            statement.setString(1, role.getValue());
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public List<User> getAllManagers() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<User> result = new ArrayList<>();
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_ALL_MANAGERS);
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
            statement = connection.prepareStatement(SQL_FOR_ADD_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().getValue());
            statement.setBoolean(6, false);
            statement.setBigDecimal(7, new BigDecimal(0));
            statement.setBigDecimal(8, new BigDecimal(0));
            statement.setBoolean(9, false);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public void repayALoan(BigDecimal sum, Integer userId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_REPAY_A_LOAN);
            statement.setBigDecimal(1, getCreditByUserId(userId).add(sum));
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public void updateIsDebtor(Boolean debtor, Integer userId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_UPDATE_IS_DEBTOR);
            statement.setBoolean(1, debtor);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }


    @Override
    public Boolean isUserDebtor(Integer userId) throws DAOException {
        if (userId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_IS_USER_DEBTOR);
            statement.setInt(1, userId);
            set = statement.executeQuery();

            if (set.next()) {
                Boolean isDebtor = set.getBoolean("debtor");
                return isDebtor;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement, set);
        }

        return null;
    }

}
