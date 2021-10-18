package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.service.ImageUpdateService;
import by.boginsky.audiostore.model.service.impl.ImageUpdateServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

import static by.boginsky.audiostore.util.constants.Constant.*;

public class ChangePhotoCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        String target = httpServletRequest.getParameter(TARGET);
        Long targetId = Long.valueOf(httpServletRequest.getParameter(TARGET_ID));

        updatePhoto(httpServletRequest, target, targetId);

        CabinetCommand cabinetCommand = new CabinetCommand();
        Router router = cabinetCommand.execute(httpServletRequest);
        return router;
    }

    private void updatePhoto(HttpServletRequest httpServletRequest, String target, Long targetId) throws CommandException {
        ImageUpdateService imageUpdateService = ImageUpdateServiceImpl.getInstance();
        try {
            Part part = httpServletRequest.getPart(FILE);
            String fileName = part.getSubmittedFileName();
            InputStream is = part.getInputStream();
            imageUpdateService.updatePhoto(is, target, targetId, fileName);
        } catch (ServiceException | IOException | ServletException e) {
            logger.error("Exception in change user photo command", e);
            throw new CommandException("Exception in change user photo command", e);
        }
    }
}
