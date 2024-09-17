package com.assessment.sogeti.carlease.service;

import com.assessment.sogeti.carlease.data.CarLease;
import com.assessment.sogeti.carlease.data.Car;
import com.assessment.sogeti.carlease.data.Customer;
import com.assessment.sogeti.carlease.repository.CarLeaseRepository;
import com.assessment.sogeti.carlease.repository.CarRepository;
import com.assessment.sogeti.carlease.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarLeaseService {

    private final CarLeaseRepository carLeaseRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public CarLeaseService(CarLeaseRepository carLeaseRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.carLeaseRepository = carLeaseRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public List<CarLease> getAllCarLeases() {
        return carLeaseRepository.findAll();
    }

    public CarLease getCarLeaseById(int id) {
        return carLeaseRepository.findById(id).orElseThrow(() -> new RuntimeException("CarLease not found"));
    }

    public void addCarLease(CarLease carLease) {
        // Ensure Car and Customer exist
        Car car = carRepository.findById(carLease.getCar().getId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        Customer customer = customerRepository.findById(carLease.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        carLease.setCar(car);
        carLease.setCustomer(customer);
        carLeaseRepository.save(carLease);
    }

    public void updateCarLease(int id, CarLease carLease) {
        if (!carLeaseRepository.existsById(id)) {
            throw new RuntimeException("CarLease not found");
        }

        Car car = carRepository.findById(carLease.getCar().getId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        Customer customer = customerRepository.findById(carLease.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        carLease.setId(id);
        carLease.setCar(car);
        carLease.setCustomer(customer);
        carLeaseRepository.save(carLease);
    }

    public void removeCarLease(int id) {
        carLeaseRepository.deleteById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
}
