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
    private final ConnectionPool pool = ConnectionPool.getPool();

    @Override
    public void addParking(Parking parking) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();

            statement = connection.prepareStatement("insert into parking(street) values (?)");
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
            statement = connection.prepareStatement("delete from Parking where id=?");
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
            statement = connection.prepareStatement("update Parking set street=? where id=?");
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
            statement = connection.prepareStatement("select * from Parking order by id asc");
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
            statement = connection.prepareStatement("select * from Parking where id=?");
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
