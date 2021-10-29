package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.SongService;
import by.boginsky.audiostore.model.service.impl.SongServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.boginsky.audiostore.util.constants.Constant.SONG_ID;
import static by.boginsky.audiostore.util.constants.Constant.USER;

/**
 * The type Remove song command.
 */
public class RemoveSongCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        Long songId = Long.parseLong(httpServletRequest.getParameter(SONG_ID));
        if (user.getUserRole() == User.UserRole.ADMIN) {
            removeSongCommand(songId);
        }
        AllAlbumsCommand allAlbumsCommand = new AllAlbumsCommand();
        Router router = allAlbumsCommand.execute(httpServletRequest);
        return router;
    }

    private void removeSongCommand(Long songId) throws CommandException {
        SongService songService = SongServiceImpl.getInstance();
        try {
            songService.removeSong(songId);
        } catch (ServiceException e) {
            logger.error("Exception in remove song command", e);
            throw new CommandException("Exception in remove song command", e);
        }
    }
}
