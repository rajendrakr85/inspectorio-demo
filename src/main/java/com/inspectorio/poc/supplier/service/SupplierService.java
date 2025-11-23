package com.inspectorio.poc.supplier.service;

import com.inspectorio.poc.supplier.model.Supplier;
import com.inspectorio.poc.supplier.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public Supplier onboardSupplier(Supplier supplier) {
        log.info("Onboarding new supplier: {}", supplier.getName());
        supplier.setRiskScore(50); // Default starting score
        supplier.setTier(Supplier.SupplierTier.TRANSACTIONAL);
        supplier.setStatus(Supplier.SupplierStatus.ACTIVE);
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplier(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
    }

    @Transactional
    public void updateRiskScore(Long supplierId, int scoreChange) {
        Supplier supplier = getSupplier(supplierId);
        int newScore = Math.max(0, Math.min(100, supplier.getRiskScore() + scoreChange));
        supplier.setRiskScore(newScore);

        // Auto-tiering logic based on risk score
        if (newScore > 80) {
            supplier.setTier(Supplier.SupplierTier.STRATEGIC);
        } else if (newScore > 50) {
            supplier.setTier(Supplier.SupplierTier.CORE);
        } else {
            supplier.setTier(Supplier.SupplierTier.TRANSACTIONAL);
        }

        log.info("Updated risk score for supplier {}: {} -> {}. New Tier: {}",
                supplier.getName(), supplier.getRiskScore(), newScore, supplier.getTier());

        supplierRepository.save(supplier);
    }
}
