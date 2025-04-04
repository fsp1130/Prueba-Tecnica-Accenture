package org.prueba.accenture.application.usecases;

import org.prueba.accenture.domain.repository.FranchiseRepository;
import org.prueba.accenture.domain.model.Franchise;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateFranchiseNameUseCase {

    private final FranchiseRepository franchiseRepository;

    public UpdateFranchiseNameUseCase(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public Franchise execute(Long franchiseId, String newName) {
        Optional<Franchise> optionalFranchise = franchiseRepository.findById(franchiseId);

        if (optionalFranchise.isEmpty()) {
            throw new RuntimeException("Franchise not found with ID: " + franchiseId);
        }

        Franchise franchise = optionalFranchise.get();
        franchise.setName(newName);

        return franchiseRepository.save(franchise); // Devuelve la franquicia actualizada
    }
}
