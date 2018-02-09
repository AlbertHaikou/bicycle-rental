package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.dao.factory.MySQLDAOFactory;
import by.haikou.bicycle_rental.entity.RentItem;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class RentItemDAOTest {
    private static final Logger LOGGER = LogManager.getLogger(RentItemDAOTest.class);

    @Test
    public void getAllUsersTest() {
        List<RentItem> rentItems = null;
        RentItemDao dao = MySQLDAOFactory.getFactory().getRentItemDao();
        try {
            rentItems = dao.historyRent(1);
        } catch (DAOException exc) {
            LOGGER.log(Level.ERROR, exc);
        }
        rentItems = dao.historyRent(1);
        assertTrue(rentItems.size() > 0);
    }
}
