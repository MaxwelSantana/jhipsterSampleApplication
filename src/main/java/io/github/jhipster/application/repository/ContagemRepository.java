package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Contagem;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Contagem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContagemRepository extends JpaRepository<Contagem, Long> {

}
