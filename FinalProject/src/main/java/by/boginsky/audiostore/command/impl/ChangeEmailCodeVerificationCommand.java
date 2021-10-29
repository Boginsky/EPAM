package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.PropertyReaderException;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.ConfirmCodeGenerator;
import by.boginsky.audiostore.util.MailSender;
import by.boginsky.audiostore.util.constants.PathPage;
import by.boginsky.audiostore.util.validator.InputDataValidator;
import by.boginsky.audiostore.util.validator.impl.InputDataValidatorImpl;

import javax.servlet.http.HttpServletRequest;

import static by.boginsky.audiostore.util.constants.Constant.REAL_CONFIRM_CODE;
import static by.boginsky.audiostore.util.constants.Constant.UPDATED_EMAIL;

/**
 * The type Change email code verification command.
 */
public class ChangeEmailCodeVerificationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        String updatedEmail = httpServletRequest.getParameter(UPDATED_EMAIL);
        String page = getPage(httpServletRequest, updatedEmail);
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }

    private String getPage(HttpServletRequest httpServletRequest, String updatedEmail) throws CommandException {
        String page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_WELCOME);
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
        return page;
    }

    private boolean validate(String email) {
        InputDataValidator inputDataValidator = InputDataValidatorImpl.getInstance();
        return inputDataValidator.isCorrectEmail(email);
    }
}

