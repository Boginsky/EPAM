package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Song;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.SongService;
import by.boginsky.audiostore.model.service.impl.SongServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.boginsky.audiostore.util.constants.Constant.*;

public class FindOrderCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        SongService songService = SongServiceImpl.getInstance();
        Long orderId = Long.parseLong(httpServletRequest.getParameter(ORDER_ID));

        findOrder(httpServletRequest, user, songService, orderId);

        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_ORDER));
        return router;
    }

    private void findOrder(HttpServletRequest httpServletRequest, User user, SongService songService, Long orderId) throws CommandException {
        List<Song> listOfSongs;
        try {
            listOfSongs = songService.findSongByOrderId(user.getId());
            httpServletRequest.setAttribute(LIST_OF_SONGS_FOR_ORDER, listOfSongs);
            httpServletRequest.setAttribute(ORDER_ID, orderId);
        } catch (ServiceException e) {
            logger.error("Exception in find order command", e);
            throw new CommandException("Exception in find order command", e);
        }
    }
}
