package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.Bicycle;

import java.util.List;

public interface BikeService {

    void deleteBike(Integer bikeId);

    Bicycle getBikeById(Integer bikeId);

    List<Bicycle> getAllBikes();

    void createBike(Bicycle bike);

    void updateBike(Bicycle bike);

    List<Bicycle> showAvailableBike();

    void rentBike(Integer bikeId, Integer userId);

    void returnBike(Integer bikeId, Integer userId);

    List<Bicycle> showBikeByParkingId(Integer parkingId);

}
