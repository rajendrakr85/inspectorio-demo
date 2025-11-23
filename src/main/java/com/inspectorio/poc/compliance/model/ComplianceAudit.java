package com.inspectorio.poc.compliance.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "compliance_audits")
public class ComplianceAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long supplierId;

    private String standard; // e.g., "SA8000", "ISO14001"

    private LocalDate auditDate;
    private LocalDate validUntil;

    private Integer score; // 0-100

    @Enumerated(EnumType.STRING)
    private AuditStatus status = AuditStatus.PLANNED;

    public enum AuditStatus {
        PLANNED, COMPLETED, EXPIRED
    }
}
