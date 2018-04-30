package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.LogAlteracaoContagem;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the LogAlteracaoContagem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LogAlteracaoContagemRepository extends JpaRepository<LogAlteracaoContagem, Long> {

}
