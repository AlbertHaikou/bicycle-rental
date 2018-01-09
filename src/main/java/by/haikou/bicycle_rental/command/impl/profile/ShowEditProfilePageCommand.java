package by.haikou.bicycle_rental.command.impl.profile;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.entity.Bicycle;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.ParkingService;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowEditProfilePageCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(ShowEditProfilePageCommand.class);
    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getUserById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("profile", user);
        request.getRequestDispatcher(ConstantsMng.ADD_OR_EDIT_PROFILE).forward(request, response);
    }
}
