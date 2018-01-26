package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.dao.factory.MySQLDAOFactory;
import by.haikou.bicycle_rental.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class UserDAOTest {
    private static final Logger LOGGER = LogManager.getLogger(UserDAOTest.class);

    @Test
    public void getAllUsersTest() {
        List<User> users = null;
        UserDao dao = MySQLDAOFactory.getFactory().getUserDao();
        try {
            users = dao.getAllUsers();
        } catch (DAOException exc) {
            LOGGER.log(Level.ERROR, exc);
        }
        assertEquals(true, users.size() > 0);
    }
}
