package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Default command.
 */
public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN));
        return router;
    }
}
