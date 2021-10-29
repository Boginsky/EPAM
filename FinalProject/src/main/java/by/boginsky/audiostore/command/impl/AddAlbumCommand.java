package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.AlbumService;
import by.boginsky.audiostore.model.service.ImageUpdateService;
import by.boginsky.audiostore.model.service.impl.AlbumServiceImpl;
import by.boginsky.audiostore.model.service.impl.ImageUpdateServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

import static by.boginsky.audiostore.util.constants.Constant.*;

/**
 * The type Add album command.
 */
public class AddAlbumCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        String target = httpServletRequest.getParameter(TARGET);
        String albumName = httpServletRequest.getParameter(ALBUM_NAME);
        String albumInfo = httpServletRequest.getParameter(ALBUM_INFO);
        if (user.getUserRole() == User.UserRole.ADMIN) {
            addAlbum(httpServletRequest, target, albumName, albumInfo);
        }
        Router router = new Router();
        router.setRedirect();
        return router;
    }

    private void addAlbum(HttpServletRequest httpServletRequest, String target, String albumName, String albumInfo) throws CommandException {
        AlbumService albumService = AlbumServiceImpl.getInstance();
        try {
            Part part = httpServletRequest.getPart(FILE);
            String fileName = part.getSubmittedFileName();
            Long albumId = albumService.addNewAlbum(albumName, albumInfo);
            if (fileName != "") {
                ImageUpdateService imageUpdateService = ImageUpdateServiceImpl.getInstance();
                InputStream is = part.getInputStream();
                imageUpdateService.updatePhoto(is, target, albumId, fileName);
            }
        } catch (ServiceException | IOException | ServletException e) {
            logger.error("Exception in add album command", e);
            throw new CommandException("Exception in add album command", e);
        }
    }
}
