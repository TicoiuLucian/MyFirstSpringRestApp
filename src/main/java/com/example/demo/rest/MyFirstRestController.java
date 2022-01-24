package com.example.demo.rest;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RestController
public class MyFirstRestController {

    //TODO: Dependency injection
    @Autowired
    CarRepository carRepository;

    @GetMapping("/get-cars")
    public ResponseEntity<Iterable<Car>> getCars() {
        return new ResponseEntity<>(carRepository.findAll(), HttpStatus.OK);
    }

    //TODO: Optional
    //TODO: lambda expressions
    @GetMapping("/get-car/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        final Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.map(car -> new ResponseEntity<>(car, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    //TODO: use DTOs
    //TODO: add logging
    @PostMapping("/create-car")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        try {
            return new ResponseEntity<>(carRepository.save(car), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            //TODO: use logger
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @DeleteMapping("/delete-car/{id}")
//    public void deleteCar(@PathVariable int id) {
//        carRepository.deleteCar(id);
//
//    }
//
//    @PutMapping("/update-car/{id}")
//    public void updateCar(@PathVariable int id, @RequestBody Car car) {
//        carRepository.updateCar(id, car);
//    }
}
