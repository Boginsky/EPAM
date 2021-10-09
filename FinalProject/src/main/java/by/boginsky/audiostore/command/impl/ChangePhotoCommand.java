package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.model.service.impl.UserServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

import static by.boginsky.audiostore.util.constants.Attribute.USER;

public class ChangePhotoCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        Router router = new Router();
        UserService userService = UserServiceImpl.getInstance();
        try {
            Part part = httpServletRequest.getPart("file");
            String fileName = part.getSubmittedFileName();
            String path = "E:\\EPAM\\Новая папка\\EPAM\\FinalProject\\src\\main\\webapp\\static\\image\\user\\" + fileName;
            InputStream is = part.getInputStream();
            boolean result = userService.updateUserPhoto(is, path,user.getId());
            String imageUrl = userService.findUserPhotoImageUrl(user.getId());
            user.setImageUrl(imageUrl);
            httpSession.setAttribute(USER,user);
            if (result) {
                router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_USER));
            } else {
                router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN));
            }
        } catch (ServiceException | IOException | ServletException e) {
            throw new CommandException("Exception in change user photo command",e);
        }
        return router;
    }
}
