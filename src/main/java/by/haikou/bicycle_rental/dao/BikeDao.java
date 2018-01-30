package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.Bicycle;

import java.util.List;

public interface BikeDao {

    void createBike(Bicycle bike) throws DAOException;

    void deleteBike(Integer bikeId) throws DAOException;

    void updateBike(Bicycle bike) throws DAOException;

    List<Bicycle> getAllBikes() throws DAOException;

    Bicycle getBikeById(Integer bikeId) throws DAOException;

    List<Bicycle> showAvailableBike() throws DAOException;

    void rentBike(Integer bikeId) throws DAOException;

    void returnBike(Integer bikeId) throws DAOException;

    List<Bicycle> showBikeByParkingId(Integer parkingId) throws DAOException;

}
