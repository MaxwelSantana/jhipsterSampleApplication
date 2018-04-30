package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.MotivoAlteracao;

import io.github.jhipster.application.repository.MotivoAlteracaoRepository;
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
 * REST controller for managing MotivoAlteracao.
 */
@RestController
@RequestMapping("/api")
public class MotivoAlteracaoResource {

    private final Logger log = LoggerFactory.getLogger(MotivoAlteracaoResource.class);

    private static final String ENTITY_NAME = "motivoAlteracao";

    private final MotivoAlteracaoRepository motivoAlteracaoRepository;

    public MotivoAlteracaoResource(MotivoAlteracaoRepository motivoAlteracaoRepository) {
        this.motivoAlteracaoRepository = motivoAlteracaoRepository;
    }

    /**
     * POST  /motivo-alteracaos : Create a new motivoAlteracao.
     *
     * @param motivoAlteracao the motivoAlteracao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new motivoAlteracao, or with status 400 (Bad Request) if the motivoAlteracao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/motivo-alteracaos")
    @Timed
    public ResponseEntity<MotivoAlteracao> createMotivoAlteracao(@RequestBody MotivoAlteracao motivoAlteracao) throws URISyntaxException {
        log.debug("REST request to save MotivoAlteracao : {}", motivoAlteracao);
        if (motivoAlteracao.getId() != null) {
            throw new BadRequestAlertException("A new motivoAlteracao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MotivoAlteracao result = motivoAlteracaoRepository.save(motivoAlteracao);
        return ResponseEntity.created(new URI("/api/motivo-alteracaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /motivo-alteracaos : Updates an existing motivoAlteracao.
     *
     * @param motivoAlteracao the motivoAlteracao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated motivoAlteracao,
     * or with status 400 (Bad Request) if the motivoAlteracao is not valid,
     * or with status 500 (Internal Server Error) if the motivoAlteracao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/motivo-alteracaos")
    @Timed
    public ResponseEntity<MotivoAlteracao> updateMotivoAlteracao(@RequestBody MotivoAlteracao motivoAlteracao) throws URISyntaxException {
        log.debug("REST request to update MotivoAlteracao : {}", motivoAlteracao);
        if (motivoAlteracao.getId() == null) {
            return createMotivoAlteracao(motivoAlteracao);
        }
        MotivoAlteracao result = motivoAlteracaoRepository.save(motivoAlteracao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, motivoAlteracao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /motivo-alteracaos : get all the motivoAlteracaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of motivoAlteracaos in body
     */
    @GetMapping("/motivo-alteracaos")
    @Timed
    public List<MotivoAlteracao> getAllMotivoAlteracaos() {
        log.debug("REST request to get all MotivoAlteracaos");
        return motivoAlteracaoRepository.findAll();
        }

    /**
     * GET  /motivo-alteracaos/:id : get the "id" motivoAlteracao.
     *
     * @param id the id of the motivoAlteracao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the motivoAlteracao, or with status 404 (Not Found)
     */
    @GetMapping("/motivo-alteracaos/{id}")
    @Timed
    public ResponseEntity<MotivoAlteracao> getMotivoAlteracao(@PathVariable Long id) {
        log.debug("REST request to get MotivoAlteracao : {}", id);
        MotivoAlteracao motivoAlteracao = motivoAlteracaoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(motivoAlteracao));
    }

    /**
     * DELETE  /motivo-alteracaos/:id : delete the "id" motivoAlteracao.
     *
     * @param id the id of the motivoAlteracao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/motivo-alteracaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteMotivoAlteracao(@PathVariable Long id) {
        log.debug("REST request to delete MotivoAlteracao : {}", id);
        motivoAlteracaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
