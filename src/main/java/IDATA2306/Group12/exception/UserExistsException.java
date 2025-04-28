package IDATA2306.Group12.exception;

/**
 * An exception to be thrown when trying to register a user with conflicting fields.
 * Examples: Email and telephone numbers
 */
public class UserExistsException extends RuntimeException {

    public UserExistsException(String message) {
        super(message);
    }
}
