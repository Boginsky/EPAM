package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Album;
import by.boginsky.audiostore.model.entity.audio.Author;
import by.boginsky.audiostore.model.entity.audio.Genre;
import by.boginsky.audiostore.model.service.AlbumService;
import by.boginsky.audiostore.model.service.AuthorService;
import by.boginsky.audiostore.model.service.GenreService;
import by.boginsky.audiostore.model.service.impl.AlbumServiceImpl;
import by.boginsky.audiostore.model.service.impl.AuthorServiceImpl;
import by.boginsky.audiostore.model.service.impl.GenreServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.boginsky.audiostore.util.constants.Constant.*;

/**
 * The type Add song command.
 */
public class AddSongCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        getLists(httpServletRequest);
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_ADD_SONG));
        return router;
    }

    private void getLists(HttpServletRequest httpServletRequest) throws CommandException {
        List<Author> listOfAuthors;
        List<Album> listOfAlbums;
        List<Genre> listOfGenres;
        AlbumService albumService = AlbumServiceImpl.getInstance();
        AuthorService authorService = AuthorServiceImpl.getInstance();
        GenreService genreService = GenreServiceImpl.getInstance();
        try {
            listOfAuthors = authorService.findAllAuthors();
            listOfGenres = genreService.findAllGenres();
            listOfAlbums = albumService.findAllAlbums();
        } catch (ServiceException e) {
            logger.error("Exception in add song command", e);
            throw new CommandException("Exception in add song command", e);
        }
        httpServletRequest.setAttribute(LIST_OF_AUTHORS, listOfAuthors);
        httpServletRequest.setAttribute(LIST_OF_GENRES, listOfGenres);
        httpServletRequest.setAttribute(LIST_OF_ALBUMS, listOfAlbums);
    }
}
