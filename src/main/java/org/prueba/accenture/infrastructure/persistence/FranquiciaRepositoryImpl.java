package org.prueba.accenture.infrastructure.persistence;

import org.prueba.accenture.domain.model.Franchise;
import org.prueba.accenture.domain.repository.FranchiseRepository;
import org.prueba.accenture.infrastructure.mapper.BranchMapper;
import org.prueba.accenture.infrastructure.mapper.FranchiseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FranquiciaRepositoryImpl implements FranchiseRepository {

    private final JpaFranquiciaRepository jpaRepository;

    public FranquiciaRepositoryImpl(JpaFranquiciaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Franchise save(Franchise franquicia) {
        FranquiciaEntity entity;

        if (franquicia.getId() != null && jpaRepository.existsById(franquicia.getId())) {
            entity = jpaRepository.findById(franquicia.getId()).orElseThrow();
            entity.setName(franquicia.getName());


            entity.getSucursales().clear();
            if (franquicia.getBranches() != null) {
                entity.getSucursales().addAll(
                        franquicia.getBranches().stream()
                                .map(branch -> BranchMapper.toEntity(branch, entity))
                                .collect(Collectors.toList())
                );
            }

        } else {

            entity = FranchiseMapper.toEntity(franquicia);
        }

        FranquiciaEntity saved = jpaRepository.save(entity);
        return FranchiseMapper.toDomain(saved);
    }


    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Franchise> findById(Long id) {
        return jpaRepository.findById(id)
                .map(entity -> new Franchise(entity.getId(), entity.getName()));
    }

    @Override
    public List<Franchise> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(entity -> new Franchise(entity.getId(), entity.getName()))
                .collect(Collectors.toList());
    }
}