package by.haikou.bicycle_rental.service.impl;

import by.haikou.bicycle_rental.dao.ParkingDao;
import by.haikou.bicycle_rental.entity.Parking;
import by.haikou.bicycle_rental.service.ParkingService;
import by.haikou.bicycle_rental.dao.factory.DAOFactory;

import java.util.List;

public class ParkingServiceImpl implements ParkingService {

    private ParkingDao parkingDao = DAOFactory.getFactory().getParkingDao();

    @Override
    public void deleteParking(Integer id) {
        parkingDao.deleteParking(id);
    }

    @Override
    public Parking getParkingById(Integer id) {
        return parkingDao.getParkingById(id);
    }

    @Override
    public List<Parking> getAllParking() {
        return parkingDao.getAllParking();
    }

    @Override
    public void addParking(Parking parking) {
        parkingDao.addParking(parking);
    }

    @Override
    public void updateParking(Parking parking) {
        parkingDao.updateParking(parking);
    }

}
