package org.prueba.accenture.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity save(ProductEntity productEntity);

    List<ProductEntity> findByBranchFranquiciaId(Long franquiciaId);


}
