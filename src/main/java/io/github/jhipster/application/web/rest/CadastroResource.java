package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Cadastro;

import io.github.jhipster.application.repository.CadastroRepository;
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
 * REST controller for managing Cadastro.
 */
@RestController
@RequestMapping("/api")
public class CadastroResource {

    private final Logger log = LoggerFactory.getLogger(CadastroResource.class);

    private static final String ENTITY_NAME = "cadastro";

    private final CadastroRepository cadastroRepository;

    public CadastroResource(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    /**
     * POST  /cadastros : Create a new cadastro.
     *
     * @param cadastro the cadastro to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cadastro, or with status 400 (Bad Request) if the cadastro has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cadastros")
    @Timed
    public ResponseEntity<Cadastro> createCadastro(@RequestBody Cadastro cadastro) throws URISyntaxException {
        log.debug("REST request to save Cadastro : {}", cadastro);
        if (cadastro.getId() != null) {
            throw new BadRequestAlertException("A new cadastro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Cadastro result = cadastroRepository.save(cadastro);
        return ResponseEntity.created(new URI("/api/cadastros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cadastros : Updates an existing cadastro.
     *
     * @param cadastro the cadastro to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cadastro,
     * or with status 400 (Bad Request) if the cadastro is not valid,
     * or with status 500 (Internal Server Error) if the cadastro couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cadastros")
    @Timed
    public ResponseEntity<Cadastro> updateCadastro(@RequestBody Cadastro cadastro) throws URISyntaxException {
        log.debug("REST request to update Cadastro : {}", cadastro);
        if (cadastro.getId() == null) {
            return createCadastro(cadastro);
        }
        Cadastro result = cadastroRepository.save(cadastro);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cadastro.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cadastros : get all the cadastros.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of cadastros in body
     */
    @GetMapping("/cadastros")
    @Timed
    public List<Cadastro> getAllCadastros() {
        log.debug("REST request to get all Cadastros");
        return cadastroRepository.findAll();
        }

    /**
     * GET  /cadastros/:id : get the "id" cadastro.
     *
     * @param id the id of the cadastro to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cadastro, or with status 404 (Not Found)
     */
    @GetMapping("/cadastros/{id}")
    @Timed
    public ResponseEntity<Cadastro> getCadastro(@PathVariable Long id) {
        log.debug("REST request to get Cadastro : {}", id);
        Cadastro cadastro = cadastroRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(cadastro));
    }

    /**
     * DELETE  /cadastros/:id : delete the "id" cadastro.
     *
     * @param id the id of the cadastro to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cadastros/{id}")
    @Timed
    public ResponseEntity<Void> deleteCadastro(@PathVariable Long id) {
        log.debug("REST request to delete Cadastro : {}", id);
        cadastroRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
