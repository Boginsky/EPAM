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

public class FindUserOrdersAndCommentsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        Long searchingUserId = Long.valueOf(httpServletRequest.getParameter(USER_ID));

        String page = findUserOrdersAndComments(httpServletRequest, user, searchingUserId);
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }

    private String findUserOrdersAndComments(HttpServletRequest httpServletRequest, User user, Long searchingUserId) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        OrderService orderService = OrderServiceImpl.getInstance();
        CommentService commentService = CommentServiceImpl.getInstance();
        List<Order> listOfOrders;
        List<Comment> listOfComments;
        User userFound;
        String page;
        try {
            if (user.getUserRole().equals(User.UserRole.ADMIN)) {
                userFound = userService.findUserById(searchingUserId);
                listOfOrders = orderService.findAllByUserId(searchingUserId);
                listOfComments = commentService.findCommentsByUserId(searchingUserId);
                httpServletRequest.setAttribute(ORDERS, listOfOrders);
                httpServletRequest.setAttribute(COMMENTS, listOfComments);
                httpServletRequest.setAttribute(FOUND_USER, userFound);
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_USER_ORDERS_AND_COMMENTS);
            } else {
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_WELCOME);
            }
        } catch (ServiceException e) {
            logger.error("Exception in find userFound orders command", e);
            throw new CommandException("Exception in find userFound orders command", e);
        }
        return page;
    }
}
