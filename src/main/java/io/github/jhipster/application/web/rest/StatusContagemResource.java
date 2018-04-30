package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.StatusContagem;

import io.github.jhipster.application.repository.StatusContagemRepository;
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
 * REST controller for managing StatusContagem.
 */
@RestController
@RequestMapping("/api")
public class StatusContagemResource {

    private final Logger log = LoggerFactory.getLogger(StatusContagemResource.class);

    private static final String ENTITY_NAME = "statusContagem";

    private final StatusContagemRepository statusContagemRepository;

    public StatusContagemResource(StatusContagemRepository statusContagemRepository) {
        this.statusContagemRepository = statusContagemRepository;
    }

    /**
     * POST  /status-contagems : Create a new statusContagem.
     *
     * @param statusContagem the statusContagem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new statusContagem, or with status 400 (Bad Request) if the statusContagem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/status-contagems")
    @Timed
    public ResponseEntity<StatusContagem> createStatusContagem(@RequestBody StatusContagem statusContagem) throws URISyntaxException {
        log.debug("REST request to save StatusContagem : {}", statusContagem);
        if (statusContagem.getId() != null) {
            throw new BadRequestAlertException("A new statusContagem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatusContagem result = statusContagemRepository.save(statusContagem);
        return ResponseEntity.created(new URI("/api/status-contagems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /status-contagems : Updates an existing statusContagem.
     *
     * @param statusContagem the statusContagem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated statusContagem,
     * or with status 400 (Bad Request) if the statusContagem is not valid,
     * or with status 500 (Internal Server Error) if the statusContagem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/status-contagems")
    @Timed
    public ResponseEntity<StatusContagem> updateStatusContagem(@RequestBody StatusContagem statusContagem) throws URISyntaxException {
        log.debug("REST request to update StatusContagem : {}", statusContagem);
        if (statusContagem.getId() == null) {
            return createStatusContagem(statusContagem);
        }
        StatusContagem result = statusContagemRepository.save(statusContagem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, statusContagem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /status-contagems : get all the statusContagems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of statusContagems in body
     */
    @GetMapping("/status-contagems")
    @Timed
    public List<StatusContagem> getAllStatusContagems() {
        log.debug("REST request to get all StatusContagems");
        return statusContagemRepository.findAll();
        }

    /**
     * GET  /status-contagems/:id : get the "id" statusContagem.
     *
     * @param id the id of the statusContagem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the statusContagem, or with status 404 (Not Found)
     */
    @GetMapping("/status-contagems/{id}")
    @Timed
    public ResponseEntity<StatusContagem> getStatusContagem(@PathVariable Long id) {
        log.debug("REST request to get StatusContagem : {}", id);
        StatusContagem statusContagem = statusContagemRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(statusContagem));
    }

    /**
     * DELETE  /status-contagems/:id : delete the "id" statusContagem.
     *
     * @param id the id of the statusContagem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/status-contagems/{id}")
    @Timed
    public ResponseEntity<Void> deleteStatusContagem(@PathVariable Long id) {
        log.debug("REST request to delete StatusContagem : {}", id);
        statusContagemRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
