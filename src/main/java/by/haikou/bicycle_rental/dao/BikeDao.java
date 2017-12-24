package by.haikou.bicycle_rental.dao;

import java.util.List;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.BikeEntity;

public interface BikeDao {

    void createBike(BikeEntity bike) throws DAOException;

    void deleteBike(Integer bikeId) throws DAOException;

    void updateBike(BikeEntity bike) throws DAOException;

    List<BikeEntity> getAllBikes() throws DAOException;

    BikeEntity getBikeById(Integer bikeId) throws DAOException;

    List<BikeEntity> showAvailableBike() throws DAOException;

    void rentBike(Integer bikeId) throws DAOException;

    void returnBike(Integer bikeId) throws DAOException;

    List<BikeEntity> showBikeByParkingId(Integer parkingId) throws DAOException;

}
