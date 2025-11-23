package com.inspectorio.poc.integration.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks/inspectorio")
@Slf4j
public class InspectorioWebhookController {

    @Value("${inspectorio.webhook.secret}")
    private String webhookSecret;

    @PostMapping("/events")
    public ResponseEntity<String> handleEvent(
            @RequestBody String payload,
            @RequestHeader(value = "X-Inspectorio-Signature", required = false) String signature) {

        log.info("Received webhook event from Inspectorio");
        log.debug("Payload: {}", payload);

        // In a real app, verify the signature using the webhookSecret
        if (signature != null) {
            log.info("Verifying signature: {}", signature);
        }

        // Process the event (e.g., parse JSON and update local entities)
        // For POC, we just log it.

        return ResponseEntity.ok("Event received");
    }
}
