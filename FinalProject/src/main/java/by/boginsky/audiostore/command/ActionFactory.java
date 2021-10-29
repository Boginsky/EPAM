package by.boginsky.audiostore.command;

import by.boginsky.audiostore.command.impl.DefaultCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * The type Action factory.
 */
public class ActionFactory {

    private static final String COMMAND = "command";
    private static final String TARGET = " ";
    private static final String REPLACEMENT = "_";
    private static Command command = new DefaultCommand();

    /**
     * Define command command.
     *
     * @param httpServletRequest the http servlet request
     * @return the command
     */
    public static Command defineCommand(HttpServletRequest httpServletRequest) {

        Optional<String> action = Optional.ofNullable(httpServletRequest.getParameter(COMMAND).replace(TARGET, REPLACEMENT).toUpperCase());

        if (action.isPresent()) {
            CommandType currentType = CommandType.valueOf(action.get());
            command = currentType.getCommand();
        }
        return command;

    }
}
