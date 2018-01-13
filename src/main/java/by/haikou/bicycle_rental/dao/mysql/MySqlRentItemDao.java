package by.haikou.bicycle_rental.dao.mysql;

import by.haikou.bicycle_rental.dao.RentItemDao;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.dao.mysql.db.ConnectionPool;
import by.haikou.bicycle_rental.dao.mysql.db.ResultSetConverter;
import by.haikou.bicycle_rental.entity.RentItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlRentItemDao implements RentItemDao {
    private static final String SQL_FOR_UPDATE_RENT_ITEM = "UPDATE `rent_item` SET `end_date`=?, `parking_to_id`=?, `total_price`=?\n" +
            "WHERE `id`=?";
    private final ConnectionPool pool = ConnectionPool.getPool();

    @Override
    public void createItem(RentItem rentItem) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();

            statement = connection.prepareStatement("insert into rent_item (bicycle_id,user_id,start_date, parking_from_id, price) values (?, ?, ?,?,?)");
            statement.setInt(1, rentItem.getBikeId());
            statement.setInt(2, rentItem.getUserId());
            statement.setTimestamp(3, new Timestamp(rentItem.getFromDate().getTime()));
            statement.setInt(4, rentItem.getParkingFromId());
            statement.setBigDecimal(5, rentItem.getPrice());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public void updateItem(RentItem rentItem) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();

            statement = connection.prepareStatement(SQL_FOR_UPDATE_RENT_ITEM);
            statement.setTimestamp(1, new Timestamp(rentItem.getToDate().getTime()));
            statement.setInt(2, 1);
            statement.setBigDecimal(3, rentItem.getTotalPrice());
            statement.setInt(4, rentItem.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public List<RentItem> historyRent(Integer id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<RentItem> result = new ArrayList<>();

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("select * from rent_item where user_id =? order by start_date desc");
            statement.setInt(1, id);
            set = statement.executeQuery();

            while (set.next()) {
                RentItem entity = ResultSetConverter.createRentItemEntity(set);
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
    public RentItem findTakenByUser(Integer userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        RentItem result = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("SELECT * FROM rent_item where user_id=? and total_price is null order by start_date desc");
            statement.setInt(1, userId);
            set = statement.executeQuery();

            if (set.next()) {
                result = ResultSetConverter.createRentItemEntity(set);
//                if (result.getStatus()) {
//                    result = null;
//                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement, set);
        }

        return result;
    }
}
