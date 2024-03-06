package annotationDriver.bean;

import lombok.Data;

/**
 * @author 陈孟飞
 * @date 2021/6/15
 */
@Data
public class Color {
    private String name;

    public Color() {

    }

    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Color [car=" + car + "]";
    }
}
