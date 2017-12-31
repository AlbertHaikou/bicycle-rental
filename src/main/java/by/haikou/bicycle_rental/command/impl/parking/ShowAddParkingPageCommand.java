package by.haikou.bicycle_rental.command.impl.parking;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowAddParkingPageCommand implements ICommand {
    ServiceFactory factory = ServiceFactory.getFactory();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        req.getRequestDispatcher(ConstantsMng.ADD_OR_EDIT_PARKING).forward(req, resp);
    }
}
