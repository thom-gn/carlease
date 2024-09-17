package com.assessment.sogeti.carlease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.sogeti.carlease.data.CarLease;

@Repository
public interface CarLeaseRepository extends JpaRepository<CarLease, Integer> {
}
