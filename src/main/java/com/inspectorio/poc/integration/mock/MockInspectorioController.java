package com.inspectorio.poc.integration.mock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mock/inspectorio/v1")
@Profile("!prod") // Active in dev/test profiles
@Slf4j
public class MockInspectorioController {

    @PostMapping("/bookings")
    public ResponseEntity<String> createBooking(@RequestBody String body) {
        log.info("[MOCK] Received booking request: {}", body);
        return ResponseEntity.ok("{\"id\": \"mock-booking-123\", \"status\": \"CREATED\"}");
    }
}
