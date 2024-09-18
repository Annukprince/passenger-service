package com.example.passenger.controller;

import com.example.passenger.dto.PassengerDto;
import com.example.passenger.Service.PassengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
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
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable Long id) {
        Optional<PassengerDto> passengerDto = passengerService.getPassengerById(id);
        return passengerDto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PassengerDto> createPassenger(@RequestBody PassengerDto passengerDto) {
        try {
            PassengerDto createdPassenger = passengerService.createPassenger(passengerDto);
            return ResponseEntity.ok(createdPassenger);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(new PassengerDto()); // Return an appropriate response
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassengerDto> updatePassenger(@PathVariable Long id, @RequestBody PassengerDto passengerDto) {
        try {
            PassengerDto updatedPassenger = passengerService.updatePassenger(id, passengerDto);
            return ResponseEntity.ok(updatedPassenger);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        try {
            passengerService.deletePassenger(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/bus/{busId}")
    public List<PassengerDto> getPassengersByBusId(@PathVariable Long busId) {
        return passengerService.getPassengersByBusId(busId);
    }
}
