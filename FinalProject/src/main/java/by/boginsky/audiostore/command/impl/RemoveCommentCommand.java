package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.service.CommentService;
import by.boginsky.audiostore.model.service.impl.CommentServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static by.boginsky.audiostore.util.constants.Constant.COMMENT_ID;

public class RemoveCommentCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        CommentService commentService = CommentServiceImpl.getInstance();
        Long commentId = Long.parseLong(httpServletRequest.getParameter(COMMENT_ID));

        removeComment(commentService, commentId);

        AllSongsForAlbumCommand allSongsForAlbumCommand = new AllSongsForAlbumCommand();
        Router router = allSongsForAlbumCommand.execute(httpServletRequest);
        return router;
    }

    private void removeComment(CommentService commentService, Long commentId) throws CommandException {
        try {
            commentService.removeComment(commentId);
        } catch (ServiceException e) {
            logger.error("Exception in removing comment command", e);
            throw new CommandException("Exception in removing comment command", e);
        }
    }
}
