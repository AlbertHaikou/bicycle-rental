package by.haikou.bicycle_rental.service.factory;

import by.haikou.bicycle_rental.service.*;
import by.haikou.bicycle_rental.service.impl.*;

public class ServiceFactoryImpl extends ServiceFactory {

    private final UserService userService;
    private final BikeService bikeService;
    private final ParkingService parkingService;
    private final RepairItemService repairItemService;
    private final RentItemService rentItemService;

    public ServiceFactoryImpl() {
        this.userService = new UserServiceImpl();
        this.bikeService = new BikeServiceImpl();
        this.parkingService = new ParkingServiceImpl();
        this.repairItemService = new RepairItemServiceImpl();
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
    public RepairItemService getRepairItemService() {
        return repairItemService;
    }

    @Override
    public RentItemService getRentItemService() {
        return rentItemService;
    }

}
