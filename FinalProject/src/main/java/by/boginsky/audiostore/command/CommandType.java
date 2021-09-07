package by.boginsky.audiostore.command;

import by.boginsky.audiostore.command.impl.SignInCommand;

public enum CommandType {

    SIGN_IN(new SignInCommand());

    private Command command;

    public Command getCommand(){
        return command;
    }

    public void setCommand(Command command){
        this.command = command;
    }

    CommandType(Command command){
        setCommand(command);
    }
}
