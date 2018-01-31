package by.haikou.bicycle_rental.command.impl.bike;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.entity.Bicycle;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.ParkingService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Collects information about chosen bicycle, and about all parkings from database using {@link BikeService#getBikeById(Integer)}, {@link ParkingService#getAllParking()}.</p>
 * <p>Then directs user to the editing bike page.</p>
 */
public class ShowEditBikePage implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(ShowEditBikePage.class);
    private BikeService bikeService = ServiceFactory.getFactory().getBikeService();
    private ParkingService parkingService = ServiceFactory.getFactory().getParkingService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Bicycle bike = bikeService.getBikeById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("bike", bike);
        request.setAttribute("parkings", parkingService.getAllParking());
        request.getRequestDispatcher(ConstantsMng.ADD_OR_EDIT_BIKE).forward(request, response);
    }
}