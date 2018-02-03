package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.RentItem;

import java.util.List;

public interface RentItemDao {

    void createItem(RentItem rentItem) throws DAOException;

    void updateItem(RentItem rentItem) throws DAOException;

    List<RentItem> historyRent(Integer userId) throws DAOException;

    RentItem findTakenByUser(Integer userId);

    RentItem findTakenByBike(Integer bikeId);

    void deleteHistoryByBikeId(Integer bikeId);

    void deleteHistoryByUserId(Integer bikeId);

}
