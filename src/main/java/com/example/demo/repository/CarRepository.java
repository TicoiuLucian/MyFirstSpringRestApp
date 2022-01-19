package com.example.demo.repository;

import com.example.demo.entity.Car;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    private List<Car> cars = new ArrayList<>();

    public List<Car> getAllCars() {
        System.out.println("Getting all cars...");
        return this.cars;
    }

    public void addCar(Car car) {
        this.cars.add(car);
        System.out.println("Added a new car: " + car);
    }

    public Car getCarById(int id) {
        return getCar(id);
    }

    public void deleteCar(int id) {
        Car car = getCar(id);
        System.out.println("Removing car: " + car);
        this.cars.remove(car);
    }

    public void updateCar(int id, Car car) {
        Car oldCar = getCar(id);
        System.out.println("Updating car: " + oldCar);
        System.out.println("New car: " + car);
        this.cars.set(this.cars.indexOf(oldCar), car);
    }

    private Car getCar(int id) {
        System.out.println("Getting car with id: " + id);
        for (Car car : this.cars) {
            if (car.getId() == id) {
                return car;
            }
        }
        return new Car();
    }
}
