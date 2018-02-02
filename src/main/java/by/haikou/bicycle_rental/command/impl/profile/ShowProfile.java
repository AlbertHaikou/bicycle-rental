package by.haikou.bicycle_rental.command.impl.profile;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.entity.RentItem;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.service.RentItemService;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Collects information about current user and his last rents from database using {@link UserService#getUserById(Integer)} and {@link RentItemService#allHistoryRent(Integer)}.</p>
 * <p>Then sends the user to profile editing page</p>
 */
public class ShowProfile implements ICommand {
    private UserService userService = ServiceFactory.getFactory().getUserService();
    private RentItemService rentItemService = ServiceFactory.getFactory().getRentItemService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        userService.getUserById(userId);
        List<RentItem> rentItems = new ArrayList<>();
        List<RentItem> temp = rentItemService.allHistoryRent(userId);
        if (temp.size() > 5) {
            for (int i = 0; i < 5; i++) {
                rentItems.add(temp.get(i));
            }
        } else {
            rentItems.addAll(temp);
        }

        request.setAttribute("rents", rentItems);
        request.setAttribute("profile", userService.getUserById(userId));

        request.getRequestDispatcher(ConstantsMng.PROFILE).forward(request, response);
    }
}