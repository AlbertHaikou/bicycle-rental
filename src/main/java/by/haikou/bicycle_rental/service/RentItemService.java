package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.RentItem;
import by.haikou.bicycle_rental.util.PaginationObject;

import java.util.List;

public interface RentItemService {

    List<RentItem> allHistoryRent(Integer userId);

    PaginationObject<RentItem> historyRent(Integer userId, Integer page);

    RentItem findTakenByUser(Integer userId);
}
