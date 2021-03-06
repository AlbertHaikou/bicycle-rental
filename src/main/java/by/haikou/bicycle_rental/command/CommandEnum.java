package by.haikou.bicycle_rental.command;

public enum CommandEnum {
    SHOW_MAIN_PAGE("showMainPage"),
    SHOW_REGISTRATION_PAGE("showRegistrationPage"),
    REGISTER("register"),
    LOGIN("login"),
    SHOW_LOGIN_PAGE("showLoginPage"),
    LOGOUT("logout"),
    CHANGE_LOCALE("changeLocale"),
    SHOW_USERS("showUsers"),
    SHOW_MANAGERS("showManagers"),
    SHOW_BIKES("showBikes"),
    SHOW_PARKINGS("showParkings"),
    SHOW_ADD_BIKE_PAGE("addBikePage"),
    ADD_BIKE("addBike"),
    DELETE_BIKE("deleteBike"),
    EDIT_BIKE("editBike"),
    SHOW_EDIT_BIKE_PAGE("showEditBikePage"),
    RENT_BIKE("rentBike"),
    SHOW_AVAILABLE_BIKES("listAvailableBikes"),
    ADD_PARKING("addParking"),
    EDIT_PARKING("editParking"),
    SHOW_ADD_PARKING_PAGE("addParkingPage"),
    DELETE_PARKING("deleteParking"),
    SHOW_EDIT_PARKING_PAGE("showEditParkingPage"),
    SHOW_BIKES_IN_PARKING_PAGE("showBikesInParkingPage"),
    ADD_MANAGER("addManager"),
    SHOW_ADD_MANAGER_PAGE("showAddManagerPage"),
    DELETE_MANAGER("deleteManager"),
    SHOW_EDIT_MANAGER_PAGE("showEditManagerPage"),
    EDIT_MANAGER("editManager"),
    BAN_USER("banUser"),
    UNBAN_USER("unbanUser"),
    APPOINT_AS_MANAGER("appointAsManager"),
    WITHDRAW_PRIVILEGES("withdrawPrivileges"),
    RETURN_BIKE("returnBike"),
    SHOW_PROFILE("showProfile"),
    SHOW_EDIT_PROFILE("showEditProfilePage"),
    UPDATE_PROFILE("updateProfile"),
    SHOW_BALANCE_MANAGE_PAGE("showBalanceManagePage"),
    FILL_UP_BALANCE("fillUpBalance"),
    TAKE_A_LOAN("takeALoan"),
    SHOW_RENTAL_HISTORY("showRentalHistory"),
    GET_IMAGE("getImage"),
    SHOW_ADD_REPAIR_PAGE("showAddRepairPage"),
    CREATE_REPAIR_ITEM("createRepairItem"),
    SHOW_REPAIR_LIST("showRepairList"),
    REPAIR_BIKE("repairBike");


    private String value;

    CommandEnum(String value) {
        this.value = value;
    }

    public static CommandEnum getEnum(String value) throws IllegalArgumentException {
        for (CommandEnum commandEnum : values()) {
            if (commandEnum.value.equals(value)) {
                return commandEnum;
            }
        }
        return getDefault();
    }

    private static CommandEnum getDefault() {
        return SHOW_MAIN_PAGE;
    }

    public String getValue() {
        return value;
    }
}
