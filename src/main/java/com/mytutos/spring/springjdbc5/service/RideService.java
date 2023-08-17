package com.mytutos.spring.springjdbc5.service;

import com.mytutos.spring.springjdbc5.model.Ride;
import com.mytutos.spring.springjdbc5.repository.RideRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RideService {

    private final RideRepository rideRepository;

    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public List<Ride> getRides() {
        return rideRepository.getRides();
    }

    public Ride getRide(Integer id) {
        return rideRepository.getRide(id);
    }

    public Ride createRide(Ride ride) {
        return rideRepository.createRide(ride);
    }

    public Ride updateRide(Ride id) {
        return rideRepository.updateRide(id);
    }

    @Transactional
    public void batch() {
        List<Ride> rides = rideRepository.getRides();
        List<Object[]> pairs = new ArrayList<>();
        for (Ride ride : rides) {
            Object[] tmp = {new Date(), ride.getId()};
            pairs.add(tmp);
        }

        rideRepository.updateRides(pairs);
        throw new DataAccessException("Testing Exception handling") { };
    }

    public void deleteRide(Integer id) {
        rideRepository.deleteRide(id);
    }
}
