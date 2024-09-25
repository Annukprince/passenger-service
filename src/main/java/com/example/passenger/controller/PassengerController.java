package com.example.passenger.controller;

import com.example.passenger.dto.PassengerDto;
import com.example.passenger.Service.PassengerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<PassengerDto> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public Optional<PassengerDto> getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public PassengerDto createPassenger(@RequestBody PassengerDto passengerDto) {
        return passengerService.createPassenger(passengerDto);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public PassengerDto updatePassenger(@PathVariable Long id, @RequestBody PassengerDto passengerDto) {
        return passengerService.updatePassenger(id, passengerDto);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }

    @GetMapping("/bus/{busId}")
    public List<PassengerDto> getPassengersByBusId(@PathVariable Long busId) {
        return passengerService.getPassengersByBusId(busId);
    }

    @GetMapping("/route")
    public List<PassengerDto> getPassengersForRoute(@RequestParam String fromLocation, @RequestParam String toLocation) {
        return passengerService.getPassengersForRoute(fromLocation, toLocation);
    }
}
