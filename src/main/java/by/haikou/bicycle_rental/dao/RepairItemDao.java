package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.RepairItemEntity;

import java.util.List;

public interface RepairItemDao {

    void createItem(RepairItemEntity supportItem) throws DAOException;

    List<RepairItemEntity> getAllItems() throws DAOException;

    List<RepairItemEntity> unperformedItem() throws DAOException;

    RepairItemEntity getItemById(Integer bikeId) throws DAOException;

    void repairItem(Integer bikeId) throws DAOException;
}
