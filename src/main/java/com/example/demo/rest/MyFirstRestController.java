package com.example.demo.rest;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
public class MyFirstRestController {

    CarRepository carRepository = new CarRepository();

    @GetMapping("/get-cars")
    public List<Car> getCars() {
        return carRepository.getAllCars();
    }

    @GetMapping("/get-car/{id}")
    public Car getCarById(@PathVariable int id) {
        return carRepository.getCarById(id);
    }

    @PostMapping("/create-car")
    public void createCar(@RequestBody Car car) {
        carRepository.addCar(car);
    }

    @DeleteMapping("/delete-car/{id}")
    public void deleteCar(@PathVariable int id) {
        carRepository.deleteCar(id);

    }

    @PutMapping("/update-car/{id}")
    public void updateCar(@PathVariable int id, @RequestBody Car car) {
        carRepository.updateCar(id, car);
    }
}
