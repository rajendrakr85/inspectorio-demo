package com.inspectorio.poc.supplier.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String country;

    @Enumerated(EnumType.STRING)
    private SupplierTier tier = SupplierTier.TRANSACTIONAL;

    @Enumerated(EnumType.STRING)
    private SupplierStatus status = SupplierStatus.ONBOARDING;

    // 0-100, where 100 is perfect score (Low Risk)
    private Integer riskScore = 50;

    public enum SupplierTier {
        STRATEGIC, CORE, TRANSACTIONAL
    }

    public enum SupplierStatus {
        ONBOARDING, ACTIVE, SUSPENDED, TERMINATED
    }
}
