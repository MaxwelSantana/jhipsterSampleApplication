package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Secao;

import io.github.jhipster.application.repository.SecaoRepository;
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
 * REST controller for managing Secao.
 */
@RestController
@RequestMapping("/api")
public class SecaoResource {

    private final Logger log = LoggerFactory.getLogger(SecaoResource.class);

    private static final String ENTITY_NAME = "secao";

    private final SecaoRepository secaoRepository;

    public SecaoResource(SecaoRepository secaoRepository) {
        this.secaoRepository = secaoRepository;
    }

    /**
     * POST  /secaos : Create a new secao.
     *
     * @param secao the secao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new secao, or with status 400 (Bad Request) if the secao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/secaos")
    @Timed
    public ResponseEntity<Secao> createSecao(@RequestBody Secao secao) throws URISyntaxException {
        log.debug("REST request to save Secao : {}", secao);
        if (secao.getId() != null) {
            throw new BadRequestAlertException("A new secao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Secao result = secaoRepository.save(secao);
        return ResponseEntity.created(new URI("/api/secaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /secaos : Updates an existing secao.
     *
     * @param secao the secao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated secao,
     * or with status 400 (Bad Request) if the secao is not valid,
     * or with status 500 (Internal Server Error) if the secao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/secaos")
    @Timed
    public ResponseEntity<Secao> updateSecao(@RequestBody Secao secao) throws URISyntaxException {
        log.debug("REST request to update Secao : {}", secao);
        if (secao.getId() == null) {
            return createSecao(secao);
        }
        Secao result = secaoRepository.save(secao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, secao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /secaos : get all the secaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of secaos in body
     */
    @GetMapping("/secaos")
    @Timed
    public List<Secao> getAllSecaos() {
        log.debug("REST request to get all Secaos");
        return secaoRepository.findAll();
        }

    /**
     * GET  /secaos/:id : get the "id" secao.
     *
     * @param id the id of the secao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the secao, or with status 404 (Not Found)
     */
    @GetMapping("/secaos/{id}")
    @Timed
    public ResponseEntity<Secao> getSecao(@PathVariable Long id) {
        log.debug("REST request to get Secao : {}", id);
        Secao secao = secaoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(secao));
    }

    /**
     * DELETE  /secaos/:id : delete the "id" secao.
     *
     * @param id the id of the secao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/secaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteSecao(@PathVariable Long id) {
        log.debug("REST request to delete Secao : {}", id);
        secaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
