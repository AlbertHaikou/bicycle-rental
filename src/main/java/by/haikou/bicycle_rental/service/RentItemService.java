package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.RentItem;

import java.util.List;

public interface RentItemService {

    List<RentItem> historyRent(Integer userId);

    RentItem findTakenByUser(Integer userId);
}
