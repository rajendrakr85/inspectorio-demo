package com.inspectorio.poc.quality.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Defect {
    private String description;
    private DefectSeverity severity;

    public enum DefectSeverity {
        CRITICAL, MAJOR, MINOR
    }
}
