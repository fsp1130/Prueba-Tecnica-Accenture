package org.prueba.accenture.infrastructure.mapper;

import org.prueba.accenture.domain.model.Branch;
import org.prueba.accenture.domain.model.Product;
import org.prueba.accenture.infrastructure.persistence.BranchEntity;
import org.prueba.accenture.infrastructure.persistence.FranquiciaEntity;
import org.prueba.accenture.infrastructure.persistence.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BranchMapper {

    public static BranchEntity toEntity(Branch branch, FranquiciaEntity franquiciaEntity) {
        if (branch == null) return null;

        BranchEntity entity = new BranchEntity();
        entity.setId(branch.getId());
        entity.setName(branch.getName());
        entity.setFranquicia(franquiciaEntity);

        if (branch.getProducts() != null) {
            entity.setProducts(
                    branch.getProducts().stream()
                            .map(p -> ProductMapper.toEntity(p, entity))
                            .collect(Collectors.toList())
            );
        }


        return entity;
    }
    public static Branch toDomain(BranchEntity entity) {
        if (entity == null) return null;

        List<Product> products = null;
        if (entity.getProducts() != null) {
            products = entity.getProducts().stream()
                    .map(ProductMapper::toDomain)
                    .collect(Collectors.toList());
        }

        return new Branch(
                entity.getId(),
                entity.getName(),
                products
        );
    }


}

