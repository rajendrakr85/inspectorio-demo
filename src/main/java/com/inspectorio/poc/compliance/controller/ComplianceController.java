package com.inspectorio.poc.compliance.controller;

import com.inspectorio.poc.compliance.model.ComplianceAudit;
import com.inspectorio.poc.compliance.service.ComplianceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/compliance")
@RequiredArgsConstructor
public class ComplianceController {

    private final ComplianceService complianceService;

    @PostMapping("/audits")
    public ResponseEntity<ComplianceAudit> submitAudit(@RequestBody ComplianceAudit audit) {
        return ResponseEntity.ok(complianceService.submitAudit(audit));
    }

    @GetMapping("/audits/supplier/{supplierId}")
    public ResponseEntity<List<ComplianceAudit>> getSupplierAudits(@PathVariable Long supplierId) {
        return ResponseEntity.ok(complianceService.getAuditsForSupplier(supplierId));
    }
}
