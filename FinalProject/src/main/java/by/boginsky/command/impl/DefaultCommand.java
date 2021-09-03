package by.boginsky.command.impl;

import by.boginsky.command.Command;
import by.boginsky.controller.Router;
import by.boginsky.exception.CommandException;
import by.boginsky.util.ConfigurationManager;
import by.boginsky.util.constants.ConstantPathPages;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN));
        return router;
    }
}
