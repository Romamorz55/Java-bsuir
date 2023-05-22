package Labs.Temp.models;


import Labs.Temp.exception.InvalidParametersException;

import java.io.Serializable;
import java.util.Objects;

public class ParametersKey implements Serializable {

    private int rectangleWidth;
    private int rectangleHeight;
    public ParametersKey(int width, int height){
        if(width <= 0 || height <= 0){
            throw new InvalidParametersException("width/height cannot be lower or equal to zero");
        }
        this.rectangleHeight = height;
        this.rectangleWidth = width;
    }
    public ParametersKey(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ParametersKey key = (ParametersKey) o;
        return ((Objects.equals(rectangleWidth, key.rectangleWidth) && Objects.equals(rectangleHeight, key.rectangleHeight)) ||
                (Objects.equals(rectangleWidth,key.rectangleHeight) && Objects.equals(rectangleHeight,key.rectangleWidth)));
    }

    public int getRectangleHeight() {
        return rectangleHeight;
    }
    public int getRectangleWidth() {
        return rectangleWidth;
    }
    public void setRectangleHeight(int height) {
        this.rectangleHeight = height;
    }
    public void setRectangleWidth(int width) {
        this.rectangleWidth = width;
    }
}