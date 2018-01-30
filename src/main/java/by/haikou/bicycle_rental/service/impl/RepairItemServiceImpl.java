package by.haikou.bicycle_rental.service.impl;

import by.haikou.bicycle_rental.dao.RepairItemDao;
import by.haikou.bicycle_rental.dao.factory.DAOFactory;
import by.haikou.bicycle_rental.entity.RepairItemEntity;
import by.haikou.bicycle_rental.service.RepairItemService;

import java.util.List;

public class RepairItemServiceImpl implements RepairItemService {

    private RepairItemDao repairItemDao = DAOFactory.getFactory().getRepairItemDao();

    @Override
    public void createItem(RepairItemEntity supportItem) {
        repairItemDao.createItem(supportItem);
    }

    @Override
    public List<RepairItemEntity> getAllItems() {
        return repairItemDao.getAllItems();
    }

    @Override
    public List<RepairItemEntity> unperformedItem() {
        return repairItemDao.unperformedItem();
    }

    @Override
    public RepairItemEntity getItemById(Integer bikeId) {
        return repairItemDao.getItemById(bikeId);
    }

    @Override
    public void repairItem(Integer bikeId) {
        repairItemDao.repairItem(bikeId);
    }
}
