package mathpar.web.learning.account.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotAuthenticatedException extends RuntimeException{
    public NotAuthenticatedException() {
        super("Invalid credentials");
    }

    public NotAuthenticatedException(String message) {
        super(message);
    }
}
