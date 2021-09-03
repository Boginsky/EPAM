package by.boginsky.controller;

import by.boginsky.util.ConfigurationManager;
import by.boginsky.util.constants.ConstantPathPages;

public class Router {

    public enum RouterType {
        FORWARD, REDIRECT;
    }

    private String pagePath = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN);
    private RouterType routerType = RouterType.FORWARD;

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        if (pagePath != null) {
            this.pagePath = pagePath;
        }
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public void setRouterTypeRedirect() {
        this.routerType = RouterType.REDIRECT;
    }

}
