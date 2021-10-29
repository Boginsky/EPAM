package by.boginsky.audiostore.util;

import by.boginsky.audiostore.util.constants.Bundle;

import java.util.ResourceBundle;

/**
 * The type Configuration manager.
 */
public class ConfigurationManager {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(Bundle.CONFIG);

    private ConfigurationManager() {
    }

    /**
     * Gets property.
     *
     * @param key the key
     * @return the property
     */
    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
