package by.haikou.bicycle_rental.command.impl.manager;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Collects information about chosen manager from database using {@link UserService#getUserById(Integer)}.</p>
 * <p>Then sends the user to the manager editing page</p>
 */
public class ShowEditManagerPage implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(ShowEditManagerPage.class);
    UserService userService = ServiceFactory.getFactory().getUserService();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getUserById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("manager", user);
        request.getRequestDispatcher(ConstantsMng.ADD_EDIT_MANAGER).forward(request, response);
    }
}