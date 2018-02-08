package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.RepairItem;

import java.util.List;

public interface RepairItemDao {

    void createItem(RepairItem supportItem) throws DAOException;

    List<RepairItem> getAllItems() throws DAOException;

    List<RepairItem> unperformedItem() throws DAOException;

    RepairItem getItemById(Integer bikeId) throws DAOException;

    void repairItem(Integer repairId) throws DAOException;
}
