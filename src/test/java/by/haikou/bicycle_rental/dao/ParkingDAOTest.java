package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.dao.factory.MySQLDAOFactory;
import by.haikou.bicycle_rental.entity.Parking;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ParkingDAOTest {
    private static final Logger LOGGER = LogManager.getLogger(ParkingDAOTest.class);

    @Test
    public void getAllUsersTest() {
        List<Parking> parkings = null;
        ParkingDao dao = MySQLDAOFactory.getFactory().getParkingDao();
        try {
            parkings = dao.getAllParking();
        } catch (DAOException exc) {
            LOGGER.log(Level.ERROR, exc);
        }
        assertTrue(parkings.size() > 0);
    }
}
