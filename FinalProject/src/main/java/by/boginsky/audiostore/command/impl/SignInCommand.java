package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.SongService;
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.model.service.impl.SongServiceImpl;
import by.boginsky.audiostore.model.service.impl.UserServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;
import by.boginsky.audiostore.util.validator.InputDataValidator;
import by.boginsky.audiostore.util.validator.impl.InputDataValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static by.boginsky.audiostore.util.constants.Attribute.*;
import static by.boginsky.audiostore.util.constants.Parameter.EMAIL;
import static by.boginsky.audiostore.util.constants.Parameter.PASSWORD;


public class SignInCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        httpServletRequest.setAttribute(ERROR_SIGN_IN_MESSAGE, null); // FIXME: 12.09.2021

        String email = httpServletRequest.getParameter(EMAIL);
        String password = httpServletRequest.getParameter(PASSWORD);

        UserService userService = UserServiceImpl.getInstance();
        SongService songService = SongServiceImpl.getInstance();

        try {
            String page = getPage(httpSession, email, password, userService, songService);
            Router router = new Router();
            router.setPagePath(page);
            return router;
        } catch (ServiceException e) {
            throw new CommandException("Exception in signIn command", e);
        }
    }

    private String getPage(HttpSession httpSession, String email, String password, UserService userService, SongService songService) throws ServiceException {
        String page;
        if (validate(email, password)) {
            Optional<User> optionalUser = userService.findUserByEmailAndPassword(email, password);
            List<String> listOfSongImgUrl = songService.findSongImg();
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_WELCOME);
                httpSession.setAttribute(LIST_OF_SONGS_IMG_URL, listOfSongImgUrl);
                httpSession.setAttribute(ROLE, user.getUserRole());
                httpSession.setAttribute(EMAIL, user.getEmail());
                httpSession.setAttribute(USER, user);
                httpSession.setAttribute(PASSWORD, password);
            } else {
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
            }
        } else {
            page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
        }
        return page;
    }

    private boolean validate(String email, String password) {
        InputDataValidator inputDataValidator = InputDataValidatorImpl.getInstance();
        return inputDataValidator.isCorrectEmail(email) && inputDataValidator.isCorrectPassword(password);
    }
}