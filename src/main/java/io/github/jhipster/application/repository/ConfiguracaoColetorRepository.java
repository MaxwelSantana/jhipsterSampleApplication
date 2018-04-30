package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ConfiguracaoColetor;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ConfiguracaoColetor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConfiguracaoColetorRepository extends JpaRepository<ConfiguracaoColetor, Long> {

}
