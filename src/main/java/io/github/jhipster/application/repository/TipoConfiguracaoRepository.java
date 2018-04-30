package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.TipoConfiguracao;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TipoConfiguracao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoConfiguracaoRepository extends JpaRepository<TipoConfiguracao, Long> {

}
