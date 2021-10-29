package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.model.service.impl.UserServiceImpl;
import by.boginsky.audiostore.util.validator.InputDataValidator;
import by.boginsky.audiostore.util.validator.impl.InputDataValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.boginsky.audiostore.util.constants.Constant.*;

/**
 * The type Change email confirm command.
 */
public class ChangeEmailConfirmCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        String updatedEmail = httpServletRequest.getParameter(UPDATED_EMAIL);
        String realConfirmCode = httpServletRequest.getParameter(REAL_CONFIRM_CODE);
        String submittedConfirmCode = httpServletRequest.getParameter(SUBMITTED_CONFIRM_CODE);
        updateEmail(user, updatedEmail, realConfirmCode, submittedConfirmCode);
        httpSession.setAttribute(USER, user);
        CabinetCommand cabinetCommand = new CabinetCommand();
        Router router = cabinetCommand.execute(httpServletRequest);
        return router;
    }

    private void updateEmail(User user, String updatedEmail, String realConfirmCode, String submittedConfirmCode) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        if (realConfirmCode.equals(submittedConfirmCode)) {
            if (validate(updatedEmail)) {
                try {
                    userService.updateUserEmail(user.getEmail(), updatedEmail);
                    user.setEmail(updatedEmail);
                } catch (ServiceException e) {
                    logger.error("Exception in change email command", e);
                    throw new CommandException("Exception in change email command", e);
                }
            }
        }
    }

    private boolean validate(String updatedEmail) {
        InputDataValidator inputDataValidator = InputDataValidatorImpl.getInstance();
        return inputDataValidator.isCorrectEmail(updatedEmail);
    }
}
