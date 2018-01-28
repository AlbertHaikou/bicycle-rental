package by.haikou.bicycle_rental.service.impl;

import by.haikou.bicycle_rental.dao.RentItemDao;
import by.haikou.bicycle_rental.dao.factory.DAOFactory;
import by.haikou.bicycle_rental.entity.RentItem;
import by.haikou.bicycle_rental.service.RentItemService;
import by.haikou.bicycle_rental.util.PaginationObject;

import java.util.List;

public class RentItemServiceImpl implements RentItemService {

    private RentItemDao rentItemDao = DAOFactory.getFactory().getRentItemDao();

    @Override
    public PaginationObject<RentItem> historyRent(Integer userId, Integer page) {
        PaginationObject<RentItem> paginationObject = new PaginationObject<>();
        List<RentItem> rents = rentItemDao.historyRent(userId);
        paginationObject.setPageCount((int) Math.ceil((double) rents.size() / PaginationObject.PER_PAGE));
        paginationObject.setPage(page);
        int start = (paginationObject.getPage() - 1) * PaginationObject.PER_PAGE;
        int end = start + PaginationObject.PER_PAGE > rents.size() ? rents.size() : start + PaginationObject.PER_PAGE;
        paginationObject.setElementList(rents.subList(start, end));
        return paginationObject;
    }

    @Override
    public List<RentItem> allHistoryRent(Integer userId) {
        return rentItemDao.historyRent(userId);
    }

    @Override
    public RentItem findTakenByUser(Integer userId) {
        return rentItemDao.findTakenByUser(userId);
    }
}
