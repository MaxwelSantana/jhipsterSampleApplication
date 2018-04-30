package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Mapeamento;

import io.github.jhipster.application.repository.MapeamentoRepository;
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
 * REST controller for managing Mapeamento.
 */
@RestController
@RequestMapping("/api")
public class MapeamentoResource {

    private final Logger log = LoggerFactory.getLogger(MapeamentoResource.class);

    private static final String ENTITY_NAME = "mapeamento";

    private final MapeamentoRepository mapeamentoRepository;

    public MapeamentoResource(MapeamentoRepository mapeamentoRepository) {
        this.mapeamentoRepository = mapeamentoRepository;
    }

    /**
     * POST  /mapeamentos : Create a new mapeamento.
     *
     * @param mapeamento the mapeamento to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mapeamento, or with status 400 (Bad Request) if the mapeamento has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mapeamentos")
    @Timed
    public ResponseEntity<Mapeamento> createMapeamento(@RequestBody Mapeamento mapeamento) throws URISyntaxException {
        log.debug("REST request to save Mapeamento : {}", mapeamento);
        if (mapeamento.getId() != null) {
            throw new BadRequestAlertException("A new mapeamento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Mapeamento result = mapeamentoRepository.save(mapeamento);
        return ResponseEntity.created(new URI("/api/mapeamentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mapeamentos : Updates an existing mapeamento.
     *
     * @param mapeamento the mapeamento to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mapeamento,
     * or with status 400 (Bad Request) if the mapeamento is not valid,
     * or with status 500 (Internal Server Error) if the mapeamento couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mapeamentos")
    @Timed
    public ResponseEntity<Mapeamento> updateMapeamento(@RequestBody Mapeamento mapeamento) throws URISyntaxException {
        log.debug("REST request to update Mapeamento : {}", mapeamento);
        if (mapeamento.getId() == null) {
            return createMapeamento(mapeamento);
        }
        Mapeamento result = mapeamentoRepository.save(mapeamento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mapeamento.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mapeamentos : get all the mapeamentos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of mapeamentos in body
     */
    @GetMapping("/mapeamentos")
    @Timed
    public List<Mapeamento> getAllMapeamentos() {
        log.debug("REST request to get all Mapeamentos");
        return mapeamentoRepository.findAll();
        }

    /**
     * GET  /mapeamentos/:id : get the "id" mapeamento.
     *
     * @param id the id of the mapeamento to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mapeamento, or with status 404 (Not Found)
     */
    @GetMapping("/mapeamentos/{id}")
    @Timed
    public ResponseEntity<Mapeamento> getMapeamento(@PathVariable Long id) {
        log.debug("REST request to get Mapeamento : {}", id);
        Mapeamento mapeamento = mapeamentoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(mapeamento));
    }

    /**
     * DELETE  /mapeamentos/:id : delete the "id" mapeamento.
     *
     * @param id the id of the mapeamento to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mapeamentos/{id}")
    @Timed
    public ResponseEntity<Void> deleteMapeamento(@PathVariable Long id) {
        log.debug("REST request to delete Mapeamento : {}", id);
        mapeamentoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
