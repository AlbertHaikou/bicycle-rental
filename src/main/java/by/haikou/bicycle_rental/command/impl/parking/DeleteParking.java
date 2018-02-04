package by.haikou.bicycle_rental.command.impl.parking;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.command.factory.CommandFactory;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.ParkingService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Start point of chain to delete manager by id.</p>
 * Delegates the task to the service method {@link ParkingService#deleteParking(Integer)}, then transfer control to {@link ShowParkingsPage} class.
 */
public class DeleteParking implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteParking.class);
    private ParkingService parkingService = ServiceFactory.getFactory().getParkingService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnauthorizedException {
        checkRoots(request, new User.Role[]{User.Role.MANAGER, User.Role.ADMINISTRATOR});
        String id = request.getParameter("id");
        parkingService.deleteParking(Integer.parseInt(id));
        try {
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_PARKINGS).execute(request, response);
        } catch (CommandException e) {
            LOGGER.log(Level.ERROR, e);
        } catch (UnauthorizedException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }
}

