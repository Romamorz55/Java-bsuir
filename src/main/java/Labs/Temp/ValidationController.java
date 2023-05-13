package Labs.Temp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

public class ValidationController {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @ExceptionHandler({ ResponseStatusException.class })
    public ResponseEntity<Object> handleException(ResponseStatusException ex) {
        var errorModel = new ErrorModel();
        errorModel.ErrorMessage = ex.getReason();

        logger.info("Exception: " + ex.getMessage());

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
}
