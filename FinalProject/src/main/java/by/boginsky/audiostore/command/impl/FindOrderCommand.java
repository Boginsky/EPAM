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

import static by.boginsky.audiostore.util.constants.Attribute.*;

public class FindOrderCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        SongService songService = SongServiceImpl.getInstance();
        Long orderId = Long.parseLong(httpServletRequest.getParameter(ORDER_ID));

        String page = getPage(httpServletRequest, songService,orderId, orderId);
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }

    private String getPage(HttpServletRequest httpServletRequest, SongService songService, Long userId,Long orderId) throws CommandException{
        List<Song> listOfSongs;
        String page;
        try {
            listOfSongs = songService.findSongByOrderId(userId);
            httpServletRequest.setAttribute(LIST_OF_SONGS_FOR_ORDER, listOfSongs);
            httpServletRequest.setAttribute(ORDER_ID,orderId);
            page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_ORDER);
        }catch (ServiceException e){
            throw new CommandException("Exception in find order command",e);
        }
        return page;
    }
}
