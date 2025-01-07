package io.shock.bootlab.service;

import io.shock.bootlab.dto.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    public List<Car> getCars() {
        return List.of(
                Car.of("Toyota", 2020, 3.7),
                Car.of("Honda", 2021, 4.9),
                Car.of("Ford", 2019, 4.2),
                Car.of("Chevrolet", 2018, 3.8)
        );
    }
}
