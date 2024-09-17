package com.assessment.sogeti.carlease.controller.api;

import com.assessment.sogeti.carlease.data.Customer;
import com.assessment.sogeti.carlease.service.CustomerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers") // API endpoint for JSON responses
public class CustomerApiController {

    private final CustomerService customerService;

    public CustomerApiController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Return all customers as JSON
    @GetMapping(produces = "application/json")
    public List<Customer> getAllCustomersApi() {
        return customerService.getAllCustomers();
    }

    // Return customer by ID as JSON
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Customer> getCustomerByIdApi(@PathVariable int id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    // Add a new customer via JSON API
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Customer> addCustomerApi(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    // Update customer via JSON API
    @PutMapping(value = "/update/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Customer> updateCustomerApi(@PathVariable int id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(customer);
    }

    // Delete customer via JSON API
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomerApi(@PathVariable int id) {
        customerService.removeCustomer(id);
        return ResponseEntity.ok().build();
    }
}
