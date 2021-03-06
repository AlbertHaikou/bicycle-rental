package by.haikou.bicycle_rental.command.impl.manager;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Directs the user to the manager's add page.
 */
public class ShowAddManagerPage implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnauthorizedException {
        checkRoots(request, new User.Role[]{User.Role.ADMINISTRATOR});
        request.getRequestDispatcher(ConstantsMng.ADD_EDIT_MANAGER).forward(request, response);
    }
}
