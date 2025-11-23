package com.inspectorio.poc.quality.controller;

import com.inspectorio.poc.quality.model.Defect;
import com.inspectorio.poc.quality.model.Inspection;
import com.inspectorio.poc.quality.service.InspectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inspections")
@RequiredArgsConstructor
public class InspectionController {

    private final InspectionService inspectionService;

    @PostMapping
    public ResponseEntity<Inspection> scheduleInspection(@RequestBody Inspection inspection) {
        return ResponseEntity.ok(inspectionService.scheduleInspection(inspection));
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Inspection> completeInspection(@PathVariable Long id, @RequestBody List<Defect> defects) {
        return ResponseEntity.ok(inspectionService.completeInspection(id, defects));
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<Inspection>> getSupplierInspections(@PathVariable Long supplierId) {
        return ResponseEntity.ok(inspectionService.getInspectionsForSupplier(supplierId));
    }
}
