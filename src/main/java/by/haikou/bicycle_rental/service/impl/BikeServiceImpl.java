package by.haikou.bicycle_rental.service.impl;

import by.haikou.bicycle_rental.dao.BikeDao;
import by.haikou.bicycle_rental.dao.RentItemDao;
import by.haikou.bicycle_rental.entity.BikeEntity;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.dao.factory.DAOFactory;
import java.util.List;

import by.haikou.bicycle_rental.entity.RentItemEntity;
import java.util.Date;

public class BikeServiceImpl implements BikeService {

    private BikeDao bikeDao = DAOFactory.getFactory().getBikeDao();
    private RentItemDao rentItemDao = DAOFactory.getFactory().getRentItemDao();

    @Override
    public void deleteBike(Integer id) {
        bikeDao.deleteBike(id);
    }

    @Override
    public BikeEntity getBikeById(Integer id) {
        return bikeDao.getBikeById(id);
    }

    @Override
    public List<BikeEntity> getAllBikes() {
        return bikeDao.getAllBikes();
    }
    @Override
    public void createBike(BikeEntity bike) {
        bikeDao.createBike(bike);
    }
    @Override
    public void updateBike(BikeEntity bike) {
        bikeDao.updateBike(bike);
    }
    @Override
    public List<BikeEntity> showAvailableBike() {
        return bikeDao.showAvailableBike();
    }
    @Override
    public void rentBike(Integer bikeId, Integer userId) {
        bikeDao.rentBike(bikeId);
        RentItemEntity rentItem = new RentItemEntity();
        rentItem.setUserId(userId);
        rentItem.setBikeId(bikeId);
        rentItem.setStatus(false);
        rentItem.setDate(new Date());
        rentItemDao.createItem(rentItem);
    }
    @Override
    public void returnBike(Integer bikeid, Integer userId) {
        bikeDao.returnBike(bikeid);
        RentItemEntity rentItem = new RentItemEntity();
        rentItem.setUserId(userId);
        rentItem.setBikeId(bikeid);
        rentItem.setStatus(true);
        rentItem.setDate(new Date());
        rentItemDao.createItem(rentItem);
    }
    @Override
    public List<BikeEntity> showBikeByParkingId(Integer id) {
        return bikeDao.showBikeByParkingId(id);
    }

}
