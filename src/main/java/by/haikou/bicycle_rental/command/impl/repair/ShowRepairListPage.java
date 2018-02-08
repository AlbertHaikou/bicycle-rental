package by.haikou.bicycle_rental.command.impl.repair;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.command.exception.CommandException;
import by.haikou.bicycle_rental.entity.RepairItem;
import by.haikou.bicycle_rental.entity.User;
import by.haikou.bicycle_rental.exception.UnauthorizedException;
import by.haikou.bicycle_rental.service.RepairItemService;
import by.haikou.bicycle_rental.service.factory.ServiceFactory;
import by.haikou.bicycle_rental.util.ConstantsMng;
import by.haikou.bicycle_rental.util.PaginationObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.haikou.bicycle_rental.command.CommandEnum.SHOW_BIKES;
import static by.haikou.bicycle_rental.command.CommandEnum.SHOW_REPAIR_LIST;
import static by.haikou.bicycle_rental.util.PaginationObject.DEFAULT_PAGE;

public class ShowRepairListPage implements ICommand {
    private RepairItemService repairItemService = ServiceFactory.getFactory().getRepairItemService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException, UnauthorizedException {
        checkRoots(request, new User.Role[]{User.Role.MANAGER, User.Role.ADMINISTRATOR});
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException exc) {
            page = DEFAULT_PAGE;
        }
        PaginationObject<RepairItem> items = repairItemService.getUnperformedItems(page);
        request.setAttribute("items", items);
        request.setAttribute("command", SHOW_REPAIR_LIST.getValue());
        request.getRequestDispatcher(ConstantsMng.LIST_ITEMS).forward(request, response);

    }
}
