package io.shock.bootlab.dto;

import io.shock.bootlab.service.excel.ExcelColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Car {
    @ExcelColumn
    private String carName;
    @ExcelColumn(header = "YEAR")
    private int year;
    @ExcelColumn(header = "RATING")
    private double rating;

    public static Car of(String carName, int year, double rating) {
        return new Car(carName, year, rating);
    }
}
