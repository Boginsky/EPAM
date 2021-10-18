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


public class ChangeNameCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        String updatedFirstName = httpServletRequest.getParameter(FIRST_NAME);
        String updatedLastName = httpServletRequest.getParameter(LAST_NAME);

        updateName(user, updatedFirstName, updatedLastName);

        CabinetCommand cabinetCommand = new CabinetCommand();
        Router router = cabinetCommand.execute(httpServletRequest);
        return router;
    }

    private void updateName(User user, String updatedFirstName, String updatedLastName) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        if (validate(updatedFirstName, updatedLastName)) {
            try {
                userService.updateUserName(updatedFirstName, updatedLastName, user.getId());
            } catch (ServiceException e) {
                logger.error("Exception in change name command", e);
                throw new CommandException("Exception in change name command", e);
            }
        }
    }

    private boolean validate(String updatedFirstName, String updatedLastName) {
        InputDataValidator inputDataValidator = InputDataValidatorImpl.getInstance();
        return inputDataValidator.isCorrectUserName(updatedFirstName) & inputDataValidator.isCorrectUserName(updatedLastName);
    }
}
