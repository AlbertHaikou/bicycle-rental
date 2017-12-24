//package by.haikou.bicycle_rental.command.factory;
//
//import by.haikou.bicycle_rental.command.CommandEnum;
//import by.haikou.bicycle_rental.command.ICommand;
//import by.haikou.bicycle_rental.command.impl.*;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static by.haikou.bicycle_rental.command.CommandEnum.*;
//
//public class CommandFactory {
//    private static final CommandFactory factory = new CommandFactory();
//    public static CommandFactory getFactory(){
//        return factory;
//    }
//
//    private Map<CommandEnum, ICommand> commands;
//
//    private CommandFactory(){
//        commands = new HashMap<CommandEnum, ICommand>();
//        commands.put(SHOW_MAIN_PAGE, new ShowMainPageCommand());
//        commands.put(SHOW_CATEGORY_PAGE, new ShowCategoryPageCommand());
//        commands.put(SHOW_REGISTRATION_PAGE, new ShowRegistrationPageCommand());
//        commands.put(REGISTER, new RegisterCommand());
//        commands.put(LOGIN, new LoginCommand());
//        commands.put(SHOW_LOGIN_PAGE, new ShowLoginPageCommand());
//        commands.put(LOGOUT, new LogoutCommand());
//        commands.put(CHANGE_LOCALE, new ChangeLocaleCommand());
//        commands.put(SHOW_RESULTS_PAGE, new ShowResultsPageCommand());
//        commands.put(SHOW_EVENT_PAGE, new ShowEventPageCommand());
//        commands.put(SHOW_ADD_EVENT_PAGE, new ShowAddEventPageCommand());
//        commands.put(SHOW_PERSONAL_PAGE, new ShowPersonalPageCommand());
//        commands.put(SHOW_FILL_UP_BALANCE_PAGE, new ShowFillUpPageCommand());
//        commands.put(SHOW_WITHDRAW_MONEY_PAGE, new ShowWithdrawMoneyPage());
//        commands.put(FILL_UP_BALANCE, new FillUpBalanceCommand());
//        commands.put(WITHDRAW_MONEY, new WithdrawMoneyCommand());
//        commands.put(ADD_CATEGORY, new AddCategoryCommand());
//        commands.put(SHOW_ADD_MEMBER_PAGE, new ShowAddMemberPageCommand());
//        commands.put(SHOW_ADD_LEAGUE_PAGE, new ShowAddLeaguePageCommand());
//        commands.put(SHOW_ADMIN_PAGE, new ShowAdminPageCommand());
//        commands.put(BAN, new BanCommand());
//        commands.put(UNBAN, new UnbanCommand());
//        commands.put(CHANGE_ROLE, new ChangeRoleCommand());
//    }
//
//    public ICommand createCommand(CommandEnum command){
//        if(commands.containsKey(command)){
//            return commands.get(command);
//        }
//        return null;
//    }
//}
