package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.ConfiguracaoColetor;

import io.github.jhipster.application.repository.ConfiguracaoColetorRepository;
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
 * REST controller for managing ConfiguracaoColetor.
 */
@RestController
@RequestMapping("/api")
public class ConfiguracaoColetorResource {

    private final Logger log = LoggerFactory.getLogger(ConfiguracaoColetorResource.class);

    private static final String ENTITY_NAME = "configuracaoColetor";

    private final ConfiguracaoColetorRepository configuracaoColetorRepository;

    public ConfiguracaoColetorResource(ConfiguracaoColetorRepository configuracaoColetorRepository) {
        this.configuracaoColetorRepository = configuracaoColetorRepository;
    }

    /**
     * POST  /configuracao-coletors : Create a new configuracaoColetor.
     *
     * @param configuracaoColetor the configuracaoColetor to create
     * @return the ResponseEntity with status 201 (Created) and with body the new configuracaoColetor, or with status 400 (Bad Request) if the configuracaoColetor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/configuracao-coletors")
    @Timed
    public ResponseEntity<ConfiguracaoColetor> createConfiguracaoColetor(@RequestBody ConfiguracaoColetor configuracaoColetor) throws URISyntaxException {
        log.debug("REST request to save ConfiguracaoColetor : {}", configuracaoColetor);
        if (configuracaoColetor.getId() != null) {
            throw new BadRequestAlertException("A new configuracaoColetor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConfiguracaoColetor result = configuracaoColetorRepository.save(configuracaoColetor);
        return ResponseEntity.created(new URI("/api/configuracao-coletors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /configuracao-coletors : Updates an existing configuracaoColetor.
     *
     * @param configuracaoColetor the configuracaoColetor to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated configuracaoColetor,
     * or with status 400 (Bad Request) if the configuracaoColetor is not valid,
     * or with status 500 (Internal Server Error) if the configuracaoColetor couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/configuracao-coletors")
    @Timed
    public ResponseEntity<ConfiguracaoColetor> updateConfiguracaoColetor(@RequestBody ConfiguracaoColetor configuracaoColetor) throws URISyntaxException {
        log.debug("REST request to update ConfiguracaoColetor : {}", configuracaoColetor);
        if (configuracaoColetor.getId() == null) {
            return createConfiguracaoColetor(configuracaoColetor);
        }
        ConfiguracaoColetor result = configuracaoColetorRepository.save(configuracaoColetor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, configuracaoColetor.getId().toString()))
            .body(result);
    }

    /**
     * GET  /configuracao-coletors : get all the configuracaoColetors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of configuracaoColetors in body
     */
    @GetMapping("/configuracao-coletors")
    @Timed
    public List<ConfiguracaoColetor> getAllConfiguracaoColetors() {
        log.debug("REST request to get all ConfiguracaoColetors");
        return configuracaoColetorRepository.findAll();
        }

    /**
     * GET  /configuracao-coletors/:id : get the "id" configuracaoColetor.
     *
     * @param id the id of the configuracaoColetor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the configuracaoColetor, or with status 404 (Not Found)
     */
    @GetMapping("/configuracao-coletors/{id}")
    @Timed
    public ResponseEntity<ConfiguracaoColetor> getConfiguracaoColetor(@PathVariable Long id) {
        log.debug("REST request to get ConfiguracaoColetor : {}", id);
        ConfiguracaoColetor configuracaoColetor = configuracaoColetorRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(configuracaoColetor));
    }

    /**
     * DELETE  /configuracao-coletors/:id : delete the "id" configuracaoColetor.
     *
     * @param id the id of the configuracaoColetor to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/configuracao-coletors/{id}")
    @Timed
    public ResponseEntity<Void> deleteConfiguracaoColetor(@PathVariable Long id) {
        log.debug("REST request to delete ConfiguracaoColetor : {}", id);
        configuracaoColetorRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
