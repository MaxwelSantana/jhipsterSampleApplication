package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.TipoContagem;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TipoContagem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoContagemRepository extends JpaRepository<TipoContagem, Long> {

}
