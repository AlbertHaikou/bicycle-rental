package by.haikou.bicycle_rental.command.impl.bike;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.command.factory.CommandFactory;
import by.haikou.bicycle_rental.entity.Bicycle;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBikeCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(AddBikeCommand.class);
    private BikeService bikeService = ServiceFactory.getFactory().getBikeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Bicycle bike = new Bicycle();
        bike.setType(request.getParameter("type"));
        bike.setModel(request.getParameter("model"));
        bike.setSize(request.getParameter("size"));
        bike.setIsAvailable(request.getParameter("available").equals("free"));
        bike.setParkingId(Integer.valueOf(request.getParameter("parkingId")));
        bikeService.createBike(bike);
        try {
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_BIKES).execute(request, response);
        } catch (CommandException e) {
            LOGGER.log(Level.ERROR, e);
        } catch (UnauthorizedException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }

}