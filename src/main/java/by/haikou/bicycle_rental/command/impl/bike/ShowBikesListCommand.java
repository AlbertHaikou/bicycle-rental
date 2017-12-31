package by.haikou.bicycle_rental.command.impl.bike;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ShowBikesListCommand implements ICommand {
    private BikeService bikeService = ServiceFactory.getFactory().getBikeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map parameterMap = req.getParameterMap();
        if (parameterMap.containsKey("available") && req.getParameter("available").equals("true")) {
            req.setAttribute("bikes", bikeService.showAvailableBike());
        } else {
            req.setAttribute("bikes", bikeService.getAllBikes());
        }
        req.getRequestDispatcher(ConstantsMng.LIST_BIKES).forward(req, resp);
    }
}