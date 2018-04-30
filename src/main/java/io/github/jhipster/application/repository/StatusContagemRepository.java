package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.StatusContagem;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the StatusContagem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatusContagemRepository extends JpaRepository<StatusContagem, Long> {

}
