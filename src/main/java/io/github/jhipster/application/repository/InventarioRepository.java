package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Inventario;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Inventario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

}
