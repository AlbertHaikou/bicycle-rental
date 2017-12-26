package by.haikou.bicycle_rental.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.haikou.bicycle_rental.entity.Parking;
import by.haikou.bicycle_rental.service.ParkingService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import javax.servlet.annotation.WebServlet;

public class ParkingController extends CRUDController {
    private ParkingService parkingService = ServiceFactory.getFactory().getParkingService();

    @Override
    void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Parking parking = new Parking();
        parking.setStreet(request.getParameter("street"));
        parkingService.addParking(parking);
        response.sendRedirect(request.getContextPath() + "/ParkingController?action=list");
    }

    @Override
    void delete(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        parkingService.deleteParking(id);
        response.sendRedirect(request.getContextPath() + "/ParkingController?action=list");
    }

    @Override
    void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("parkings", parkingService.getAllParking());
        forward(ConstantsMng.LIST_PARKING, request, response);
    }

    @Override
    void update(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Parking parking = new Parking();
        parking.setStreet(request.getParameter("street"));
        parking.setParkingId(id);
        parkingService.updateParking(parking);
        response.sendRedirect(request.getContextPath() + "/ParkingController?action=list");
    }

    @Override
    void edit(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Parking parking = parkingService.getParkingById(id);
        request.setAttribute("parking", parking);
        forward(ConstantsMng.ADD_OR_EDIT_PARKING, request, response);
    }

    @Override
    void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward(ConstantsMng.ADD_OR_EDIT_PARKING, request, response);
    }
}
