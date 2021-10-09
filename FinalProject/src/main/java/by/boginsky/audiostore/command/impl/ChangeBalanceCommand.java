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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import static by.boginsky.audiostore.util.constants.Attribute.USER;
import static by.boginsky.audiostore.util.constants.Parameter.AMOUNT_OF_MONEY;

public class ChangeBalanceCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) httpSession.getAttribute(USER);
        String page;
        if (user.getUserRole() == User.UserRole.USER) {
            BigDecimal userBalance = user.getBalance();
            String money = httpServletRequest.getParameter(AMOUNT_OF_MONEY);
            if(money == ""){
                money = "0";
            }
            BigDecimal addedAmount = BigDecimal.valueOf(Long.parseLong(money));
            UserService userService = UserServiceImpl.getInstance();
            try {
                userBalance = userBalance.add(addedAmount);
                userService.updateUserMoney(user.getId(), userBalance);
                user.setBalance(userBalance);
                httpSession.setAttribute(USER, user);
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_USER);
            } catch (ServiceException e) {
                throw new CommandException("Exception in change balance command", e);
            }
        } else {
            page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_ADMIN);
        }
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }
}
