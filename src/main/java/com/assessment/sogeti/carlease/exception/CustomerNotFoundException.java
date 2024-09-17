package com.assessment.sogeti.carlease.exception;

public class CustomerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(int id) {
        super("Customer not found with ID: " + id);
    }
}
