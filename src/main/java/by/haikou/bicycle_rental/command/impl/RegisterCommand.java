package by.haikou.bicycle_rental.command.impl;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.command.factory.CommandFactory;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterCommand implements ICommand {
    private static final Logger log = LogManager.getLogger(RegisterCommand.class);
    UserService userService = ServiceFactory.getFactory().getUserService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException, UnauthorizedException {

        String firstName = request.getParameter(ConstantsMng.PARAM_NAME_FIRST_NAME);
        String lastName = request.getParameter(ConstantsMng.PARAM_NAME_LAST_NAME);
        String login = request.getParameter(ConstantsMng.PARAM_NAME_LOGIN);
        String password = request.getParameter(ConstantsMng.PARAM_NAME_PASSWORD);

        Map<String, String> errorMap = validateRegisterDetails(request, firstName, lastName, login, password);

        if (errorMap.isEmpty()) {
            User user = new User();
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
            String msg = MessageUtils.getProperty(RequestUtils.getLocale(request),
                    MessageUtils.VALIDATION_SUCCESSFUL_MESSAGE);
            request.setAttribute(ConstantsMng.ATR_SUCCESSFUL, msg);
            forwardToView(ConstantsMng.LOGIN, request, response);

        } else {
            request.setAttribute(ConstantsMng.ATR_ERRORS, errorMap);
            forwardToView(ConstantsMng.REGISTRATION, request, response);
        }
    }

    private Map<String, String> validateRegisterDetails(HttpServletRequest request,
                                                        String firstName, String lastName, String login, String password) {
        Map<String, String> errorMap = new HashMap<>();
        String loginMsg = MessageUtils.getProperty(RequestUtils.getLocale(request),
                MessageUtils.EMPTY_EMAIL_ERROR_MESSAGE);
        if (StringUtils.isEmpty(login)) {
            errorMap.put(ConstantsMng.PARAM_NAME_LOGIN, loginMsg);
        }
        String passwordMsg = MessageUtils.getProperty(RequestUtils.getLocale(request),
                MessageUtils.EMPTY_PASSWORD_ERROR_MESSAGE);
        if (StringUtils.isEmpty(password)) {
            errorMap.put(ConstantsMng.PARAM_NAME_PASSWORD, passwordMsg);
        }
        String firstNameMsg = MessageUtils.getProperty(RequestUtils.getLocale(request),
                MessageUtils.EMPTY_FIRST_NAME_ERROR_MESSAGE);
        if (StringUtils.isEmpty(firstName)) {
            errorMap.put(ConstantsMng.PARAM_NAME_FIRST_NAME, firstNameMsg);
        }
        String lastNameMsg = MessageUtils.getProperty(RequestUtils.getLocale(request),
                MessageUtils.EMPTY_LAST_NAME_ERROR_MESSAGE);
        if (StringUtils.isEmpty(lastName)) {
            errorMap.put(ConstantsMng.PARAM_NAME_LAST_NAME, lastNameMsg);
        }
        return errorMap;
    }

    private void forwardToView(String viewName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
        dispatcher.forward(request, response);
    }
    }



