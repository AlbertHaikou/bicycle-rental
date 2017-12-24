package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.Parking;

import java.util.List;

public interface ParkingService {

    void deleteParking(Integer parkingId);

    Parking getParkingById(Integer parkingId);

    List<Parking> getAllParking();

    void addParking(Parking parking);

    void updateParking(Parking parking);

}
