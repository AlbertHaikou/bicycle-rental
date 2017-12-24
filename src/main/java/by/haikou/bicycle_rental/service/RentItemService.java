package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.RentItemEntity;
import java.util.List;

public interface RentItemService {

    List<RentItemEntity> historyRent(Integer userId);

    RentItemEntity findTakenByUser(Integer userId);
}
