package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Comment;
import by.boginsky.audiostore.model.entity.user.Order;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.CommentService;
import by.boginsky.audiostore.model.service.OrderService;
import by.boginsky.audiostore.model.service.impl.CommentServiceImpl;
import by.boginsky.audiostore.model.service.impl.OrderServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.boginsky.audiostore.util.constants.Attribute.*;


public class CabinetCommand implements Command {

    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        OrderService orderService = OrderServiceImpl.getInstance();
        CommentService commentService = CommentServiceImpl.getInstance();
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        String page;
        if (user == null) {
            page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
        } else {
            if (user.getUserRole() == User.UserRole.ADMIN) {
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_ADMIN);
            } else if (user.getUserRole() == User.UserRole.USER) {
                try {
                    List<Order> listOfOrders = orderService.findAllByUserId(user.getId());
                    List<Comment> listOfComments = commentService.findCommentsByUserId(user.getId());
                    httpServletRequest.setAttribute(ORDERS, listOfOrders);
                    httpServletRequest.setAttribute(COMMENTS,listOfComments);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_USER);
            } else {
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
            }
        }
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }
}
