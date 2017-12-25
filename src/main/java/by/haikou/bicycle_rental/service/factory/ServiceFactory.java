package by.haikou.bicycle_rental.service.factory;

import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.RentItemService;
import by.haikou.bicycle_rental.service.SupportItemService;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.ParkingService;

public abstract class ServiceFactory {

    private static ServiceFactory serviceFactory;

    public abstract UserService getUserService();

    public abstract BikeService getBikeService();

    public abstract ParkingService getParkingService();

    public abstract SupportItemService getSupportItemService();

    public abstract RentItemService getRentItemService();

    static {
        serviceFactory = new ServiceFactoryImpl();
    }

    public static ServiceFactory getFactory() {
        return serviceFactory;
    }

}
