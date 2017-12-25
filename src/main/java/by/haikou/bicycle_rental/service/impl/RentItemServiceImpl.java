package by.haikou.bicycle_rental.service.impl;

import by.haikou.bicycle_rental.dao.RentItemDao;
import by.haikou.bicycle_rental.dao.factory.DAOFactory;
import by.haikou.bicycle_rental.entity.RentItem;
import by.haikou.bicycle_rental.service.RentItemService;
import java.util.List;

public class RentItemServiceImpl implements RentItemService {

    private RentItemDao rentItemDao = DAOFactory.getFactory().getRentItemDao();

    @Override
    public List<RentItem> historyRent(Integer userId) {
        return rentItemDao.historyRent(userId);
    }

    @Override
    public RentItem findTakenByUser(Integer userId) {
        return rentItemDao.findTakenByUser(userId);
    }
}
