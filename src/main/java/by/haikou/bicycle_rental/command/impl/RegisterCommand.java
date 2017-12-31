package by.haikou.bicycle_rental.command.impl;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.command.factory.CommandFactory;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
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

public class RegisterCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(RegisterCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException, UnauthorizedException {
        UserService userService = ServiceFactory.getFactory().getUserService();
        User user = new User();
        String firstName = request.getParameter(ConstantsMng.PARAM_NAME_FIRST_NAME);
        String lastName = request.getParameter(ConstantsMng.PARAM_NAME_LAST_NAME);
        String login = request.getParameter(ConstantsMng.PARAM_NAME_LOGIN);
        String password = request.getParameter(ConstantsMng.PARAM_NAME_PASSWORD);
        user.setEmail(login);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setRole(User.Role.USER);
        userService.addUser(user);
        HttpSession session = request.getSession(true);
        session.setAttribute(ConstantsMng.PARAM_NAME_FIRST_NAME, user.getFirstName());
        session.setAttribute(ConstantsMng.PARAM_NAME_LAST_NAME, user.getLastName());
        session.setAttribute(ConstantsMng.PARAM_NAME_LOGIN, user.getEmail());
        session.setAttribute(ConstantsMng.PARAM_NAME_PASSWORD, user.getPassword());

        request.setAttribute("success", MessageLocalizer.getLocalizedForCurrentLocaleMessage("success.register", request));
        CommandFactory.getFactory().createCommand(CommandEnum.SHOW_REGISTRATION_PAGE).execute(request, response);
    }

}

