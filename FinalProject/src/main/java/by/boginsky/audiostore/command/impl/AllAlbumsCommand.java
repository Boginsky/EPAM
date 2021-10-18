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
import java.util.List;

import static by.boginsky.audiostore.util.constants.Constant.ALL_ALBUMS;
import static by.boginsky.audiostore.util.constants.Constant.PAGE_ID;


public class AllAlbumsCommand implements Command {

    private static final Long START_PAGE = 1L;

    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        List<Album> listOfAlbums;
        AlbumService albumService = AlbumServiceImpl.getInstance();
        String id = httpServletRequest.getParameter(PAGE_ID);
        Long pageId = getPageId(id);

        try {
            listOfAlbums = albumService.findAllAlbums(pageId);
            httpServletRequest.setAttribute(PAGE_ID, ++pageId);
            httpServletRequest.setAttribute(ALL_ALBUMS, listOfAlbums);
        } catch (ServiceException e) {
            logger.error("Exception in all albums command", e);
            throw new CommandException("Exception in all albums command", e);
        }
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_ALL_ALBUMS));
        return router;
    }

    private Long getPageId(String id) {
        if (id == null)
            return START_PAGE;
        else
            return Long.parseLong(id);
    }
}
