package org.prueba.accenture.infrastructure.persistence;

import org.prueba.accenture.domain.model.Product;
import org.prueba.accenture.domain.repository.ProductRepository;
import org.prueba.accenture.infrastructure.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaRepository;

    public ProductRepositoryImpl(JpaProductRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id)
                .map(ProductMapper::toDomain); // si usás un mapper
    }

    @Override
    public Product save(Product product) {
        return ProductMapper.toDomain(
                jpaRepository.save(ProductMapper.toEntity(product))
        );
    }

    @Override
    public void deleteById(Long id) {

    }
}
