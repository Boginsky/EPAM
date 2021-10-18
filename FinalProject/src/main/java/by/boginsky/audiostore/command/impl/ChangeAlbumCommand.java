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

public class ChangeAlbumCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        Long albumId = Long.parseLong(httpServletRequest.getParameter(ALBUM_ID));
        String target = httpServletRequest.getParameter(TARGET);
        String albumName = httpServletRequest.getParameter(ALBUM_NAME);
        String albumInfo = httpServletRequest.getParameter(ALBUM_INFO);
        if (user.getUserRole() == User.UserRole.ADMIN) {
            updateAlbum(httpServletRequest, albumId, target, albumName, albumInfo);
        }
        AllAlbumsCommand allAlbumsCommand = new AllAlbumsCommand();
        Router router = allAlbumsCommand.execute(httpServletRequest);
        return router;
    }

    private void updateAlbum(HttpServletRequest httpServletRequest, Long albumId, String target, String albumName, String albumInfo) throws CommandException {
        ImageUpdateService imageUpdateService = ImageUpdateServiceImpl.getInstance();
        AlbumService albumService = AlbumServiceImpl.getInstance();

        try {
            Part part = httpServletRequest.getPart(FILE);
            String fileName = part.getSubmittedFileName();
            albumService.updateAlbum(albumId, albumName, albumInfo);
            if (fileName != "") {
                InputStream is = part.getInputStream();
                imageUpdateService.updatePhoto(is, target, albumId, fileName);
            }
        } catch (ServiceException | IOException | ServletException e) {
            logger.error("Exception in change album command", e);
            throw new CommandException("Exception in change album command", e);
        }
    }
}
