package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Transmissao;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Transmissao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransmissaoRepository extends JpaRepository<Transmissao, Long> {

}
