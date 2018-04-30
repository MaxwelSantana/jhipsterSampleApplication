package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.MotivoAlteracao;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MotivoAlteracao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MotivoAlteracaoRepository extends JpaRepository<MotivoAlteracao, Long> {

}
