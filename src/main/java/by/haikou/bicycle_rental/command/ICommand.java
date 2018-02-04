package by.haikou.bicycle_rental.command;

import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Base Interface for all Command classes.
 *
 * @author Albert Haikou
 */
public interface ICommand {
    /**
     * Checks if the user has enough access level for further execution
     *
     * @param request
     * @param needLevels required access levels
     * @throws ServletException
     * @throws IOException
     * @throws UnauthorizedException
     */
    default void checkRoots(HttpServletRequest request, User.Role[] needLevels)
            throws ServletException, IOException, UnauthorizedException {
        if ((needLevels.length == 0) || (needLevels == null)) {
            return;
        }
        User currentUser = (User) request.getSession().getAttribute("user");
        User.Role currentRole = ServiceFactory.getFactory().getUserService().getUserById(currentUser.getId()).getRole();
        for (User.Role needLevel : needLevels) {
            if (needLevel.equals(currentRole)) {
                return;
            }
        }
        throw new UnauthorizedException("Not enough permissions for this operation");
    }


    /**
     * Main project method. Used in all Command classes to realize program logic.
     * After completing its task, the method directs the user to the jsp page or passes it to another class.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws CommandException
     * @throws UnauthorizedException
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException, UnauthorizedException;
}
