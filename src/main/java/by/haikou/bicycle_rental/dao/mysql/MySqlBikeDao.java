package by.haikou.bicycle_rental.dao.mysql;

import by.haikou.bicycle_rental.dao.BikeDao;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.dao.mysql.db.ConnectionPool;
import by.haikou.bicycle_rental.dao.mysql.db.ResultSetConverter;
import by.haikou.bicycle_rental.entity.Bicycle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlBikeDao implements BikeDao {
    private static final Logger LOGGER = LogManager.getLogger(MySqlBikeDao.class);
    private static final String SQL_FOR_CREATE_BIKE = "INSERT INTO `bicycle` (`type`,`model`,`size`,`available`,`fk_parking_id`,`price`)" +
            " VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_FOR_CREATE_BIKE_WITH_IMAGE = "INSERT INTO `bicycle` (`type`,`model`,`size`," +
            "`available`,`fk_parking_id`,`price`, `foto`)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_FOR_DELETE_BIKE = "DELETE FROM `bicycle` WHERE `id`= ?";
    private static final String SQL_FOR_RENT_BIKE = "UPDATE `bicycle` SET `available`= '0' WHERE `id`=?";
    private static final String SQL_FOR_RETURN_BIKE = "UPDATE `bicycle` SET `available`= '1', fk_parking_id = ? WHERE `id`=?";
    private static final String SQL_FOR_UPDATE_BIKE = "UPDATE `bicycle` SET `type`=?, `model`=?, `size`=?, `available`=?, `fk_parking_id`=?, `price`=? WHERE `id`=?";
    private static final String SQL_FOR_GET_ALL_BIKES = "SELECT `id`,`type`,`model`,`size`,`available`,`fk_parking_id`,`price` " +
            "FROM `bicycle` WHERE NOT `id` IN (SELECT `bicycle_id` FROM `repair_item` WHERE `status` = 0 GROUP BY `bicycle_id`)";
    private static final String SQL_FOR_GET_BIKE_BY_ID = "SELECT `id`,`type`,`model`,`size`,`available`,`fk_parking_id`,`price` FROM `bicycle` WHERE `id` = ?";
    private static final String SQL_FOR_GET_AVAILABLE_BIKES = "SELECT `id`,`type`,`model`,`size`,`available`,`fk_parking_id`,`price` " +
            "FROM `bicycle` WHERE `available` = '1' AND NOT `id` IN (SELECT `bicycle_id` FROM `repair_item` WHERE `status` = 0 GROUP BY `bicycle_id`)";
    private static final String SQL_FOR_GET_AVAILABLE_BIKES_BY_PARKING_ID = "SELECT `id`,`type`,`model`,`size`,`available`,`fk_parking_id`,`price` " +
            "FROM `bicycle` WHERE `fk_parking_id` = ? " +
            "AND NOT `id` IN (SELECT `bicycle_id` FROM `repair_item` WHERE `status` = 0 GROUP BY `bicycle_id`) HAVING `available`='1'";
    private static final String SQL_FOR_GET_BIKES_BY_PARKING_ID = "SELECT `id`,`type`,`model`,`size`,`available`,`fk_parking_id`,`price` " +
            "FROM `bicycle` WHERE `fk_parking_id` = ? AND NOT `id` IN (SELECT `bicycle_id` FROM `repair_item` WHERE `status` = 0 GROUP BY `bicycle_id`)";
    private static final String SQL_FOR_SET_BIKE_IMAGE = "UPDATE `bicycle` SET `foto`= ? WHERE `id`=?";

    private static final String SQL_FOR_GET_BIKE_IMAGE_BY_ID = "SELECT `foto` FROM `bicycle` WHERE `id` = ?";


    private final ConnectionPool pool = ConnectionPool.getPool();

    @Override
    public void createBike(Bicycle bike) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_CREATE_BIKE);
            statement.setString(1, bike.getType());
            statement.setString(2, bike.getModel());
            statement.setString(3, bike.getSize());
            statement.setBoolean(4, bike.getIsAvailable());
            statement.setInt(5, bike.getParkingId());
            statement.setBigDecimal(6, bike.getPrice());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public void createBike(Bicycle bike, Part image) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try (InputStream is = image.getInputStream()) {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_CREATE_BIKE_WITH_IMAGE);
            statement.setString(1, bike.getType());
            statement.setString(2, bike.getModel());
            statement.setString(3, bike.getSize());
            statement.setBoolean(4, bike.getIsAvailable());
            statement.setInt(5, bike.getParkingId());
            statement.setBigDecimal(6, bike.getPrice());
            statement.setBlob(7, is);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (IOException e) {
            throw new DAOException("InputStream error with Part.getInputStream", e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public void deleteBike(Integer bikeId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_DELETE_BIKE);
            statement.setInt(1, bikeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public void updateBike(Bicycle bike) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_UPDATE_BIKE);
            statement.setString(1, bike.getType());
            statement.setString(2, bike.getModel());
            statement.setString(3, bike.getSize());
            statement.setBoolean(4, bike.getIsAvailable());
            statement.setInt(5, bike.getParkingId());
            statement.setBigDecimal(6, bike.getPrice());
            statement.setInt(7, bike.getBicycleId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public List<Bicycle> getAllBikes() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<Bicycle> result = new ArrayList<>();

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_ALL_BIKES);
            set = statement.executeQuery();

            while (set.next()) {
                Bicycle entity = ResultSetConverter.createBikeEntity(set);
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
    public Bicycle getBikeById(Integer bikeId) throws DAOException {
        if (bikeId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_BIKE_BY_ID);
            statement.setInt(1, bikeId);
            set = statement.executeQuery();

            if (set.next()) {
                Bicycle entity = ResultSetConverter.createBikeEntity(set);
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
    public List<Bicycle> showAvailableBike() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Bicycle> result = new ArrayList<>();
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_AVAILABLE_BIKES);
            set = statement.executeQuery();

            while (set.next()) {
                Bicycle entity = ResultSetConverter.createBikeEntity(set);
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
    public void rentBike(Integer bikeId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_RENT_BIKE);
            statement.setInt(1, bikeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public void returnBike(Integer bikeId, Integer parkingId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_RETURN_BIKE);
            statement.setInt(1, parkingId);
            statement.setInt(2, bikeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public List<Bicycle> showBikeByParkingId(Integer parkingId) throws DAOException {
        if (parkingId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Bicycle> result = new ArrayList<>();
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_BIKES_BY_PARKING_ID);
            statement.setInt(1, parkingId);
            set = statement.executeQuery();
            while (set.next()) {
                Bicycle entity = ResultSetConverter.createBikeEntity(set);
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
    public List<Bicycle> showAvailableBikeByParkingId(Integer parkingId) throws DAOException {
        if (parkingId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<Bicycle> result = new ArrayList<>();
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_AVAILABLE_BIKES_BY_PARKING_ID);
            statement.setInt(1, parkingId);
            set = statement.executeQuery();
            while (set.next()) {
                Bicycle entity = ResultSetConverter.createBikeEntity(set);
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
    public void setBikeImage(int id, Part image) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_SET_BIKE_IMAGE);
            InputStream is = image.getInputStream();
            statement.setBlob(1, is);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (IOException e) {
            throw new DAOException("InputStream error with Part.getInputStream", e);
        } finally {
            pool.returnConnectionToPool(connection);
            ConnectionPool.getPool().closeDbResources(statement);
        }
    }

    @Override
    public byte[] getBikeImage(Integer bikeId) throws DAOException {
        if (bikeId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(SQL_FOR_GET_BIKE_IMAGE_BY_ID);
            statement.setInt(1, bikeId);
            set = statement.executeQuery();

            if (set.next()) {
                byte[] image = set.getBytes("foto");
                return image;
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
