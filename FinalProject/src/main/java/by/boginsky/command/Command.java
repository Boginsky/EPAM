package by.boginsky.command;

import by.boginsky.controller.Router;
import by.boginsky.exception.CommandException;
import by.boginsky.validator.InputDataValidator;
import by.boginsky.validator.impl.InputDataValidatorImpl;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest httpServletRequest) throws CommandException;
}
