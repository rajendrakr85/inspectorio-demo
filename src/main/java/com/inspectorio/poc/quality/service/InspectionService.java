package com.inspectorio.poc.quality.service;

import com.inspectorio.poc.quality.model.Inspection;
import com.inspectorio.poc.quality.repository.InspectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InspectionService {

    private final InspectionRepository inspectionRepository;
    private final RiskEngine riskEngine;

    public Inspection scheduleInspection(Inspection inspection) {
        // In a real app, we'd fetch the supplier's current risk score here to set AQL
        // For POC, we default to Standard, and let the RiskEngine adjust future ones
        inspection.setAqlLevel("LEVEL_II");
        return inspectionRepository.save(inspection);
    }

    @Transactional
    public Inspection completeInspection(Long id, List<com.inspectorio.poc.quality.model.Defect> defects) {
        Inspection inspection = inspectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inspection not found"));

        inspection.setDefects(defects);
        inspection.setStatus(Inspection.InspectionStatus.COMPLETED);

        // Simple logic: Fail if any Critical defects
        boolean hasCritical = defects.stream()
                .anyMatch(d -> d.getSeverity() == com.inspectorio.poc.quality.model.Defect.DefectSeverity.CRITICAL);

        inspection.setResult(hasCritical ? Inspection.InspectionResult.FAIL : Inspection.InspectionResult.PASS);

        Inspection saved = inspectionRepository.save(inspection);

        // Trigger Risk Engine Analysis
        riskEngine.analyzeInspectionResult(saved);

        return saved;
    }

    public List<Inspection> getInspectionsForSupplier(Long supplierId) {
        return inspectionRepository.findBySupplierId(supplierId);
    }
}
