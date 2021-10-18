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

import static by.boginsky.audiostore.util.constants.Constant.AMOUNT_OF_MONEY;
import static by.boginsky.audiostore.util.constants.Constant.USER;

public class ChangeBalanceCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        if (user.getUserRole() == User.UserRole.USER) {
            updateBalance(httpServletRequest, user);
        }
        CabinetCommand cabinetCommand = new CabinetCommand();
        Router router = cabinetCommand.execute(httpServletRequest);
        return router;
    }

    private void updateBalance(HttpServletRequest httpServletRequest, User user) throws CommandException {
        BigDecimal userBalance = user.getBalance();
        BigDecimal addedAmount = BigDecimal.valueOf(Long.parseLong(httpServletRequest.getParameter(AMOUNT_OF_MONEY)));
        UserService userService = UserServiceImpl.getInstance();
        try {
            userBalance = userBalance.add(addedAmount);
            userService.updateUserMoney(user.getId(), userBalance);
        } catch (ServiceException e) {
            logger.error("Exception in change balance command", e);
            throw new CommandException("Exception in change balance command", e);
        }
    }
}
