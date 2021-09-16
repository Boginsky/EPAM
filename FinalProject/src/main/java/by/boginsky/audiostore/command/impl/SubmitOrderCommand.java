package by.boginsky.audiostore.command.impl;

import by.boginsky.audiostore.command.Command;
import by.boginsky.audiostore.controller.Router;
import by.boginsky.audiostore.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class SubmitOrderCommand implements Command {
    @Override
    public Router execute(HttpServletRequest httpServletRequest) throws CommandException {
return new Router();
    }
}
