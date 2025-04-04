package org.prueba.accenture.application.usecases;


import org.prueba.accenture.domain.model.Branch;
import org.prueba.accenture.domain.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RemoveProductFromBranchUseCase {
    private final BranchRepository branchRepository;

    public RemoveProductFromBranchUseCase(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch execute(Long branchId, Long productId) {
        Optional<Branch> branch = branchRepository.findById(branchId);
        if (branch.isPresent()) {
            branch.get().getProducts().removeIf(product -> product.getId().equals(productId));
            return branchRepository.save(branch.get());
        }
        throw new RuntimeException("Sucursal no encontrada");
    }
}
