package com.fmi.rent_a_car.controllers;

import com.fmi.rent_a_car.entities.Car;
import com.fmi.rent_a_car.http.AppResponse;
import com.fmi.rent_a_car.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CarController {
    private final CarService carService;
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<?> getAllCarsByClientLocation(@RequestParam String clientLocation) {

        ArrayList<Car> collection = (ArrayList<Car>) this.carService.getAllCarsByClientLocation(clientLocation);

        return AppResponse.success().withData(collection).build();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<?> getCarById(@PathVariable int id) {
        Car carResult = this.carService.getCarById(id);

        if (carResult == null) {
            return AppResponse.error().withMessage("Car data not found").build();
        }

        return AppResponse.success().withData(carResult).build();
    }

    @PostMapping("/cars")
    public ResponseEntity<?> createCar(@RequestBody Car car) {
        if (this.carService.createCar(car)) {
            return AppResponse.success().withMessage("Car created successfully").build();
        }

        return AppResponse.error()
                .withMessage("Car could not be created")
                .build();
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<?> updateCar(@PathVariable int id, @RequestBody Car car) {
        boolean isUpdateSuccessful =  this.carService.updateCar(id, car);

        if(!isUpdateSuccessful) {
            return AppResponse.error()
                    .withMessage("Car data not found")
                    .build();
        }

        return AppResponse.success()
                .withMessage("Update successful")
                .build();
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable int id) {
        boolean isDeleteSuccessful = this.carService.deleteCar(id);

        if (!isDeleteSuccessful) {
            return AppResponse.error()
                    .withMessage("Car data not found")
                    .build();
        }

        return AppResponse.success()
                .withMessage("Car deleted successfully")
                .build();
    }
}
