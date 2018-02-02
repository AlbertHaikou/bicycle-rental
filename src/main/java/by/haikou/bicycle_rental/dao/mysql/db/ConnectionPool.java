package by.haikou.bicycle_rental.dao.mysql.db;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final Properties properties = DBPropertiesReader.getDBProperties();
    private static final ConnectionPool connectionPool = new ConnectionPool(properties);

    private static final String DB_DRIVER_NAME = "com.mysql.jdbc.Driver";

    private ArrayBlockingQueue<Connection> connections;
    private ReentrantLock lockForReturnConnection;

    private ConnectionPool(Properties properties) {
        try {
            Class.forName(DB_DRIVER_NAME);
        } catch (ClassNotFoundException exc) {
            LOGGER.log(Level.ERROR, exc);
        }
        int poolSize = Integer.parseInt(properties.getProperty("poolSize"));
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        connections = new ArrayBlockingQueue<>(poolSize);
        lockForReturnConnection = new ReentrantLock();
        Connection connection;
        for (int i = 0; i < poolSize; i++) {
            try {
                connection = DriverManager.getConnection(url, username, password);
                connections.offer(connection);
            } catch (SQLException exc) {
                LOGGER.log(Level.ERROR, exc);
            }
        }
    }

    public static ConnectionPool getPool() {
        return connectionPool;
    }

    public Connection getConnection() {
        Connection connection;
        try {
            connection = connections.take();
        } catch (InterruptedException exc) {
            connection = null;
            LOGGER.log(Level.ERROR, exc);
        }
        return connection;
    }

    public void returnConnectionToPool(Connection c) {
        lockForReturnConnection.lock();
        if (!connections.contains(c)) {
            connections.offer(c);
        }
        lockForReturnConnection.unlock();
    }

    @Override
    protected void finalize() throws Throwable {
        for (Connection c : connections) {
            c.close();
        }
    }


    public void closeDbResources(PreparedStatement statement) {
        closeDbResources(statement, null);
    }

    public void closeDbResources(PreparedStatement statement, ResultSet resultSet) {
        closeResultSet(resultSet);
        closeStatement(statement);
    }

    private void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Can't close prepared statement.", e);
            }
        }
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Can't close result set.", e);
            }
        }
    }
}
