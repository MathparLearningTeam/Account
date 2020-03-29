package mathpar.web.learning.account.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super("Token hasn't been found");
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
