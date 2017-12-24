package by.haikou.bicycle_rental.controllers;

import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import by.haikou.bicycle_rental.util.MessageUtils;
import by.haikou.bicycle_rental.util.RequestUtils;
import by.haikou.bicycle_rental.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
 private static final long serialVersionUID = 6297383302078230514L;
    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(ConstantsMng.PARAM_NAME_LOGIN);
        String password = request.getParameter(ConstantsMng.PARAM_NAME_PASSWORD);
        Map<String, String> errorMap = validateLoginDetails(login, password, request);
        if (errorMap.isEmpty()) {
            User user = userService.getUser(login, password);
            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + ConstantsMng.INDEX);
            } else {
                String message = MessageUtils.getProperty(RequestUtils.getLocale(request), MessageUtils.VALIDATION_ERRORS_PARAM);
                request.setAttribute(ConstantsMng.ATR_ERRORS, message);
                forwardToView(ConstantsMng.LOGIN, request, response);
            }
        } else {
            request.setAttribute("error", errorMap);
            forwardToView(ConstantsMng.LOGIN, request, response);
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
