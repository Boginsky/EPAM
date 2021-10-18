package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.boginsky.audiostore.util.constants.Constant.USER;

public class ToCartCommand implements Command {

    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);

        String page = getPage(user);
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }

    private String getPage(User user) {
        if (user == null) {
            return ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
        }
        if (user.getUserRole() == User.UserRole.ADMIN) {
            return ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_ADMIN);
        }
        if (user.getUserRole() == User.UserRole.USER) {
            return ConfigurationManager.getProperty(PathPage.PATH_PAGE_CART);
        }
        return ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
    }
}
