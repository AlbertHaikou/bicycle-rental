package by.haikou.bicycle_rental.service.impl;

import by.haikou.bicycle_rental.dao.BikeDao;
import by.haikou.bicycle_rental.dao.RentItemDao;
import by.haikou.bicycle_rental.dao.UserDao;
import by.haikou.bicycle_rental.dao.factory.DAOFactory;
import by.haikou.bicycle_rental.entity.Bicycle;
import by.haikou.bicycle_rental.entity.RentItem;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.util.PaginationObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BikeServiceImpl implements BikeService {
    private static final Logger LOGGER = LogManager.getLogger(BikeServiceImpl.class);

    private BikeDao bikeDao = DAOFactory.getFactory().getBikeDao();
    private RentItemDao rentItemDao = DAOFactory.getFactory().getRentItemDao();
    private UserDao userDao = DAOFactory.getFactory().getUserDao();

    @Override
    public void deleteBike(Integer id) {
        rentItemDao.deleteHistoryByBikeId(id);
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
    public PaginationObject<Bicycle> getAllBikes(Integer page) {
        PaginationObject<Bicycle> paginationObject = new PaginationObject<>();
        List<Bicycle> bikes = bikeDao.getAllBikes();
        paginationObject.setPageCount((int) Math.ceil((double) bikes.size() / PaginationObject.PER_PAGE_FOR_BIKES));
        paginationObject.setPage(page);
        int start = (paginationObject.getPage() - 1) * PaginationObject.PER_PAGE_FOR_BIKES;
        int end = start + PaginationObject.PER_PAGE_FOR_BIKES > bikes.size() ? bikes.size() : start + PaginationObject.PER_PAGE_FOR_BIKES;
        paginationObject.setElementList(bikes.subList(start, end));
        return paginationObject;
    }

    @Override
    public void createBike(Bicycle bike) {
        bikeDao.createBike(bike);
    }

    @Override
    public void createBike(Bicycle bike, Part image) {
        bikeDao.createBike(bike, image);
    }

    @Override
    public void setBikeImage(int id, Part image) {
        bikeDao.setBikeImage(id, image);
    }

    @Override
    public byte[] getBikeImage(Integer id) {
        return bikeDao.getBikeImage(id);
    }

    @Override
    public void updateBike(Bicycle bike) {

        if (!bikeDao.getBikeById(bike.getBicycleId()).getIsAvailable()) {
            if (null != rentItemDao.findTakenByBike(bike.getBicycleId())) {
                returnBike(bike.getBicycleId(), rentItemDao.findTakenByBike(bike.getBicycleId()).getUserId());
            }
        }
        bikeDao.updateBike(bike);
    }

    @Override
    public List<Bicycle> showAvailableBike() {
        return bikeDao.showAvailableBike();
    }

    @Override
    public PaginationObject<Bicycle> showAvailableBike(Integer page) {
        PaginationObject<Bicycle> paginationObject = new PaginationObject<>();
        List<Bicycle> bikes = bikeDao.showAvailableBike();
        paginationObject.setPageCount((int) Math.ceil((double) bikes.size() / PaginationObject.PER_PAGE_FOR_BIKES));
        paginationObject.setPage(page);
        int start = (paginationObject.getPage() - 1) * PaginationObject.PER_PAGE_FOR_BIKES;
        int end = start + PaginationObject.PER_PAGE_FOR_BIKES > bikes.size() ? bikes.size() : start + PaginationObject.PER_PAGE_FOR_BIKES;
        paginationObject.setElementList(bikes.subList(start, end));
        return paginationObject;
    }

    @Override
    public void rentBike(Integer bikeId, Integer userId) {
        bikeDao.rentBike(bikeId);
        RentItem rentItem = new RentItem();
        rentItem.setUserId(userId);
        rentItem.setBikeId(bikeId);
        rentItem.setPrice(bikeDao.getBikeById(bikeId).getPrice());
        rentItem.setFromDate(new Date());
        rentItem.setParkingFromId(1);
        rentItemDao.createItem(rentItem);
    }

    @Override
    public PaginationObject<Bicycle> showAvailableBikeByParkingId(Integer parkingId, Integer page) {
        PaginationObject<Bicycle> paginationObject = new PaginationObject<>();
        List<Bicycle> bikes = bikeDao.showAvailableBikeByParkingId(parkingId);
        paginationObject.setPageCount((int) Math.ceil((double) bikes.size() / PaginationObject.PER_PAGE_FOR_BIKES));
        paginationObject.setPage(page);
        int start = (paginationObject.getPage() - 1) * PaginationObject.PER_PAGE_FOR_BIKES;
        int end = start + PaginationObject.PER_PAGE_FOR_BIKES > bikes.size() ? bikes.size() : start + PaginationObject.PER_PAGE_FOR_BIKES;
        paginationObject.setElementList(bikes.subList(start, end));
        return paginationObject;
    }

    @Override
    public void returnBike(Integer bikeId, Integer userId) {
        Bicycle bicycle = bikeDao.getBikeById(bikeId);
        returnBike(bikeId, userId, bicycle.getParkingId());
    }

    @Override
    public void returnBike(Integer bikeId, Integer userId, Integer parkingId) {
        RentItem rentItem = rentItemDao.findTakenByUser(userId);
        Date fromDate = rentItem.getFromDate();
        Date newDate = new Date();
        BigDecimal amount = rentItem.getPrice().multiply(new BigDecimal((newDate.getTime() - fromDate.getTime()) / 3600000.));
        userDao.fillUpBalance(amount.negate(), userId);
        rentItem.setToDate(newDate);
        rentItem.setParkingToId(parkingId);
        rentItem.setTotalPrice(amount);
        rentItemDao.updateItem(rentItem);
        bikeDao.returnBike(bikeId, parkingId);
    }

    @Override
    public PaginationObject<Bicycle> showBikeByParkingId(Integer id, Integer page) {
        PaginationObject<Bicycle> paginationObject = new PaginationObject<>();
        List<Bicycle> bikes = bikeDao.showBikeByParkingId(id);
        paginationObject.setPageCount((int) Math.ceil((double) bikes.size() / PaginationObject.PER_PAGE_FOR_BIKES));
        paginationObject.setPage(page);
        int start = (paginationObject.getPage() - 1) * PaginationObject.PER_PAGE_FOR_BIKES;
        int end = start + PaginationObject.PER_PAGE_FOR_BIKES > bikes.size() ? bikes.size() : start + PaginationObject.PER_PAGE_FOR_BIKES;
        paginationObject.setElementList(bikes.subList(start, end));
        return paginationObject;
    }
}
