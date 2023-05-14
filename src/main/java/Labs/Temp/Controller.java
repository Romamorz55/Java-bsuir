package Labs.Temp;

import Labs.Temp.models.CalculationResult;
import Labs.Temp.models.ParametersKey;
import Labs.Temp.service.CalculationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@Validated
@RestController("/Figure")

@RequestMapping("/Param")
public class Controller {

    private final CalculationService calculationService;
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public Controller(CalculationService calculationService){
        this.calculationService = calculationService;
    }

    @GetMapping("/result")
    // @JsonProperty() @RequestParam int width, @RequestParam int height
    public CalculationResult calculation (@RequestParam int width, @RequestParam int height) throws JsonProcessingException {

        //отлавливание ошибки 400
        if(height == 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ValidationController.ValidationErrorConstants.BadArguments);
        }
        //отлавливание ошибки 500
        if(height == 8) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,  ValidationController.ValidationErrorConstants.ServerError);
        }

        ParametersKey parametersKey = new ParametersKey(width,height);

        CalculationResult calculationResult = calculationService.calcAndBuildResult(parametersKey);

        return calculationResult;
    }


    //@GetMapping
    //@PostMapping("")
}

