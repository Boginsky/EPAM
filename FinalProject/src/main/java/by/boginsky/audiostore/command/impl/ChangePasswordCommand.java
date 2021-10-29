package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.model.service.impl.UserServiceImpl;
import by.boginsky.audiostore.util.PasswordEncryption;
import by.boginsky.audiostore.util.validator.InputDataValidator;
import by.boginsky.audiostore.util.validator.impl.InputDataValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.boginsky.audiostore.util.constants.Constant.*;

/**
 * The type Change password command.
 */
public class ChangePasswordCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        String enteredPassword = httpServletRequest.getParameter(ENTERED_PASSWORD);
        String updatedPassword = httpServletRequest.getParameter(UPDATED_PASSWORD);
        String submittedPassword = httpServletRequest.getParameter(SUBMITTED_PASSWORD);
        changePassword(user, enteredPassword, updatedPassword, submittedPassword);
        CabinetCommand cabinetCommand = new CabinetCommand();
        Router router = cabinetCommand.execute(httpServletRequest);
        return router;
    }

    private void changePassword(User user, String enteredPassword, String updatedPassword, String submittedPassword) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        if (validate(enteredPassword, updatedPassword, submittedPassword)) {
            try {
                String originalPassword = userService.findPasswordByUserId(user.getId());
                enteredPassword = PasswordEncryption.encryptsPassword(enteredPassword);
                if (originalPassword.equals(enteredPassword)) {
                    submittedPassword = PasswordEncryption.encryptsPassword(submittedPassword);
                    userService.updateUserPassword(submittedPassword, user.getId());
                }
            } catch (ServiceException e) {
                logger.error("Exception in change password command", e);
                throw new CommandException("Exception in change password command", e);
            }
        }
    }

    private boolean validate(String enteredPassword, String updatedPassword, String submittedPassword) {
        InputDataValidator inputDataValidator = InputDataValidatorImpl.getInstance();
        return inputDataValidator.isCorrectPassword(enteredPassword)
                & inputDataValidator.isCorrectPassword(updatedPassword)
                & inputDataValidator.isCorrectPassword(submittedPassword)
                & inputDataValidator.arePasswordsEqual(updatedPassword, submittedPassword);
    }
}