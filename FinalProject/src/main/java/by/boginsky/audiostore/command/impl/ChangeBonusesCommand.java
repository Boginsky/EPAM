package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import static by.boginsky.audiostore.util.constants.Constant.*;

public class ChangeBonusesCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        if (user.getUserRole() == User.UserRole.ADMIN) {
            updateBonuses(httpServletRequest);
        }
        CabinetCommand cabinetCommand = new CabinetCommand();
        Router router = cabinetCommand.execute(httpServletRequest);
        return router;
    }

    private void updateBonuses(HttpServletRequest httpServletRequest) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        Long userId = Long.valueOf(httpServletRequest.getParameter(USER_ID));
        BigDecimal addedBonuses = BigDecimal.valueOf(Long.parseLong(httpServletRequest.getParameter(ADDED_BONUSES)));
        BigDecimal userBonuses = BigDecimal.valueOf(Long.parseLong(httpServletRequest.getParameter(USER_BONUSES)));
        userBonuses = userBonuses.add(addedBonuses);

        try {
            userService.updateUserBonus(userId, userBonuses);
        } catch (ServiceException e) {
            logger.error("Exception in change bonuses command", e);
            throw new CommandException("Exception in change bonuses command", e);
        }
    }
}
