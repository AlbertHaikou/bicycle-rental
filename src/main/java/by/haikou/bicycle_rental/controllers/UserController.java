package by.haikou.bicycle_rental.controllers;


import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends CRUDController {


    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("command");
        if (action != null) {
            action = action.trim().toLowerCase();
        }
        switch (action) {
            case "getusers":
                this.getAllUsers(request, response);
                break;
            default:
                super.service(request, response);
        }


    }

    @Override
    void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        User.Role role = (User.Role.valueOf(request.getParameter("manager").toUpperCase()));
        user.setRole(role);
        userService.addUser(user);
        response.sendRedirect(request.getContextPath() + "/UserController?action=list");
    }

    @Override
    void delete(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userService.deleteUser(id);
        list(request, response);
        response.sendRedirect(request.getContextPath() + "/UserController?action=list");
    }

    @Override
    void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("supports", userService.getAllSupports());
        forward(ConstantsMng.LIST_SUPPORTS, request, response);
    }

    @Override
    void update(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        User.Role role = User.Role.valueOf(request.getParameter("support").toUpperCase());
        user.setRole(role);
        String userId = request.getParameter("id");
        user.setId(Integer.parseInt(userId));
        userService.updateUser(user);
        response.sendRedirect(request.getContextPath() + "/UserController?action=list");
    }

    @Override
    void edit(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = userService.getUserById(id);
        request.setAttribute("support", user);
        forward(ConstantsMng.ADD_EDIT_SUPPORTS, request, response);
    }

    @Override
    void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward(ConstantsMng.ADD_EDIT_SUPPORTS, request, response);
    }

    void getAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("users", userService.getAllUsers());
        forward(ConstantsMng.LIST_USERS, request, response);
    }

}
