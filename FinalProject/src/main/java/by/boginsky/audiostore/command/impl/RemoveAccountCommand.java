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

import static by.boginsky.audiostore.util.constants.Constant.USER_ID;
import static by.boginsky.audiostore.util.constants.Constant.USER_TO_REMOVE;

/**
 * The type Remove account command.
 */
public class RemoveAccountCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        Long userId = Long.valueOf(httpServletRequest.getParameter(USER_ID));
        removeAccount(httpServletRequest, userId);
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_REMOVE_ACCOUNT));
        return router;
    }

    private void removeAccount(HttpServletRequest httpServletRequest, Long userId) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        User user;
        try {
            user = userService.findUserById(userId);
        } catch (ServiceException e) {
            logger.error("Exception in remove account command", e);
            throw new CommandException("Exception in remove account command", e);
        }
        httpServletRequest.setAttribute(USER_TO_REMOVE, user);
    }
}
