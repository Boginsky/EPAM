package by.boginsky.audiostore.command;

import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Command.
 */
public interface Command {

    /**
     * The constant logger.
     */
    Logger logger = LogManager.getLogger();

    /**
     * Execute router.
     *
     * @param httpServletRequest the http servlet request
     * @return the router
     * @throws CommandException the command exception
     */
    Router execute(HttpServletRequest httpServletRequest) throws CommandException;
}
