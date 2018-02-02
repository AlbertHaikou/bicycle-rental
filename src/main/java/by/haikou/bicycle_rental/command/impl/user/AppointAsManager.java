package by.haikou.bicycle_rental.command.impl.user;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.command.factory.CommandFactory;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Adds manager privileges to the selected user.
 */
public class AppointAsManager implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(AppointAsManager.class);
    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        userService.appointUserAsManager(id);
        try {
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_USERS).execute(request, response);
        } catch (CommandException e) {
            LOGGER.log(Level.ERROR, e);
        } catch (UnauthorizedException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }
}