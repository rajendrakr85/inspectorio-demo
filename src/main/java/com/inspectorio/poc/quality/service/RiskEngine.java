package com.inspectorio.poc.quality.service;

import com.inspectorio.poc.common.event.SupplierRiskEvent;
import com.inspectorio.poc.quality.model.Defect;
import com.inspectorio.poc.quality.model.Inspection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiskEngine {

    private final ApplicationEventPublisher eventPublisher;

    /**
     * AI-mimic algorithm to calculate risk impact based on inspection results.
     */
    public void analyzeInspectionResult(Inspection inspection) {
        log.info("Risk Engine analyzing inspection {}", inspection.getId());

        int riskImpact = 0;
        List<Defect> defects = inspection.getDefects();

        long criticalDefects = defects.stream()
                .filter(d -> d.getSeverity() == Defect.DefectSeverity.CRITICAL)
                .count();

        long majorDefects = defects.stream()
                .filter(d -> d.getSeverity() == Defect.DefectSeverity.MAJOR)
                .count();

        if (criticalDefects > 0) {
            riskImpact = -20; // High negative impact
            log.warn("Critical defects found! Decreasing supplier score.");
        } else if (majorDefects > 2) {
            riskImpact = -10;
        } else if (defects.isEmpty()) {
            riskImpact = 5; // Reward for clean inspection
        }

        if (riskImpact != 0) {
            eventPublisher.publishEvent(new SupplierRiskEvent(
                    inspection.getSupplierId(),
                    riskImpact,
                    "Inspection " + inspection.getId() + " Analysis"));
        }
    }

    /**
     * Determines AQL Level based on current Supplier Risk Score.
     * In a real app, this would query the Supplier Service or use a cached score.
     * For POC, we'll assume a default or pass it in.
     */
    public String determineAqlLevel(int currentSupplierRiskScore) {
        if (currentSupplierRiskScore < 40) {
            return "LEVEL_III (Strict)"; // High Risk Supplier -> Strict Inspection
        } else if (currentSupplierRiskScore > 80) {
            return "LEVEL_I (Reduced)"; // Low Risk Supplier -> Reduced Inspection
        }
        return "LEVEL_II (Standard)";
    }
}
