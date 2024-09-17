package com.assessment.sogeti.carlease.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String make;
    private String model;
    private String version;
    private int numberOfDoors;
    private double co2Emission;
    private double grossPrice;
    private double nettPrice;
  
    // Default constructor
    public Car() {
    }
    
    // Constructors, getters, and setters
    public Car(String make, String model, String version, int numberOfDoors, double co2Emission, double grossPrice, double nettPrice) {
    	this.make = make;
        this.model = model;
        this.version = version;
        this.numberOfDoors = numberOfDoors;
        this.co2Emission = co2Emission;
        this.grossPrice = grossPrice;
        this.nettPrice = nettPrice;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public int getNumberOfDoors() { return numberOfDoors; }
    public void setNumberOfDoors(int numberOfDoors) { this.numberOfDoors = numberOfDoors; }

    public double getCo2Emission() { return co2Emission; }
    public void setCo2Emission(double co2Emission) { this.co2Emission = co2Emission; }

    public double getGrossPrice() { return grossPrice; }
    public void setGrossPrice(double grossPrice) { this.grossPrice = grossPrice; }

    public double getNettPrice() { return nettPrice; }
    public void setNettPrice(double nettPrice) { this.nettPrice = nettPrice; }
}
