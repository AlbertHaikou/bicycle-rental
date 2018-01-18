package by.haikou.bicycle_rental.dao.mysql;

import by.haikou.bicycle_rental.dao.ParkingDao;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.dao.mysql.db.ConnectionPool;
import by.haikou.bicycle_rental.dao.mysql.db.ResultSetConverter;
import by.haikou.bicycle_rental.entity.Parking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlParkingDao implements ParkingDao {
    private static final String SQL_FOR_CREATE_PARKING = "INSERT INTO `parking` (`street`) VALUES (?)";
    private static final String SQL_FOR_DELETE_PARKING = "DELETE FROM `parking` WHERE `id`=?";
    private static final String SQL_FOR_UPDATE_PARKING = "UPDATE `parking` SET `street`=? WHERE `id`=?";
    private static final String SQL_FOR_GET_ALL_PARKING = "SELECT `id`, `street` from parking order by id asc";
    private static final String SQL_FOR_GET_PARKING_BY_ID = "SELECT `id`, `street` from parking WHERE `id`=?";


    private final ConnectionPool pool = ConnectionPool.getPool();

    @Override
    public void addParking(Parking parking) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();

            statement = connection.prepareStatement(SQL_FOR_CREATE_PARKING);
            statement.setString(1, parking.getStreet());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public void deleteParking(Integer parkingId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_DELETE_PARKING);
            statement.setInt(1, parkingId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public void updateParking(Parking parking) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_UPDATE_PARKING);
            statement.setString(1, parking.getStreet());
            statement.setInt(2, parking.getParkingId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public List<Parking> getAllParking() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<Parking> result = new ArrayList<>();

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_ALL_PARKING);
            set = statement.executeQuery();

            while (set.next()) {
                Parking entity = ResultSetConverter.createParkingEntity(set);
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
    public Parking getParkingById(Integer parkingId) throws DAOException {
        if (parkingId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_PARKING_BY_ID);
            statement.setInt(1, parkingId);
            set = statement.executeQuery();

            if (set.next()) {
                Parking entity = ResultSetConverter.createParkingEntity(set);
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
}
