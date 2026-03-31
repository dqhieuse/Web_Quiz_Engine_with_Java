package engine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IndexOutOfBoundsException extends RuntimeException {
    public IndexOutOfBoundsException(String message) {
        super(message);
    }
}
