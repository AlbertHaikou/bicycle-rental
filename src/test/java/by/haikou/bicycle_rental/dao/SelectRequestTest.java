package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.mysql.db.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.testng.Assert.assertTrue;

public class SelectRequestTest {
    private static final Logger LOGGER = LogManager.getLogger(SelectRequestTest.class);

    @Test
    public void selectRequest() {
        ResultSet resultSet = null;
        ConnectionPool pool = ConnectionPool.getPool();
        Connection c = pool.getConnection();
        try {
            Statement statement = c.createStatement();
            statement.execute("SELECT * FROM `user`");
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                LOGGER.log(Level.INFO, resultSet.getString("email"));
            }
        } catch (SQLException exc) {
            LOGGER.log(Level.ERROR, exc);
        }
        assertTrue(resultSet != null);
    }
}
