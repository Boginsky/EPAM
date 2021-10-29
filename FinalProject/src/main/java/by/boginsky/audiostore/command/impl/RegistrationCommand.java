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
import by.boginsky.audiostore.util.PasswordEncryption;
import by.boginsky.audiostore.util.constants.PathPage;
import by.boginsky.audiostore.util.validator.InputDataValidator;
import by.boginsky.audiostore.util.validator.impl.InputDataValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.boginsky.audiostore.util.constants.Constant.*;


/**
 * The type Registration command.
 */
public class RegistrationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        String email = httpServletRequest.getParameter(EMAIL);
        String password = httpServletRequest.getParameter(PASSWORD);
        String firstName = httpServletRequest.getParameter(FIRST_NAME);
        String lastName = httpServletRequest.getParameter(LAST_NAME);
        String userRole = httpServletRequest.getParameter(USER_ROLE);
        HttpSession httpSession = httpServletRequest.getSession();
        String page = getPage(httpServletRequest, email, password, firstName, lastName, userRole, httpSession);
        Router router = new Router();
        router.setPagePath(page);
        return router;
    }

    private String getPage(HttpServletRequest httpServletRequest, String email, String password, String firstName, String lastName, String userRole, HttpSession httpSession) throws CommandException {
        String page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_REGISTRATION);
        if (validate(email, password)) {
            User user;
            if (userRole != null) {
                user = User.builder()
                        .setEmail(email)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setUserRole(User.UserRole.ADMIN)
                        .build();
            } else {
                user = User.builder()
                        .setEmail(email)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setUserRole(User.UserRole.USER)
                        .build();
            }

            String confirmCode = ConfirmCodeGenerator.generateConfirmCode();
            httpServletRequest.setAttribute(REAL_CONFIRM_CODE, confirmCode);
            MailSender mailSender = new MailSender();
            try {
                mailSender.sendEmail(email, confirmCode);
                httpSession.setAttribute(USER, user);
                httpServletRequest.setAttribute(ENCRYPTED_PASSWORD, PasswordEncryption.encryptsPassword(password));
                page = ConfigurationManager.getProperty(PathPage.PATH_PAGE_CONFIRM);
            } catch (PropertyReaderException e) {
                logger.error("Cant send confirm code", e);
                throw new CommandException("Cant send confirm code", e);
            }
        }
        return page;
    }

    private boolean validate(String email, String password) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        InputDataValidator inputDataValidator = InputDataValidatorImpl.getInstance();
        List<User> listOfUsersEmail;
        try {
            listOfUsersEmail = userService.findAllUsers();
        } catch (ServiceException e) {
            throw new CommandException("Exception in registration command", e);
        }
        for (User user : listOfUsersEmail) {
            if (user.getEmail().equals(email)
                    && inputDataValidator.isCorrectEmail(email)
                    && inputDataValidator.isCorrectPassword(password)) {
                return false;
            }
        }
        return true;
    }

}
