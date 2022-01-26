package com.example.demo.rest;

import com.example.demo.CarService;
import com.example.demo.entity.Car;
import com.example.demo.mapper.CarMapper;
import com.example.demo.repository.CarRepository;
import com.example.demo.rest.model.CarDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MyFirstRestController {

    //TODO: Dependency injection
    //TODO: Optional
    //TODO: lambda expressions
    //TODO: use DTOs

    private static final Logger logger = LogManager.getLogger(MyFirstRestController.class);

    @Autowired
    CarRepository carRepository;

    @Autowired
    CarService carService;

    @Autowired
    CarMapper carMapper;

    @GetMapping("/get-cars")
    public ResponseEntity<Iterable<Car>> getCars() {
        logger.info("Getting all cars");
        return new ResponseEntity<>(carRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get-car/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        final Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.map(car -> new ResponseEntity<>(carMapper.fromEntityToDto(car), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/create-car")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        try {
            return new ResponseEntity<>(carRepository.save(car), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            logger.error("DataIntegrityViolationException: car {} already exists", car.getName());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-car/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        //TODO: TRY-CATCH
        carRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/update-car/{id}")
    public ResponseEntity<Void> updateCar(@PathVariable Long id, @RequestBody Car car) {
        carRepository.update(car.getName(), id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/create-random-car")
    public ResponseEntity<Car> createRandomCar() {
        logger.info("Example of transactional rollback");
        try {
            return new ResponseEntity<>(carService.customSave(), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            logger.error("DataIntegrityViolationException: car already exists");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
