package com.assessment.sogeti.carlease.controller;

import com.assessment.sogeti.carlease.data.Customer;
import com.assessment.sogeti.carlease.service.CustomerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers") // Map all requests starting with /customers for HTML views
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Return HTML view for "/customers"
    @GetMapping
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers/customers";
    }

    // Return HTML form to add a new customer
    @GetMapping("/add")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer(null, null, null, null, null, null, null));
        return "customers/add-customer";
    }

    // Return HTML form to update a customer
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customers/update-customer";
    }

    // Add a new customer via HTML form
    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.addCustomer(customer);
        return "redirect:/customers";
    }

    // Update customer via HTML form
    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer customer) {
        customerService.updateCustomer(customer.getId(), customer);
        return "redirect:/customers";
    }

    // Delete customer (HTML redirect)
    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.removeCustomer(id);
        return "redirect:/customers";
    }
}
