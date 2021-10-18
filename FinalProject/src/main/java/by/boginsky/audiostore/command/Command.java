package by.boginsky.audiostore.command;

import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    static final Logger logger = LogManager.getLogger();

    Router execute(HttpServletRequest httpServletRequest) throws CommandException;
}
