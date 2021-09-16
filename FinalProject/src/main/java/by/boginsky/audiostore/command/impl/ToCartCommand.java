package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.boginsky.audiostore.util.constants.Attribute.USER;

public class ToCartCommand implements Command {

    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        String page;
        if(user == null){
            page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
        }else {
            if(user.getUserRole() == User.UserRole.ADMIN){
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_ADMIN);
            }else if (user.getUserRole() == User.UserRole.USER){
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_CART);
            }else {
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
            }
        }
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }
}
