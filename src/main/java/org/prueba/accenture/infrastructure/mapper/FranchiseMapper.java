package org.prueba.accenture.infrastructure.mapper;

import org.prueba.accenture.domain.model.Franchise;
import org.prueba.accenture.infrastructure.persistence.FranquiciaEntity;

import java.util.stream.Collectors;

public class FranchiseMapper {

    public static FranquiciaEntity toEntity(Franchise franchise) {
        if (franchise == null) return null;

        FranquiciaEntity entity = new FranquiciaEntity();
        entity.setId(franchise.getId());
        entity.setName(franchise.getName());

        if (franchise.getBranches() != null) {
            entity.setSucursales(
                    franchise.getBranches().stream()
                            .map(branch -> BranchMapper.toEntity(branch, entity))
                            .collect(Collectors.toList())
            );
        }

        return entity;
    }


    public static Franchise toDomain(FranquiciaEntity entity) {
        if (entity == null) return null;

        Franchise franchise = new Franchise(
                entity.getId(),
                entity.getName()
        );

        if (entity.getSucursales() != null) {
            franchise.setBranches(
                    entity.getSucursales().stream()
                            .map(BranchMapper::toDomain)
                            .collect(Collectors.toList())
            );
        }

        return franchise;
    }
}
