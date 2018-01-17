package by.haikou.bicycle_rental.command.impl.manager;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.MD5Converter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddManagerCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(AddManagerCommand.class);
    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(MD5Converter.getHash(request.getParameter("password")));
        user.setRole(User.Role.MANAGER);
        userService.addUser(user);

        response.sendRedirect("main?command=showManagers");

    }

}