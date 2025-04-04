package org.prueba.accenture.domain.repository;

import org.prueba.accenture.domain.model.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchRepository {
    List<Branch> findAll();
    Optional<Branch> findById(Long id);
    Branch save(Branch branch);
    void deleteById(Long id);
}



