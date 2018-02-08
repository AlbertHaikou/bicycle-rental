package by.haikou.bicycle_rental.dao.mysql;

import by.haikou.bicycle_rental.dao.RepairItemDao;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.dao.mysql.db.ConnectionPool;
import by.haikou.bicycle_rental.dao.mysql.db.ResultSetConverter;
import by.haikou.bicycle_rental.entity.RepairItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlRepairItemDao implements RepairItemDao {
    private final static String SQL_FOR_CREATE_ITEM = "INSERT INTO `repair_item` (`bicycle_id`,`description`,`status`) VALUES (?,?,?)";
    private final static String SQL_FOR_GET_ALL_ITEMS = "SELECT `id`,`bicycle_id`,`description`,`status` FROM `repair_item`";
    private final static String SQL_FOR_GET_UNPERFORMED_ITEMS = "SELECT `id`,`bicycle_id`,`description`,`status` FROM `repair_item` WHERE `status`='0'";
    private final static String SQL_FOR_GET_ITEM_BY_BIKE_ID = "SELECT `id`,`bicycle_id`,`description`,`status` FROM `repair_item` WHERE `bicycle_id`=?";
    private final static String SQL_FOR_REPAIR_BIKE = "UPDATE `repair_item` SET `status` = 1 WHERE `id`=?";

    private final ConnectionPool pool = ConnectionPool.getPool();

    @Override
    public void createItem(RepairItem supportItem) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();

            statement = connection.prepareStatement(SQL_FOR_CREATE_ITEM);
            statement.setInt(1, supportItem.getBikeId());
            statement.setString(2, supportItem.getDescription());
            statement.setBoolean(3, supportItem.getStatus());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public List<RepairItem> getAllItems() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<RepairItem> result = new ArrayList<>();

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_ALL_ITEMS);
            set = statement.executeQuery();

            while (set.next()) {
                RepairItem entity = ResultSetConverter.createRepairItemEntity(set);
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
    public List<RepairItem> unperformedItem() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<RepairItem> result = new ArrayList<>();

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_UNPERFORMED_ITEMS);
            set = statement.executeQuery();

            while (set.next()) {
                RepairItem entity = ResultSetConverter.createRepairItemEntity(set);
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
    public RepairItem getItemById(Integer bikeId) throws DAOException {
        if (bikeId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_ITEM_BY_BIKE_ID);
            statement.setInt(1, bikeId);
            set = statement.executeQuery();

            if (set.next()) {
                RepairItem entity = ResultSetConverter.createRepairItemEntity(set);
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
    public void repairItem(Integer id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_REPAIR_BIKE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }
}
