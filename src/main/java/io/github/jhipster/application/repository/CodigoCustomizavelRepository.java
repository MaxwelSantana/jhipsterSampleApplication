package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.CodigoCustomizavel;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CodigoCustomizavel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CodigoCustomizavelRepository extends JpaRepository<CodigoCustomizavel, Long> {

}
