package com.inspectorio.poc.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupplierRiskEvent {
    private Long supplierId;
    private int riskScoreChange; // e.g., +10 or -5
    private String reason;
}
