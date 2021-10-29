package by.boginsky.audiostore.exception;

/**
 * The type Property reader exception.
 */
public class PropertyReaderException extends Exception {
    /**
     * Instantiates a new Property reader exception.
     */
    public PropertyReaderException() {
    }

    /**
     * Instantiates a new Property reader exception.
     *
     * @param message the message
     */
    public PropertyReaderException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Property reader exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public PropertyReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Property reader exception.
     *
     * @param cause the cause
     */
    public PropertyReaderException(Throwable cause) {
        super(cause);
    }
}
