package Labs.Temp;

//import Labs.Temp.Controller;
import Labs.Temp.models.CalculationResult;
//import Labs.Temp.models.ParametersKey;
import Labs.Temp.service.CalculationService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationControllerTest {
    // private CachingService cachingService;
    private CalculationService calculationService;
    //private BulkCalculationService bulkCalculationService;
    private Controller controller;
    //private ParametersProcessingService parametersProcessingService;
    //private CalculationDataService dataService;
    // private AsyncService asyncService;
    public void setUp(){
        //CallCountService callCountService = mock(CallCountService.class);
        //when(callCountService.incrementCountAndReturnValue()).thenReturn(1);
        //cachingService = new CachingService();
        calculationService = new CalculationService();
        //bulkCalculationService = new BulkCalculationService();
        //parametersProcessingService = new ParametersProcessingService();
        //dataService = new CalculationDataService();
        //asyncService = new AsyncService();
        controller = new Controller(calculationService/*,cachingService,callCountService,bulkCalculationService,parametersProcessingService,dataService,asyncService*/);
    }


    @Test
    void calculationNormalReturnFromCache() {

        //ParametersKey parametersKey = new ParametersKey(5,10);
        //CalculationResult calculat = null;
       // CalculationResult calculationResult = new CalculationResult(32,60);

        //cachingService.addResult(parametersKey,calculationResult);

       // assertEquals(calculationResult, new CalculationResult(32,60));
    }
}