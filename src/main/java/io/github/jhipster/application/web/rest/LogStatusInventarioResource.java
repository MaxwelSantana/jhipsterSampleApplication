package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.LogStatusInventario;

import io.github.jhipster.application.repository.LogStatusInventarioRepository;
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
 * REST controller for managing LogStatusInventario.
 */
@RestController
@RequestMapping("/api")
public class LogStatusInventarioResource {

    private final Logger log = LoggerFactory.getLogger(LogStatusInventarioResource.class);

    private static final String ENTITY_NAME = "logStatusInventario";

    private final LogStatusInventarioRepository logStatusInventarioRepository;

    public LogStatusInventarioResource(LogStatusInventarioRepository logStatusInventarioRepository) {
        this.logStatusInventarioRepository = logStatusInventarioRepository;
    }

    /**
     * POST  /log-status-inventarios : Create a new logStatusInventario.
     *
     * @param logStatusInventario the logStatusInventario to create
     * @return the ResponseEntity with status 201 (Created) and with body the new logStatusInventario, or with status 400 (Bad Request) if the logStatusInventario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/log-status-inventarios")
    @Timed
    public ResponseEntity<LogStatusInventario> createLogStatusInventario(@RequestBody LogStatusInventario logStatusInventario) throws URISyntaxException {
        log.debug("REST request to save LogStatusInventario : {}", logStatusInventario);
        if (logStatusInventario.getId() != null) {
            throw new BadRequestAlertException("A new logStatusInventario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LogStatusInventario result = logStatusInventarioRepository.save(logStatusInventario);
        return ResponseEntity.created(new URI("/api/log-status-inventarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /log-status-inventarios : Updates an existing logStatusInventario.
     *
     * @param logStatusInventario the logStatusInventario to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated logStatusInventario,
     * or with status 400 (Bad Request) if the logStatusInventario is not valid,
     * or with status 500 (Internal Server Error) if the logStatusInventario couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/log-status-inventarios")
    @Timed
    public ResponseEntity<LogStatusInventario> updateLogStatusInventario(@RequestBody LogStatusInventario logStatusInventario) throws URISyntaxException {
        log.debug("REST request to update LogStatusInventario : {}", logStatusInventario);
        if (logStatusInventario.getId() == null) {
            return createLogStatusInventario(logStatusInventario);
        }
        LogStatusInventario result = logStatusInventarioRepository.save(logStatusInventario);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, logStatusInventario.getId().toString()))
            .body(result);
    }

    /**
     * GET  /log-status-inventarios : get all the logStatusInventarios.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of logStatusInventarios in body
     */
    @GetMapping("/log-status-inventarios")
    @Timed
    public List<LogStatusInventario> getAllLogStatusInventarios() {
        log.debug("REST request to get all LogStatusInventarios");
        return logStatusInventarioRepository.findAll();
        }

    /**
     * GET  /log-status-inventarios/:id : get the "id" logStatusInventario.
     *
     * @param id the id of the logStatusInventario to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the logStatusInventario, or with status 404 (Not Found)
     */
    @GetMapping("/log-status-inventarios/{id}")
    @Timed
    public ResponseEntity<LogStatusInventario> getLogStatusInventario(@PathVariable Long id) {
        log.debug("REST request to get LogStatusInventario : {}", id);
        LogStatusInventario logStatusInventario = logStatusInventarioRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(logStatusInventario));
    }

    /**
     * DELETE  /log-status-inventarios/:id : delete the "id" logStatusInventario.
     *
     * @param id the id of the logStatusInventario to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/log-status-inventarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteLogStatusInventario(@PathVariable Long id) {
        log.debug("REST request to delete LogStatusInventario : {}", id);
        logStatusInventarioRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
