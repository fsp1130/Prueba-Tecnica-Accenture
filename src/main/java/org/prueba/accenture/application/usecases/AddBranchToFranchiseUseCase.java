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

            // ⚠️ Inicializa la lista si viene null
            if (franchise.getBranches() == null) {
                franchise.setBranches(new ArrayList<>());
            }
            // Relación inversa (importante si usas JPA bidireccional)
            //branch.setFranchise(franchise);
            // Agregar la sucursal
            franchise.getBranches().add(branch);

            // Guardar la franquicia (y la sucursal, si usas CascadeType.ALL)
            return franchiseRepository.save(franchise);
        }

        throw new RuntimeException("Franquicia no encontrada");
    }
}