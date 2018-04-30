package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Transmissao;

import io.github.jhipster.application.repository.TransmissaoRepository;
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
 * REST controller for managing Transmissao.
 */
@RestController
@RequestMapping("/api")
public class TransmissaoResource {

    private final Logger log = LoggerFactory.getLogger(TransmissaoResource.class);

    private static final String ENTITY_NAME = "transmissao";

    private final TransmissaoRepository transmissaoRepository;

    public TransmissaoResource(TransmissaoRepository transmissaoRepository) {
        this.transmissaoRepository = transmissaoRepository;
    }

    /**
     * POST  /transmissaos : Create a new transmissao.
     *
     * @param transmissao the transmissao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new transmissao, or with status 400 (Bad Request) if the transmissao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/transmissaos")
    @Timed
    public ResponseEntity<Transmissao> createTransmissao(@RequestBody Transmissao transmissao) throws URISyntaxException {
        log.debug("REST request to save Transmissao : {}", transmissao);
        if (transmissao.getId() != null) {
            throw new BadRequestAlertException("A new transmissao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Transmissao result = transmissaoRepository.save(transmissao);
        return ResponseEntity.created(new URI("/api/transmissaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /transmissaos : Updates an existing transmissao.
     *
     * @param transmissao the transmissao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated transmissao,
     * or with status 400 (Bad Request) if the transmissao is not valid,
     * or with status 500 (Internal Server Error) if the transmissao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/transmissaos")
    @Timed
    public ResponseEntity<Transmissao> updateTransmissao(@RequestBody Transmissao transmissao) throws URISyntaxException {
        log.debug("REST request to update Transmissao : {}", transmissao);
        if (transmissao.getId() == null) {
            return createTransmissao(transmissao);
        }
        Transmissao result = transmissaoRepository.save(transmissao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, transmissao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /transmissaos : get all the transmissaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of transmissaos in body
     */
    @GetMapping("/transmissaos")
    @Timed
    public List<Transmissao> getAllTransmissaos() {
        log.debug("REST request to get all Transmissaos");
        return transmissaoRepository.findAll();
        }

    /**
     * GET  /transmissaos/:id : get the "id" transmissao.
     *
     * @param id the id of the transmissao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the transmissao, or with status 404 (Not Found)
     */
    @GetMapping("/transmissaos/{id}")
    @Timed
    public ResponseEntity<Transmissao> getTransmissao(@PathVariable Long id) {
        log.debug("REST request to get Transmissao : {}", id);
        Transmissao transmissao = transmissaoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(transmissao));
    }

    /**
     * DELETE  /transmissaos/:id : delete the "id" transmissao.
     *
     * @param id the id of the transmissao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/transmissaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTransmissao(@PathVariable Long id) {
        log.debug("REST request to delete Transmissao : {}", id);
        transmissaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
