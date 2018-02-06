package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.Bicycle;

import javax.servlet.http.Part;
import java.util.List;

public interface BikeDao {

    void createBike(Bicycle bike) throws DAOException;

    void createBike(Bicycle bike, Part image) throws DAOException;

    void deleteBike(Integer bikeId) throws DAOException;

    void updateBike(Bicycle bike) throws DAOException;

    void setBikeImage(int id, Part image) throws DAOException;

    byte[] getBikeImage(Integer id) throws DAOException;

    List<Bicycle> getAllBikes() throws DAOException;

    Bicycle getBikeById(Integer bikeId) throws DAOException;

    List<Bicycle> showAvailableBike() throws DAOException;

    List<Bicycle> showAvailableBikeByParkingId(Integer parkingId);

    void rentBike(Integer bikeId) throws DAOException;

    void returnBike(Integer bikeId) throws DAOException;

    List<Bicycle> showBikeByParkingId(Integer parkingId) throws DAOException;

}
