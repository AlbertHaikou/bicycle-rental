package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.Bicycle;
import by.haikou.bicycle_rental.entity.RepairItem;
import by.haikou.bicycle_rental.util.PaginationObject;

import java.util.List;

public interface RepairItemService {

    void createItem(RepairItem supportItem);

    PaginationObject<RepairItem> getAllItems(Integer page);

    PaginationObject<RepairItem> getUnperformedItems(Integer page);

    RepairItem getItemById(Integer bikeId);

    void repairItem(Integer repairId);
}
