package by.haikou.bicycle_rental.command.impl.parking;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.service.ParkingService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.haikou.bicycle_rental.command.CommandEnum.SHOW_PARKINGS;
import static by.haikou.bicycle_rental.util.PaginationObject.DEFAULT_PAGE;

/**
 * <p>Forms a list of rental points, given the number of the current page.</p>
 */
public class ShowParkingsList implements ICommand {
    private ParkingService parkingService = ServiceFactory.getFactory().getParkingService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        int page;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException exc) {
            page = DEFAULT_PAGE;
        }

        try {
            req.setAttribute("items", parkingService.getAllParking(page));
        } catch (DAOException exc) {
            throw new CommandException(exc);
        }
        req.setAttribute("command", SHOW_PARKINGS.getValue());
        req.getRequestDispatcher(ConstantsMng.LIST_PARKING).forward(req, resp);
    }
}
