package org.prueba.accenture.domain.repository;

import org.prueba.accenture.domain.model.Franchise;

import java.util.List;
import java.util.Optional;

public interface FranchiseRepository {
    List<Franchise> findAll();
    Optional<Franchise> findById(Long id);
    Franchise save(Franchise franchise);
    void deleteById(Long id);
}