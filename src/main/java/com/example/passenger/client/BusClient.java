package com.example.passenger.client;

import lombok.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "transportation", url = "http://localhost:8100")
public interface BusClient {

    @PutMapping("/api/buses/{busId}/occupancy")
    void updateOccupancy(@PathVariable("busId") Long busId, @RequestParam("change") int change);

    @GetMapping("/api/buses/{busId}")
    BusDTO getBusById(@PathVariable("busId") Long busId);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    class BusDTO {
        private Long id;
        private String busName;
        private String route;
        private double capacity;
        private int occupancy;
        private String currentLocation;
        private boolean isOperating;
    }
}