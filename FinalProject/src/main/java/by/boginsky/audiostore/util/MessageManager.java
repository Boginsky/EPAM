package by.boginsky.audiostore.util;

import java.util.Locale;
import java.util.ResourceBundle;

import static by.boginsky.audiostore.util.constants.Bundle.MESSAGE;
import static by.boginsky.audiostore.util.constants.Locale.*;

/**
 * The type Message manager.
 */
public class MessageManager {

    private final ResourceBundle resourceBundle;

    private MessageManager(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    /**
     * Gets message.
     *
     * @param key the key
     * @return the message
     */
    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    /**
     * Define locale message manager.
     *
     * @param locale the locale
     * @return the message manager
     */
    public static MessageManager defineLocale(String locale) {
        MessageManager messageManager = null;
        if (locale == null) {
            messageManager = new MessageManager(ResourceBundle.getBundle(MESSAGE, new Locale(RUSSIAN_LOCALE)));
        } else {
            switch (locale) {
                case ENGLISH_LOCALE:
                    messageManager = new MessageManager(ResourceBundle.getBundle(MESSAGE,
                            new Locale(ENGLISH_LANGUAGE, US)));
                    break;
                case RUSSIAN_LOCALE:
                    messageManager = new MessageManager(ResourceBundle.getBundle(MESSAGE,
                            new Locale(RUSSIAN_LANGUAGE, RUSSIA)));
            }
        }
        return messageManager;
    }
}
