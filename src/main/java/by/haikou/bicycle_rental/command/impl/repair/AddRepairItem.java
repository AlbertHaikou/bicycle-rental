package by.haikou.bicycle_rental.command.impl.repair;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.entity.RepairItem;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.RepairItemService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddRepairItem implements ICommand {
    private static final Logger LOGGER = LogManager.getLogger(AddRepairItem.class);
    private RepairItemService repairItemService = ServiceFactory.getFactory().getRepairItemService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException, UnauthorizedException {
        checkRoots(request, new User.Role[]{User.Role.MANAGER, User.Role.ADMINISTRATOR});
        Integer bikeId = Integer.parseInt(request.getParameter("id"));
        String description = request.getParameter("description");
        RepairItem repairItem = new RepairItem();
        repairItem.setBikeId(bikeId);
        repairItem.setDescription(description);
        repairItem.setStatus(false);
        repairItemService.createItem(repairItem);

        response.sendRedirect(ConstantsMng.SHOW_BIKES);
    }
}
