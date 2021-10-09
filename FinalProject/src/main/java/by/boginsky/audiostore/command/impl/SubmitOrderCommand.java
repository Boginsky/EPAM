package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Song;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.OrderService;
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.model.service.impl.OrderServiceImpl;
import by.boginsky.audiostore.model.service.impl.UserServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Set;

import static by.boginsky.audiostore.util.constants.Attribute.*;
import static by.boginsky.audiostore.util.constants.Parameter.*;


public class SubmitOrderCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        Set<Song> listOfSongsInCart = (Set<Song>) httpSession.getAttribute(LIST_OF_SONGS_IN_CART);
        String page;
        User user = (User) httpSession.getAttribute(USER);
        BigDecimal moneyForPayment = BigDecimal.valueOf(Long.parseLong(httpServletRequest.getParameter(MONEY_FOR_PAYMENT)));
        BigDecimal bonusForPayment = BigDecimal.valueOf(Long.parseLong(httpServletRequest.getParameter(BONUS_FOR_PAYMENT)));
        BigDecimal totalPrice = BigDecimal.valueOf(Long.parseLong(httpServletRequest.getParameter(TOTAL_PRICE)));
        if(listOfSongsInCart.size() != 0) {
            OrderService orderService = OrderServiceImpl.getInstance();
            UserService userService = UserServiceImpl.getInstance();
            try {
                orderService.addNewOrder(user,moneyForPayment,bonusForPayment,totalPrice,listOfSongsInCart);
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_USER);
                listOfSongsInCart.removeAll(listOfSongsInCart);
                user = userService.findUserByEmail(user.getEmail());
            } catch (ServiceException e) {
                throw new CommandException("Exception in submitOrder command", e);
            }
        }else {
            page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_WELCOME);
        }
        httpSession.setAttribute(USER, user);
        httpSession.setAttribute(LIST_OF_SONGS_IN_CART, listOfSongsInCart);
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }
}
