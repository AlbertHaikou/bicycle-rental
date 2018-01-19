package by.haikou.bicycle_rental.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtils {

    public static final String VALIDATION_ERRORS_PARAM = "VALIDATION_ERRORS_PARAM";
    public static final String VALIDATION_SUCCESSFUL_MESSAGE = "VALIDATION_SUCCESSFUL_MESSAGE";
    public static final String EMPTY_EMAIL_ERROR_MESSAGE = "EMPTY_EMAIL_ERROR_MESSAGE";
    public static final String NOT_UNIQ_LIGIN_ERROR_MESSAGE = "NOT_UNIQ_LIGIN_ERROR_MESSAGE";
    public static final String EMPTY_PASSWORD_ERROR_MESSAGE = "EMPTY_PASSWORD_ERROR_MESSAGE";
    public static final String EMPTY_FIRST_NAME_ERROR_MESSAGE = "EMPTY_FIRST_NAME_ERROR_MESSAGE";
    public static final String EMPTY_LAST_NAME_ERROR_MESSAGE = "EMPTY_LAST_NAME_ERROR_MESSAGE";
    public static final String BID_MESSAGE = "BID_MESSAGE";
    private static final String BUNDLE_NAME = "messages";

    public static String getProperty(String locale, String key) {
        String[] parts = locale.split("_");
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, new Locale(parts[0], parts[1]));
        return bundle.getString(key);
    }
}
