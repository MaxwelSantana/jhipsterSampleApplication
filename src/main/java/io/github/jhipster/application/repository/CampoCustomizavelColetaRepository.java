package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.CampoCustomizavelColeta;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CampoCustomizavelColeta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampoCustomizavelColetaRepository extends JpaRepository<CampoCustomizavelColeta, Long> {

}
