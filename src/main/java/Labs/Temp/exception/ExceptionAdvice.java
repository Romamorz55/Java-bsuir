package Labs.Temp.exception;

import Labs.Temp.models.ErrorData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler({ ResponseStatusException.class })
    public ResponseEntity<Object> handleException(ResponseStatusException ex) {
        var errorModel = new ErrorData();
        errorModel.message = ex.getReason();
        log.info("Exception: " + ex.getMessage());
        ResponseEntity responseEntity = new ResponseEntity<>(errorModel, ex.getStatusCode());
        return responseEntity;
    }
    @ExceptionHandler(InvalidParametersException.class)
    public ResponseEntity<ErrorData> exceptionInvalidParametersHandler(InvalidParametersException e){
        log.info("InvalidParametersException thrown");
        ErrorData errorData = new ErrorData(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorData);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorData> exceptionNoSuchElementException(NoSuchElementException e){
        log.info("NoSuchElementException thrown");
        ErrorData errorData = new ErrorData("POST bulk request has empty list");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorData);
    }
    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<ErrorData> exceptionInterruptedHandler(InterruptedException e){
        log.info("InterruptedException thrown");
        ErrorData errorData = new ErrorData("Failed to invoke thread sleep");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorData);
    }
    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<ErrorData> exceptionExecutionHandler(ExecutionException e){
        log.info("ExecutionException thrown");
        ErrorData errorData = new ErrorData("Failed to receive data from async operation");
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorData);
    }
}