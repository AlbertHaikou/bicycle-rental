package by.haikou.bicycle_rental.command.impl.manager;

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

import static by.haikou.bicycle_rental.command.CommandEnum.SHOW_MANAGERS;
import static by.haikou.bicycle_rental.util.PaginationObject.DEFAULT_PAGE;

public class ShowManagersPage implements ICommand {
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
            req.setAttribute("items", userService.getAllSupports(page));
        } catch (DAOException exc) {
            throw new CommandException(exc);
        }
        req.setAttribute("command", SHOW_MANAGERS.getValue());
        req.getRequestDispatcher(ConstantsMng.LIST_SUPPORTS).forward(req, resp);
    }
}