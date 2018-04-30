package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.LogStatusInventario;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the LogStatusInventario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LogStatusInventarioRepository extends JpaRepository<LogStatusInventario, Long> {

}
