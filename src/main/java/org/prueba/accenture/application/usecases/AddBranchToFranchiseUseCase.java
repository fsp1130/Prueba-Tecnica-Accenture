package org.prueba.accenture.application.usecases;


import org.prueba.accenture.domain.model.Branch;
import org.prueba.accenture.domain.model.Franchise;
import org.prueba.accenture.domain.repository.FranchiseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AddBranchToFranchiseUseCase {
    private final FranchiseRepository franchiseRepository;

    public AddBranchToFranchiseUseCase(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public Franchise execute(Long franchiseId, Branch branch) {
        Optional<Franchise> franchiseOptional = franchiseRepository.findById(franchiseId);

        if (franchiseOptional.isPresent()) {
            Franchise franchise = franchiseOptional.get();
            if (franchise.getBranches() == null) {
                franchise.setBranches(new ArrayList<>());
            }
            franchise.getBranches().add(branch);

            return franchiseRepository.save(franchise);
        }

        throw new RuntimeException("Franquicia no encontrada");
    }
}