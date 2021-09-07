package by.boginsky.audiostore.command;

import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest httpServletRequest) throws CommandException;
}
