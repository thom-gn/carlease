package com.assessment.sogeti.carlease.controller.api;

import com.assessment.sogeti.carlease.data.Car;
import com.assessment.sogeti.carlease.service.CarService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars") // API endpoint for JSON responses
public class CarApiController {

    private final CarService carService;

    public CarApiController(CarService carService) {
        this.carService = carService;
    }

    // Return all cars as JSON
    @GetMapping(produces = "application/json")
    public List<Car> getAllCarsApi() {
        return carService.getAllCars();
    }

    // Return car by ID as JSON
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Car> getCarByIdApi(@PathVariable int id) {
        Car car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }

    // Add a new car via JSON API
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Car> addCarApi(@RequestBody Car car) {
        Car createdCar = carService.addCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    // Update car via JSON API
    @PutMapping(value = "/update/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Car> updateCarApi(@PathVariable int id, @RequestBody Car car) {
        carService.updateCar(id, car);
        return ResponseEntity.ok(car);
    }

    // Delete car via JSON API
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCarApi(@PathVariable int id) {
        carService.removeCar(id);
        return ResponseEntity.ok().build();
    }
}
