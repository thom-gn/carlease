package com.assessment.sogeti.carlease.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "car_leases")
public class CarLease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Customer customer;

    private double mileage;
    private int duration;
    private double interestRate;

    // Default constructor
    public CarLease() {
    }

    // Constructors, getters, and setters
    public CarLease(Car car, Customer customer, double mileage, int duration, double interestRate) {
        this.car = car;
        this.customer = customer;
        this.mileage = mileage;
        this.duration = duration;
        this.interestRate = interestRate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public double getMileage() { return mileage; }
    public void setMileage(double mileage) { this.mileage = mileage; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }
    
    public double calculateLeaseRate() {
        if (car == null || car.getNettPrice() == 0) {
            throw new IllegalArgumentException("Car or Nett Price not set");
        }

        double nettPrice = car.getNettPrice();
        double leaseRate = (((mileage / 12.0) * duration) / nettPrice) + (((interestRate / 100.0) * nettPrice) / 12.0);

        // Round the result to 2 decimal places
        BigDecimal bd = new BigDecimal(leaseRate).setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
 
    }
}
