package com.inspectorio.poc.compliance.service;

import com.inspectorio.poc.compliance.model.ComplianceAudit;
import com.inspectorio.poc.compliance.repository.ComplianceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplianceService {

    private final ComplianceRepository complianceRepository;

    public ComplianceAudit submitAudit(ComplianceAudit audit) {
        // Logic to validate audit or trigger alerts could go here
        return complianceRepository.save(audit);
    }

    public List<ComplianceAudit> getAuditsForSupplier(Long supplierId) {
        return complianceRepository.findBySupplierId(supplierId);
    }
}
