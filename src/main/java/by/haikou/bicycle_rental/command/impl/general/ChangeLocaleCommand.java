package by.haikou.bicycle_rental.command.impl.general;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.command.factory.CommandFactory;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.util.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocaleCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException, UnauthorizedException {
        RequestUtils.setLocale(request);
        if (null != request.getParameter("urlAdd") && !request.getParameter("urlAdd").equals("") &&
                !request.getParameter("urlAdd").contains("changeLocale")) {
            String s = request.getParameter("urlAdd");
            request.getRequestDispatcher("/main?" + s).forward(request, response);
        } else {
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_MAIN_PAGE).execute(request, response);
        }
    }
}
