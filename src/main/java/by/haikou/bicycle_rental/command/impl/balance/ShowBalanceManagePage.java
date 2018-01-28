package by.haikou.bicycle_rental.command.impl.balance;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class ShowBalanceManagePage implements ICommand {
    UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        BigDecimal balance = userService.getBalanceByUserId(userId);
        BigDecimal credit = userService.getCreditByUserId(userId);
        Boolean isDebtor = userService.isUserDebtor(userId);
        request.setAttribute("balance", balance);
        request.setAttribute("credit", credit);
        request.setAttribute("debtor", isDebtor);
        request.getRequestDispatcher(ConstantsMng.BALANCE_MANAGE).forward(request, response);
    }
}
