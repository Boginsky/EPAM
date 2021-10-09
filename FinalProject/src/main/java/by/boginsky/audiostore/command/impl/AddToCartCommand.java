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

import static by.boginsky.audiostore.util.constants.Attribute.*;

public class AddToCartCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        Router router = new Router();
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        String page;
        if (user == null) {
            page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
        } else {
            Long audioId = Long.parseLong(httpServletRequest.getParameter(TRACK_ID));
            Set<Song> listOfSongsInCart = (Set<Song>) httpSession.getAttribute("listOfSongsInCart");
            SongService songService = SongServiceImpl.getInstance();
            Optional<Song> foundSong = null;
            try {
                foundSong = songService.findSongById(audioId);
                if (listOfSongsInCart == null) {
                    listOfSongsInCart = new HashSet<>();
                    listOfSongsInCart.add(foundSong.get());
                } else {
                    listOfSongsInCart.add(foundSong.get());
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            httpSession.setAttribute(LIST_OF_SONGS_IN_CART, listOfSongsInCart);
            page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_CART);
            router.setPagePath(page);
        }
        return router;
    }
}