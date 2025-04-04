package org.prueba.accenture.application.usecases;

import org.prueba.accenture.domain.model.Branch;
import org.prueba.accenture.domain.model.Franchise;
import org.prueba.accenture.domain.model.Product;
import org.prueba.accenture.domain.repository.FranchiseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetTopStockedProductsUseCase {
    private final FranchiseRepository franchiseRepository;

    public GetTopStockedProductsUseCase(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public List<Product> execute(Long franchiseId) {
        Optional<Franchise> franchise = franchiseRepository.findById(franchiseId);
        if (franchise.isPresent()) {

            System.out.println("entro a la fraanquicia  ");
            System.out.println("Franquicia encontrada: " + franchise.get().getName());

            return franchise.get().getBranches().stream()
                    .map(branch -> branch.getProducts().stream()
                            .max((p1, p2) -> Integer.compare(p1.getStock(), p2.getStock()))
                            .map(product -> {
                                product.setName(product.getName() + " (Sucursal: " + branch.getName() + ")");
                                return product;
                            }).orElse(null))
                    .filter(product -> product != null)
                    .collect(Collectors.toList());

        }
        throw new RuntimeException("Franquicia no encontrada");
    }
}
