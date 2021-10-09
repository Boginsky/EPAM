package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Album;
import by.boginsky.audiostore.model.entity.audio.Comment;
import by.boginsky.audiostore.model.entity.audio.Song;
import by.boginsky.audiostore.model.service.AlbumService;
import by.boginsky.audiostore.model.service.CommentService;
import by.boginsky.audiostore.model.service.SongService;
import by.boginsky.audiostore.model.service.impl.AlbumServiceImpl;
import by.boginsky.audiostore.model.service.impl.CommentServiceImpl;
import by.boginsky.audiostore.model.service.impl.SongServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static by.boginsky.audiostore.util.constants.Attribute.*;

public class AllAlbumSongCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        SongService songService = SongServiceImpl.getInstance();
        AlbumService albumService = AlbumServiceImpl.getInstance();
        CommentService commentService = CommentServiceImpl.getInstance();
        Long albumId = Long.parseLong(httpServletRequest.getParameter(ALBUM_ID));
        List<Song> listOfSongsForAlbum;
        List<Comment> listOfComments;
        Optional<Album> optionalAlbum;
        String page;
        try {
            listOfComments = commentService.findCommentsByAlbumId(albumId);
            listOfSongsForAlbum = songService.findSongByAlbumId(albumId);
            optionalAlbum = albumService.findAlbumById(albumId);
            if (optionalAlbum.isPresent()) {
                Album album = optionalAlbum.get();
                httpServletRequest.setAttribute(ALBUM, album);
                httpServletRequest.setAttribute(LIST_OF_SONGS_FOR_ALBUM, listOfSongsForAlbum);
                httpServletRequest.setAttribute(COMMENTS,listOfComments);
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_ALBUM);
            } else {
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_ALL_ALBUMS);
            }
        } catch (ServiceException e) {
            throw new CommandException("Exception in allAlbumSong command", e);
        }
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }
}
