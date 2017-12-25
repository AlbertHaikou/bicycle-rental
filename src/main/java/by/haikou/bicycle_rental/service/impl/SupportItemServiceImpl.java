package by.haikou.bicycle_rental.service.impl;

import by.haikou.bicycle_rental.service.SupportItemService;
import by.haikou.bicycle_rental.dao.SupportItemDao;
import by.haikou.bicycle_rental.dao.factory.DAOFactory;
import by.haikou.bicycle_rental.entity.SupportItemEntity;
import java.util.List;

public class SupportItemServiceImpl implements SupportItemService {

    private SupportItemDao supportItemDao = DAOFactory.getFactory().getSupportItemDao();

    @Override
    public void createItem(SupportItemEntity supportItem) {
        supportItemDao.createItem(supportItem);
    }

    @Override
    public List<SupportItemEntity> getAllItems() {
        return supportItemDao.getAllItems();
    }

    @Override
    public List<SupportItemEntity> unperformedItem() {
        return supportItemDao.unperformedItem();
    }

    @Override
    public SupportItemEntity getItemById(Integer bikeId) {
        return supportItemDao.getItemById(bikeId);
    }

    @Override
    public void repairItem(Integer bikeId) {
        supportItemDao.repairItem(bikeId);
    }
}
