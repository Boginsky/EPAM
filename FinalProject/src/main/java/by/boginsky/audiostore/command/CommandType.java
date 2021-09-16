package by.boginsky.audiostore.command;

import by.boginsky.audiostore.command.impl.*;
import by.boginsky.audiostore.command.impl.language.EnglishLanguageCommand;
import by.boginsky.audiostore.command.impl.language.RussianLanguageCommand;

public enum CommandType {
    // FIXME: 13.09.2021  add roles
    SIGN_IN(new SignInCommand()),
    ENGLISH(new EnglishLanguageCommand()),
    RUSSIAN(new RussianLanguageCommand()),
    LOG_OUT(new LogOutCommand()),
    MAIN(new MainCommand()),
    LOGIN(new LoginCommand()),
    CABINET(new CabinetCommand()),
    ADD_TO_CART(new AddToCartCommand()),
    REMOVE_FROM_CART(new RemoveFromCartCommand()),
    TO_CART(new ToCartCommand()),
    SIGN_UP(new SignUpCommand()),
    REGISTER(new RegisterCommand()),
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand()),
    ALL_SONGS(new AllSongsCommand()),
    ALL_ALBUMS(new AllAlbumsCommand());

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
