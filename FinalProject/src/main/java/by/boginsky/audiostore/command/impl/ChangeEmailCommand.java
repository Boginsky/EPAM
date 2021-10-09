package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.model.service.impl.UserServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.boginsky.audiostore.util.constants.Attribute.USER;
import static by.boginsky.audiostore.util.constants.Parameter.*;

public class ChangeEmailCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        String updatedEmail = httpServletRequest.getParameter(UPDATED_EMAIL);
        UserService userService = UserServiceImpl.getInstance();
        try {
            userService.updateUserEmail(user.getEmail(), updatedEmail);// FIXME: 04.10.2021 add validation
            user.setEmail(updatedEmail);
            httpSession.setAttribute(USER, user);
        } catch (ServiceException e) {
            throw new CommandException("Exception in change email command", e);
        }
        String page;
        Router router = new Router();
        if (user.getUserRole() == User.UserRole.USER) {
            page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_USER);
        } else {
            page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_ADMIN);
        }
        router.setPagePath(page);
        return router;
    }
}
