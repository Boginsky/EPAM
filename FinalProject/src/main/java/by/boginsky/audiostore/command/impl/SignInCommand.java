package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.AlbumService;
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.model.service.impl.AlbumServiceImpl;
import by.boginsky.audiostore.model.service.impl.UserServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.MessageManager;
import by.boginsky.audiostore.util.constants.Message;
import by.boginsky.audiostore.util.constants.PathPage;
import by.boginsky.audiostore.util.validator.InputDataValidator;
import by.boginsky.audiostore.util.validator.impl.InputDataValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static by.boginsky.audiostore.util.constants.Constant.*;


/**
 * The type Sign in command.
 */
public class SignInCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession(true);
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(CHANGE_LANGUAGE));
        User user = (User) httpSession.getAttribute(USER);
        String email = httpServletRequest.getParameter(EMAIL);
        String password = httpServletRequest.getParameter(PASSWORD);
        String page;
        if (user == null) {
            page = getPage(httpServletRequest, httpSession, messageManager, email, password);
        } else {
            page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
            httpServletRequest.setAttribute(ERROR_SIGN_IN_MESSAGE, messageManager.getMessage(Message.PATH_ERROR_SIGN_IN_ALREADY_IN_SYSTEM));
        }
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }

    private String getPage(HttpServletRequest httpServletRequest, HttpSession httpSession, MessageManager messageManager, String email, String password) throws CommandException {
        String page;
        UserService userService = UserServiceImpl.getInstance();
        AlbumService albumService = AlbumServiceImpl.getInstance();
        try {
            if (validate(email, password)) {
                Optional<User> optionalUser = userService.findUserByEmailAndPassword(email, password);
                List<String> listOfAlbumImgUrl = albumService.findAlbumImg();
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    if (user.getUserStatus().equals(User.UserStatus.ACTIVE)) {
                        page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_WELCOME);
                        httpSession.setAttribute(LIST_OF_ALBUMS_IMG_URL, listOfAlbumImgUrl);
                        httpSession.setAttribute(USER, user);
                    } else {
                        page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
                        httpServletRequest.setAttribute(ERROR_SIGN_IN_MESSAGE, messageManager.getMessage(Message.PATH_ERROR_SIGN_IN_STATUS_BLOCKED));
                    }
                } else {
                    page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
                    httpServletRequest.setAttribute(ERROR_SIGN_IN_MESSAGE, messageManager.getMessage(Message.PATH_ERROR_SIGN_IN_WRONG_PASSWORD_OR_EMAIL));
                }
            } else {
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
                httpServletRequest.setAttribute(ERROR_SIGN_IN_MESSAGE, messageManager.getMessage(Message.PATH_ERROR_SIGN_IN_WRONG_PASSWORD_OR_EMAIL));
            }
        } catch (ServiceException e) {
            logger.error("Exception in signIn command", e);
            throw new CommandException("Exception in signIn command", e);
        }
        return page;
    }

    private boolean validate(String email, String password) {
        InputDataValidator inputDataValidator = InputDataValidatorImpl.getInstance();
        return inputDataValidator.isCorrectEmail(email) && inputDataValidator.isCorrectPassword(password);
    }
}
