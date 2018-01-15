package by.haikou.bicycle_rental.command.factory;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.impl.*;
import by.haikou.bicycle_rental.command.impl.balance.FillUpBalanceCommand;
import by.haikou.bicycle_rental.command.impl.balance.ShowBalanceManagePageCommand;
import by.haikou.bicycle_rental.command.impl.bike.*;
import by.haikou.bicycle_rental.command.impl.general.ChangeLocaleCommand;
import by.haikou.bicycle_rental.command.impl.general.LoginCommand;
import by.haikou.bicycle_rental.command.impl.general.LogoutCommand;
import by.haikou.bicycle_rental.command.impl.general.RegisterCommand;
import by.haikou.bicycle_rental.command.impl.manager.*;
import by.haikou.bicycle_rental.command.impl.parking.*;
import by.haikou.bicycle_rental.command.impl.profile.ShowEditProfilePageCommand;
import by.haikou.bicycle_rental.command.impl.profile.ShowProfileCommand;
import by.haikou.bicycle_rental.command.impl.profile.UpdateProfileCommand;
import by.haikou.bicycle_rental.command.impl.user.AppointAsManagerCommand;
import by.haikou.bicycle_rental.command.impl.user.BanUserCommand;
import by.haikou.bicycle_rental.command.impl.user.UnBanUserCommand;
import by.haikou.bicycle_rental.command.impl.user.WithdrawPrivilegesCommand;

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
        commands.put(SHOW_MANAGERS, new ShowManagersPageCommand());

        commands.put(SHOW_BIKES, new ShowBikesListCommand());
        commands.put(SHOW_ADD_BIKE_PAGE, new ShowAddBikePageCommand());
        commands.put(ADD_BIKE, new AddBikeCommand());
        commands.put(EDIT_BIKE, new UpdateBikeCommand());
        commands.put(DELETE_BIKE, new DeleteBikeCommand());
        commands.put(SHOW_EDIT_BIKE_PAGE, new ShowEditBikePageCommand());
        commands.put(RENT_BIKE, new RentBikeCommand());
        commands.put(RETURN_BIKE, new ReturnBikeCommand());

        commands.put(SHOW_PARKINGS, new ShowParkingPageCommand());
        commands.put(ADD_PARKING, new AddParkingCommand());
        commands.put(EDIT_PARKING, new UpdateParkingCommand());
        commands.put(SHOW_EDIT_PARKING_PAGE, new ShowEditParkingPageCommand());
        commands.put(SHOW_ADD_PARKING_PAGE, new ShowAddParkingPageCommand());
        commands.put(DELETE_PARKING, new DeleteParkingCommand());
        commands.put(SHOW_BIKES_IN_PARKING_PAGE, new ShowBikesInParkingCommand());

        commands.put(SHOW_ADD_MANAGER_PAGE, new ShowAddManagerPageCommand());
        commands.put(ADD_MANAGER, new AddManagerCommand());
        commands.put(EDIT_MANAGER, new UpdateManagerCommand());
        commands.put(DELETE_MANAGER, new DeleteManagerCommand());
        commands.put(SHOW_EDIT_MANAGER_PAGE, new ShowEditManagerPageCommand());

        commands.put(BAN_USER, new BanUserCommand());
        commands.put(UNBAN_USER, new UnBanUserCommand());
        commands.put(APPOINT_AS_MANAGER, new AppointAsManagerCommand());
        commands.put(WITHDRAW_PRIVILEGES, new WithdrawPrivilegesCommand());

        commands.put(SHOW_PROFILE, new ShowProfileCommand());
        commands.put(SHOW_EDIT_PROFILE, new ShowEditProfilePageCommand());
        commands.put(UPDATE_PROFILE, new UpdateProfileCommand());
        commands.put(SHOW_BALANCE_MANAGE_PAGE, new ShowBalanceManagePageCommand());
        commands.put(FILL_UP_BALANCE, new FillUpBalanceCommand());
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
