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
 * The type Confirm registration command.
 */
public class ConfirmRegistrationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        String submittedCode = httpServletRequest.getParameter(SUBMITTED_CONFIRM_CODE).trim();
        String realCode = (httpServletRequest.getParameter(REAL_CONFIRM_CODE)).trim();
        confirmRegistration(httpServletRequest, httpSession, submittedCode, realCode);
        CabinetCommand cabinetCommand = new CabinetCommand();
        Router router = cabinetCommand.execute(httpServletRequest);
        return router;
    }

    private void confirmRegistration(HttpServletRequest httpServletRequest, HttpSession httpSession, String submittedCode, String realCode) throws CommandException {
        try {
            if (submittedCode.equals(realCode)) {
                User user = (User) httpSession.getAttribute(USER);
                String encryptedPassword = httpServletRequest.getParameter(ENCRYPTED_PASSWORD);
                UserService userService = UserServiceImpl.getInstance();
                if (user.getUserRole() == User.UserRole.ADMIN) {
                    userService.createAdmin(user, encryptedPassword);
                } else {
                    userService.createUser(user, encryptedPassword);
                }
            } else {
                httpSession.invalidate();
            }
        } catch (ServiceException e) {
            logger.error("Exception in confirm command", e);
            throw new CommandException("Exception in confirm command", e);
        }
    }
}
