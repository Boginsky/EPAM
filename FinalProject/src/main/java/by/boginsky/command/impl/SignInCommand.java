package by.boginsky.command.impl;

import by.boginsky.command.Command;
import by.boginsky.controller.Router;
import by.boginsky.exception.CommandException;
import by.boginsky.exception.ServiceException;
import by.boginsky.model.entity.user.User;
import by.boginsky.service.UserService;
import by.boginsky.util.ConfigurationManager;
import by.boginsky.util.MessageManager;
import by.boginsky.util.constants.ConstantAttributes;
import by.boginsky.util.constants.ConstantMessages;
import by.boginsky.util.constants.ConstantPathPages;
import by.boginsky.validator.InputDataValidator;
import by.boginsky.validator.impl.InputDataValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignInCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        MessageManager messageManager = MessageManager.defineLocale((String)httpSession.getAttribute(ConstantAttributes.CHANGE_LANGUAGE));
        httpServletRequest.setAttribute(ConstantAttributes.ERROR_SIGN_IN_MESSAGE, null);

        String email = httpServletRequest.getParameter(ConstantAttributes.EMAIL);
        String password = httpServletRequest.getParameter(ConstantAttributes.PASSWORD);

        InputDataValidator inputDataValidator = new InputDataValidatorImpl();
        String page;
        try {
            if (inputDataValidator.isCorrectEmail(email) && inputDataValidator.isCorrectPassword(password)) {
                Optional<User> optionalUser = UserService.findUserByEmailAndPassword(email, password);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    if(user.getUserRole() == User.UserRole.USER){
                        page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_USER);
                    } else {
                        page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_MAIN_ADMIN);
                    }
                    httpSession.setAttribute(ConstantAttributes.ROLE, user.getUserRole());
                    httpSession.setAttribute(ConstantAttributes.EMAIL, user.getEmail());
                    httpSession.setAttribute(ConstantAttributes.USER, user);
                    httpSession.setAttribute(ConstantAttributes.PASSWORD, password);
                } else {
                    page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN);
                    httpServletRequest.setAttribute(ConstantAttributes.ERROR_SIGN_IN_MESSAGE, messageManager.getMessage(ConstantMessages.PATH_ERROR_SIGN_IN_MESSAGE));
                }
            } else {
                page = ConfigurationManager.getProperty(ConstantPathPages.PATH_PAGE_LOGIN);
                httpServletRequest.setAttribute(ConstantAttributes.ERROR_SIGN_IN_MESSAGE, messageManager.getMessage(ConstantMessages.PATH_ERROR_SIGN_IN_MESSAGE));
            }
            Router router = new Router();
            router.setPagePath(page);
            return router;
        } catch (ServiceException e) {
            throw new CommandException("Exception in signIn command",e);
        }
    }
}
