package Labs.Temp;

import Labs.Temp.models.CalculationResult;
import Labs.Temp.models.ParametersKey;
import Labs.Temp.service.CalculationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private CalculationService calculationService;
    private Controller controller;

    public void setUp(){
        calculationService = new CalculationService();
        controller = new Controller(calculationService);
    }

    @Test
    void calculationNormalReturn() throws JsonProcessingException {
        
        ParametersKey parametersKey = new ParametersKey(5, 10);
        CalculationResult calculationResult = new CalculationResult(30,50);

        assertEquals(calculationResult,controller.calculation(5,10));
    }

}