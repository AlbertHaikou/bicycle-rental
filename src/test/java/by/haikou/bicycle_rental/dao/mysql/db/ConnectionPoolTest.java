package by.haikou.bicycle_rental.dao.mysql.db;

import org.testng.annotations.Test;

import java.sql.Connection;

import static org.testng.Assert.assertEquals;


public class ConnectionPoolTest {
    @Test
    public void ConnectionPoolTest() {
        ConnectionPool pool = ConnectionPool.getPool();
        Connection c = pool.getConnection();
        pool.returnConnectionToPool(c);
        assertEquals(true, c != null);
    }
}
