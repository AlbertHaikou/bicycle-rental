package by.haikou.bicycle_rental.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestUtils {

    /**
     * Changes the localization of the current session using the request parameter "locale".
     *
     * @param request
     */
    public static void setLocale(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String locale = request.getParameter("locale");
        switch (locale) {
            case "rus":
                session.setAttribute("locale", "ru_RU");
                break;
            case "eng":
                session.setAttribute("locale", "en_US");
                break;
            case "bel":
                session.setAttribute("locale", "by_BY");
                break;
        }
    }

    /**
     * @param request
     * @return current session localization.
     */
    public static String getLocale(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute("locale");
        if (StringUtils.isEmpty(locale)) {
            locale = "ru_RU";
        }
        return locale;
    }

}
