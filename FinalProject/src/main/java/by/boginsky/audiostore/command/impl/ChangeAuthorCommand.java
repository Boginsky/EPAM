package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.AuthorService;
import by.boginsky.audiostore.model.service.ImageUpdateService;
import by.boginsky.audiostore.model.service.impl.AuthorServiceImpl;
import by.boginsky.audiostore.model.service.impl.ImageUpdateServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

import static by.boginsky.audiostore.util.constants.Constant.*;

/**
 * The type Change author command.
 */
public class ChangeAuthorCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        Long authorId = Long.parseLong(httpServletRequest.getParameter(AUTHOR_ID));
        String target = httpServletRequest.getParameter(TARGET);
        String authorName = httpServletRequest.getParameter(AUTHOR_NAME);
        String authorInfo = httpServletRequest.getParameter(AUTHOR_INFO);
        if (user.getUserRole().equals(User.UserRole.ADMIN)) {
            updateAuthor(httpServletRequest, authorId, target, authorName, authorInfo);
        }
        AllAuthorsCommand allAuthorsCommand = new AllAuthorsCommand();
        Router router = allAuthorsCommand.execute(httpServletRequest);
        return router;
    }

    private void updateAuthor(HttpServletRequest httpServletRequest, Long authorId, String target, String authorName, String authorInfo) throws CommandException {
        ImageUpdateService imageUpdateService = ImageUpdateServiceImpl.getInstance();
        AuthorService authorService = AuthorServiceImpl.getInstance();

        try {
            Part part = httpServletRequest.getPart(FILE);
            String fileName = part.getSubmittedFileName();
            authorService.updateAuthor(authorId, authorName, authorInfo);
            if (fileName != "") {
                InputStream is = part.getInputStream();
                imageUpdateService.updatePhoto(is, target, authorId, fileName);
            }
        } catch (ServiceException | IOException | ServletException e) {
            logger.error("Exception in change author command", e);
            throw new CommandException("Exception in change author command", e);
        }
    }
}
