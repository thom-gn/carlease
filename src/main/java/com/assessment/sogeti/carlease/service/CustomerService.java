package com.assessment.sogeti.carlease.service;

import com.assessment.sogeti.carlease.data.Customer;
import com.assessment.sogeti.carlease.exception.CustomerNotFoundException;
import com.assessment.sogeti.carlease.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
    	System.out.println("asdfsadaadeeed");
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public List<Customer> getAllCustomers() {
        Collection<Customer> customers = customerRepository.findAll();
        return customers.stream().collect(Collectors.toList());
    }

    public Customer updateCustomer(int id, Customer customer) {
        if (customerRepository.existsById(id)) {
            customer.setId(id);
            return customerRepository.save(customer);
        } else {
            throw new CustomerNotFoundException(id);
        }
    }

    public void removeCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
