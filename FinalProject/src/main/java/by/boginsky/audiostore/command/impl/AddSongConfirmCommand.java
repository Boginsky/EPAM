package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.SongService;
import by.boginsky.audiostore.model.service.impl.SongServiceImpl;
import by.boginsky.audiostore.util.validator.InputDataValidator;
import by.boginsky.audiostore.util.validator.impl.InputDataValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import static by.boginsky.audiostore.util.constants.Constant.*;

/**
 * The type Add song confirm command.
 */
public class AddSongConfirmCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        String songTitle = httpServletRequest.getParameter(SONG_TITLE);
        BigDecimal songPrice = BigDecimal.valueOf(Long.parseLong(httpServletRequest.getParameter(SONG_PRICE)));
        Long genreId = Long.parseLong(httpServletRequest.getParameter(GENRE_ID));
        Long albumId = Long.parseLong(httpServletRequest.getParameter(ALBUM_ID));
        Long authorId = Long.parseLong(httpServletRequest.getParameter(AUTHOR_ID));
        if (user.getUserRole() == User.UserRole.ADMIN) {
            addSong(songTitle, songPrice, genreId, albumId, authorId);
        }
        Router router = new Router();
        router.setRedirect();
        return router;
    }

    private void addSong(String songTitle, BigDecimal songPrice, Long genreId, Long albumId, Long authorId) throws CommandException {
        SongService songService = SongServiceImpl.getInstance();
        if (validate(songPrice)) {
            try {
                songService.addNewSong(songTitle, songPrice, authorId, genreId, albumId);
            } catch (ServiceException e) {
                logger.error("Exception in add song confirm command", e);
                throw new CommandException("Exception in add song confirm command", e);
            }
        }
    }

    private boolean validate(BigDecimal price) {
        InputDataValidator inputDataValidator = InputDataValidatorImpl.getInstance();
        return inputDataValidator.isCorrectPrice(price);
    }
}
