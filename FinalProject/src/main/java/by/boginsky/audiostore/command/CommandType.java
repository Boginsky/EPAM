package by.boginsky.audiostore.command;

import by.boginsky.audiostore.command.impl.*;
import by.boginsky.audiostore.command.impl.language.EnglishLanguageCommand;
import by.boginsky.audiostore.command.impl.language.RussianLanguageCommand;

public enum CommandType {
    SIGN_IN(new SignInCommand()),
    ENGLISH(new EnglishLanguageCommand()),
    RUSSIAN(new RussianLanguageCommand()),
    LOG_OUT(new LogOutCommand()),
    LOGIN(new LoginCommand()),
    CABINET(new CabinetCommand()),
    ADD_TO_CART(new AddToCartCommand()),
    REMOVE_FROM_CART(new RemoveFromCartCommand()),
    TO_CART(new ToCartCommand()),
    SIGN_UP(new SignUpCommand()),
    REGISTER(new RegisterCommand()),
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand()),
    ALL_SONGS(new AllSongsCommand()),
    ALL_ALBUMS(new AllAlbumsCommand()),
    ALL_ALBUM_SONG(new AllAlbumSongCommand()),
    ALL_AUTHORS(new AllAuthorsCommand()),
    ALBUMS_FOR_AUTHOR(new AlbumsForAuthorCommand()),
    SUBMIT_ORDER(new SubmitOrderCommand()),
    CHANGE_EMAIL(new ChangeEmailCommand()),
    CHANGE_NAME(new ChangeNameCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    CHANGE_BALANCE(new ChangeBalanceCommand()),
    CHANGE_PHOTO(new ChangePhotoCommand()),
    FIND_ORDER(new FindOrderCommand()),
    ADD_COMMENT(new AddCommentCommand()),
    CHANGE_COMMENT(new ChangeCommentCommand()),
    REMOVE_COMMENT(new RemoveCommentCommand());

    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    CommandType(Command command) {
        setCommand(command);
    }
}
