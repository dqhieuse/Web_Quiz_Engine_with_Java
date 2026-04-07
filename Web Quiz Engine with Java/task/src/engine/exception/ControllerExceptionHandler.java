package engine.exception;

import engine.model.dto.AnswerExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<AnswerExceptionMessage> handleIndexOutOfBoundsException(IndexOutOfBoundsException e) {
        return new ResponseEntity<>(new AnswerExceptionMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
