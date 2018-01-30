package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.RepairItemEntity;

import java.util.List;

public interface RepairItemService {

    void createItem(RepairItemEntity supportItem);

    List<RepairItemEntity> getAllItems();

    List<RepairItemEntity> unperformedItem();

    RepairItemEntity getItemById(Integer bikeId);

    void repairItem(Integer bikeId);
}
