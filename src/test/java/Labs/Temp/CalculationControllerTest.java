package Labs.Temp;

import Labs.Temp.Controller;
import Labs.Temp.models.CalculationResult;
import Labs.Temp.models.ParametersKey;
import Labs.Temp.service.*;

import Labs.Temp.service.CallCountService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculationControllerTest {
    private CachingService cachingService;
    private CalculationService calculationService;
    private BulkCalculationService bulkCalculationService;
    private Controller controller;

    public void setUp(){
        CallCountService callCountService = mock(CallCountService.class);
        when(callCountService.incrementCountAndReturnValue()).thenReturn(1);
        cachingService = new CachingService();
        calculationService = new CalculationService();
        bulkCalculationService = new BulkCalculationService();
        controller = new Controller(calculationService,cachingService,callCountService,bulkCalculationService);
    }


    @Test
    void calculationNormalReturnFromCache() {

        ParametersKey parametersKey = new ParametersKey(5,10);
        CalculationResult calculationResult = new CalculationResult(32,60);

        cachingService.addResult(parametersKey,calculationResult);

        assertEquals(calculationResult, new CalculationResult(32,60));
    }
}