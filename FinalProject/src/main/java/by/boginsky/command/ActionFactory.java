package by.boginsky.command;

import by.boginsky.command.impl.CommandEnum;
import by.boginsky.command.impl.DefaultCommand;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static final String COMMAND = "command";
    private static final String TARGET = " ";
    private static final String REPLACEMENT = "_";
    private static Command command = new DefaultCommand();

    public static Command defineCommand(HttpServletRequest httpServletRequest) {
        String action = httpServletRequest.getParameter(COMMAND).replace(TARGET, REPLACEMENT).toUpperCase();
        if (action.isEmpty()) {
            return command;
        }

        CommandEnum currentEnum = CommandEnum.valueOf(action);
        command = currentEnum.getCommand();

        return command;
    }
}
