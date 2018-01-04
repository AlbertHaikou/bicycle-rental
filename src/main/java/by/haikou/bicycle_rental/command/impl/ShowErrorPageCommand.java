package by.haikou.bicycle_rental.command.impl;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.haikou.bicycle_rental.command.CommandEnum.SHOW_MAIN_PAGE;

public class ShowErrorPageCommand implements ICommand {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        req.setAttribute("command", SHOW_MAIN_PAGE.getValue());
        req.getRequestDispatcher(ConstantsMng.INDEX).forward(req, resp);
    }
}
