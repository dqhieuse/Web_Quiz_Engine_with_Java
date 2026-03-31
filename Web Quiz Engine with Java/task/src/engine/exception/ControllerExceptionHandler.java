package engine.exception;

import engine.model.dto.AnswerExceptionMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<AnswerExceptionMessage> handleIndexOutOfBoundsException(IndexOutOfBoundsException e) {
        return new ResponseEntity<>(new AnswerExceptionMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<AnswerExceptionMessage> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(new AnswerExceptionMessage(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<AnswerExceptionMessage> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(new AnswerExceptionMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Nullable
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        throw new BadRequestException(ex.getMessage());
    }
}
