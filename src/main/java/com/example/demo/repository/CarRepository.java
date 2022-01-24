package com.example.demo.repository;

import com.example.demo.entity.Car;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    @Transactional
    @Modifying
    @Query("update Car c set c.name = ?1 where c.id = ?2")
    void update(String name, Long id);

}
