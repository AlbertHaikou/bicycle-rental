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
 * <p>Extracts from the request the data about the manager and checks email for uniqueness.</p>
 * <p>Forms a new object and sends the service a command to update chosen manager in the database.</p>
 */
public class UpdateManager implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(UpdateManager.class);
    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnauthorizedException {
        checkRoots(request, new User.Role[]{User.Role.ADMINISTRATOR});
        String loginMsg = MessageUtils.getProperty(RequestUtils.getLocale(request), MessageUtils.NOT_UNIQ_LIGIN_ERROR_MESSAGE);
        if (!request.getParameter("currentEmail").equals(request.getParameter("email"))
                && !userService.isLoginFree(request.getParameter("email"))) {
            request.setAttribute(ConstantsMng.ATR_ERRORS, loginMsg);
            try {
                CommandFactory.getFactory().createCommand(CommandEnum.SHOW_EDIT_PROFILE).execute(request, response);
            } catch (CommandException e) {
                LOGGER.log(Level.ERROR, e);
            } catch (UnauthorizedException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            User user = new User();
            user.setId(Integer.valueOf(request.getParameter("id")));
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setEmail(request.getParameter("email"));
            user.setRole(User.Role.MANAGER);
            userService.updateProfile(user);

            response.sendRedirect("main?command=showManagers");
        }
    }
}
