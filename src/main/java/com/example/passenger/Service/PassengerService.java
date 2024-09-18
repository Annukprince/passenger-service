package com.example.passenger.Service;

import com.example.passenger.client.BusClient;
import com.example.passenger.domain.Passenger;
import com.example.passenger.dto.PassengerDto;
import com.example.passenger.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final BusClient busClient;

    public PassengerService(PassengerRepository passengerRepository, BusClient busClient) {
        this.passengerRepository = passengerRepository;
        this.busClient = busClient;
    }

    public List<PassengerDto> getAllPassengers() {
        return passengerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PassengerDto> getPassengerById(Long id) {
        return passengerRepository.findById(id)
                .map(this::convertToDTO);
    }

    public PassengerDto createPassenger(PassengerDto passengerDto) {
        BusClient.BusDTO busDTO = busClient.getBusById(passengerDto.getBusId());
        if (busDTO.getOccupancy() < busDTO.getCapacity()) {
            busClient.updateOccupancy(passengerDto.getBusId(), 1);

            Passenger passenger = convertToEntity(passengerDto);
            Passenger savedPassenger = passengerRepository.save(passenger);
            return convertToDTO(savedPassenger);
        } else {
            throw new RuntimeException("Cannot add passenger. Bus is full.");
        }
    }

    public PassengerDto updatePassenger(Long id, PassengerDto passengerDto) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        passenger.setName(passengerDto.getName());
        passenger.setBusId(passengerDto.getBusId());

        Passenger updatedPassenger = passengerRepository.save(passenger);
        return convertToDTO(updatedPassenger);
    }

    public void deletePassenger(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        busClient.updateOccupancy(passenger.getBusId(), -1);
        passengerRepository.deleteById(id);
    }

    public List<PassengerDto> getPassengersByBusId(Long busId) {
        return passengerRepository.findByBusId(busId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PassengerDto convertToDTO(Passenger passenger) {
        PassengerDto dto = new PassengerDto();
        dto.setId(passenger.getId());
        dto.setName(passenger.getName());
        dto.setBusId(passenger.getBusId());
        return dto;
    }

    private Passenger convertToEntity(PassengerDto dto) {
        Passenger passenger = new Passenger();
        passenger.setId(dto.getId());
        passenger.setName(dto.getName());
        passenger.setBusId(dto.getBusId());
        return passenger;
    }
}
