package org.keyczar.i18n;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
    private static ResourceBundle RESOURCE_BUNDLE;

    static {
        RESOURCE_BUNDLE = ResourceBundle.getBundle("org.keyczar.i18n.messages");
    }

    public static String getString(String key, Object... params) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key), params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
