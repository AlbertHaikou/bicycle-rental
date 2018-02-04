package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.RentItem;
import by.haikou.bicycle_rental.util.PaginationObject;

import java.util.List;

public interface RentItemService {
    /**
     * @param userId
     * @return List of full rental history of the selected user.
     */
    List<RentItem> allHistoryRent(Integer userId);

    /**
     * @param userId
     * @return List of rental history of the selected user for the selected page.
     */
    PaginationObject<RentItem> historyRent(Integer userId, Integer page);

    /**
     * @param userId
     * @return actual rental item of chosen user.
     */
    RentItem findTakenByUser(Integer userId);
}
