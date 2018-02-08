package by.haikou.bicycle_rental.command.impl.bike;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Start point of chain to realize logic of return bicycle to point by user.
 * Delegates the task to the service method {@link BikeService#returnBike(Integer, Integer)}, then redirect the user to the page with a list of bikes.
 */
public class ReturnBike implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(ReturnBike.class);
    private BikeService bikeService = ServiceFactory.getFactory().getBikeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        Integer bikeId = Integer.parseInt(request.getParameter("id"));
        Integer chosenParkingId = Integer.parseInt(request.getParameter("chosenParkingId"));
        if (null != chosenParkingId) {
            bikeService.returnBike(bikeId, userId, chosenParkingId);
        } else {
            bikeService.returnBike(bikeId, userId);
        }
        Integer parkingId = (Integer) session.getAttribute("parkingScope");
        if (null != parkingId) {
            response.sendRedirect("main?command=showBikesInParkingPage&id=" + parkingId);
        } else {
            response.sendRedirect(ConstantsMng.SHOW_BIKES);
        }
    }
}
