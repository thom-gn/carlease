package com.assessment.sogeti.carlease.repository;

import com.assessment.sogeti.carlease.data.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
