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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.boginsky.audiostore.util.constants.Attribute.TRACK_ID;

public class AddToCartCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        Router router = new Router();
        HttpSession httpSession = httpServletRequest.getSession();
        Long audioId = Long.parseLong(httpServletRequest.getParameter(TRACK_ID));
        List<Song> listOfSongsInCart = (List<Song>) httpSession.getAttribute("listOfSongsInCart");
        SongService songService = SongServiceImpl.getInstance();
        Optional<Song> foundSong = null;
        try {
            foundSong = songService.findSongById(audioId);
            if (listOfSongsInCart == null) {
                listOfSongsInCart = new ArrayList<>();
                listOfSongsInCart.add(foundSong.get());
            }else {
                for(Song item:listOfSongsInCart){
                    if(item.getId() != foundSong.get().getId()){
                        listOfSongsInCart.add(foundSong.get());
                    }
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        httpSession.setAttribute("listOfSongsInCart", listOfSongsInCart);
        router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_CART));
        return router;
    }
}