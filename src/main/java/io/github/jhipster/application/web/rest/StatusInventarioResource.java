package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.StatusInventario;

import io.github.jhipster.application.repository.StatusInventarioRepository;
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
 * REST controller for managing StatusInventario.
 */
@RestController
@RequestMapping("/api")
public class StatusInventarioResource {

    private final Logger log = LoggerFactory.getLogger(StatusInventarioResource.class);

    private static final String ENTITY_NAME = "statusInventario";

    private final StatusInventarioRepository statusInventarioRepository;

    public StatusInventarioResource(StatusInventarioRepository statusInventarioRepository) {
        this.statusInventarioRepository = statusInventarioRepository;
    }

    /**
     * POST  /status-inventarios : Create a new statusInventario.
     *
     * @param statusInventario the statusInventario to create
     * @return the ResponseEntity with status 201 (Created) and with body the new statusInventario, or with status 400 (Bad Request) if the statusInventario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/status-inventarios")
    @Timed
    public ResponseEntity<StatusInventario> createStatusInventario(@RequestBody StatusInventario statusInventario) throws URISyntaxException {
        log.debug("REST request to save StatusInventario : {}", statusInventario);
        if (statusInventario.getId() != null) {
            throw new BadRequestAlertException("A new statusInventario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StatusInventario result = statusInventarioRepository.save(statusInventario);
        return ResponseEntity.created(new URI("/api/status-inventarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /status-inventarios : Updates an existing statusInventario.
     *
     * @param statusInventario the statusInventario to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated statusInventario,
     * or with status 400 (Bad Request) if the statusInventario is not valid,
     * or with status 500 (Internal Server Error) if the statusInventario couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/status-inventarios")
    @Timed
    public ResponseEntity<StatusInventario> updateStatusInventario(@RequestBody StatusInventario statusInventario) throws URISyntaxException {
        log.debug("REST request to update StatusInventario : {}", statusInventario);
        if (statusInventario.getId() == null) {
            return createStatusInventario(statusInventario);
        }
        StatusInventario result = statusInventarioRepository.save(statusInventario);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, statusInventario.getId().toString()))
            .body(result);
    }

    /**
     * GET  /status-inventarios : get all the statusInventarios.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of statusInventarios in body
     */
    @GetMapping("/status-inventarios")
    @Timed
    public List<StatusInventario> getAllStatusInventarios() {
        log.debug("REST request to get all StatusInventarios");
        return statusInventarioRepository.findAll();
        }

    /**
     * GET  /status-inventarios/:id : get the "id" statusInventario.
     *
     * @param id the id of the statusInventario to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the statusInventario, or with status 404 (Not Found)
     */
    @GetMapping("/status-inventarios/{id}")
    @Timed
    public ResponseEntity<StatusInventario> getStatusInventario(@PathVariable Long id) {
        log.debug("REST request to get StatusInventario : {}", id);
        StatusInventario statusInventario = statusInventarioRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(statusInventario));
    }

    /**
     * DELETE  /status-inventarios/:id : delete the "id" statusInventario.
     *
     * @param id the id of the statusInventario to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/status-inventarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteStatusInventario(@PathVariable Long id) {
        log.debug("REST request to delete StatusInventario : {}", id);
        statusInventarioRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
