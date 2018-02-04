package by.haikou.bicycle_rental.command.impl.parking;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.entity.Parking;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
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
 * <p>Extracts from the request the data about the parking.</p>
 * <p>Forms a new object and sends the service a command to update chosen parking in the database.</p>
 */
public class UpdateParking implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(UpdateParking.class);
    private ParkingService parkingService = ServiceFactory.getFactory().getParkingService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        checkRoots(request, new User.Role[]{User.Role.MANAGER, User.Role.ADMINISTRATOR});
        Parking parking = new Parking();
        parking.setStreet(request.getParameter("street"));
        parking.setParkingId(Integer.parseInt(request.getParameter("id")));
        parkingService.updateParking(parking);
        resp.sendRedirect(ConstantsMng.SHOW_PARKINGS);

    }
}
