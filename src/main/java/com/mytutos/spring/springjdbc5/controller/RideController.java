package com.mytutos.spring.springjdbc5.controller;

import com.mytutos.spring.springjdbc5.model.Ride;
import com.mytutos.spring.springjdbc5.service.RideService;
import com.mytutos.spring.springjdbc5.util.ServiceError;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping("/rides")
    public List<Ride> getRides() {
        return rideService.getRides();
    }

    @GetMapping("/ride/{id}")
    public Ride getRide(@PathVariable(value = "id") Integer id) {
        return rideService.getRide(id);
    }

    @PutMapping("/ride")
    public Ride updateRide(@RequestBody Ride ride) {
        return rideService.updateRide(ride);
    }

    @PostMapping("/ride")
    public Ride createRide(@RequestBody Ride ride) {
        return rideService.createRide(ride);
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteRide(@PathVariable(value = "id") Integer id) {
        rideService.deleteRide(id);
        return null;
    }

    @GetMapping("/batch")
    public Object batchUpdate() {
        rideService.batch();
        return null;
    }

    @GetMapping("/test")
    public Object test() {
        throw new DataAccessException("Testing Exception thrown") {
        };
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ServiceError> handle(RuntimeException ex) {
        ServiceError serviceError = new ServiceError(HttpStatus.OK.value(), ex.getMessage());
        return new ResponseEntity<>(serviceError, HttpStatus.OK);
    }
}


