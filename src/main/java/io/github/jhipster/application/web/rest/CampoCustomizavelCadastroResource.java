package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.CampoCustomizavelCadastro;

import io.github.jhipster.application.repository.CampoCustomizavelCadastroRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CampoCustomizavelCadastro.
 */
@RestController
@RequestMapping("/api")
public class CampoCustomizavelCadastroResource {

    private final Logger log = LoggerFactory.getLogger(CampoCustomizavelCadastroResource.class);

    private static final String ENTITY_NAME = "campoCustomizavelCadastro";

    private final CampoCustomizavelCadastroRepository campoCustomizavelCadastroRepository;

    public CampoCustomizavelCadastroResource(CampoCustomizavelCadastroRepository campoCustomizavelCadastroRepository) {
        this.campoCustomizavelCadastroRepository = campoCustomizavelCadastroRepository;
    }

    /**
     * POST  /campo-customizavel-cadastros : Create a new campoCustomizavelCadastro.
     *
     * @param campoCustomizavelCadastro the campoCustomizavelCadastro to create
     * @return the ResponseEntity with status 201 (Created) and with body the new campoCustomizavelCadastro, or with status 400 (Bad Request) if the campoCustomizavelCadastro has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/campo-customizavel-cadastros")
    @Timed
    public ResponseEntity<CampoCustomizavelCadastro> createCampoCustomizavelCadastro(@RequestBody CampoCustomizavelCadastro campoCustomizavelCadastro) throws URISyntaxException {
        log.debug("REST request to save CampoCustomizavelCadastro : {}", campoCustomizavelCadastro);
        if (campoCustomizavelCadastro.getId() != null) {
            throw new BadRequestAlertException("A new campoCustomizavelCadastro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CampoCustomizavelCadastro result = campoCustomizavelCadastroRepository.save(campoCustomizavelCadastro);
        return ResponseEntity.created(new URI("/api/campo-customizavel-cadastros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /campo-customizavel-cadastros : Updates an existing campoCustomizavelCadastro.
     *
     * @param campoCustomizavelCadastro the campoCustomizavelCadastro to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated campoCustomizavelCadastro,
     * or with status 400 (Bad Request) if the campoCustomizavelCadastro is not valid,
     * or with status 500 (Internal Server Error) if the campoCustomizavelCadastro couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/campo-customizavel-cadastros")
    @Timed
    public ResponseEntity<CampoCustomizavelCadastro> updateCampoCustomizavelCadastro(@RequestBody CampoCustomizavelCadastro campoCustomizavelCadastro) throws URISyntaxException {
        log.debug("REST request to update CampoCustomizavelCadastro : {}", campoCustomizavelCadastro);
        if (campoCustomizavelCadastro.getId() == null) {
            return createCampoCustomizavelCadastro(campoCustomizavelCadastro);
        }
        CampoCustomizavelCadastro result = campoCustomizavelCadastroRepository.save(campoCustomizavelCadastro);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, campoCustomizavelCadastro.getId().toString()))
            .body(result);
    }

    /**
     * GET  /campo-customizavel-cadastros : get all the campoCustomizavelCadastros.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of campoCustomizavelCadastros in body
     */
    @GetMapping("/campo-customizavel-cadastros")
    @Timed
    public List<CampoCustomizavelCadastro> getAllCampoCustomizavelCadastros() {
        log.debug("REST request to get all CampoCustomizavelCadastros");
        return campoCustomizavelCadastroRepository.findAll();
        }

    /**
     * GET  /campo-customizavel-cadastros/:id : get the "id" campoCustomizavelCadastro.
     *
     * @param id the id of the campoCustomizavelCadastro to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the campoCustomizavelCadastro, or with status 404 (Not Found)
     */
    @GetMapping("/campo-customizavel-cadastros/{id}")
    @Timed
    public ResponseEntity<CampoCustomizavelCadastro> getCampoCustomizavelCadastro(@PathVariable Long id) {
        log.debug("REST request to get CampoCustomizavelCadastro : {}", id);
        CampoCustomizavelCadastro campoCustomizavelCadastro = campoCustomizavelCadastroRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(campoCustomizavelCadastro));
    }

    /**
     * DELETE  /campo-customizavel-cadastros/:id : delete the "id" campoCustomizavelCadastro.
     *
     * @param id the id of the campoCustomizavelCadastro to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/campo-customizavel-cadastros/{id}")
    @Timed
    public ResponseEntity<Void> deleteCampoCustomizavelCadastro(@PathVariable Long id) {
        log.debug("REST request to delete CampoCustomizavelCadastro : {}", id);
        campoCustomizavelCadastroRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
