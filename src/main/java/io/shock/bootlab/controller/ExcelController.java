package io.shock.bootlab.controller;

import io.shock.bootlab.dto.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class ExcelController {
    @GetMapping("/json")
    @ResponseBody
    public List<Car> getJson() {
        List<Car> cars = List.of(
                Car.of("Toyota", 2020),
                Car.of("Honda", 2021)
        );
        return cars;
    }
}
