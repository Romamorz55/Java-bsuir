package Labs.Temp;

import Labs.Temp.constans.ValidationErrorConstants;
import Labs.Temp.models.CalculationResult;
import Labs.Temp.models.ParametersKey;
import Labs.Temp.service.CalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController("/Figure")
@RequestMapping("/Param")
public class Controller {

    private final CalculationService calculationService;

    public Controller(CalculationService calculationService){
        this.calculationService = calculationService;
    }

    @GetMapping("/result")
    // @JsonProperty() @RequestParam int width, @RequestParam int height
    public CalculationResult calculation (@RequestParam int width, @RequestParam int height) {

        log.info("Received parameters: length = " + width + " height = " + height);

        //отлавливание ошибки 400
        if(height == 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ValidationErrorConstants.BadArguments);
        }
        //отлавливание ошибки 500
        if(height == 8) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,  ValidationErrorConstants.ServerError);
        }

        ParametersKey parametersKey = new ParametersKey(width,height);

        CalculationResult calculationResult = calculationService.calcAndBuildResult(parametersKey);

        log.info("Calculation results: perimeter = " + calculationResult.getPerimeter() + " square = " + calculationResult.getSquare());

        return calculationResult;
    }


    //@GetMapping
    //@PostMapping("")
}

