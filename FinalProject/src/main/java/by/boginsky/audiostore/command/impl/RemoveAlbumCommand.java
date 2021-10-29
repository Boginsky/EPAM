package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.AlbumService;
import by.boginsky.audiostore.model.service.impl.AlbumServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.boginsky.audiostore.util.constants.Constant.ALBUM_ID;
import static by.boginsky.audiostore.util.constants.Constant.USER;

/**
 * The type Remove album command.
 */
public class RemoveAlbumCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        if (user.getUserRole() == User.UserRole.ADMIN) {
            removeAlbum(httpServletRequest);
        }
        AllAlbumsCommand allAlbumsCommand = new AllAlbumsCommand();
        Router router = allAlbumsCommand.execute(httpServletRequest);
        return router;
    }

    private void removeAlbum(HttpServletRequest httpServletRequest) throws CommandException {
        Long albumId = Long.parseLong(httpServletRequest.getParameter(ALBUM_ID));
        AlbumService albumService = AlbumServiceImpl.getInstance();
        try {
            albumService.removeAlbum(albumId);
        } catch (ServiceException e) {
            logger.error("Exception in remove album command", e);
            throw new CommandException("Exception in remove album command", e);
        }
    }
}
