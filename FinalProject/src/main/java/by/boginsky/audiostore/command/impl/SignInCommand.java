package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.model.service.impl.UserServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.MessageManager;
import by.boginsky.audiostore.util.constants.Message;
import by.boginsky.audiostore.util.constants.PathPage;
import by.boginsky.audiostore.util.validator.InputDataValidator;
import by.boginsky.audiostore.util.validator.impl.InputDataValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.boginsky.audiostore.util.constants.Attribute.*;
import static by.boginsky.audiostore.util.constants.Parameter.*;


public class SignInCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        MessageManager messageManager = MessageManager.defineLocale((String)httpSession.getAttribute(CHANGE_LANGUAGE));
        httpServletRequest.setAttribute(ERROR_SIGN_IN_MESSAGE, null);

        String email = httpServletRequest.getParameter(EMAIL);
        String password = httpServletRequest.getParameter(PASSWORD);

        UserService userService = UserServiceImpl.getInstance();
        InputDataValidator inputDataValidator = InputDataValidatorImpl.getInstance();
        String page;
        try {
            if (inputDataValidator.isCorrectEmail(email) && inputDataValidator.isCorrectPassword(password)) {
                Optional<User> optionalUser = userService.findUserByEmailAndPassword(email, password);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    if(user.getUserRole() == User.UserRole.USER){
                        page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_USER);
                    } else {
                        page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_ADMIN);
                    }
                    httpSession.setAttribute(ROLE, user.getUserRole());
                    httpSession.setAttribute(EMAIL, user.getEmail());
                    httpSession.setAttribute(USER, user);
                    httpSession.setAttribute(PASSWORD, password);
                } else {
                    page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
                    httpServletRequest.setAttribute(ERROR_SIGN_IN_MESSAGE, messageManager.getMessage(Message.PATH_ERROR_SIGN_IN_MESSAGE));
                }
            } else {
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
                httpServletRequest.setAttribute(ERROR_SIGN_IN_MESSAGE, messageManager.getMessage(Message.PATH_ERROR_SIGN_IN_MESSAGE));
            }
            Router router = new Router();
            router.setPagePath(page);
            return router;
        } catch (ServiceException e) {
            throw new CommandException("Exception in signIn command",e);
        }
    }
}
