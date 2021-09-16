package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.exception.PropertyReaderException;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.ConfirmCodeGenerator;
import by.boginsky.audiostore.util.MailSender;
import by.boginsky.audiostore.util.PasswordEncryption;
import by.boginsky.audiostore.util.constants.PathPage;
import by.boginsky.audiostore.util.validator.InputDataValidator;
import by.boginsky.audiostore.util.validator.impl.InputDataValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.boginsky.audiostore.util.constants.Attribute.*;
import static by.boginsky.audiostore.util.constants.Parameter.*;


public class RegisterCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        Router router = new Router();
        String email = httpServletRequest.getParameter(EMAIL);
        String password = httpServletRequest.getParameter(PASSWORD);
        String firstName = httpServletRequest.getParameter(FIRST_NAME);
        String lastName = httpServletRequest.getParameter(LAST_NAME);

        HttpSession httpSession = httpServletRequest.getSession();
        String page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_REGISTRATION);
        InputDataValidator validator = InputDataValidatorImpl.getInstance();
        if (validator.isCorrectEmail(email) && validator.isCorrectPassword(password)) {
            User user = User.builder()
                    .setEmail(email)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setUserRole(User.UserRole.USER)
                    .build();
            httpSession.setAttribute(USER, user);
            httpSession.setAttribute(ENCRYPTED_PASSWORD, PasswordEncryption.encryptsPassword(password));

            page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_CONFIRM);

            String confirmCode = ConfirmCodeGenerator.generateConfirmCode();

            httpSession.setAttribute(CONFIRM_CODE, confirmCode);

            MailSender mailSender = new MailSender();
            try {
                mailSender.sendEmail(email,confirmCode);
            } catch (PropertyReaderException e) {
                throw new CommandException("Cant send confirm code",e);
            }
        }
        router.setPagePath(page);
        return router;
    }
}
