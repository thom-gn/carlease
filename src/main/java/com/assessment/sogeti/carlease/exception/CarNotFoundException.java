package com.assessment.sogeti.carlease.exception;

public class CarNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CarNotFoundException(int id) {
        super("Car not found with ID: " + id);
    }
}
