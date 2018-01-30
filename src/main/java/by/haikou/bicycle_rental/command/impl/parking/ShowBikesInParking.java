package by.haikou.bicycle_rental.command.impl.parking;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.haikou.bicycle_rental.command.CommandEnum.SHOW_BIKES_IN_PARKING_PAGE;
import static by.haikou.bicycle_rental.util.PaginationObject.DEFAULT_PAGE;

public class ShowBikesInParking implements ICommand {
    private BikeService bikeService = ServiceFactory.getFactory().getBikeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        Map parameterMap = req.getParameterMap();
//        if (parameterMap.containsKey("available") && req.getParameter("available").equals("true")) {
//            req.setAttribute("bikes", bikeService.showAvailableBike());
//        } else {
//            req.setAttribute("bikes", bikeService.getAllBikes());
//        }
        int id = Integer.parseInt(req.getParameter("id"));
        int page;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException exc) {
            page = DEFAULT_PAGE;
        }

        req.setAttribute("items", bikeService.showBikeByParkingId(id, page));
        req.setAttribute("command", SHOW_BIKES_IN_PARKING_PAGE.getValue() + "&id=" + id);
        req.getRequestDispatcher(ConstantsMng.LIST_BIKES).forward(req, resp);
    }
}