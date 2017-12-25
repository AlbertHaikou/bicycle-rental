package by.haikou.bicycle_rental.service.factory;

import by.haikou.bicycle_rental.service.BikeService;
import by.haikou.bicycle_rental.service.RentItemService;
import by.haikou.bicycle_rental.service.SupportItemService;
import by.haikou.bicycle_rental.service.UserService;
import by.haikou.bicycle_rental.service.impl.ParkingServiceImpl;
import by.haikou.bicycle_rental.service.ParkingService;
import by.haikou.bicycle_rental.service.impl.BikeServiceImpl;
import by.haikou.bicycle_rental.service.impl.RentItemServiceImpl;
import by.haikou.bicycle_rental.service.impl.SupportItemServiceImpl;
import by.haikou.bicycle_rental.service.impl.UserServiceImpl;

public class ServiceFactoryImpl extends ServiceFactory {

    private final UserService userService;
    private final BikeService bikeService;
    private final ParkingService parkingService;
    private final SupportItemService supportItemService;
    private final RentItemService rentItemService;

    public ServiceFactoryImpl() {
        this.userService = new UserServiceImpl();
        this.bikeService = new BikeServiceImpl();
        this.parkingService = new ParkingServiceImpl();
        this.supportItemService = new SupportItemServiceImpl();
        this.rentItemService = new RentItemServiceImpl();
    }

    @Override
    public UserService getUserService() {
        return this.userService;
    }

    @Override
    public BikeService getBikeService() {
        return this.bikeService;
    }

    @Override
    public ParkingService getParkingService() {
        return this.parkingService;
    }

    @Override
    public SupportItemService getSupportItemService() {
        return supportItemService;
    }

    @Override
    public RentItemService getRentItemService() {
        return rentItemService;
    }

}
