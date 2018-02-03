package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.dao.factory.MySQLDAOFactory;
import by.haikou.bicycle_rental.entity.Bicycle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class BikeDAOTest {
    private static final Logger LOGGER = LogManager.getLogger(BikeDAOTest.class);

    @Test
    public void getAllUsersTest() {
        List<Bicycle> bikes = null;
        BikeDao dao = MySQLDAOFactory.getFactory().getBikeDao();
        try {
            bikes = dao.getAllBikes();
        } catch (DAOException exc) {
            LOGGER.log(Level.ERROR, exc);
        }
        assertTrue(bikes.size() > 0);
    }
}
