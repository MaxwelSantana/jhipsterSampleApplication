package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.TipoConfiguracao;

import io.github.jhipster.application.repository.TipoConfiguracaoRepository;
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
 * REST controller for managing TipoConfiguracao.
 */
@RestController
@RequestMapping("/api")
public class TipoConfiguracaoResource {

    private final Logger log = LoggerFactory.getLogger(TipoConfiguracaoResource.class);

    private static final String ENTITY_NAME = "tipoConfiguracao";

    private final TipoConfiguracaoRepository tipoConfiguracaoRepository;

    public TipoConfiguracaoResource(TipoConfiguracaoRepository tipoConfiguracaoRepository) {
        this.tipoConfiguracaoRepository = tipoConfiguracaoRepository;
    }

    /**
     * POST  /tipo-configuracaos : Create a new tipoConfiguracao.
     *
     * @param tipoConfiguracao the tipoConfiguracao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoConfiguracao, or with status 400 (Bad Request) if the tipoConfiguracao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-configuracaos")
    @Timed
    public ResponseEntity<TipoConfiguracao> createTipoConfiguracao(@RequestBody TipoConfiguracao tipoConfiguracao) throws URISyntaxException {
        log.debug("REST request to save TipoConfiguracao : {}", tipoConfiguracao);
        if (tipoConfiguracao.getId() != null) {
            throw new BadRequestAlertException("A new tipoConfiguracao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoConfiguracao result = tipoConfiguracaoRepository.save(tipoConfiguracao);
        return ResponseEntity.created(new URI("/api/tipo-configuracaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-configuracaos : Updates an existing tipoConfiguracao.
     *
     * @param tipoConfiguracao the tipoConfiguracao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoConfiguracao,
     * or with status 400 (Bad Request) if the tipoConfiguracao is not valid,
     * or with status 500 (Internal Server Error) if the tipoConfiguracao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-configuracaos")
    @Timed
    public ResponseEntity<TipoConfiguracao> updateTipoConfiguracao(@RequestBody TipoConfiguracao tipoConfiguracao) throws URISyntaxException {
        log.debug("REST request to update TipoConfiguracao : {}", tipoConfiguracao);
        if (tipoConfiguracao.getId() == null) {
            return createTipoConfiguracao(tipoConfiguracao);
        }
        TipoConfiguracao result = tipoConfiguracaoRepository.save(tipoConfiguracao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoConfiguracao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-configuracaos : get all the tipoConfiguracaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipoConfiguracaos in body
     */
    @GetMapping("/tipo-configuracaos")
    @Timed
    public List<TipoConfiguracao> getAllTipoConfiguracaos() {
        log.debug("REST request to get all TipoConfiguracaos");
        return tipoConfiguracaoRepository.findAll();
        }

    /**
     * GET  /tipo-configuracaos/:id : get the "id" tipoConfiguracao.
     *
     * @param id the id of the tipoConfiguracao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoConfiguracao, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-configuracaos/{id}")
    @Timed
    public ResponseEntity<TipoConfiguracao> getTipoConfiguracao(@PathVariable Long id) {
        log.debug("REST request to get TipoConfiguracao : {}", id);
        TipoConfiguracao tipoConfiguracao = tipoConfiguracaoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipoConfiguracao));
    }

    /**
     * DELETE  /tipo-configuracaos/:id : delete the "id" tipoConfiguracao.
     *
     * @param id the id of the tipoConfiguracao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-configuracaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoConfiguracao(@PathVariable Long id) {
        log.debug("REST request to delete TipoConfiguracao : {}", id);
        tipoConfiguracaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
