/*package Labs.Temp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestControllerAdvice
public class ValidationController {

    @ExceptionHandler({ ResponseStatusException.class })
    public ResponseEntity<Object> handleException(ResponseStatusException ex) {
        var errorModel = new ErrorModel();
        errorModel.ErrorMessage = ex.getReason();

        log.info("Exception: " + ex.getMessage());

        ResponseEntity responseEntity = new ResponseEntity<>(errorModel, ex.getStatusCode());
        return responseEntity;
    }

    public class ErrorModel {
        public String ErrorMessage;
    }

    public class ValidationErrorConstants {
        public static final String ServerError = "Something went wrong. Server error";
        public static final String BadArguments = "Bad arguments";
    }
}*/
