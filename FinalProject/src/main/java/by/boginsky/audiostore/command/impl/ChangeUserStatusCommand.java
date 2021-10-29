package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.boginsky.audiostore.util.constants.Constant.*;


/**
 * The type Change user status command.
 */
public class ChangeUserStatusCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        UserService userService = UserServiceImpl.getInstance();
        Long userId = Long.parseLong(httpServletRequest.getParameter(USER_ID));
        String userStatus = httpServletRequest.getParameter(USER_STATUS);
        if(user != null) {
            if (user.getUserRole().equals(User.UserRole.ADMIN) & userId != 1) {
                updateStatus(userService, userId, userStatus);
            }
        }
        CabinetCommand cabinetCommand = new CabinetCommand();
        Router router = cabinetCommand.execute(httpServletRequest);
        return router;
    }

    private void updateStatus(UserService userService, Long userId, String userStatus) throws CommandException {
        try {
            userService.changeUserStatus(userId, userStatus);
        } catch (ServiceException e) {
            logger.error("Exception in change user status command", e);
            throw new CommandException("Exception in change user status command", e);
        }
    }
}
