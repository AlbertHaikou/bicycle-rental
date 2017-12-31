package by.haikou.bicycle_rental.command.impl.parking;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.service.ParkingService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowParkingPageCommand implements ICommand {
    private ParkingService parkingService = ServiceFactory.getFactory().getParkingService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("parkings", parkingService.getAllParking());
        req.getRequestDispatcher(ConstantsMng.LIST_PARKING).forward(req, resp);
    }
}
