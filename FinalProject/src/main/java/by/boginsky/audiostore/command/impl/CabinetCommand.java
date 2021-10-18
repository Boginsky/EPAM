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
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.model.service.impl.CommentServiceImpl;
import by.boginsky.audiostore.model.service.impl.OrderServiceImpl;
import by.boginsky.audiostore.model.service.impl.UserServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.boginsky.audiostore.util.constants.Constant.*;


public class CabinetCommand implements Command {

    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {

        OrderService orderService = OrderServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        CommentService commentService = CommentServiceImpl.getInstance();
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);

        String page = getPage(httpServletRequest, orderService, userService, commentService, user);
        try { // FIXME: 17.10.2021 
            user = userService.findUserByEmail(user.getEmail());
        } catch (ServiceException e) {
            throw new CommandException("Exception in cabinet command", e);
        }
        httpSession.setAttribute(USER, user);
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }

    private String getPage(HttpServletRequest httpServletRequest, OrderService orderService, UserService userService, CommentService commentService, User user) throws CommandException {
        String page;
        if (user == null) {
            page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_LOGIN);
        } else {
            if (user.getUserRole() == User.UserRole.ADMIN) {
                try {
                    List<User> listOfUsers = userService.findAllUsers();
                    httpServletRequest.setAttribute(USERS, listOfUsers);
                } catch (ServiceException e) {
                    logger.error("Exception in cabinet command", e);
                    throw new CommandException("Exception in cabinet command", e);
                }
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_ADMIN);
            } else {
                try {
                    List<Order> listOfOrders = orderService.findAllByUserId(user.getId());
                    List<Comment> listOfComments = commentService.findCommentsByUserId(user.getId());
                    httpServletRequest.setAttribute(ORDERS, listOfOrders);
                    httpServletRequest.setAttribute(COMMENTS, listOfComments);
                } catch (ServiceException e) {
                    logger.error("Exception in cabinet command", e);
                    throw new CommandException("Exception in cabinet command", e);
                }
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_USER);
            }
        }
        return page;
    }
}
