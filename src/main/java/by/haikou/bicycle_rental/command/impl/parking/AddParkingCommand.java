package by.haikou.bicycle_rental.command.impl.parking;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.command.factory.CommandFactory;
import by.haikou.bicycle_rental.entity.Parking;
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

public class AddParkingCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(AddParkingCommand.class);
    private ParkingService parkingService = ServiceFactory.getFactory().getParkingService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        Parking parking = new Parking();
        parking.setStreet(req.getParameter("street"));
        parkingService.addParking(parking);
        try {
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_PARKINGS).execute(req, resp);
        } catch (CommandException e) {
            LOGGER.log(Level.ERROR, e);
        } catch (UnauthorizedException e) {
            LOGGER.log(Level.ERROR, e);
        }

    }
}
