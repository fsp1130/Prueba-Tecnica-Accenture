package org.prueba.accenture.infrastructure.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaBranchRepository extends JpaRepository<BranchEntity, Long> {
    Optional<BranchEntity> findById(Long id);

}
