package com.assessment.sogeti.carlease.controller.api;

import com.assessment.sogeti.carlease.data.CarLease;
import com.assessment.sogeti.carlease.exception.CarLeaseNotFoundException;
import com.assessment.sogeti.carlease.exception.CarNotFoundException;
import com.assessment.sogeti.carlease.exception.CustomerNotFoundException;
import com.assessment.sogeti.carlease.service.CarLeaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carleases")
public class CarLeaseApiController {

    private final CarLeaseService carLeaseService;

    public CarLeaseApiController(CarLeaseService carLeaseService) {
        this.carLeaseService = carLeaseService;
    }

    @GetMapping
    public ResponseEntity<List<CarLease>> getAllCarLeases() {
        List<CarLease> carLeases = carLeaseService.getAllCarLeases();
        return ResponseEntity.ok(carLeases);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarLease> getCarLeaseById(@PathVariable int id) {
    	try {
            CarLease carLease = carLeaseService.getCarLeaseById(id);
            return ResponseEntity.ok(carLease);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CarLease> addCarLease(@RequestBody CarLease carLease) {
        CarLease createdCarLease = carLeaseService.addCarLease(carLease);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCarLease);
    }

    // Update an existing car lease
    @PutMapping("/update/{id}")
    public ResponseEntity<CarLease> updateCarLease(@PathVariable int id, @RequestBody CarLease carLease) {
        try {
            CarLease updatedCarLease = carLeaseService.updateCarLease(id, carLease);
            return ResponseEntity.ok(updatedCarLease);
        } catch (CarLeaseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 for CarLease not found
        } catch (CarNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 400 for Car not found
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null); // 400 for Customer not found
        } catch (RuntimeException e) {
            // Return status NOT_FOUND if the CarLease, Car, or Customer is not found
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            // Optionally handle other unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 for unexpected errors
        }
    }

    // Delete a car lease
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCarLease(@PathVariable int id) {
        try {
            carLeaseService.removeCarLease(id);
            return ResponseEntity.ok().build();
        } catch (CarLeaseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 for CarLease not found
        } catch (Exception e) {
            // Optionally handle other unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 for unexpected errors
        }
    }
}
