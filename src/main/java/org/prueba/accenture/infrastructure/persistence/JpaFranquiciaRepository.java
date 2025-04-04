package org.prueba.accenture.infrastructure.persistence;

import org.prueba.accenture.domain.model.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaFranquiciaRepository extends JpaRepository<FranquiciaEntity, Long> {

}