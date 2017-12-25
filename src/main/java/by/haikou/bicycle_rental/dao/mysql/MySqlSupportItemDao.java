package by.haikou.bicycle_rental.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.haikou.bicycle_rental.dao.SupportItemDao;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.dao.mysql.db.ConnectionPool;
import by.haikou.bicycle_rental.entity.SupportItemEntity;
import by.haikou.bicycle_rental.dao.mysql.db.ResultSetConverter;

public class MySqlSupportItemDao implements SupportItemDao {
    private final ConnectionPool pool = ConnectionPool.getPool();

    @Override
    public void createItem (SupportItemEntity supportItem) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();

            statement = connection.prepareStatement("insert into SupportItem(fk_Bikes_id,description,status) values (?, ?, ?)");
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
    public List<SupportItemEntity> getAllItems() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<SupportItemEntity> result = new ArrayList<>();

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("select * from SupportItem");
            set = statement.executeQuery();

            while (set.next()) {
                SupportItemEntity entity = ResultSetConverter.createSupportItemEntity(set);
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
    public List<SupportItemEntity> unperformedItem() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        List<SupportItemEntity> result = new ArrayList<>();

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("select * from SupportItem where status=0");
            set = statement.executeQuery();

            while (set.next()) {
                SupportItemEntity entity = ResultSetConverter.createSupportItemEntity(set);
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
    public SupportItemEntity getItemById(Integer bikeId) throws DAOException {
        if (bikeId == null) {
            return null;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement("select * from SupportItem where fk_Bikes_id=?");
            statement.setInt(1, bikeId);
            set = statement.executeQuery();

            if (set.next()) {
                SupportItemEntity entity = ResultSetConverter.createSupportItemEntity(set);
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
            statement = connection.prepareStatement("update SupportItem set status=true where fk_Bikes_id=?");
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
