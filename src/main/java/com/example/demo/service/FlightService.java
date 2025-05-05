package com.example.demo.service;

import com.example.demo.model.FlightModel;
import com.example.demo.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    //Create
    public FlightModel createFlight(FlightModel flightModel){
        return flightRepository.save(flightModel);
    }
    //Read
    public List<FlightModel> getAllFlights(){
        return flightRepository.findAll();
    }
    //Read by id
    public Optional<FlightModel> getById(Long id){
        return flightRepository.findById(id);
    }

    //delete
    public void delete(Long id){
        flightRepository.deleteById(id);
    }
    //update
    public FlightModel updateFlight(FlightModel flightModel){
        if (flightRepository.existsById(flightModel.getId())){
            return flightRepository.save(flightModel);
        }
        return null;
    }
}
