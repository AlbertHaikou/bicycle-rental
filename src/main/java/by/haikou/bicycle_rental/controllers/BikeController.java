package by.haikou.bicycle_rental.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.haikou.bicycle_rental.entity.Bicycle;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.ParkingService;
import by.haikou.bicycle_rental.service.RentItemService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

public class BikeController extends CRUDController {


    private BikeService bikeService = ServiceFactory.getFactory().getBikeService();
    private ParkingService parkingService = ServiceFactory.getFactory().getParkingService();
    private RentItemService rentItemService = ServiceFactory.getFactory().getRentItemService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            action = action.trim().toLowerCase();
        }
        switch (action) {
            case "showbike":
                this.showBikeByParkingId(getIdParameter(request), request, response);
                break;
            case "isavailable":
                this.showAvailableBike(request, response);
                break;
            case "rentbike":
                this.rentBike(getIdParameter(request), request, response);
                break;
            case "returnbike":
                this.returnBike(getIdParameter(request), request, response);
                break;
            default:
                super.service(request, response);
        }
    }

    @Override
    void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Bicycle bike = new Bicycle();
        bike.setType(request.getParameter("type"));
        bike.setModel(request.getParameter("model"));
        bike.setSize(request.getParameter("size"));
        bike.setIsAvailable(request.getParameter("available").equals("free"));
        bike.setParkingId(Integer.valueOf(request.getParameter("parkingId")));
        bikeService.createBike(bike);
        response.sendRedirect(request.getContextPath() + "/BikeController?action=list");
    }

    @Override
    void delete(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        bikeService.deleteBike(id);
        response.sendRedirect(request.getContextPath() + "/BikeController?action=list");
    }

    @Override
    void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
//        RentItem rentItem = rentItemService.findTakenByUser(userId);
//        if (rentItem != null) {
//            request.setAttribute("userRentedBikeId", rentItem.getBicycleId());
//        }
        request.setAttribute("bikes", bikeService.getAllBikes());
        forward(ConstantsMng.LIST_BIKES, request, response);
    }

    @Override
    void update(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Bicycle bike = new Bicycle();
        bike.setType(request.getParameter("type"));
        bike.setModel(request.getParameter("model"));
        bike.setSize(request.getParameter("size"));
        bike.setIsAvailable(request.getParameter("available").equalsIgnoreCase("free"));
        bike.setParkingId(Integer.valueOf(request.getParameter("parkingId")));
        bike.setBicycleId(id);
        bikeService.updateBike(bike);
        response.sendRedirect(request.getContextPath() + "/BikeController?action=list");
    }

    @Override
    void edit(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Bicycle bike = bikeService.getBikeById(id);
        request.setAttribute("bike", bike);
        request.setAttribute("parkings", parkingService.getAllParking());
        forward(ConstantsMng.ADD_OR_EDIT_BIKE, request, response);
    }

    @Override
    void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("parkings", parkingService.getAllParking());
        forward(ConstantsMng.ADD_OR_EDIT_BIKE, request, response);

    }

    void showAvailableBike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bikes", bikeService.showAvailableBike());
        forward(ConstantsMng.LIST_BIKES, request, response);
    }

    void rentBike(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        bikeService.rentBike(id, userId);
        response.sendRedirect(request.getContextPath() + "/BikeController?action=list");
    }

    void returnBike(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        bikeService.returnBike(id, userId);
        response.sendRedirect(request.getContextPath() + "/BikeController?action=list");
    }

    void showBikeByParkingId(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bikes", bikeService.showBikeByParkingId(id));
        forward(ConstantsMng.LIST_BIKES, request, response);
    }
}
