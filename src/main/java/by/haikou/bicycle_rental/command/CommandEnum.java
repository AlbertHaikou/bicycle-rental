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
    SHOW_PARKINGS("showParkings");

    private String value;

    private CommandEnum(String value) {
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
