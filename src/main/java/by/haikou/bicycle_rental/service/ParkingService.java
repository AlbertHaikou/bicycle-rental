package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.Parking;
import by.haikou.bicycle_rental.util.PaginationObject;

import java.util.List;

public interface ParkingService {

    void deleteParking(Integer parkingId);

    Parking getParkingById(Integer parkingId);

    List<Parking> getAllParking();

    PaginationObject<Parking> getAllParking(int page);

    void addParking(Parking parking);

    void updateParking(Parking parking);

}
