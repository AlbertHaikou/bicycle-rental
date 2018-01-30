package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.Bicycle;
import by.haikou.bicycle_rental.util.PaginationObject;

import java.util.List;

public interface BikeService {

    void deleteBike(Integer bikeId);

    Bicycle getBikeById(Integer bikeId);

    List<Bicycle> getAllBikes();

    PaginationObject<Bicycle> getAllBikes(Integer page);

    PaginationObject<Bicycle> showAvailableBike(Integer page);

    void createBike(Bicycle bike);

    void updateBike(Bicycle bike);

    List<Bicycle> showAvailableBike();

    void rentBike(Integer bikeId, Integer userId);

    void returnBike(Integer bikeId, Integer userId);

    PaginationObject<Bicycle> showBikeByParkingId(Integer parkingId, Integer page);

}
