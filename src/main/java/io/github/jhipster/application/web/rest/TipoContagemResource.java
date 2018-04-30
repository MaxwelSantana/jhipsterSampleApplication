package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.TipoContagem;

import io.github.jhipster.application.repository.TipoContagemRepository;
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
 * REST controller for managing TipoContagem.
 */
@RestController
@RequestMapping("/api")
public class TipoContagemResource {

    private final Logger log = LoggerFactory.getLogger(TipoContagemResource.class);

    private static final String ENTITY_NAME = "tipoContagem";

    private final TipoContagemRepository tipoContagemRepository;

    public TipoContagemResource(TipoContagemRepository tipoContagemRepository) {
        this.tipoContagemRepository = tipoContagemRepository;
    }

    /**
     * POST  /tipo-contagems : Create a new tipoContagem.
     *
     * @param tipoContagem the tipoContagem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoContagem, or with status 400 (Bad Request) if the tipoContagem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-contagems")
    @Timed
    public ResponseEntity<TipoContagem> createTipoContagem(@RequestBody TipoContagem tipoContagem) throws URISyntaxException {
        log.debug("REST request to save TipoContagem : {}", tipoContagem);
        if (tipoContagem.getId() != null) {
            throw new BadRequestAlertException("A new tipoContagem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoContagem result = tipoContagemRepository.save(tipoContagem);
        return ResponseEntity.created(new URI("/api/tipo-contagems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-contagems : Updates an existing tipoContagem.
     *
     * @param tipoContagem the tipoContagem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoContagem,
     * or with status 400 (Bad Request) if the tipoContagem is not valid,
     * or with status 500 (Internal Server Error) if the tipoContagem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-contagems")
    @Timed
    public ResponseEntity<TipoContagem> updateTipoContagem(@RequestBody TipoContagem tipoContagem) throws URISyntaxException {
        log.debug("REST request to update TipoContagem : {}", tipoContagem);
        if (tipoContagem.getId() == null) {
            return createTipoContagem(tipoContagem);
        }
        TipoContagem result = tipoContagemRepository.save(tipoContagem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoContagem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-contagems : get all the tipoContagems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipoContagems in body
     */
    @GetMapping("/tipo-contagems")
    @Timed
    public List<TipoContagem> getAllTipoContagems() {
        log.debug("REST request to get all TipoContagems");
        return tipoContagemRepository.findAll();
        }

    /**
     * GET  /tipo-contagems/:id : get the "id" tipoContagem.
     *
     * @param id the id of the tipoContagem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoContagem, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-contagems/{id}")
    @Timed
    public ResponseEntity<TipoContagem> getTipoContagem(@PathVariable Long id) {
        log.debug("REST request to get TipoContagem : {}", id);
        TipoContagem tipoContagem = tipoContagemRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipoContagem));
    }

    /**
     * DELETE  /tipo-contagems/:id : delete the "id" tipoContagem.
     *
     * @param id the id of the tipoContagem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-contagems/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoContagem(@PathVariable Long id) {
        log.debug("REST request to delete TipoContagem : {}", id);
        tipoContagemRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
