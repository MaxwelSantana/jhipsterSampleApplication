package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Configuracao;

import io.github.jhipster.application.repository.ConfiguracaoRepository;
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
 * REST controller for managing Configuracao.
 */
@RestController
@RequestMapping("/api")
public class ConfiguracaoResource {

    private final Logger log = LoggerFactory.getLogger(ConfiguracaoResource.class);

    private static final String ENTITY_NAME = "configuracao";

    private final ConfiguracaoRepository configuracaoRepository;

    public ConfiguracaoResource(ConfiguracaoRepository configuracaoRepository) {
        this.configuracaoRepository = configuracaoRepository;
    }

    /**
     * POST  /configuracaos : Create a new configuracao.
     *
     * @param configuracao the configuracao to create
     * @return the ResponseEntity with status 201 (Created) and with body the new configuracao, or with status 400 (Bad Request) if the configuracao has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/configuracaos")
    @Timed
    public ResponseEntity<Configuracao> createConfiguracao(@RequestBody Configuracao configuracao) throws URISyntaxException {
        log.debug("REST request to save Configuracao : {}", configuracao);
        if (configuracao.getId() != null) {
            throw new BadRequestAlertException("A new configuracao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Configuracao result = configuracaoRepository.save(configuracao);
        return ResponseEntity.created(new URI("/api/configuracaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /configuracaos : Updates an existing configuracao.
     *
     * @param configuracao the configuracao to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated configuracao,
     * or with status 400 (Bad Request) if the configuracao is not valid,
     * or with status 500 (Internal Server Error) if the configuracao couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/configuracaos")
    @Timed
    public ResponseEntity<Configuracao> updateConfiguracao(@RequestBody Configuracao configuracao) throws URISyntaxException {
        log.debug("REST request to update Configuracao : {}", configuracao);
        if (configuracao.getId() == null) {
            return createConfiguracao(configuracao);
        }
        Configuracao result = configuracaoRepository.save(configuracao);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, configuracao.getId().toString()))
            .body(result);
    }

    /**
     * GET  /configuracaos : get all the configuracaos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of configuracaos in body
     */
    @GetMapping("/configuracaos")
    @Timed
    public List<Configuracao> getAllConfiguracaos() {
        log.debug("REST request to get all Configuracaos");
        return configuracaoRepository.findAll();
        }

    /**
     * GET  /configuracaos/:id : get the "id" configuracao.
     *
     * @param id the id of the configuracao to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the configuracao, or with status 404 (Not Found)
     */
    @GetMapping("/configuracaos/{id}")
    @Timed
    public ResponseEntity<Configuracao> getConfiguracao(@PathVariable Long id) {
        log.debug("REST request to get Configuracao : {}", id);
        Configuracao configuracao = configuracaoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(configuracao));
    }

    /**
     * DELETE  /configuracaos/:id : delete the "id" configuracao.
     *
     * @param id the id of the configuracao to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/configuracaos/{id}")
    @Timed
    public ResponseEntity<Void> deleteConfiguracao(@PathVariable Long id) {
        log.debug("REST request to delete Configuracao : {}", id);
        configuracaoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
