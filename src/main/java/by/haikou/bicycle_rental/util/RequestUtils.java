package by.haikou.bicycle_rental.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestUtils {

    /**
     * Changes the localization of the current session.
     *
     * @param request
     */
    public static void setLocale(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String locale = request.getParameter("locale");
        if (locale.equals("eng")) {
            session.setAttribute("locale", "en_US");
        } else if (locale.equals("rus")) {
            session.setAttribute("locale", "ru_RU");
        } else if (locale.equals("bel")) {
            session.setAttribute("locale", "by_BY");
        }
    }

    /**
     * @param request
     * @return current session localization.
     */
    public static String getLocale(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute("locale");
        if (StringUtils.isEmpty(locale)) {
            locale = "en_US";
        }
        return locale;
    }

}
