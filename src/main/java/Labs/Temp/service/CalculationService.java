package Labs.Temp.service;

import Labs.Temp.models.CalculationResult;
import Labs.Temp.models.ParametersKey;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculationService {
    public CalculationResult calcAndBuildResult(ParametersKey parametersKey) {
        return new CalculationResult(calcPerimeter(parametersKey.getRectangleWidth(),parametersKey.getRectangleHeight()),calcSquare(parametersKey.getRectangleWidth(),parametersKey.getRectangleHeight()));

    }
    public int calcPerimeter(int width, int height){
        return 2 * width + 2 * height;
    }
    public int calcSquare(int width, int height){
        return width * height;
    }
}