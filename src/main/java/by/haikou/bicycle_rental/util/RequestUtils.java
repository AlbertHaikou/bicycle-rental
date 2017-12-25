package by.haikou.bicycle_rental.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestUtils {

    public static void setLocale(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        if (action.equals("eng")) {
            session.setAttribute("locale", "en_US");
        } else if (action.equals("rus")) {
            session.setAttribute("locale", "ru_RU");
        }

    }

    public static String getLocale(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute("locale");
        if (StringUtils.isEmpty(locale)) {
            locale = "en_US";
        }
        return locale;
    }

}
