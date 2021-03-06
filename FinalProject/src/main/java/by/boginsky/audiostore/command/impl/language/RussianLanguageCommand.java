package by.boginsky.audiostore.command.impl.language;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.command.impl.AllSongsCommand;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import by.boginsky.audiostore.util.constants.Locale;

import javax.servlet.http.HttpServletRequest;

import static by.boginsky.audiostore.util.constants.Constant.CHANGE_LANGUAGE;

/**
 * The type Russian language command.
 */
public class RussianLanguageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
        httpServletRequest.setAttribute(CHANGE_LANGUAGE, Locale.RUSSIAN_LOCALE);
        httpServletRequest.getSession().setAttribute(CHANGE_LANGUAGE, Locale.RUSSIAN_LOCALE);
        AllSongsCommand allSongsCommand = new AllSongsCommand();
        Router router = allSongsCommand.execute(httpServletRequest);
        return router;
    }
}
