package org.prueba.accenture.infrastructure.persistence;

import org.prueba.accenture.domain.model.Branch;
import org.prueba.accenture.domain.model.Product;
import org.prueba.accenture.domain.repository.BranchRepository;
import org.prueba.accenture.infrastructure.mapper.ProductMapper;
import org.prueba.accenture.infrastructure.persistence.BranchEntity;
import org.prueba.accenture.infrastructure.persistence.JpaBranchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SucursalRepositoryImpl implements BranchRepository {

    private final JpaBranchRepository jpaRepository;

    public SucursalRepositoryImpl(JpaBranchRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Branch> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public Optional<Branch> findById(Long id) {
        return jpaRepository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public Branch save(Branch branch) {
        BranchEntity entity = mapToEntity(branch);
        return mapToDomain(jpaRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    private Branch mapToDomain(BranchEntity entity) {
        List<Product> products = entity.getProducts()
                .stream()
                .map(ProductMapper::toDomain)
                .toList();

        return new Branch(entity.getId(), entity.getName(), products);
    }

    private BranchEntity mapToEntity(Branch branch) {
        BranchEntity entity = new BranchEntity();
        entity.setId(branch.getId());
        entity.setName(branch.getName());
        return entity;
    }
}
