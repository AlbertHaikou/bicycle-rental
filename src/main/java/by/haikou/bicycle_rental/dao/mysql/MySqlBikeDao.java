package by.haikou.bicycle_rental.dao.mysql;

import by.haikou.bicycle_rental.dao.BikeDao;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.dao.mysql.db.ConnectionPool;
import by.haikou.bicycle_rental.dao.mysql.db.ResultSetConverter;
import by.haikou.bicycle_rental.entity.Bicycle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlBikeDao implements BikeDao {
    private static final Logger LOGGER = LogManager.getLogger(MySqlBikeDao.class);
    private final ConnectionPool pool = ConnectionPool.getPool();

    @Override
    public void createBike(Bicycle bike) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("insert into bicycle(type,model,size,available,fk_parking_id,price) values (?, ?, ?, ?, ?, ?)");
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
    public void deleteBike(Integer bikeId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("delete from bicycle where id=?");
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
            statement = connection.prepareStatement("UPDATE `bicycle` SET `type`=?, `model`=?, `size`=?, `available`=?, `fk_parking_id`=?, `price`=? WHERE `id`=?");
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
            statement = connection.prepareStatement("select * from bicycle");
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
            statement = connection.prepareStatement("select * from bicycle where id=?");
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
            statement = connection.prepareStatement("select * from bicycle where available=1");
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
            statement = connection.prepareStatement("update bicycle set available=false where id=?");
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
    public void returnBike(Integer bikeId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("update bicycle set available=true where id=?");
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

            statement = connection.prepareStatement("select * from bicycle where fk_parking_id=?");
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

}
