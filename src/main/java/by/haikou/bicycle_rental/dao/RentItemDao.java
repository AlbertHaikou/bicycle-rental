package by.haikou.bicycle_rental.dao;

import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.RentItemEntity;
import java.util.List;

public interface RentItemDao {

    void createItem(RentItemEntity rentItem) throws DAOException;

    List<RentItemEntity> historyRent(Integer userId) throws DAOException;

    RentItemEntity findTakenByUser(Integer userId);

}
