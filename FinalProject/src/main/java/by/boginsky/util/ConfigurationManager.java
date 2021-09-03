package by.boginsky.util;

import by.boginsky.util.constants.ConstantBundles;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(ConstantBundles.CONFIG);

    private ConfigurationManager(){}

    public static String getProperty(String key){
        return RESOURCE_BUNDLE.getString(key);
    }
}
