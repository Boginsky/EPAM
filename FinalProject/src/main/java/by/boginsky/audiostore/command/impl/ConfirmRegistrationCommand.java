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

import static by.boginsky.audiostore.util.constants.Attribute.*;
import static by.boginsky.audiostore.util.constants.Parameter.EMAIL;

public class ConfirmRegistrationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        Router router = new Router();
        String submittedCode = httpServletRequest.getParameter(CONFIRM_CODE);
        HttpSession httpSession = httpServletRequest.getSession();
        String realCode = String.valueOf(httpSession.getAttribute(CONFIRM_CODE)).trim();
        String page;
        try {
            if (submittedCode.equals(realCode)) {
                User user = (User) httpSession.getAttribute(USER);
                String encryptedPassword = String.valueOf(httpSession.getAttribute(ENCRYPTED_PASSWORD));
                UserService userService = UserServiceImpl.getInstance();
                userService.createUser(user, encryptedPassword);
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_USER);
                httpSession.setAttribute(EMAIL, user.getEmail());
                httpSession.setAttribute(USER, user);
            } else {
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_CONFIRM);
            }
        } catch (ServiceException e) {
            throw new CommandException("Exception in confirm command", e);
        }
        router.setPagePath(page);
        return router;
    }
}
