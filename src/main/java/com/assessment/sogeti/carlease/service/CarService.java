package com.assessment.sogeti.carlease.service;

import com.assessment.sogeti.carlease.data.Car;
import com.assessment.sogeti.carlease.exception.CarNotFoundException;
import com.assessment.sogeti.carlease.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car getCarById(int id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    public List<Car> getAllCars() {
        Collection<Car> cars = carRepository.findAll();
        return cars.stream().collect(Collectors.toList());
    }

    public Car updateCar(int id, Car car) {
        if (carRepository.existsById(id)) {
            car.setId(id);
            return carRepository.save(car);
        } else {
            throw new CarNotFoundException(id);
        }
    }

    public void removeCar(int id) {
        carRepository.deleteById(id);
    }
}
