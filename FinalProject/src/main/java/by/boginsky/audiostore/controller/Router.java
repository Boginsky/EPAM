package by.boginsky.audiostore.controller;

import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

/**
 * The type Router.
 */
public class Router {

    /**
     * The enum Router type.
     */
    public enum RouterType {
        /**
         * Forward router type.
         */
        FORWARD,
        /**
         * Redirect router type.
         */
        REDIRECT
    }

    private String pagePath = ConfigurationManager.getProperty(PathPage.PATH_PAGE_SUCCESS);
    private RouterType routerType = RouterType.FORWARD;

    /**
     * Gets page path.
     *
     * @return the page path
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * Sets page path.
     *
     * @param pagePath the page path
     */
    public void setPagePath(String pagePath) {
        if (pagePath != null) {
            this.pagePath = pagePath;
        }
    }

    /**
     * Gets router type.
     *
     * @return the router type
     */
    public RouterType getRouterType() {
        return routerType;
    }

    /**
     * Sets redirect.
     */
    public void setRedirect() {
        this.routerType = RouterType.REDIRECT;
    }

}
