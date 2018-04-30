package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.StatusInventario;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the StatusInventario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatusInventarioRepository extends JpaRepository<StatusInventario, Long> {

}
