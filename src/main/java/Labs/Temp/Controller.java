package Labs.Temp;

import Labs.Temp.constans.ValidationErrorConstants;
import Labs.Temp.models.CalculationResult;
import Labs.Temp.models.ParametersKey;
import Labs.Temp.service.BulkCalculationService;
import Labs.Temp.service.CachingService;
import Labs.Temp.service.CalculationService;
import Labs.Temp.service.CallCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Slf4j
@RestController("/Figure")
@RequestMapping("/Param")
public class Controller {
    private final CalculationService calculationService;
    private final CachingService cachingService;
    private final CallCountService callCountService;
    private final BulkCalculationService bulkCalculationService;

    public Controller(CalculationService calculationService, CachingService cachingService, CallCountService callCountService, BulkCalculationService bulkCalculationService) {
        this.calculationService = calculationService;
        this.cachingService = cachingService;
        this.callCountService = callCountService;
        this.bulkCalculationService = bulkCalculationService;
    }

    @GetMapping("/result")
    // @JsonProperty() @RequestParam int width, @RequestParam int height
    public CalculationResult calculation(@RequestParam int width, @RequestParam int height) {
        callCountService.incrementCountAndReturnValue();

        log.info("Received parameters: length = " + width + " height = " + height);

        //отлавливание ошибки 400
        if (height == 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ValidationErrorConstants.BadArguments);
        }
        //отлавливание ошибки 500
        if (height == 8) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ValidationErrorConstants.ServerError);
        }

        ParametersKey parametersKey = new ParametersKey(width, height);
        CalculationResult calculationResult = calculationService.calcAndBuildResult(parametersKey);
        log.info("Calculation results: perimeter = " + calculationResult.getPerimeter() + " square = " + calculationResult.getSquare());

        cachingService.addResult(parametersKey, calculationResult);

        return calculationResult;
    }

    @GetMapping("/count")
    public int count() {
        return callCountService.getCounter();
    }

    @GetMapping("/mymap")
    public Map<ParametersKey, CalculationResult> GetMap() {
        return cachingService.getCalculationHashMap();

    }

    @PostMapping("/calculatorBulk")
    public ResponseEntity<?> calculateBulk(@RequestBody List<ParametersKey> requestList) throws NoSuchElementException {

        List<CalculationResult> responseList = new ArrayList<>();

        requestList.stream().forEach(currentElement -> log.debug("Received pair { " + currentElement.getRectangleWidth() + " ; " + currentElement.getRectangleHeight() + " }"));

        requestList.stream()
                .filter(currentElement -> cachingService.contains(currentElement))
                .forEach(currentElement ->
                {
                    log.debug("received result from cache");
                    responseList.add(cachingService.getResultByKey(currentElement));
                });

        requestList.stream()
                .filter(currentElement -> !cachingService.contains(currentElement))
                .forEach(currentElement ->
                {
                    log.debug("no such key in cache");
                    CalculationResult calculationResult = calculationService.calcAndBuildResult(currentElement);
                    responseList.add(calculationResult);
                    cachingService.addResult(currentElement, responseList.get(responseList.indexOf(calculationResult)));
                });

        return new ResponseEntity<>("Result: " + responseList + "\nmaxPerimeter: "
                + bulkCalculationService.findMaxByPerimeter(responseList) + "\nminSquare: "
                + bulkCalculationService.findMinBySquare(responseList) + "\naverageResult: " + bulkCalculationService.calcAverageResult(responseList), HttpStatus.OK);
    }
}

