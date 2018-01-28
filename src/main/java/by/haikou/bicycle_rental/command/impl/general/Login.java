package by.haikou.bicycle_rental.command.impl.general;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
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

public class Login implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(Login.class);
    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnauthorizedException, CommandException {
        String login = request.getParameter(ConstantsMng.PARAM_NAME_LOGIN);
        String password = MD5Converter.getHash(request.getParameter(ConstantsMng.PARAM_NAME_PASSWORD));
        Map<String, String> errorMap = validateLoginDetails(login, password, request);
        try {
            if (errorMap.isEmpty()) {
                User user = userService.login(login, password);
                if (user != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    response.sendRedirect(ConstantsMng.MAIN);
                } else {
                    String message = MessageUtils.getProperty(RequestUtils.getLocale(request), MessageUtils.VALIDATION_ERRORS_PARAM);
                    request.setAttribute(ConstantsMng.ATR_ERRORS, message);
                    forwardToView(ConstantsMng.LOGIN, request, response);
                }
            } else {
                request.setAttribute("error", errorMap);
                forwardToView(ConstantsMng.LOGIN, request, response);
            }
        } catch (Exception e) {
        }
    }

    private Map<String, String> validateLoginDetails(String login, String password, HttpServletRequest request) {
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

        return errorMap;
    }

    private void forwardToView(String viewName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
        dispatcher.forward(request, response);
    }
}