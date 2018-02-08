package by.haikou.bicycle_rental.command.impl.parking;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.RentItem;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.ParkingService;
import by.haikou.bicycle_rental.service.RentItemService;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

import static by.haikou.bicycle_rental.command.CommandEnum.SHOW_BIKES_IN_PARKING_PAGE;
import static by.haikou.bicycle_rental.util.PaginationObject.DEFAULT_PAGE;

/**
 * <p>Takes information about bicycles on the selected parking from the database, using {@link BikeService#showBikeByParkingId(Integer, Integer)}.</p>
 * <p>Then sends the user to the page with the received list of bicycles.</p>
 */
public class ShowParkingsBikes implements ICommand {
    private BikeService bikeService = ServiceFactory.getFactory().getBikeService();
    private UserService userService = ServiceFactory.getFactory().getUserService();
    private RentItemService rentItemService = ServiceFactory.getFactory().getRentItemService();
    private ParkingService parkingService = ServiceFactory.getFactory().getParkingService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        request.setAttribute("parkings", parkingService.getAllParking());
        int parkingId = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        session.setAttribute("parkingScope", parkingId);
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        user = userService.getUserById(userId);
        request.setAttribute("user", user);
        RentItem rentItem = rentItemService.findTakenByUser(userId);
        if (rentItem != null) {
            request.setAttribute("userRentedBikeId", rentItem.getBikeId());
        }

        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException exc) {
            page = DEFAULT_PAGE;
        }


        try {
            Map parameterMap = request.getParameterMap();
            if (parameterMap.containsKey("available") && request.getParameter("available").equals("true")) {
                request.setAttribute("items", bikeService.showAvailableBikeByParkingId(parkingId, page));
            } else {
                request.setAttribute("items", bikeService.showBikeByParkingId(parkingId, page));
            }
        } catch (DAOException exc) {
            throw new CommandException(exc);
        }

        request.setAttribute("command", SHOW_BIKES_IN_PARKING_PAGE.getValue() + "&id=" + parkingId);
        request.getRequestDispatcher(ConstantsMng.LIST_BIKES).forward(request, response);
    }
}