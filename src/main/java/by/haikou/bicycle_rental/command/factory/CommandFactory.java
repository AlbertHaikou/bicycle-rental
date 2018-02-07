package by.haikou.bicycle_rental.command.factory;

import by.haikou.bicycle_rental.command.CommandEnum;
import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.impl.ShowMainPage;
import by.haikou.bicycle_rental.command.impl.balance.FillUpBalance;
import by.haikou.bicycle_rental.command.impl.balance.ShowBalanceManagePage;
import by.haikou.bicycle_rental.command.impl.balance.TakeLoan;
import by.haikou.bicycle_rental.command.impl.bike.*;
import by.haikou.bicycle_rental.command.impl.general.*;
import by.haikou.bicycle_rental.command.impl.manager.*;
import by.haikou.bicycle_rental.command.impl.parking.*;
import by.haikou.bicycle_rental.command.impl.profile.ShowEditProfilePage;
import by.haikou.bicycle_rental.command.impl.profile.ShowProfile;
import by.haikou.bicycle_rental.command.impl.profile.ShowRentalHistory;
import by.haikou.bicycle_rental.command.impl.profile.UpdateProfile;
import by.haikou.bicycle_rental.command.impl.user.AppointAsManager;
import by.haikou.bicycle_rental.command.impl.user.BanUser;
import by.haikou.bicycle_rental.command.impl.user.ShowUsersList;
import by.haikou.bicycle_rental.command.impl.user.UnBanUser;

import java.util.HashMap;
import java.util.Map;

import static by.haikou.bicycle_rental.command.CommandEnum.*;

public class CommandFactory {
    private static final CommandFactory factory = new CommandFactory();
    private Map<CommandEnum, ICommand> commands;

    private CommandFactory() {
        commands = new HashMap<>();

        commands.put(SHOW_MAIN_PAGE, new ShowMainPage());
        commands.put(SHOW_REGISTRATION_PAGE, new ShowRegistrationPage());
        commands.put(REGISTER, new Register());
        commands.put(LOGIN, new Login());
        commands.put(SHOW_LOGIN_PAGE, new ShowLoginPage());
        commands.put(LOGOUT, new Logout());
        commands.put(GET_IMAGE, new GetImage());
        commands.put(CHANGE_LOCALE, new ChangeLocale());
        commands.put(SHOW_USERS, new ShowUsersList());
        commands.put(SHOW_MANAGERS, new ShowManagersPage());

        commands.put(SHOW_BIKES, new ShowBikesList());
        commands.put(SHOW_ADD_BIKE_PAGE, new ShowAddBikePage());
        commands.put(ADD_BIKE, new AddBike());
        commands.put(EDIT_BIKE, new UpdateBike());
        commands.put(DELETE_BIKE, new DeleteBike());
        commands.put(SHOW_EDIT_BIKE_PAGE, new ShowEditBikePage());
        commands.put(RENT_BIKE, new RentBike());
        commands.put(RETURN_BIKE, new ReturnBike());

        commands.put(SHOW_PARKINGS, new ShowParkingsList());
        commands.put(ADD_PARKING, new AddParking());
        commands.put(EDIT_PARKING, new UpdateParking());
        commands.put(SHOW_EDIT_PARKING_PAGE, new ShowEditParkingPage());
        commands.put(SHOW_ADD_PARKING_PAGE, new ShowAddParkingPage());
        commands.put(DELETE_PARKING, new DeleteParking());
        commands.put(SHOW_BIKES_IN_PARKING_PAGE, new ShowParkingsBikes());

        commands.put(SHOW_ADD_MANAGER_PAGE, new ShowAddManagerPage());
        commands.put(ADD_MANAGER, new AddManager());
        commands.put(EDIT_MANAGER, new UpdateManager());
        commands.put(DELETE_MANAGER, new DeleteManager());
        commands.put(SHOW_EDIT_MANAGER_PAGE, new ShowEditManagerPage());

        commands.put(BAN_USER, new BanUser());
        commands.put(UNBAN_USER, new UnBanUser());
        commands.put(APPOINT_AS_MANAGER, new AppointAsManager());
        commands.put(WITHDRAW_PRIVILEGES, new WithdrawPrivileges());

        commands.put(SHOW_PROFILE, new ShowProfile());
        commands.put(SHOW_EDIT_PROFILE, new ShowEditProfilePage());
        commands.put(UPDATE_PROFILE, new UpdateProfile());
        commands.put(SHOW_BALANCE_MANAGE_PAGE, new ShowBalanceManagePage());
        commands.put(FILL_UP_BALANCE, new FillUpBalance());
        commands.put(TAKE_A_LOAN, new TakeLoan());
        commands.put(SHOW_RENTAL_HISTORY, new ShowRentalHistory());
    }

    public static CommandFactory getFactory() {
        return factory;
    }

    /**
     * Returns an instance of the command class specified by the input parameter
     *
     * @param command
     * @return instance of the command class
     */
    public ICommand createCommand(CommandEnum command) {
        if (commands.containsKey(command)) {
            return commands.get(command);
        }
        return null;
    }
}
