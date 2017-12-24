package by.haikou.bicycle_rental.controllers;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.haikou.bicycle_rental.entity.UserEntity;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import by.haikou.bicycle_rental.util.Role;
import java.util.Arrays;
import java.util.List;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends CRUDController {

    private static final long serialVersionUID = 6297383302078210511L;
    
    private UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
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
        UserEntity user = new UserEntity();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        List<Role> roles = Arrays.asList(Role.valueOf(request.getParameter("support").toUpperCase()));
        user.setRoles(roles);
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
        UserEntity user = new UserEntity();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        List<Role> roles = Arrays.asList(Role.valueOf(request.getParameter("support").toUpperCase()));
        user.setRoles(roles);
        String userId = request.getParameter("id");
        user.setId(Integer.parseInt(userId));
        userService.updateUser(user);
        response.sendRedirect(request.getContextPath() + "/UserController?action=list");
    }

    @Override
    void edit(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserEntity user = userService.getUserById(id);
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
