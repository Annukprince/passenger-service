package com.example.passenger.client;

import lombok.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "transportation", url = "http://localhost:8100")
public interface BusClient {

    @GetMapping("/buses/{id}")
    BusDTO getBusById(@PathVariable("id") Long id);

    @GetMapping("/buses/route")
    List<BusDTO> getBusesByRoute(@RequestParam("route") String route);

    @PutMapping("/buses/{id}/occupancy")
    void updateOccupancy(@PathVariable("id") Long id, @RequestParam("change") int change);

    class BusDTO {
        private Long id;
        private String busName;
        private double capacity;
        private int occupancy;
        private String currentLocation;
        private boolean isOperating;
        private List<String> routes;
        private double scheduledArrivalTime;
        private double actualArrivalTime;

        // Getters and Setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getBusName() {
            return busName;
        }

        public void setBusName(String busName) {
            this.busName = busName;
        }

        public double getCapacity() {
            return capacity;
        }

        public void setCapacity(double capacity) {
            this.capacity = capacity;
        }

        public int getOccupancy() {
            return occupancy;
        }

        public void setOccupancy(int occupancy) {
            this.occupancy = occupancy;
        }

        public String getCurrentLocation() {
            return currentLocation;
        }

        public void setCurrentLocation(String currentLocation) {
            this.currentLocation = currentLocation;
        }

        public boolean isOperating() {
            return isOperating;
        }

        public void setOperating(boolean operating) {
            isOperating = operating;
        }

        public List<String> getRoutes() {
            return routes;
        }

        public void setRoutes(List<String> routes) {
            this.routes = routes;
        }

        public double getScheduledArrivalTime() {
            return scheduledArrivalTime;
        }

        public void setScheduledArrivalTime(double scheduledArrivalTime) {
            this.scheduledArrivalTime = scheduledArrivalTime;
        }

        public double getActualArrivalTime() {
            return actualArrivalTime;
        }

        public void setActualArrivalTime(double actualArrivalTime) {
            this.actualArrivalTime = actualArrivalTime;
        }
    }
}