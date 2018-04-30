package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.CampoCustomizavelColeta;

import io.github.jhipster.application.repository.CampoCustomizavelColetaRepository;
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
 * REST controller for managing CampoCustomizavelColeta.
 */
@RestController
@RequestMapping("/api")
public class CampoCustomizavelColetaResource {

    private final Logger log = LoggerFactory.getLogger(CampoCustomizavelColetaResource.class);

    private static final String ENTITY_NAME = "campoCustomizavelColeta";

    private final CampoCustomizavelColetaRepository campoCustomizavelColetaRepository;

    public CampoCustomizavelColetaResource(CampoCustomizavelColetaRepository campoCustomizavelColetaRepository) {
        this.campoCustomizavelColetaRepository = campoCustomizavelColetaRepository;
    }

    /**
     * POST  /campo-customizavel-coletas : Create a new campoCustomizavelColeta.
     *
     * @param campoCustomizavelColeta the campoCustomizavelColeta to create
     * @return the ResponseEntity with status 201 (Created) and with body the new campoCustomizavelColeta, or with status 400 (Bad Request) if the campoCustomizavelColeta has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/campo-customizavel-coletas")
    @Timed
    public ResponseEntity<CampoCustomizavelColeta> createCampoCustomizavelColeta(@RequestBody CampoCustomizavelColeta campoCustomizavelColeta) throws URISyntaxException {
        log.debug("REST request to save CampoCustomizavelColeta : {}", campoCustomizavelColeta);
        if (campoCustomizavelColeta.getId() != null) {
            throw new BadRequestAlertException("A new campoCustomizavelColeta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CampoCustomizavelColeta result = campoCustomizavelColetaRepository.save(campoCustomizavelColeta);
        return ResponseEntity.created(new URI("/api/campo-customizavel-coletas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /campo-customizavel-coletas : Updates an existing campoCustomizavelColeta.
     *
     * @param campoCustomizavelColeta the campoCustomizavelColeta to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated campoCustomizavelColeta,
     * or with status 400 (Bad Request) if the campoCustomizavelColeta is not valid,
     * or with status 500 (Internal Server Error) if the campoCustomizavelColeta couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/campo-customizavel-coletas")
    @Timed
    public ResponseEntity<CampoCustomizavelColeta> updateCampoCustomizavelColeta(@RequestBody CampoCustomizavelColeta campoCustomizavelColeta) throws URISyntaxException {
        log.debug("REST request to update CampoCustomizavelColeta : {}", campoCustomizavelColeta);
        if (campoCustomizavelColeta.getId() == null) {
            return createCampoCustomizavelColeta(campoCustomizavelColeta);
        }
        CampoCustomizavelColeta result = campoCustomizavelColetaRepository.save(campoCustomizavelColeta);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, campoCustomizavelColeta.getId().toString()))
            .body(result);
    }

    /**
     * GET  /campo-customizavel-coletas : get all the campoCustomizavelColetas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of campoCustomizavelColetas in body
     */
    @GetMapping("/campo-customizavel-coletas")
    @Timed
    public List<CampoCustomizavelColeta> getAllCampoCustomizavelColetas() {
        log.debug("REST request to get all CampoCustomizavelColetas");
        return campoCustomizavelColetaRepository.findAll();
        }

    /**
     * GET  /campo-customizavel-coletas/:id : get the "id" campoCustomizavelColeta.
     *
     * @param id the id of the campoCustomizavelColeta to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the campoCustomizavelColeta, or with status 404 (Not Found)
     */
    @GetMapping("/campo-customizavel-coletas/{id}")
    @Timed
    public ResponseEntity<CampoCustomizavelColeta> getCampoCustomizavelColeta(@PathVariable Long id) {
        log.debug("REST request to get CampoCustomizavelColeta : {}", id);
        CampoCustomizavelColeta campoCustomizavelColeta = campoCustomizavelColetaRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(campoCustomizavelColeta));
    }

    /**
     * DELETE  /campo-customizavel-coletas/:id : delete the "id" campoCustomizavelColeta.
     *
     * @param id the id of the campoCustomizavelColeta to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/campo-customizavel-coletas/{id}")
    @Timed
    public ResponseEntity<Void> deleteCampoCustomizavelColeta(@PathVariable Long id) {
        log.debug("REST request to delete CampoCustomizavelColeta : {}", id);
        campoCustomizavelColetaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
