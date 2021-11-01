package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Song;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.OrderService;
import by.boginsky.audiostore.model.service.impl.OrderServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.MessageManager;
import by.boginsky.audiostore.util.constants.Message;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Set;

import static by.boginsky.audiostore.util.constants.Constant.*;


/**
 * The type Submit order command.
 */
public class SubmitOrderCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        Set<Song> listOfSongsInCart = (Set<Song>) httpSession.getAttribute(LIST_OF_SONGS_IN_CART);
        User user = (User) httpSession.getAttribute(USER);
        BigDecimal moneyForPayment = BigDecimal.valueOf(Long.parseLong(httpServletRequest.getParameter(MONEY_FOR_PAYMENT)));
        BigDecimal bonusForPayment = BigDecimal.valueOf(Long.parseLong(httpServletRequest.getParameter(BONUS_FOR_PAYMENT)));
        BigDecimal totalPrice = BigDecimal.valueOf(Long.parseLong(httpServletRequest.getParameter(TOTAL_PRICE)));
        Router router = new Router();
        MessageManager messageManager = MessageManager.defineLocale((String) httpSession.getAttribute(CHANGE_LANGUAGE));
        if (submitOrder(httpSession, listOfSongsInCart, user, moneyForPayment, bonusForPayment, totalPrice)) {
            router.setRedirect();
        } else {
            httpServletRequest.setAttribute(NOT_ENOUGH_MONEY, messageManager.getMessage(Message.PATH_ERROR_NOT_ENOUGH_MONEY));
            router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_CART));
        }
        return router;
    }

    private boolean submitOrder(HttpSession httpSession, Set<Song> listOfSongsInCart, User user, BigDecimal moneyForPayment, BigDecimal bonusForPayment, BigDecimal totalPrice) throws CommandException {
        if (moneyForPayment.add(bonusForPayment).compareTo(totalPrice) >= 0) {
            if (listOfSongsInCart.size() != 0) {
                OrderService orderService = OrderServiceImpl.getInstance();
                try {
                    orderService.addNewOrder(user, moneyForPayment, bonusForPayment, totalPrice, listOfSongsInCart);
                    httpSession.removeAttribute(LIST_OF_SONGS_IN_CART);
                    return true;
                } catch (ServiceException e) {
                    logger.error("Exception in submit order command", e);
                    throw new CommandException("Exception in submit order command", e);
                }
            }
        }
        return false;
    }
}
