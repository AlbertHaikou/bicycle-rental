package by.haikou.bicycle_rental.command.impl.general;

import by.haikou.bicycle_rental.command.ICommand;
import by.haikou.bicycle_rental.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @see RequestUtils#setLocale(HttpServletRequest)
 */
public class ChangeLocale implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (null != request) {
            RequestUtils.setLocale(request);
        }
    }
}
