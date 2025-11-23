package com.inspectorio.poc.supplier.listener;

import com.inspectorio.poc.common.event.SupplierRiskEvent;
import com.inspectorio.poc.supplier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SupplierRiskListener {

    private final SupplierService supplierService;

    @EventListener
    @Async
    public void handleSupplierRiskEvent(SupplierRiskEvent event) {
        log.info("Received SupplierRiskEvent for supplier {}: {} (Reason: {})",
                event.getSupplierId(), event.getRiskScoreChange(), event.getReason());

        supplierService.updateRiskScore(event.getSupplierId(), event.getRiskScoreChange());
    }
}
