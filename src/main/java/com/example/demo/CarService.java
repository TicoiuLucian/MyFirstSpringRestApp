package com.example.demo;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Transactional
    public Car customSave() {
        carRepository.save(new Car("CAR 0"));
        carRepository.save(new Car("CAR_exception"));
        carRepository.save(new Car("Car 0.5"));
        carRepository.save(new Car("CAR_exception"));
        carRepository.save(new Car("CAR 1"));
        carRepository.save(new Car("CAR 2"));
        return new Car();
    }

}
