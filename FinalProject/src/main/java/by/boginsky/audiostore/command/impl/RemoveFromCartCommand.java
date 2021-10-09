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
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static by.boginsky.audiostore.util.constants.Attribute.TRACK_ID;

public class RemoveFromCartCommand implements Command {

    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        Set<Song> listOfSongsInCart = (Set<Song>) httpSession.getAttribute("listOfSongsInCart");
        Long audioId = Long.parseLong(httpServletRequest.getParameter(TRACK_ID));

        listOfSongsInCart = getSongs(listOfSongsInCart, audioId);

        httpSession.setAttribute("listOfSongsInCart", listOfSongsInCart);
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_CART));
        return router;
    }

    private Set<Song> getSongs(Set<Song> listOfSongsInCart, Long audioId) {
        SongService songService = SongServiceImpl.getInstance();
        if (listOfSongsInCart == null) {
            listOfSongsInCart = new HashSet<>();
        }
        try {
            Optional<Song> foundSong = songService.findSongById(audioId);
            listOfSongsInCart.remove(foundSong.get());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return listOfSongsInCart;
    }
}
