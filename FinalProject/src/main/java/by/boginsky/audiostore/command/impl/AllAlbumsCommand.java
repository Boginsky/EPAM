package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Album;
import by.boginsky.audiostore.model.service.AlbumService;
import by.boginsky.audiostore.model.service.impl.AlbumServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.boginsky.audiostore.util.constants.Attribute.ALL_ALBUMS;


public class AllAlbumsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        List<Album> listOfAlbums;
        AlbumService albumService = AlbumServiceImpl.getInstance();
        try {
            listOfAlbums = albumService.findAllAlbums();
            httpSession.setAttribute(ALL_ALBUMS, listOfAlbums);
        } catch (ServiceException e) {
            throw new CommandException("Exception in all albums command", e);
        }
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_ALL_ALBUMS));
        return router;
    }
}
