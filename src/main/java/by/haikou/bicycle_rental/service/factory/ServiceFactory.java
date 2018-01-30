package by.haikou.bicycle_rental.service.factory;

import by.haikou.bicycle_rental.service.*;

public abstract class ServiceFactory {

    private static ServiceFactory serviceFactory;

    public abstract UserService getUserService();

    public abstract BikeService getBikeService();

    public abstract ParkingService getParkingService();

    public abstract RepairItemService getRepairItemService();

    public abstract RentItemService getRentItemService();

    static {
        serviceFactory = new ServiceFactoryImpl();
    }

    public static ServiceFactory getFactory() {
        return serviceFactory;
    }

}
