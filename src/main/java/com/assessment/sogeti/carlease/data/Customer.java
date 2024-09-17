package com.assessment.sogeti.carlease.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
    private String name;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String place;
    private String emailAddress;
    private String phoneNumber;

    // Default constructor
    public Customer() {
    }
    
    // Constructors, getters, and setters
    public Customer(String name, String street, String houseNumber, String zipCode, String place, String emailAddress, String phoneNumber) {       
    	this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.place = place;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getHouseNumber() { return houseNumber; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
