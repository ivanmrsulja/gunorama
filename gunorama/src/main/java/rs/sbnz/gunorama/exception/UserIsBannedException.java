package rs.sbnz.gunorama.exception;

public class UserIsBannedException extends RuntimeException {
    public UserIsBannedException(String message) {
        super(message);
    }
}
