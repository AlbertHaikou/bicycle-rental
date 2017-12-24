package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.ParkingEntity;

import java.util.List;

public interface ParkingService {

    void deleteParking(Integer parkingId);

    ParkingEntity getParkingById(Integer parkingId);

    List<ParkingEntity> getAllParking();

    void addParking(ParkingEntity parking);

    void updateParking(ParkingEntity parking);

}
