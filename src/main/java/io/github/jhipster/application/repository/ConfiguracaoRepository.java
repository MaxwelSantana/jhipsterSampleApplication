package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Configuracao;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Configuracao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {

}
