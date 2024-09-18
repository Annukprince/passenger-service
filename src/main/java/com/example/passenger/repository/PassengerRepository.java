package com.example.passenger.repository;

import com.example.passenger.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    List<Passenger> findByBusId(Long busId);
}