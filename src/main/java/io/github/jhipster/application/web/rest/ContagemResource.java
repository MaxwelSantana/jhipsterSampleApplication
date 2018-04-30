package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Contagem;

import io.github.jhipster.application.repository.ContagemRepository;
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
 * REST controller for managing Contagem.
 */
@RestController
@RequestMapping("/api")
public class ContagemResource {

    private final Logger log = LoggerFactory.getLogger(ContagemResource.class);

    private static final String ENTITY_NAME = "contagem";

    private final ContagemRepository contagemRepository;

    public ContagemResource(ContagemRepository contagemRepository) {
        this.contagemRepository = contagemRepository;
    }

    /**
     * POST  /contagems : Create a new contagem.
     *
     * @param contagem the contagem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contagem, or with status 400 (Bad Request) if the contagem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contagems")
    @Timed
    public ResponseEntity<Contagem> createContagem(@RequestBody Contagem contagem) throws URISyntaxException {
        log.debug("REST request to save Contagem : {}", contagem);
        if (contagem.getId() != null) {
            throw new BadRequestAlertException("A new contagem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Contagem result = contagemRepository.save(contagem);
        return ResponseEntity.created(new URI("/api/contagems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contagems : Updates an existing contagem.
     *
     * @param contagem the contagem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contagem,
     * or with status 400 (Bad Request) if the contagem is not valid,
     * or with status 500 (Internal Server Error) if the contagem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contagems")
    @Timed
    public ResponseEntity<Contagem> updateContagem(@RequestBody Contagem contagem) throws URISyntaxException {
        log.debug("REST request to update Contagem : {}", contagem);
        if (contagem.getId() == null) {
            return createContagem(contagem);
        }
        Contagem result = contagemRepository.save(contagem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contagem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contagems : get all the contagems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of contagems in body
     */
    @GetMapping("/contagems")
    @Timed
    public List<Contagem> getAllContagems() {
        log.debug("REST request to get all Contagems");
        return contagemRepository.findAll();
        }

    /**
     * GET  /contagems/:id : get the "id" contagem.
     *
     * @param id the id of the contagem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contagem, or with status 404 (Not Found)
     */
    @GetMapping("/contagems/{id}")
    @Timed
    public ResponseEntity<Contagem> getContagem(@PathVariable Long id) {
        log.debug("REST request to get Contagem : {}", id);
        Contagem contagem = contagemRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(contagem));
    }

    /**
     * DELETE  /contagems/:id : delete the "id" contagem.
     *
     * @param id the id of the contagem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contagems/{id}")
    @Timed
    public ResponseEntity<Void> deleteContagem(@PathVariable Long id) {
        log.debug("REST request to delete Contagem : {}", id);
        contagemRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
