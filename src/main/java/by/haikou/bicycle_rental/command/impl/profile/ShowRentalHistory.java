package by.haikou.bicycle_rental.command.impl.profile;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.RentItemService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.haikou.bicycle_rental.command.CommandEnum.SHOW_RENTAL_HISTORY;
import static by.haikou.bicycle_rental.util.PaginationObject.DEFAULT_PAGE;

/**
 * <p>Collects information about current user rents from database using {@link RentItemService#historyRent(Integer, Integer)}.</p>
 * <p>Then sends the user to the rental history page.</p>
 */
public class ShowRentalHistory implements ICommand {
    private RentItemService rentItemService = ServiceFactory.getFactory().getRentItemService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();

        int page;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException exc) {
            page = DEFAULT_PAGE;
        }

        try {
            req.setAttribute("items", rentItemService.historyRent(userId, page));
        } catch (DAOException exc) {
            throw new CommandException(exc);
        }
        req.setAttribute("command", SHOW_RENTAL_HISTORY.getValue());
        req.getRequestDispatcher(ConstantsMng.LIST_HISTORY_RENT).forward(req, resp);
    }
}