package org.deblock.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
public class FlightController {


    @GetMapping("/flights")
    public ResponseEntity<String> getFlights(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String outboundDate,
            @RequestParam String inboundDate,
            @RequestParam int numberOfAdults) {

        // Log the input parameters (optional, for debugging purposes)
        System.out.println("Origin: " + from);
        System.out.println("Destination: " + to);
        System.out.println("Departure Date: " + outboundDate);
        System.out.println("Return Date: " + inboundDate);
        System.out.println("Number of Passengers: " + numberOfAdults);

        System.out.println("from file : though-jet.json");

        // Load the JSON file from the classpath
        Resource resource = new ClassPathResource("tough-jet.json");
        try (InputStream inputStream = resource.getInputStream()) {
            // Convert InputStream to String
            String jsonResponse = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

            return new ResponseEntity<>(jsonResponse, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error reading JSON file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
