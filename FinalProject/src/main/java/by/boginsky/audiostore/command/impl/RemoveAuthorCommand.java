package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.AuthorService;
import by.boginsky.audiostore.model.service.impl.AuthorServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.boginsky.audiostore.util.constants.Constant.AUTHOR_ID;
import static by.boginsky.audiostore.util.constants.Constant.USER;

/**
 * The type Remove author command.
 */
public class RemoveAuthorCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        Long authorId = Long.parseLong(httpServletRequest.getParameter(AUTHOR_ID));
        if (user.getUserRole() == User.UserRole.ADMIN) {
            removeAuthor(authorId);
        }
        AllAuthorsCommand allAuthorsCommand = new AllAuthorsCommand();
        Router router = allAuthorsCommand.execute(httpServletRequest);
        return router;
    }

    private void removeAuthor(Long authorId) throws CommandException {
        AuthorService authorService = AuthorServiceImpl.getInstance();
        try {
            authorService.removeAuthor(authorId);
        } catch (ServiceException e) {
            logger.error("Exception in command remove author command", e);
            throw new CommandException("Exception in command remove author command", e);
        }
    }
}
