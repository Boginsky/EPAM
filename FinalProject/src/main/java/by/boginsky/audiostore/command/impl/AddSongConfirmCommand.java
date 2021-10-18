package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.ImageUpdateService;
import by.boginsky.audiostore.model.service.SongService;
import by.boginsky.audiostore.model.service.impl.ImageUpdateServiceImpl;
import by.boginsky.audiostore.model.service.impl.SongServiceImpl;
import by.boginsky.audiostore.util.validator.InputDataValidator;
import by.boginsky.audiostore.util.validator.impl.InputDataValidatorImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import static by.boginsky.audiostore.util.constants.Constant.*;

public class AddSongConfirmCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);

        String songTitle = httpServletRequest.getParameter(SONG_TITLE);
        BigDecimal songPrice = BigDecimal.valueOf(Long.parseLong(httpServletRequest.getParameter(SONG_PRICE)));
        String target = httpServletRequest.getParameter(TARGET);
        Long genreId = Long.parseLong(httpServletRequest.getParameter(GENRE_ID));
        Long albumId = Long.parseLong(httpServletRequest.getParameter(ALBUM_ID));
        Long authorId = Long.parseLong(httpServletRequest.getParameter(AUTHOR_ID));
        if (user.getUserRole() == User.UserRole.ADMIN) {
            addSong(httpServletRequest, songTitle, songPrice, target, genreId, albumId, authorId);
        }
        AllAlbumsCommand allAlbumsCommand = new AllAlbumsCommand();
        Router router = allAlbumsCommand.execute(httpServletRequest);
        return router;
    }

    private void addSong(HttpServletRequest httpServletRequest, String songTitle, BigDecimal songPrice, String target, Long genreId, Long albumId, Long authorId) throws CommandException {
        ImageUpdateService imageUpdateService = ImageUpdateServiceImpl.getInstance();
        SongService songService = SongServiceImpl.getInstance();
        if (validate(songPrice)) {
            try {
                Part part = httpServletRequest.getPart(FILE);
                String fileName = part.getSubmittedFileName();
                Long songId = songService.addNewSong(songTitle, songPrice, authorId, genreId, albumId);
                if (fileName != "") {
                    InputStream is = part.getInputStream();
                    imageUpdateService.updatePhoto(is, target, songId, fileName);
                }
            } catch (ServiceException | IOException | ServletException e) {
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