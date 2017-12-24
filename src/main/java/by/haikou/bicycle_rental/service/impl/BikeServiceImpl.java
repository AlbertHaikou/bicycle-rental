package by.haikou.bicycle_rental.service.impl;

import by.haikou.bicycle_rental.dao.BikeDao;
import by.haikou.bicycle_rental.dao.RentItemDao;
import by.haikou.bicycle_rental.entity.Bicycle;
import by.haikou.bicycle_rental.entity.RentItem;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.dao.factory.DAOFactory;
import java.util.List;

import java.util.Date;

public class BikeServiceImpl implements BikeService {

    private BikeDao bikeDao = DAOFactory.getFactory().getBikeDao();
    private RentItemDao rentItemDao = DAOFactory.getFactory().getRentItemDao();

    @Override
    public void deleteBike(Integer id) {
        bikeDao.deleteBike(id);
    }

    @Override
    public Bicycle getBikeById(Integer id) {
        return bikeDao.getBikeById(id);
    }

    @Override
    public List<Bicycle> getAllBikes() {
        return bikeDao.getAllBikes();
    }
    @Override
    public void createBike(Bicycle bike) {
        bikeDao.createBike(bike);
    }
    @Override
    public void updateBike(Bicycle bike) {
        bikeDao.updateBike(bike);
    }
    @Override
    public List<Bicycle> showAvailableBike() {
        return bikeDao.showAvailableBike();
    }
    @Override
    public void rentBike(Integer bikeId, Integer userId) {
        bikeDao.rentBike(bikeId);
        RentItem rentItem = new RentItem();
        rentItem.setUserId(userId);
        rentItem.setBikeId(bikeId);
        rentItem.setStatus(false);
        rentItem.setDate(new Date());
        rentItemDao.createItem(rentItem);
    }
    @Override
    public void returnBike(Integer bikeid, Integer userId) {
        bikeDao.returnBike(bikeid);
        RentItem rentItem = new RentItem();
        rentItem.setUserId(userId);
        rentItem.setBikeId(bikeid);
        rentItem.setStatus(true);
        rentItem.setDate(new Date());
        rentItemDao.createItem(rentItem);
    }
    @Override
    public List<Bicycle> showBikeByParkingId(Integer id) {
        return bikeDao.showBikeByParkingId(id);
    }

}
