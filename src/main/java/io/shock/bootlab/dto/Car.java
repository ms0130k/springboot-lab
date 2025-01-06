package io.shock.bootlab.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
    private String name;
    private int year;

    public static Car of(String name, int year) {
        Car car = new Car();
        car.setName(name);
        car.setYear(year);
        return car;
    }
}
