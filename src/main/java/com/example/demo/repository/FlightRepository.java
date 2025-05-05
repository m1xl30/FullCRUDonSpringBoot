package com.example.demo.repository;

import com.example.demo.model.FlightModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<FlightModel,Long> {
    //extends for crud
}
