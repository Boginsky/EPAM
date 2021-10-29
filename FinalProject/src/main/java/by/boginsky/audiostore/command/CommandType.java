package by.boginsky.audiostore.command;

import by.boginsky.audiostore.command.impl.*;
import by.boginsky.audiostore.command.impl.language.EnglishLanguageCommand;
import by.boginsky.audiostore.command.impl.language.RussianLanguageCommand;

/**
 * The enum Command type.
 */
public enum CommandType {
    /**
     * The Sign in.
     */
    SIGN_IN(new SignInCommand()),
    /**
     * The English.
     */
    ENGLISH(new EnglishLanguageCommand()),
    /**
     * The Russian.
     */
    RUSSIAN(new RussianLanguageCommand()),
    /**
     * The Log out.
     */
    LOG_OUT(new LogOutCommand()),
    /**
     * The Login.
     */
    LOGIN(new LoginCommand()),
    /**
     * The Cabinet.
     */
    CABINET(new CabinetCommand()),
    /**
     * The Add to cart.
     */
    ADD_TO_CART(new AddToCartCommand()),
    /**
     * The Remove from cart.
     */
    REMOVE_FROM_CART(new RemoveFromCartCommand()),
    /**
     * The To cart.
     */
    TO_CART(new ToCartCommand()),
    /**
     * The Sign up.
     */
    SIGN_UP(new SignUpCommand()),
    /**
     * The Register.
     */
    REGISTER(new RegistrationCommand()),
    /**
     * The Confirm registration.
     */
    CONFIRM_REGISTRATION(new ConfirmRegistrationCommand()),
    /**
     * The All songs.
     */
    ALL_SONGS(new AllSongsCommand()),
    /**
     * The All albums.
     */
    ALL_ALBUMS(new AllAlbumsCommand()),
    /**
     * The All songs for album.
     */
    ALL_SONGS_FOR_ALBUM(new AllSongsForAlbumCommand()),
    /**
     * The All authors.
     */
    ALL_AUTHORS(new AllAuthorsCommand()),
    /**
     * The Albums for author.
     */
    ALBUMS_FOR_AUTHOR(new AlbumsForAuthorCommand()),
    /**
     * The Submit order.
     */
    SUBMIT_ORDER(new SubmitOrderCommand()),
    /**
     * The Change email.
     */
    CHANGE_EMAIL(new ChangeEmailConfirmCommand()),
    /**
     * The Change name.
     */
    CHANGE_NAME(new ChangeNameCommand()),
    /**
     * The Change password.
     */
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    /**
     * The Change balance.
     */
    CHANGE_BALANCE(new ChangeBalanceCommand()),
    /**
     * The Change photo.
     */
    CHANGE_PHOTO(new ChangePhotoCommand()),
    /**
     * The Find order.
     */
    FIND_ORDER(new FindOrderCommand()),
    /**
     * The Add comment.
     */
    ADD_COMMENT(new AddCommentCommand()),
    /**
     * The Change comment.
     */
    CHANGE_COMMENT(new ChangeCommentCommand()),
    /**
     * The Remove comment.
     */
    REMOVE_COMMENT(new RemoveCommentCommand()),
    /**
     * The Change email code verification.
     */
    CHANGE_EMAIL_CODE_VERIFICATION(new ChangeEmailCodeVerificationCommand()),
    /**
     * The Change email confirm.
     */
    CHANGE_EMAIL_CONFIRM(new ChangeEmailConfirmCommand()),
    /**
     * The Remove account.
     */
    REMOVE_ACCOUNT(new RemoveAccountCommand()),
    /**
     * The Remove account confirm.
     */
    REMOVE_ACCOUNT_CONFIRM(new RemoveAccountConfirmCommand()),
    /**
     * The Change bonuses.
     */
    CHANGE_BONUSES(new ChangeBonusesCommand()),
    /**
     * The Change user status.
     */
    CHANGE_USER_STATUS(new ChangeUserStatusCommand()),
    /**
     * The Find user orders.
     */
    FIND_USER_ORDERS(new FindUserOrdersAndCommentsCommand()),
    /**
     * The Add author.
     */
    ADD_AUTHOR(new AddAuthorCommand()),
    /**
     * The Remove author.
     */
    REMOVE_AUTHOR(new RemoveAuthorCommand()),
    /**
     * The Change author.
     */
    CHANGE_AUTHOR(new ChangeAuthorCommand()),
    /**
     * The Add album.
     */
    ADD_ALBUM(new AddAlbumCommand()),
    /**
     * The Remove album.
     */
    REMOVE_ALBUM(new RemoveAlbumCommand()),
    /**
     * The Change album.
     */
    CHANGE_ALBUM(new ChangeAlbumCommand()),
    /**
     * The Add song.
     */
    ADD_SONG(new AddSongCommand()),
    /**
     * The Add song confirm.
     */
    ADD_SONG_CONFIRM(new AddSongConfirmCommand()),
    /**
     * The Remove song.
     */
    REMOVE_SONG(new RemoveSongCommand()),
    /**
     * The Change song.
     */
    CHANGE_SONG(new ChangeSongCommand()),
    /**
     * The Change song confirm.
     */
    CHANGE_SONG_CONFIRM(new ChangeSongConfirmCommand());


    private Command command;

    /**
     * Gets command.
     *
     * @return the command
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Sets command.
     *
     * @param command the command
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    CommandType(Command command) {
        setCommand(command);
    }
}
