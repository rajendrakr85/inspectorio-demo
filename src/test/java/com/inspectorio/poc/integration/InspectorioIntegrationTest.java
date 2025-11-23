package com.inspectorio.poc.integration;

import com.inspectorio.poc.compliance.model.ComplianceAudit;
import com.inspectorio.poc.integration.client.InspectorioApiClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server.port=9999")
@ActiveProfiles("test")
public class InspectorioIntegrationTest {

    @Autowired
    private InspectorioApiClient inspectorioApiClient;

    @TestConfiguration
    static class TestConfig {
        @Bean
        @Primary
        public RestClient testRestClient(RestClient.Builder builder) {
            return builder
                    .baseUrl("http://localhost:9999/mock/inspectorio/v1")
                    .defaultHeader("Authorization", "Bearer test-key")
                    .defaultHeader("Content-Type", "application/json")
                    .build();
        }
    }

    @Test
    void testCreateBooking() {
        ComplianceAudit audit = new ComplianceAudit();
        audit.setId(1L);
        audit.setSupplierId(100L);
        audit.setAuditDate(LocalDate.now());

        assertDoesNotThrow(() -> inspectorioApiClient.createBooking(audit));
    }
}
