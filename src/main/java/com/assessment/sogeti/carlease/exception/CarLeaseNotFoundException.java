package com.assessment.sogeti.carlease.exception;

public class CarLeaseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CarLeaseNotFoundException(int id) {
        super("CarLease with ID " + id + " not found.");
    }
}
