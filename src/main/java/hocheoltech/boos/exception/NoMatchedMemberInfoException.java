package hocheoltech.boos.exception;

public class NoMatchedMemberInfoException extends RuntimeException {

    public NoMatchedMemberInfoException() {
            super();
        }

    public NoMatchedMemberInfoException(String message) {
            super(message);
        }

    public NoMatchedMemberInfoException(String message, Throwable cause) {
            super(message, cause);
        }

    public NoMatchedMemberInfoException(Throwable cause) {
            super(cause);
        }

}
