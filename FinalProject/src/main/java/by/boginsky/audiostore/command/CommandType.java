package by.boginsky.audiostore.command;

import by.boginsky.audiostore.command.impl.*;
import by.boginsky.audiostore.command.impl.language.EnglishLanguageCommand;
import by.boginsky.audiostore.command.impl.language.RussianLanguageCommand;

public enum CommandType {

    SIGN_IN(new SignInCommand()),
    ENGLISH(new EnglishLanguageCommand()),
    RUSSIAN(new RussianLanguageCommand()),
    LOG_OUT(new LogOutCommand()),
    MAIN(new MainCommand()),
    LOGIN(new LoginCommand()),
    CABINET(new CabinetCommand());

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
