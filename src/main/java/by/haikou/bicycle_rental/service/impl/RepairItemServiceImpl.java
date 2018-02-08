package by.haikou.bicycle_rental.service.impl;

import by.haikou.bicycle_rental.dao.RepairItemDao;
import by.haikou.bicycle_rental.dao.factory.DAOFactory;
import by.haikou.bicycle_rental.entity.Bicycle;
import by.haikou.bicycle_rental.entity.RepairItem;
import by.haikou.bicycle_rental.service.RepairItemService;
import by.haikou.bicycle_rental.util.PaginationObject;

import java.util.List;

public class RepairItemServiceImpl implements RepairItemService {

    private RepairItemDao repairItemDao = DAOFactory.getFactory().getRepairItemDao();

    @Override
    public void createItem(RepairItem supportItem) {
        repairItemDao.createItem(supportItem);
    }

    @Override
    public PaginationObject<RepairItem> getAllItems(Integer page) {
        PaginationObject<RepairItem> paginationObject = new PaginationObject<>();
        List<RepairItem> items = repairItemDao.getAllItems();
        paginationObject.setPageCount((int) Math.ceil((double) items.size() / PaginationObject.PER_PAGE_FOR_BIKES));
        paginationObject.setPage(page);
        int start = (paginationObject.getPage() - 1) * PaginationObject.PER_PAGE_FOR_BIKES;
        int end = start + PaginationObject.PER_PAGE_FOR_BIKES > items.size() ? items.size() : start + PaginationObject.PER_PAGE_FOR_BIKES;
        paginationObject.setElementList(items.subList(start, end));
        return paginationObject;
    }

    @Override
    public PaginationObject<RepairItem> getUnperformedItems(Integer page) {
        PaginationObject<RepairItem> paginationObject = new PaginationObject<>();
        List<RepairItem> items = repairItemDao.unperformedItem();
        paginationObject.setPageCount((int) Math.ceil((double) items.size() / PaginationObject.PER_PAGE_FOR_BIKES));
        paginationObject.setPage(page);
        int start = (paginationObject.getPage() - 1) * PaginationObject.PER_PAGE_FOR_BIKES;
        int end = start + PaginationObject.PER_PAGE_FOR_BIKES > items.size() ? items.size() : start + PaginationObject.PER_PAGE_FOR_BIKES;
        paginationObject.setElementList(items.subList(start, end));
        return paginationObject;
    }

    @Override
    public RepairItem getItemById(Integer bikeId) {
        return repairItemDao.getItemById(bikeId);
    }

    @Override
    public void repairItem(Integer repairId) {
        repairItemDao.repairItem(repairId);
    }
}
