package hocheoltech.boos.exception;

public class DuplicateMemberIdException extends RuntimeException {

    public DuplicateMemberIdException() {
        super();
    }

    public DuplicateMemberIdException(String message) {
        super(message);
    }

    public DuplicateMemberIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateMemberIdException(Throwable cause) {
        super(cause);
    }

}
