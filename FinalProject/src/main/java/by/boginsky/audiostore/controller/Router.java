package by.boginsky.audiostore.controller;

import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

public class Router {

    public enum RouterType {
        FORWARD, REDIRECT;
    }

    private String pagePath = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
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

    public void setRedirect() {
        this.routerType = RouterType.REDIRECT;
    }

}
