package by.haikou.bicycle_rental.dao;

import java.util.List;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.SupportItemEntity;

public interface SupportItemDao {

    void createItem(SupportItemEntity supportItem) throws DAOException;

    List<SupportItemEntity> getAllItems() throws DAOException;

    List<SupportItemEntity> unperformedItem() throws DAOException;

    SupportItemEntity getItemById(Integer bikeId) throws DAOException;

    void repairItem(Integer bikeId) throws DAOException;
}
