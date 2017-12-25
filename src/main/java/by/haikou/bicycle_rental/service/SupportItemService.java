package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.SupportItemEntity;

import java.util.List;

public interface SupportItemService {

    void createItem(SupportItemEntity supportItem);

    List<SupportItemEntity> getAllItems();

    List<SupportItemEntity> unperformedItem();

    SupportItemEntity getItemById(Integer bikeId);

    void repairItem(Integer bikeId);
}
