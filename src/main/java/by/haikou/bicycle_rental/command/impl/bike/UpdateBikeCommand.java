package by.haikou.bicycle_rental.command.impl.bike;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.entity.Bicycle;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class UpdateBikeCommand implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(UpdateBikeCommand.class);
    private BikeService bikeService = ServiceFactory.getFactory().getBikeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Bicycle bike = new Bicycle();
        bike.setType(request.getParameter("type"));
        bike.setModel(request.getParameter("model"));
        bike.setSize(request.getParameter("size"));
        bike.setIsAvailable(request.getParameter("available").equalsIgnoreCase("free"));
        bike.setParkingId(Integer.valueOf(request.getParameter("parkingId")));
        bike.setPrice(new BigDecimal(request.getParameter("price")));
        bike.setBicycleId(Integer.valueOf(request.getParameter("id")));
        bikeService.updateBike(bike);

        response.sendRedirect(ConstantsMng.SHOW_BIKES);
    }
}
