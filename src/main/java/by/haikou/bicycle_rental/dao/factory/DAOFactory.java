package by.haikou.bicycle_rental.dao.factory;

import by.haikou.bicycle_rental.dao.*;

public abstract class DAOFactory {

    private static DAOFactory DAOFactory = new MySQLDAOFactory();

    public static DAOFactory getFactory() {
        return DAOFactory;
    }

    public abstract UserDao getUserDao();

    public abstract BikeDao getBikeDao();

    public abstract ParkingDao getParkingDao();

    public abstract SupportItemDao getSupportItemDao();

    public abstract RentItemDao getRentItemDao();

}
