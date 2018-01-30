package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.Parking;

import java.util.List;

public interface ParkingDao {

    void addParking(Parking parking) throws DAOException;

    void deleteParking(Integer parkingId) throws DAOException;

    void updateParking(Parking parking) throws DAOException;

    List<Parking> getAllParking() throws DAOException;

    Parking getParkingById(Integer parkingId) throws DAOException;
}
