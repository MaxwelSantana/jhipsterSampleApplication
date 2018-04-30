package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Secao;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Secao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecaoRepository extends JpaRepository<Secao, Long> {

}
