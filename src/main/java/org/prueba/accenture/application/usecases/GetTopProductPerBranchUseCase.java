package org.prueba.accenture.application.usecases;

import org.prueba.accenture.domain.model.Product;
import org.prueba.accenture.infrastructure.persistence.BranchEntity;
import org.prueba.accenture.infrastructure.persistence.ProductEntity;
import org.prueba.accenture.infrastructure.persistence.JpaProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GetTopProductPerBranchUseCase {

    private final JpaProductRepository productRepository;

    public GetTopProductPerBranchUseCase(JpaProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> execute(Long franquiciaId) {
        List<ProductEntity> allProducts = productRepository.findByBranchFranquiciaId(franquiciaId);

        // Agrupar por sucursal y seleccionar el producto con mayor stock
        Map<BranchEntity, Optional<ProductEntity>> topPerBranch = allProducts.stream()
                .collect(Collectors.groupingBy(
                        ProductEntity::getBranch,
                        Collectors.maxBy(Comparator.comparingInt(ProductEntity::getStock))
                ));

        // Retornar solo los productos encontrados
        return topPerBranch.values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
