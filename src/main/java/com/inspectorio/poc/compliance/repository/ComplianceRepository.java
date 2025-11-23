package com.inspectorio.poc.compliance.repository;

import com.inspectorio.poc.compliance.model.ComplianceAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplianceRepository extends JpaRepository<ComplianceAudit, Long> {
    List<ComplianceAudit> findBySupplierId(Long supplierId);
}
