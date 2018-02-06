package by.haikou.bicycle_rental.command.impl.bike;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.command.factory.CommandFactory;
import by.haikou.bicycle_rental.entity.Bicycle;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import by.haikou.bicycle_rental.util.MessageUtils;
import by.haikou.bicycle_rental.util.RequestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Start point of chain to delete bicycle by id.</p>
 * Delegates the task to the service method {@link BikeService#deleteBike(Integer)}, then transfer control to {@link ShowBikesList} class.
 */
public class DeleteBike implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteBike.class);
    private BikeService bikeService = ServiceFactory.getFactory().getBikeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnauthorizedException {
        checkRoots(request, new User.Role[]{User.Role.MANAGER, User.Role.ADMINISTRATOR});
        Integer id = Integer.parseInt(request.getParameter("id"));
        Bicycle bicycle = bikeService.getBikeById(id);
        if (null != bicycle) {
            if (bicycle.getIsAvailable()) {
                bikeService.deleteBike(id);
            } else {
                String deleteBikeErrMsg = MessageUtils.getProperty(RequestUtils.getLocale(request), MessageUtils.DELETE_BIKE_ERROR_MESSAGE);
                request.setAttribute(ConstantsMng.ATR_ERRORS, deleteBikeErrMsg);
            }
        }
        try {
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_BIKES).execute(request, response);
        } catch (CommandException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }
}