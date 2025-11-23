package com.inspectorio.poc.quality.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "inspections")
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long supplierId;

    private LocalDate scheduledDate;

    @Enumerated(EnumType.STRING)
    private InspectionStatus status = InspectionStatus.SCHEDULED;

    @Enumerated(EnumType.STRING)
    private InspectionResult result = InspectionResult.PENDING;

    @ElementCollection
    @CollectionTable(name = "inspection_defects", joinColumns = @JoinColumn(name = "inspection_id"))
    private List<Defect> defects = new ArrayList<>();

    // AQL Level assigned by Risk Engine
    private String aqlLevel = "LEVEL_II";

    public enum InspectionStatus {
        SCHEDULED, IN_PROGRESS, COMPLETED
    }

    public enum InspectionResult {
        PENDING, PASS, FAIL
    }
}
