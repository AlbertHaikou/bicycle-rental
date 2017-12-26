package by.haikou.bicycle_rental.command.impl;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.command.factory.CommandFactory;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.exception.UserException;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import by.haikou.bicycle_rental.util.MessageLocalizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnauthorizedException, CommandException {
        String login = request.getParameter(ConstantsMng.PARAM_NAME_LOGIN);
        String password = request.getParameter(ConstantsMng.PARAM_NAME_PASSWORD);
        try {
            User user = userService.login(login, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
            }

        } catch (UserException exc) {
            request.setAttribute("user", exc.getUser());
            request.setAttribute("error", MessageLocalizer.getLocalizedForCurrentLocaleMessage(exc.getMessage(), request));
            CommandFactory.getFactory().createCommand(CommandEnum.SHOW_LOGIN_PAGE).execute(request, response);
        }

        CommandFactory.getFactory().createCommand(CommandEnum.SHOW_MAIN_PAGE).execute(request, response);
    }
}

