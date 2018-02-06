package by.haikou.bicycle_rental.command.impl.bike;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.command.factory.CommandFactory;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.UserService;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>Start point of chain to realize logic of rent bicycle by user.</p>
 * Delegates the task to the service method {@link BikeService#rentBike(Integer, Integer)}, then redirect the user to the page with a list of bikes.
 */
public class RentBike implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(RentBike.class);
    private BikeService bikeService = ServiceFactory.getFactory().getBikeService();
    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        Integer bikeId = Integer.parseInt(request.getParameter("id"));
        if (userService.getUserById(userId).getBalance().compareTo(bikeService.getBikeById(bikeId).getPrice()) < 0) {
            String balanceMsg = MessageUtils.getProperty(RequestUtils.getLocale(request), MessageUtils.NOT_MONEY_TO_RENT_BIKE_ERROR_MESSAGE);
            request.setAttribute(ConstantsMng.ATR_ERRORS, balanceMsg);
            try {
                CommandFactory.getFactory().createCommand(CommandEnum.SHOW_BALANCE_MANAGE_PAGE).execute(request, response);
            } catch (CommandException e) {
                LOGGER.log(Level.ERROR, e);
            } catch (UnauthorizedException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            bikeService.rentBike(bikeId, userId);
            Integer parkingId = (Integer) session.getAttribute("parkingScope");
            if (null != parkingId) {
                response.sendRedirect("main?command=showBikesInParkingPage&id=" + parkingId);
            } else {
                response.sendRedirect(ConstantsMng.SHOW_BIKES);
            }
        }
    }
}
