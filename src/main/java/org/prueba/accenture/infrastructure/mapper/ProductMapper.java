package org.prueba.accenture.infrastructure.mapper;

import org.prueba.accenture.domain.model.Product;
import org.prueba.accenture.infrastructure.persistence.ProductEntity;
import org.prueba.accenture.infrastructure.persistence.BranchEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {


    public static ProductEntity toEntity(Product product) {
        return toEntity(product, null);
    }
    // Mapea un Product a ProductEntity, incluyendo la relación con BranchEntity
    public static ProductEntity toEntity(Product product, BranchEntity branchEntity) {
        if (product == null) return null;

        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setStock(product.getStock());
        entity.setBranch(branchEntity); // Mantener la relación inversa con BranchEntity
        return entity;
    }

    // Mapea un ProductEntity a Product
    public static Product toDomain(ProductEntity entity) {
        if (entity == null) return null;

        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getStock()
        );
    }
}
