package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.BikeEntity;

import java.util.List;

public interface BikeService {

    void deleteBike(Integer bikeId);

    BikeEntity getBikeById(Integer bikeId);

    List<BikeEntity> getAllBikes();

    void createBike(BikeEntity bike);

    void updateBike(BikeEntity bike);

    List<BikeEntity> showAvailableBike();

    void rentBike(Integer bikeId, Integer userId);

    void returnBike(Integer bikeId, Integer userId);

    List<BikeEntity> showBikeByParkingId(Integer parkingId);

}
