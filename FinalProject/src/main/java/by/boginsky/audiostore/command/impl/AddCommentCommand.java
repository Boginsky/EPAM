package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.CommentService;
import by.boginsky.audiostore.model.service.impl.CommentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.boginsky.audiostore.util.constants.Constant.*;

public class AddCommentCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {

        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);

        Long albumId = Long.parseLong(httpServletRequest.getParameter(ALBUM_ID));
        String comment = httpServletRequest.getParameter(COMMENT);
        if (user.getUserRole() == User.UserRole.USER) {
            addComment(user, albumId, comment);
        }
        AllSongsForAlbumCommand allAlbumSongCommand = new AllSongsForAlbumCommand();
        Router router = allAlbumSongCommand.execute(httpServletRequest);
        return router;
    }

    private void addComment(User user, Long albumId, String comment) throws CommandException {
        CommentService commentService = CommentServiceImpl.getInstance();
        try {
            commentService.insertNewComment(albumId, user.getId(), comment);
        } catch (ServiceException e) {
            logger.error("Exception in add comment command", e);
            throw new CommandException("Exception in add comment command", e);
        }
    }
}
