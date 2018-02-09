package by.haikou.bicycle_rental.command.impl.balance;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * <p>Use to transfer money to a user account.</p>
 * <p>When the work is done, <code>response.sendRedirect</code> is used to avoid re-querying.</p>
 * <p>If the user has a loan - first the money is used to repay it.</p>
 *
 * @see TakeLoan
 */
public class FillUpBalance implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(FillUpBalance.class);
    UserService userService = ServiceFactory.getFactory().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        BigDecimal sum = new BigDecimal(request.getParameter("sum"));
        if (sum.compareTo(new BigDecimal(0)) > 0) {
            userService.fillUpUserBalance(sum, userId);
        }

        response.sendRedirect("main?command=showBalanceManagePage");
    }
}