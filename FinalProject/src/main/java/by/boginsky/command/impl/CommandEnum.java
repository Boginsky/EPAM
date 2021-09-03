package by.boginsky.command.impl;

import by.boginsky.command.Command;

public enum CommandEnum {

    SIGN_IN(new SignInCommand());

    private Command command;

    public Command getCommand(){
        return command;
    }

    public void setCommand(Command command){
        this.command = command;
    }

    CommandEnum(Command command){
        setCommand(command);
    }
}
