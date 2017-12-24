package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.entity.ParkingEntity;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;

import java.util.List;

public interface ParkingDao {

    void addParking(ParkingEntity parking)throws DAOException;

    void deleteParking(Integer parkingId)throws DAOException;

    void updateParking(ParkingEntity parking)throws DAOException;

    List<ParkingEntity> getAllParking()throws DAOException;

    ParkingEntity getParkingById(Integer parkingId)throws DAOException;
}
