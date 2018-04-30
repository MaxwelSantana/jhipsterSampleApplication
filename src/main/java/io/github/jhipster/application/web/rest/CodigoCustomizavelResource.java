package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.CodigoCustomizavel;

import io.github.jhipster.application.repository.CodigoCustomizavelRepository;
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
 * REST controller for managing CodigoCustomizavel.
 */
@RestController
@RequestMapping("/api")
public class CodigoCustomizavelResource {

    private final Logger log = LoggerFactory.getLogger(CodigoCustomizavelResource.class);

    private static final String ENTITY_NAME = "codigoCustomizavel";

    private final CodigoCustomizavelRepository codigoCustomizavelRepository;

    public CodigoCustomizavelResource(CodigoCustomizavelRepository codigoCustomizavelRepository) {
        this.codigoCustomizavelRepository = codigoCustomizavelRepository;
    }

    /**
     * POST  /codigo-customizavels : Create a new codigoCustomizavel.
     *
     * @param codigoCustomizavel the codigoCustomizavel to create
     * @return the ResponseEntity with status 201 (Created) and with body the new codigoCustomizavel, or with status 400 (Bad Request) if the codigoCustomizavel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/codigo-customizavels")
    @Timed
    public ResponseEntity<CodigoCustomizavel> createCodigoCustomizavel(@RequestBody CodigoCustomizavel codigoCustomizavel) throws URISyntaxException {
        log.debug("REST request to save CodigoCustomizavel : {}", codigoCustomizavel);
        if (codigoCustomizavel.getId() != null) {
            throw new BadRequestAlertException("A new codigoCustomizavel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CodigoCustomizavel result = codigoCustomizavelRepository.save(codigoCustomizavel);
        return ResponseEntity.created(new URI("/api/codigo-customizavels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /codigo-customizavels : Updates an existing codigoCustomizavel.
     *
     * @param codigoCustomizavel the codigoCustomizavel to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated codigoCustomizavel,
     * or with status 400 (Bad Request) if the codigoCustomizavel is not valid,
     * or with status 500 (Internal Server Error) if the codigoCustomizavel couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/codigo-customizavels")
    @Timed
    public ResponseEntity<CodigoCustomizavel> updateCodigoCustomizavel(@RequestBody CodigoCustomizavel codigoCustomizavel) throws URISyntaxException {
        log.debug("REST request to update CodigoCustomizavel : {}", codigoCustomizavel);
        if (codigoCustomizavel.getId() == null) {
            return createCodigoCustomizavel(codigoCustomizavel);
        }
        CodigoCustomizavel result = codigoCustomizavelRepository.save(codigoCustomizavel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, codigoCustomizavel.getId().toString()))
            .body(result);
    }

    /**
     * GET  /codigo-customizavels : get all the codigoCustomizavels.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of codigoCustomizavels in body
     */
    @GetMapping("/codigo-customizavels")
    @Timed
    public List<CodigoCustomizavel> getAllCodigoCustomizavels() {
        log.debug("REST request to get all CodigoCustomizavels");
        return codigoCustomizavelRepository.findAll();
        }

    /**
     * GET  /codigo-customizavels/:id : get the "id" codigoCustomizavel.
     *
     * @param id the id of the codigoCustomizavel to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the codigoCustomizavel, or with status 404 (Not Found)
     */
    @GetMapping("/codigo-customizavels/{id}")
    @Timed
    public ResponseEntity<CodigoCustomizavel> getCodigoCustomizavel(@PathVariable Long id) {
        log.debug("REST request to get CodigoCustomizavel : {}", id);
        CodigoCustomizavel codigoCustomizavel = codigoCustomizavelRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(codigoCustomizavel));
    }

    /**
     * DELETE  /codigo-customizavels/:id : delete the "id" codigoCustomizavel.
     *
     * @param id the id of the codigoCustomizavel to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/codigo-customizavels/{id}")
    @Timed
    public ResponseEntity<Void> deleteCodigoCustomizavel(@PathVariable Long id) {
        log.debug("REST request to delete CodigoCustomizavel : {}", id);
        codigoCustomizavelRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
