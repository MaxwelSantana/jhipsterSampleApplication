package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.CampoCustomizavelCadastro;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CampoCustomizavelCadastro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampoCustomizavelCadastroRepository extends JpaRepository<CampoCustomizavelCadastro, Long> {

}
