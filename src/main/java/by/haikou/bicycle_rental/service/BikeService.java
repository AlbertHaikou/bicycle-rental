package by.haikou.bicycle_rental.service;

import by.haikou.bicycle_rental.entity.Bicycle;
import by.haikou.bicycle_rental.util.PaginationObject;
import org.omg.CORBA.INTERNAL;

import javax.servlet.http.Part;
import java.util.List;

public interface BikeService {

    /**
     * Delete bicycle from database by bicycle id.
     *
     * @param bikeId
     */
    void deleteBike(Integer bikeId);

    /**
     * Gets bicycle from database by bicycle id.
     *
     * @param bikeId
     * @return Bicycle
     */
    Bicycle getBikeById(Integer bikeId);

    /**
     * @return List of all bicycles.
     */
    List<Bicycle> getAllBikes();

    /**
     * @return List of all bicycles for the selected page.
     */
    PaginationObject<Bicycle> getAllBikes(Integer page);

    /**
     * @return List of available bicycles for the selected page.
     */
    PaginationObject<Bicycle> showAvailableBike(Integer page);

    PaginationObject<Bicycle> showAvailableBikeByParkingId(Integer parkingId, Integer page);

    /**
     * Adds a new bicycle into a database.
     *
     * @param bike
     */
    void createBike(Bicycle bike);

    void createBike(Bicycle bike, Part image);

    void setBikeImage(int id, Part image);

    byte[] getBikeImage(Integer id);

    /**
     * Updates bicycle information in database.
     *
     * @param bike
     */
    void updateBike(Bicycle bike);

    /**
     * @return List of available bicycles.
     */
    List<Bicycle> showAvailableBike();

    /**
     * Marks the bike as busy and creates new {@link by.haikou.bicycle_rental.entity.RentItem}.
     *
     * @param bikeId
     * @param userId
     */
    void rentBike(Integer bikeId, Integer userId);

    /**
     * Marks the bike as free and update information of {@link by.haikou.bicycle_rental.entity.RentItem}.
     *
     * @param bikeId
     * @param userId
     */
    void returnBike(Integer bikeId, Integer userId);

    void returnBike(Integer bikeId, Integer userId, Integer parkingId);

    /**
     * @return List of bicycles at chosen parking for the selected page.
     */
    PaginationObject<Bicycle> showBikeByParkingId(Integer parkingId, Integer page);

}
