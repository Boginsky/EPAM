package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Album;
import by.boginsky.audiostore.model.entity.audio.Author;
import by.boginsky.audiostore.model.service.AlbumService;
import by.boginsky.audiostore.model.service.AuthorService;
import by.boginsky.audiostore.model.service.impl.AlbumServiceImpl;
import by.boginsky.audiostore.model.service.impl.AuthorServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static by.boginsky.audiostore.util.constants.Constant.*;

public class AlbumsForAuthorCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        AlbumService albumService = AlbumServiceImpl.getInstance();
        AuthorService authorService = AuthorServiceImpl.getInstance();
        Long authorId = Long.parseLong(httpServletRequest.getParameter(AUTHOR_ID));
        String page = getPage(httpServletRequest, albumService, authorService, authorId);
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }

    private String getPage(HttpServletRequest httpServletRequest, AlbumService albumService, AuthorService authorService, Long authorId) throws CommandException {
        List<Album> listOfAlbums;
        Optional<Author> optionalAuthor;
        String page;
        try {
            listOfAlbums = albumService.findByAuthor(authorId);
            optionalAuthor = authorService.findById(authorId);
            if (optionalAuthor.isPresent()) {
                Author author = optionalAuthor.get();
                httpServletRequest.setAttribute(AUTHOR, author);
                httpServletRequest.setAttribute(LIST_OF_ALBUMS_FOR_AUTHOR, listOfAlbums);
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_AUTHORS_ALBUMS);
            } else {
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_ALL_AUTHORS);
            }
        } catch (ServiceException e) {
            logger.error("Exception in albums for author command", e);
            throw new CommandException("Exception in albums for author command", e);
        }
        return page;
    }
}
