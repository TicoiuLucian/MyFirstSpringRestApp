package com.example.demo.mapper;


import com.example.demo.entity.Car;
import com.example.demo.rest.model.CarDTO;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {


    public Car fromDtoToEntity(CarDTO carDto) {
        return new Car(carDto.getId(), carDto.getName());
    }

    public CarDTO fromEntityToDto(Car car) {
        return new CarDTO(car.getId(), car.getName(), "E X A M P L E");
    }
}
