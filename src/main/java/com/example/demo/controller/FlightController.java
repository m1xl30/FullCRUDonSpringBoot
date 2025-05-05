package com.example.demo.controller;

import com.example.demo.model.FlightModel;
import com.example.demo.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RequestMapping("/controller")
@RestController
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping("/createByURL/{id}/{flightNumber}/{destination}")
    public ResponseEntity<?> createByURL(
            @PathVariable("id")Long id,
            @PathVariable("flightNumber")String flightNumber,
            @PathVariable("destination")String destination){
        try{
            FlightModel createdByURL = new FlightModel();
            System.out.println(id + flightNumber + destination);
            createdByURL.setId(id);
            createdByURL.setFlightNumber(flightNumber);
            createdByURL.setDestination(destination);
            FlightModel createdFlight = flightService.createFlight(createdByURL);
            return ResponseEntity.ok(createdFlight);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to create a flight "+ e.getMessage());
        }
    }


    @PostMapping("/create")
    public ResponseEntity<?> createFlight(@RequestBody List<FlightModel> flightModels){ //mapping from query to an Object
        try{
            if( flightModels==null || flightModels.isEmpty() ){
                return ResponseEntity.badRequest().body("List is empty");
            }
            else{
                for (FlightModel flightModel : flightModels){
                    FlightModel createdFlight = flightService.createFlight(flightModel);
                }
                return ResponseEntity.ok("Flights created succesfully");

            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to create a flight "+ e.getMessage());
        }
    }

    //ok
    @GetMapping("/getAll")
    public ResponseEntity<List<FlightModel>> getAll(){
        List<FlightModel> ArrayMod = flightService.getAllFlights();
        return ResponseEntity.ok(ArrayMod);
    }
    //ok
    @GetMapping("/get/{id}")
    public ResponseEntity<FlightModel> getById(@PathVariable Long id){//binding from id we got
        Optional<FlightModel> flightById = flightService.getById(id);// Type optional cuz of JPA Repository
        return flightById.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());//what is this
    }

    @PutMapping("/put")
    public ResponseEntity<?> updateFlight(@RequestBody FlightModel flightModel){
        try{
            FlightModel updatedFlight = flightService.updateFlight(flightModel);
            return ResponseEntity.ok("Put successfully "+updatedFlight);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Put failed");
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id){
        try{
            flightService.delete(id);
            return ResponseEntity.ok().body("Delete success");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Delete failed");
        }

    }
    
}
