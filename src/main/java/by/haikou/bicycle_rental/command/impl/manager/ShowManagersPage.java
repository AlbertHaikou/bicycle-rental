package by.haikou.bicycle_rental.command.impl.manager;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowManagersPage implements ICommand {
    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("supports", userService.getAllSupports());
        req.getRequestDispatcher(ConstantsMng.LIST_SUPPORTS).forward(req, resp);
    }
}