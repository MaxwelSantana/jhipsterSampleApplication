package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Coletor;

import io.github.jhipster.application.repository.ColetorRepository;
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
 * REST controller for managing Coletor.
 */
@RestController
@RequestMapping("/api")
public class ColetorResource {

    private final Logger log = LoggerFactory.getLogger(ColetorResource.class);

    private static final String ENTITY_NAME = "coletor";

    private final ColetorRepository coletorRepository;

    public ColetorResource(ColetorRepository coletorRepository) {
        this.coletorRepository = coletorRepository;
    }

    /**
     * POST  /coletors : Create a new coletor.
     *
     * @param coletor the coletor to create
     * @return the ResponseEntity with status 201 (Created) and with body the new coletor, or with status 400 (Bad Request) if the coletor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/coletors")
    @Timed
    public ResponseEntity<Coletor> createColetor(@RequestBody Coletor coletor) throws URISyntaxException {
        log.debug("REST request to save Coletor : {}", coletor);
        if (coletor.getId() != null) {
            throw new BadRequestAlertException("A new coletor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Coletor result = coletorRepository.save(coletor);
        return ResponseEntity.created(new URI("/api/coletors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /coletors : Updates an existing coletor.
     *
     * @param coletor the coletor to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated coletor,
     * or with status 400 (Bad Request) if the coletor is not valid,
     * or with status 500 (Internal Server Error) if the coletor couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/coletors")
    @Timed
    public ResponseEntity<Coletor> updateColetor(@RequestBody Coletor coletor) throws URISyntaxException {
        log.debug("REST request to update Coletor : {}", coletor);
        if (coletor.getId() == null) {
            return createColetor(coletor);
        }
        Coletor result = coletorRepository.save(coletor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, coletor.getId().toString()))
            .body(result);
    }

    /**
     * GET  /coletors : get all the coletors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of coletors in body
     */
    @GetMapping("/coletors")
    @Timed
    public List<Coletor> getAllColetors() {
        log.debug("REST request to get all Coletors");
        return coletorRepository.findAll();
        }

    /**
     * GET  /coletors/:id : get the "id" coletor.
     *
     * @param id the id of the coletor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the coletor, or with status 404 (Not Found)
     */
    @GetMapping("/coletors/{id}")
    @Timed
    public ResponseEntity<Coletor> getColetor(@PathVariable Long id) {
        log.debug("REST request to get Coletor : {}", id);
        Coletor coletor = coletorRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(coletor));
    }

    /**
     * DELETE  /coletors/:id : delete the "id" coletor.
     *
     * @param id the id of the coletor to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/coletors/{id}")
    @Timed
    public ResponseEntity<Void> deleteColetor(@PathVariable Long id) {
        log.debug("REST request to delete Coletor : {}", id);
        coletorRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
