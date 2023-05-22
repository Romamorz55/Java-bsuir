package Labs.Temp.service;

import Labs.Temp.models.CalculationResult;
import Labs.Temp.models.ParametersKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@ApplicationScope
public class CachingService {
    private static final Map<ParametersKey, CalculationResult> calculationHashMap = new HashMap<>();
    public CachingService(){}

    public boolean contains(ParametersKey parametersKey) {
        return calculationHashMap.containsKey(parametersKey);
    }
    public CalculationResult getResultByKey(ParametersKey parametersKey){
        return calculationHashMap.get(parametersKey);
    }
    public void addResult(ParametersKey parametersKey, CalculationResult calculationResult){
        calculationHashMap.put(parametersKey,calculationResult);
    }
    public static Map<ParametersKey, CalculationResult> getCalculationHashMap() {
        return calculationHashMap;
    }

    public void printMap(){
        calculationHashMap.forEach((key, value) -> log.info(key + " : " + value));
    }
}
