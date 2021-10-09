package by.boginsky.audiostore.util;

import java.util.ResourceBundle;

import static by.boginsky.audiostore.util.constants.Bundle.MESSAGE;
import static by.boginsky.audiostore.util.constants.Locale.*;

public class MessageManager {

    private ResourceBundle resourceBundle;

    MessageManager(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    public static MessageManager defineLocale(String locale) {
        MessageManager messageManager = null;
        if (locale == null) {
            messageManager = new MessageManager(ResourceBundle.getBundle(MESSAGE, new java.util.Locale(RUSSIAN_LANGUAGE)));// FIXME: 07.09.2021 maybe different name??/
        } else {
            switch (locale) {
                case ENGLISH_LOCALE:
                    messageManager = new MessageManager(ResourceBundle.getBundle(MESSAGE,
                            new java.util.Locale(ENGLISH_LOCALE, US)));
                    break;
                case RUSSIAN_LOCALE:
                    messageManager = new MessageManager(ResourceBundle.getBundle(MESSAGE,
                            new java.util.Locale(RUSSIAN_LOCALE, RUSSIA)));
            }
        }
        return messageManager;
    }
}
