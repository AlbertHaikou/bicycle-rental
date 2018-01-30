package by.haikou.bicycle_rental.command.impl.balance;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Use to take a loan.
 * The user is given money on credit, and he will be marked by the system as a debtor.
 */
public class TakeALoan implements ICommand {
    UserService userService = ServiceFactory.getFactory().getUserService();

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see ICommand
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        BigDecimal sum = new BigDecimal(request.getParameter("loan"));
        userService.takeALoan(sum, userId);

        response.sendRedirect("main?command=showBalanceManagePage");
    }
}
