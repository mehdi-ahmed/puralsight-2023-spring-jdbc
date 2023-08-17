package com.mytutos.spring.springjdbc5.controller;

import com.mytutos.spring.springjdbc5.model.Ride;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

class RideControllerTest {

    @Test()
    @Timeout(300)
    public void testGetRides() {

        RestTemplate restTemplate = new RestTemplate();

        val response = restTemplate.exchange("http://localhost:8080/ride_tracker/rides",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Ride>>() {
                });

        List<Ride> body = response.getBody();
        if (body != null) {
            for (Ride ride : body) {
                System.out.println("ride = " + ride);
            }
        }
    }

    @Test()
    @Timeout(300)
    public void testCreateRide() {

        RestTemplate restTemplate = new RestTemplate();

        Ride ride = new Ride();
        ride.setName("Koh Samui");
        ride.setDuration(9);

        ride = restTemplate.postForObject("http://localhost:8080/ride_tracker/ride", ride, Ride.class);
        System.out.println("ride = " + ride);
    }

    @Test()
    @Timeout(300)
    public void testGetRide() {

        RestTemplate restTemplate = new RestTemplate();

        Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/6", Ride.class);
        System.out.println("ride = " + ride);
    }

    @Test()
    @Timeout(300)
    public void testUpdateRide() {

        RestTemplate restTemplate = new RestTemplate();

        Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/6", Ride.class);
        if (ride != null) {
            ride.setDuration(999);
        }

        restTemplate.put("http://localhost:8080/ride_tracker/ride", ride);
        System.out.println("ride = " + ride);
    }

    @Test()
    @Timeout(300)
    public void testBatchUpdate() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("http://localhost:8080/ride_tracker/batch", Object.class);
    }

    @Test()
    @Timeout(300)
    public void testDeleteRide() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8080/ride_tracker/delete/11");
    }

    @Test()
    @Timeout(300)
    public void testException() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("http://localhost:8080/ride_tracker/test", Ride.class);
    }
}