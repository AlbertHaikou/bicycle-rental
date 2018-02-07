package by.haikou.bicycle_rental.command.impl.bike;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.entity.Bicycle;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Extracts from the request the data about the bicycle, forms a new object and sends the service a command to register it in the database.
 */
public class AddBike implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(AddBike.class);
    private BikeService bikeService = ServiceFactory.getFactory().getBikeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnauthorizedException {
        checkRoots(request, new User.Role[]{User.Role.MANAGER, User.Role.ADMINISTRATOR});
        Part part = request.getPart("image");
        Bicycle bike = new Bicycle();
        bike.setType(request.getParameter("type"));
        bike.setModel(request.getParameter("model"));
        bike.setSize(request.getParameter("size"));
        bike.setIsAvailable(request.getParameter("available").equals("free"));
        bike.setParkingId(Integer.valueOf(request.getParameter("parkingId")));
        bike.setPrice(new BigDecimal(request.getParameter("price")));

        try {
            bikeService.createBike(bike, part);
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
        response.sendRedirect(ConstantsMng.SHOW_BIKES);
    }

}