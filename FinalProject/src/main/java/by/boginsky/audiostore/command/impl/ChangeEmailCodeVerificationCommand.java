package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.PropertyReaderException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.model.service.impl.UserServiceImpl;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.ConfirmCodeGenerator;
import by.boginsky.audiostore.util.MailSender;
import by.boginsky.audiostore.util.constants.PathPage;
import by.boginsky.audiostore.util.validator.InputDataValidator;
import by.boginsky.audiostore.util.validator.impl.InputDataValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.boginsky.audiostore.util.constants.Constant.REAL_CONFIRM_CODE;
import static by.boginsky.audiostore.util.constants.Constant.UPDATED_EMAIL;

public class ChangeEmailCodeVerificationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        String updatedEmail = httpServletRequest.getParameter(UPDATED_EMAIL);
        String page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN_USER);

        page = getPage(httpServletRequest, updatedEmail, page);

        Router router = new Router();
        router.setPagePath(page);
        return router;
    }

    private String getPage(HttpServletRequest httpServletRequest, String updatedEmail, String page) throws CommandException {
        if (isUnique(updatedEmail)) {
            if (validate(updatedEmail)) {
                String readConfirmCode = ConfirmCodeGenerator.generateConfirmCode();
                httpServletRequest.setAttribute(REAL_CONFIRM_CODE, readConfirmCode);
                httpServletRequest.setAttribute(UPDATED_EMAIL, updatedEmail);
                MailSender mailSender = new MailSender();
                try {
                    mailSender.sendEmail(updatedEmail, readConfirmCode);
                    page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_CHANGE_EMAIL_CONFIRM);
                } catch (PropertyReaderException e) {
                    logger.error("Cant send confirm code", e);
                    throw new CommandException("Cant send confirm code", e);
                }
            }
        }
        return page;
    }

    private boolean isUnique(String updatedEmail) throws CommandException { // FIXME: 17.10.2021 extract into services
        UserService userService = UserServiceImpl.getInstance();
        boolean flag = true;
        List<User> listOfUsersEmail;
        try {
            listOfUsersEmail = userService.findAllUsers();
            for (User user : listOfUsersEmail) {
                if (user.getEmail().equals(updatedEmail)) {
                    flag = false;
                }
            }
        } catch (ServiceException e) {
            logger.error("Exception in change email code verification command", e);
            throw new CommandException("Exception in change email code verification command", e);
        }
        return flag;
    }

    private boolean validate(String email) {
        InputDataValidator inputDataValidator = InputDataValidatorImpl.getInstance();
        return inputDataValidator.isCorrectEmail(email);
    }
}

