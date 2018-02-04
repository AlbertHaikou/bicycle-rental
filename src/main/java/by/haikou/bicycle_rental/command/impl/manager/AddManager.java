package by.haikou.bicycle_rental.command.impl.manager;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.command.factory.CommandFactory;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import by.haikou.bicycle_rental.util.MD5Converter;
import by.haikou.bicycle_rental.util.MessageUtils;
import by.haikou.bicycle_rental.util.RequestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Gets the manager information from the request, creates a new object, and sends the command to the service to write the new manager to the database.
 */
public class AddManager implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(AddManager.class);
    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnauthorizedException {
        checkRoots(request, new User.Role[]{User.Role.ADMINISTRATOR});
        String loginMsg = MessageUtils.getProperty(RequestUtils.getLocale(request), MessageUtils.NOT_UNIQ_LIGIN_ERROR_MESSAGE);
        if (!userService.isLoginFree(request.getParameter("email"))) {
            request.setAttribute(ConstantsMng.ATR_ERRORS, loginMsg);
            try {
                CommandFactory.getFactory().createCommand(CommandEnum.SHOW_ADD_MANAGER_PAGE).execute(request, response);
            } catch (CommandException e) {
                LOGGER.log(Level.ERROR, e);
            } catch (UnauthorizedException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
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

}