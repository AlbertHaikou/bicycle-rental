package by.haikou.bicycle_rental.dao.factory;

import by.haikou.bicycle_rental.dao.BikeDao;
import by.haikou.bicycle_rental.dao.RentItemDao;
import by.haikou.bicycle_rental.dao.mysql.MySqlParkingDao;
import by.haikou.bicycle_rental.dao.mysql.MySqlBikeDao;
import by.haikou.bicycle_rental.dao.ParkingDao;
import by.haikou.bicycle_rental.dao.SupportItemDao;
import by.haikou.bicycle_rental.dao.UserDao;
import by.haikou.bicycle_rental.dao.mysql.MySqlRentItemDao;
import by.haikou.bicycle_rental.dao.mysql.MySqlSupportItemDao;
import by.haikou.bicycle_rental.dao.mysql.MySqlUserDao;

public class MySQLDAOFactory extends DAOFactory {

    private final UserDao userDao;
    private final BikeDao bikeDao;
    private final ParkingDao parkingDao;
    private final SupportItemDao supportItemDao;
    private final RentItemDao rentItemDao;

    public MySQLDAOFactory() {
        this.userDao = new MySqlUserDao();
        this.bikeDao = new MySqlBikeDao();
        this.parkingDao = new MySqlParkingDao();
        this.supportItemDao = new MySqlSupportItemDao();
        this.rentItemDao = new MySqlRentItemDao();
    }

    @Override
    public UserDao getUserDao() {
        return this.userDao;
    }

    @Override
    public BikeDao getBikeDao() {
        return this.bikeDao;
    }

    @Override
    public ParkingDao getParkingDao() {
        return this.parkingDao;
    }

    @Override
    public SupportItemDao getSupportItemDao() {
        return supportItemDao;
    }

    @Override
    public RentItemDao getRentItemDao() {
        return rentItemDao;
    }

}
