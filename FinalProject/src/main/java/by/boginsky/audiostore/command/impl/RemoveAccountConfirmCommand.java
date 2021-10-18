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

import static by.boginsky.audiostore.util.constants.Constant.USER;
import static by.boginsky.audiostore.util.constants.Constant.USER_ID;

public class RemoveAccountConfirmCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        Long userId = Long.valueOf(httpServletRequest.getParameter(USER_ID));

        removeAccountConfirm(httpSession, user, userId);

        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_WELCOME));
        return router;
    }

    private void removeAccountConfirm(HttpSession httpSession, User user, Long userId) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        if (user.getUserRole().equals(User.UserRole.ADMIN) || user.getId().equals(userId)) {
            try {
                userService.removeUser(userId);
                if (user.getUserRole().equals(User.UserRole.USER)) {
                    httpSession.invalidate();
                }
            } catch (ServiceException e) {
                logger.error("Exception in method deleting account confirm command", e);
                throw new CommandException("Exception in method deleting account confirm command", e);
            }
        }
    }
}
