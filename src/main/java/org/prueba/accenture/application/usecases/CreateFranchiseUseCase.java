package org.prueba.accenture.application.usecases;

import org.prueba.accenture.domain.model.Franchise;
import org.prueba.accenture.domain.repository.FranchiseRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateFranchiseUseCase {
    private final FranchiseRepository franchiseRepository;

    public CreateFranchiseUseCase(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public Franchise execute(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }
}