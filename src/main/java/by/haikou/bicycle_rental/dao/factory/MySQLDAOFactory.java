package by.haikou.bicycle_rental.dao.factory;

import by.haikou.bicycle_rental.dao.*;
import by.haikou.bicycle_rental.dao.mysql.*;

public class MySQLDAOFactory extends DAOFactory {

    private final UserDao userDao;
    private final BikeDao bikeDao;
    private final ParkingDao parkingDao;
    private final RepairItemDao repairItemDao;
    private final RentItemDao rentItemDao;

    public MySQLDAOFactory() {
        this.userDao = new MySqlUserDao();
        this.bikeDao = new MySqlBikeDao();
        this.parkingDao = new MySqlParkingDao();
        this.repairItemDao = new MySqlRepairItemDao();
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
    public RepairItemDao getRepairItemDao() {
        return repairItemDao;
    }

    @Override
    public RentItemDao getRentItemDao() {
        return rentItemDao;
    }

}
