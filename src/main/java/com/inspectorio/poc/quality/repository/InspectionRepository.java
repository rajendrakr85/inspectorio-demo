package com.inspectorio.poc.quality.repository;

import com.inspectorio.poc.quality.model.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {
    List<Inspection> findBySupplierId(Long supplierId);
}
