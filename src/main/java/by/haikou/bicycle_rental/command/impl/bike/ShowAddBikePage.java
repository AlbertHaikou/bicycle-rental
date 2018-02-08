package by.haikou.bicycle_rental.command.impl.bike;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.ParkingService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Collects information about parkings from database using {@link ParkingService#getAllParking()} and directs user Directs the user to the page of adding a bicycle.
 */
public class ShowAddBikePage implements ICommand {
    private ParkingService parkingService = ServiceFactory.getFactory().getParkingService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnauthorizedException {
        checkRoots(request, new User.Role[]{User.Role.MANAGER, User.Role.ADMINISTRATOR});
        request.setAttribute("parkings", parkingService.getAllParking());
        request.getRequestDispatcher(ConstantsMng.ADD_OR_EDIT_BIKE).forward(request, response);
    }
}
