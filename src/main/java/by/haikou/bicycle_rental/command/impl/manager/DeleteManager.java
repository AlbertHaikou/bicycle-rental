package by.haikou.bicycle_rental.command.impl.manager;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.command.factory.CommandFactory;
import by.haikou.bicycle_rental.entity.User;
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
 * <p>Start point of chain to delete manager by id.</p>
 * Delegates the task to the service method {@link UserService#deleteUser(Integer)}, then transfer control to {@link ShowManagersPage} class.
 */
public class DeleteManager implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteManager.class);
    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnauthorizedException {
        checkRoots(request, new User.Role[]{User.Role.ADMINISTRATOR});
        String action = request.getParameter("id");
        userService.deleteUser(Integer.parseInt(action));
        try {
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_MANAGERS).execute(request, response);
        } catch (CommandException e) {
            LOGGER.log(Level.ERROR, e);
        } catch (UnauthorizedException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }
}

