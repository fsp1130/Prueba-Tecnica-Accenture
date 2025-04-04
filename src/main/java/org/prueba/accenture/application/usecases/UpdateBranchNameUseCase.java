package org.prueba.accenture.application.usecases;

import org.prueba.accenture.domain.model.Branch;
import org.prueba.accenture.domain.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateBranchNameUseCase {

    private final BranchRepository branchRepository;

    public UpdateBranchNameUseCase(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch execute(Long branchId, String newName) {
        Optional<Branch> optionalBranch = branchRepository.findById(branchId);

        if (optionalBranch.isEmpty()) {
            throw new RuntimeException("Branch not found with ID: " + branchId);
        }

        Branch branch = optionalBranch.get();
        branch.setName(newName);

        return branchRepository.save(branch); // Devuelve la sucursal actualizada
    }
}
