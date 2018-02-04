package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.Parking;
import by.haikou.bicycle_rental.util.PaginationObject;

import java.util.List;

public interface ParkingService {

    /**
     * Delete parking from database by user id.
     *
     * @param parkingId
     */
    void deleteParking(Integer parkingId);

    /**
     * Gets parking from database by user id.
     *
     * @param parkingId
     * @return
     */
    Parking getParkingById(Integer parkingId);

    /**
     * @return List of all parkings.
     */
    List<Parking> getAllParking();

    /**
     * @return List of all parkings for the selected page.
     */
    PaginationObject<Parking> getAllParking(int page);

    /**
     * Adds a new parking into a database.
     *
     * @param parking
     */
    void addParking(Parking parking);

    /**
     * Updates parking information in database.
     *
     * @param parking
     */
    void updateParking(Parking parking);

}
