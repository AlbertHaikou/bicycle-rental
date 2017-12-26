package by.haikou.bicycle_rental.controllers;

import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.service.RentItemService;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

public class ProfileController extends CRUDController {


    private UserService userService = ServiceFactory.getFactory().getUserService();
    private RentItemService rentItemService = ServiceFactory.getFactory().getRentItemService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            action = action.trim().toLowerCase();
        }
        switch (action) {
            case "historyrent":
                this.historyRent(request, response);
                break;
            default:
                super.service(request, response);
        }
    }

    @Override
    void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    void delete(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        request.setAttribute("profile", userService.getUserById(userId));
        forward(ConstantsMng.LIST_PROFILE, request, response);
    }

    @Override
    void update(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.valueOf(request.getParameter("id")));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        userService.updateUser(user);
        response.sendRedirect(request.getContextPath() + "/ProfileController?action=list");
    }

    @Override
    void edit(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        request.setAttribute("profile", userService.getUserById(userId));
        forward(ConstantsMng.ADD_OR_EDIT_PROFILE, request, response);
    }

    @Override
    void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void historyRent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        request.setAttribute("rents", rentItemService.historyRent(userId));
        forward(ConstantsMng.LIST_HISTORY_RENT, request, response);
    }
}
