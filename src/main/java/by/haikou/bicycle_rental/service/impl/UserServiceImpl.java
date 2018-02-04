package by.haikou.bicycle_rental.service.impl;

import by.haikou.bicycle_rental.dao.RentItemDao;
import by.haikou.bicycle_rental.dao.UserDao;
import by.haikou.bicycle_rental.dao.factory.DAOFactory;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UserException;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.util.PaginationObject;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;


public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LogManager.getLogger(UserService.class);
    private UserDao userDao = DAOFactory.getFactory().getUserDao();
    private RentItemDao rentItemDao = DAOFactory.getFactory().getRentItemDao();

    @Override
    public User login(String login, String password) throws UserException {
        User user = userDao.getUser(login, password);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void deleteUser(Integer userId) {

        rentItemDao.deleteHistoryByUserId(userId);
        userDao.delete(userId);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    public Boolean isLoginFree(String login) {
        boolean userNotFound = false;
        try {
            userNotFound = null == userDao.getUser(login);
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        return userNotFound;
    }

    public BigDecimal getBalanceByUserId(Integer userId) {
        return userDao.getBalanceByUserId(userId);
    }

    /**
     * If user not a debtor, he just add money to his balance, else he first pay off his credit.
     *
     * @param sum
     * @param userId
     */
    @Override
    public void fillUpUserBalance(BigDecimal sum, Integer userId) {
        if (!userDao.isUserDebtor(userId)) {
            userDao.fillUpBalance(sum, userId);
        } else {
            BigDecimal credit = userDao.getCreditByUserId(userId);
            if (sum.compareTo(credit) > 0) {
                userDao.repayALoan(credit.negate(), userId);
                userDao.updateIsDebtor(false, userId);
                userDao.fillUpBalance(sum.add(credit.negate()), userId);
            } else if (sum.compareTo(credit) < 0) {
                userDao.repayALoan(sum.negate(), userId);
            } else {
                userDao.repayALoan(credit.negate(), userId);
                userDao.updateIsDebtor(false, userId);
            }
        }
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    /**
     * Registers the taking of a loan by the user.
     * User gets money for balance, but becomes debtor.
     *
     * @param sum
     * @param id
     */
    @Override
    public void takeALoan(BigDecimal sum, Integer id) {
        userDao.repayALoan(sum, id);
        userDao.fillUpBalance(sum, id);
        userDao.updateIsDebtor(true, id);
    }

    @Override
    public Boolean isUserDebtor(Integer userId) {
        return userDao.isUserDebtor(userId);
    }

    @Override
    public BigDecimal getCreditByUserId(Integer userId) {
        return userDao.getCreditByUserId(userId);
    }

    @Override
    public void updateProfile(User user) {
        userDao.updateProfile(user);
    }

    @Override
    public void banUser(Integer id) {
        userDao.banUser(id);
    }

    @Override
    public void appointUserAsManager(Integer id) {
        userDao.changeUserRole(id, User.Role.MANAGER);
    }

    @Override
    public void withdrawUserPrivileges(Integer id) {
        userDao.changeUserRole(id, User.Role.USER);
    }

    @Override
    public void unBanUser(Integer id) {
        userDao.unBanUser(id);
    }

    @Override
    public List<User> getAllManagers() {
        return userDao.getAllManagers();
    }

    @Override
    public PaginationObject<User> getAllManagers(Integer page) {
        PaginationObject<User> paginationObject = new PaginationObject<>();
        List<User> users = userDao.getAllManagers();
        paginationObject.setPageCount((int) Math.ceil((double) users.size() / PaginationObject.PER_PAGE));
        paginationObject.setPage(page);
        int start = (paginationObject.getPage() - 1) * PaginationObject.PER_PAGE;
        int end = start + PaginationObject.PER_PAGE > users.size() ? users.size() : start + PaginationObject.PER_PAGE;
        paginationObject.setElementList(users.subList(start, end));
        return paginationObject;
    }

    @Override
    public PaginationObject<User> getAllUsers(Integer page) {
        PaginationObject<User> paginationObject = new PaginationObject<>();
        List<User> users = userDao.getAllUsers();
        paginationObject.setPageCount((int) Math.ceil((double) users.size() / PaginationObject.PER_PAGE));
        paginationObject.setPage(page);
        int start = (paginationObject.getPage() - 1) * PaginationObject.PER_PAGE;
        int end = start + PaginationObject.PER_PAGE > users.size() ? users.size() : start + PaginationObject.PER_PAGE;
        paginationObject.setElementList(users.subList(start, end));
        return paginationObject;
    }

}
