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

/**
 * The type Change comment command.
 */
public class ChangeCommentCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        Long commentId = Long.parseLong(httpServletRequest.getParameter(COMMENT_ID));
        String updatedComment = httpServletRequest.getParameter(UPDATED_COMMENT);
        updateComment(commentId, updatedComment);

        CabinetCommand cabinetCommand = new CabinetCommand();
        Router router = cabinetCommand.execute(httpServletRequest);
        return router;
    }

    private void updateComment(Long commentId, String updatedComment) throws CommandException {
        CommentService commentService = CommentServiceImpl.getInstance();
        try {
            commentService.updatedComment(updatedComment, commentId);
        } catch (ServiceException e) {
            logger.error("Exception in updating comment command", e);
            throw new CommandException("Exception in updating comment command", e);
        }
    }
}