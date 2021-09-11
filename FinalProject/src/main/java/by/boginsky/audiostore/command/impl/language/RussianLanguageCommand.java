package by.boginsky.audiostore.command.impl.language;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.util.ConfigurationManager;
import by.boginsky.audiostore.util.constants.Attribute;
import by.boginsky.audiostore.util.constants.Locale;
import by.boginsky.audiostore.util.constants.PathPage;

import javax.servlet.http.HttpServletRequest;

// FIXME: 11.09.2021 need to make redirect on page of request not to main
public class RussianLanguageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        httpServletRequest.setAttribute(Attribute.CHANGE_LANGUAGE, Locale.RUSSIAN_LOCALE);
        httpServletRequest.getSession().setAttribute(Attribute.CHANGE_LANGUAGE,Locale.RUSSIAN_LOCALE);
        Router router = new Router();
        router.setPagePath(ConfigurationManager.getProperty(PathPage.PATH_PAGE_MAIN));
        return router;
    }
}
