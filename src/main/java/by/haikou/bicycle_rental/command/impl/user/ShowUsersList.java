package by.haikou.bicycle_rental.command.impl.user;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.dao.exceptions.DAOException;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.haikou.bicycle_rental.command.CommandEnum.SHOW_USERS;
import static by.haikou.bicycle_rental.util.PaginationObject.DEFAULT_PAGE;

public class ShowUsersList implements ICommand {
    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        int page;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException exc) {
            page = DEFAULT_PAGE;
        }

        try {
            req.setAttribute("items", userService.getAllUsers(page));
        } catch (DAOException exc) {
            throw new CommandException(exc);
        }
        req.setAttribute("command", SHOW_USERS.getValue());
        req.getRequestDispatcher(ConstantsMng.LIST_USERS).forward(req, resp);
    }
}
