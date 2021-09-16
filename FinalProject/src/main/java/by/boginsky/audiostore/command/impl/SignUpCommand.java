package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_REGISTRATION));
        return router;
    }
}
