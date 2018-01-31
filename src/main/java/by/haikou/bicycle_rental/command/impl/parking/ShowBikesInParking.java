package by.haikou.bicycle_rental.command.impl.parking;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.haikou.bicycle_rental.command.CommandEnum.SHOW_BIKES_IN_PARKING_PAGE;
import static by.haikou.bicycle_rental.util.PaginationObject.DEFAULT_PAGE;

/**
 * <p>Takes information about bicycles on the selected parking from the database, using {@link BikeService#showBikeByParkingId(Integer, Integer)}.</p>
 * <p>Then sends the user to the page with the received list of bicycles.</p>
 */
public class ShowBikesInParking implements ICommand {
    private BikeService bikeService = ServiceFactory.getFactory().getBikeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        int page;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException exc) {
            page = DEFAULT_PAGE;
        }

        req.setAttribute("items", bikeService.showBikeByParkingId(id, page));
        req.setAttribute("command", SHOW_BIKES_IN_PARKING_PAGE.getValue() + "&id=" + id);
        req.getRequestDispatcher(ConstantsMng.LIST_BIKES).forward(req, resp);
    }
}