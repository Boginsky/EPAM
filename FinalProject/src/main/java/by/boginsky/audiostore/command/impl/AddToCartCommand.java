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
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static by.boginsky.audiostore.util.constants.Constant.*;

/**
 * The type Add to cart command.
 */
public class AddToCartCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        String page = getString(httpServletRequest, httpSession, user);
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }

    private String getString(HttpServletRequest httpServletRequest, HttpSession httpSession, User user) throws CommandException {
        String page;
        if (user == null) {
            page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
        } else {
            if (user.getUserRole() == User.UserRole.USER) {
                Set<Song> listOfSongsInCart = (Set<Song>) httpSession.getAttribute(LIST_OF_SONGS_IN_CART);
                SongService songService = SongServiceImpl.getInstance();
                Optional<Song> foundSong;
                try {
                    Long audioId = Long.parseLong(httpServletRequest.getParameter(TRACK_ID));
                    foundSong = songService.findSongById(audioId);
                    if (listOfSongsInCart == null) {
                        listOfSongsInCart = new HashSet<>();
                    }
                    listOfSongsInCart.add(foundSong.get());
                } catch (ServiceException e) {
                    logger.error("Exception in add to cart command", e);
                    throw new CommandException("Exception in add to cart command", e);
                }
                httpSession.setAttribute(LIST_OF_SONGS_IN_CART, listOfSongsInCart);
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_CART);
            } else {
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_WELCOME);
            }
        }
        return page;
    }
}