package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Mapeamento;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Mapeamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MapeamentoRepository extends JpaRepository<Mapeamento, Long> {

}
