package by.haikou.bicycle_rental.command.factory;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.impl.*;

import java.util.HashMap;
import java.util.Map;

import static by.haikou.bicycle_rental.command.CommandEnum.*;

public class CommandFactory {
    private static final CommandFactory factory = new CommandFactory();
    private Map<CommandEnum, ICommand> commands;

    private CommandFactory() {
        commands = new HashMap<>();
        commands.put(SHOW_MAIN_PAGE, new ShowMainPageCommand());
        commands.put(SHOW_REGISTRATION_PAGE, new ShowRegistrationPageCommand());
        commands.put(REGISTER, new RegisterCommand());
        commands.put(LOGIN, new LoginCommand());
        commands.put(SHOW_LOGIN_PAGE, new ShowLoginPageCommand());
        commands.put(LOGOUT, new LogoutCommand());
        commands.put(CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(SHOW_USERS, new ShowUsersListCommand());
        commands.put(SHOW_MANAGERS, new ShowManagersListCommand());
        commands.put(SHOW_BIKES, new ShowBikesListCommand());
        commands.put(SHOW_PARKINGS, new ShowParkingsListCommand());
        commands.put(SHOW_ADD_BIKE_PAGE, new ShowAddBikePageCommand());
        commands.put(ADD_BIKE, new AddBikeCommand());
        commands.put(EDIT_BIKE, new EditBikeCommand());
        commands.put(DELETE_BIKE, new DeleteBikeCommand());
        commands.put(SHOW_EDIT_BIKE_PAGE, new ShowEditBikePageCommand());
        commands.put(RENT_BIKE, new RentBikeCommand());
    }

    public static CommandFactory getFactory() {
        return factory;
    }

    public ICommand createCommand(CommandEnum command) {
        if (commands.containsKey(command)) {
            return commands.get(command);
        }
        return null;
    }
}
