package by.haikou.bicycle_rental.command.impl.profile;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.RentItemService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowRentalHistory implements ICommand {
    private RentItemService rentItemService = ServiceFactory.getFactory().getRentItemService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, UnauthorizedException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        req.setAttribute("rents", rentItemService.historyRent(userId));
        req.getRequestDispatcher(ConstantsMng.LIST_HISTORY_RENT).forward(req, resp);
    }
}
