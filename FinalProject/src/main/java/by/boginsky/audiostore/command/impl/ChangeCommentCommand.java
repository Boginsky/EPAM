package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.service.CommentService;
import by.boginsky.audiostore.model.service.impl.CommentServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static by.boginsky.audiostore.util.constants.Constant.COMMENT_ID;
import static by.boginsky.audiostore.util.constants.Constant.UPDATED_COMMENT;

public class ChangeCommentCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        CommentService commentService = CommentServiceImpl.getInstance();
        Long commentId = Long.parseLong(httpServletRequest.getParameter(COMMENT_ID));
        String updatedComment = httpServletRequest.getParameter(UPDATED_COMMENT);

        try {
            commentService.updatedComment(updatedComment, commentId);
        } catch (ServiceException e) {
            throw new CommandException("Exception in updating comment command", e);
        }

        AllSongsForAlbumCommand allSongsForAlbumCommand = new AllSongsForAlbumCommand();
        Router router = allSongsForAlbumCommand.execute(httpServletRequest);
        return router;
    }
}