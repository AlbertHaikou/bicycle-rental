package by.haikou.bicycle_rental.command.impl.parking;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.entity.Parking;
import by.haikou.bicycle_rental.service.ParkingService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowEditParkingPage implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(ShowEditParkingPage.class);
    private ParkingService parkingService = ServiceFactory.getFactory().getParkingService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Parking parking = parkingService.getParkingById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("parking", parking);
        request.getRequestDispatcher(ConstantsMng.ADD_OR_EDIT_PARKING).forward(request, response);
    }
}
