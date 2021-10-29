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
import java.math.BigDecimal;

import static by.boginsky.audiostore.util.constants.Constant.*;

/**
 * The type Change song confirm command.
 */
public class ChangeSongConfirmCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        String songTitle = httpServletRequest.getParameter(SONG_TITLE);
        BigDecimal songPrice = BigDecimal.valueOf(Long.parseLong(httpServletRequest.getParameter(SONG_PRICE)));
        Long songIdForUpdate = Long.parseLong(httpServletRequest.getParameter(SONG_ID));
        Long genreId = Long.parseLong(httpServletRequest.getParameter(GENRE_ID));
        Long albumId = Long.parseLong(httpServletRequest.getParameter(ALBUM_ID));
        Long authorId = Long.parseLong(httpServletRequest.getParameter(AUTHOR_ID));
        if (user.getUserRole() == User.UserRole.ADMIN) {
            updateSong(songTitle, songPrice, songIdForUpdate, genreId, albumId, authorId);
        }
        AllAlbumsCommand allAlbumsCommand = new AllAlbumsCommand();
        Router router = allAlbumsCommand.execute(httpServletRequest);
        return router;
    }

    private void updateSong(String songTitle, BigDecimal songPrice, Long songIdForUpdate, Long genreId, Long albumId, Long authorId) throws CommandException {
        SongService songService = SongServiceImpl.getInstance();
        try {
            songService.updateSong(songTitle, songPrice, authorId, genreId, albumId, songIdForUpdate);
        } catch (ServiceException e) {
            logger.error("Exception in add song confirm command", e);
            throw new CommandException("Exception in add song confirm command", e);
        }
    }
}
