package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Song;
import by.boginsky.audiostore.model.service.SongService;
import by.boginsky.audiostore.model.service.impl.SongServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.boginsky.audiostore.util.constants.Constant.ALL_SONGS;

public class AllSongsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        SongService songService = SongServiceImpl.getInstance();
        try {
            List<Song> listOfSongs = songService.findAllSongs();
            httpServletRequest.setAttribute(ALL_SONGS, listOfSongs);
        } catch (ServiceException e) {
            throw new CommandException("Exception in main command", e);
        }
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_ALL_SONGS));
        return router;
    }
}
