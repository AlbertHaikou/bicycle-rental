package by.haikou.bicycle_rental.command;

public enum CommandEnum {
    SHOW_MAIN_PAGE("showMainPage"),
    SHOW_ERROR_PAGE("showErrorPage"),
    SHOW_CATEGORY_PAGE("showCategoryPage"),
    SHOW_REGISTRATION_PAGE("showRegistrationPage"),
    REGISTER("register"),
    LOGIN("login"),
    SHOW_LOGIN_PAGE("showLoginPage"),
    LOGOUT("logout"),
    CHANGE_LOCALE("changeLocale"),
    SHOW_RESULTS_PAGE("showResultsPage"),
    SHOW_EVENT_PAGE("showEventPage"),
    SHOW_ADD_EVENT_PAGE("showAddEventPage"),
    SHOW_PERSONAL_PAGE("showPersonalPage"),
    SHOW_FILL_UP_BALANCE_PAGE("showFillUpBalancePage"),
    SHOW_WITHDRAW_MONEY_PAGE("showWithdrawMoneyPage"),
    FILL_UP_BALANCE("fillUpBalance"),
    WITHDRAW_MONEY("withdrawMoney"),
    ADD_CATEGORY("addCategory"),
    SHOW_ADD_LEAGUE_PAGE("showAddLeaguePage"),
    SHOW_ADD_MEMBER_PAGE("showAddMemberPage"),
    SHOW_ADMIN_PAGE("showAdminPage"),
    BAN("ban"),
    UNBAN("unban"),
    CHANGE_ROLE("changeRole");


    private String value;

    private CommandEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static CommandEnum getEnum(String value) throws IllegalArgumentException{
        for(CommandEnum commandEnum : values()){
            if(commandEnum.value.equals(value)){
                return commandEnum;
            }
        }
        return getDefault();
    }

    private static CommandEnum getDefault(){
        return SHOW_MAIN_PAGE;
    }
}
