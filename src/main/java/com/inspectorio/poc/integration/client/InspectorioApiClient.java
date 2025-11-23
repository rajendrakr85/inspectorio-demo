package com.inspectorio.poc.integration.client;

import com.inspectorio.poc.compliance.model.ComplianceAudit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class InspectorioApiClient {

    private final RestClient inspectorioRestClient;

    public void createBooking(ComplianceAudit audit) {
        log.info("Sending booking request to Inspectorio for audit: {}", audit.getId());

        // Example payload mapping
        var payload = new BookingRequest(
                audit.getSupplierId(),
                audit.getAuditDate(),
                "Initial Booking");

        try {
            inspectorioRestClient.post()
                    .uri("/bookings")
                    .body(payload)
                    .retrieve()
                    .toBodilessEntity();
            log.info("Successfully created booking in Inspectorio");
        } catch (Exception e) {
            log.error("Failed to create booking in Inspectorio", e);
            throw new RuntimeException("Integration failed", e);
        }
    }

    // Inner record for the payload
    record BookingRequest(Long supplierId, java.time.LocalDate requestedDate, String notes) {
    }
}
