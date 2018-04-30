package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.LogAlteracaoContagem;

import io.github.jhipster.application.repository.LogAlteracaoContagemRepository;
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
 * REST controller for managing LogAlteracaoContagem.
 */
@RestController
@RequestMapping("/api")
public class LogAlteracaoContagemResource {

    private final Logger log = LoggerFactory.getLogger(LogAlteracaoContagemResource.class);

    private static final String ENTITY_NAME = "logAlteracaoContagem";

    private final LogAlteracaoContagemRepository logAlteracaoContagemRepository;

    public LogAlteracaoContagemResource(LogAlteracaoContagemRepository logAlteracaoContagemRepository) {
        this.logAlteracaoContagemRepository = logAlteracaoContagemRepository;
    }

    /**
     * POST  /log-alteracao-contagems : Create a new logAlteracaoContagem.
     *
     * @param logAlteracaoContagem the logAlteracaoContagem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new logAlteracaoContagem, or with status 400 (Bad Request) if the logAlteracaoContagem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/log-alteracao-contagems")
    @Timed
    public ResponseEntity<LogAlteracaoContagem> createLogAlteracaoContagem(@RequestBody LogAlteracaoContagem logAlteracaoContagem) throws URISyntaxException {
        log.debug("REST request to save LogAlteracaoContagem : {}", logAlteracaoContagem);
        if (logAlteracaoContagem.getId() != null) {
            throw new BadRequestAlertException("A new logAlteracaoContagem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LogAlteracaoContagem result = logAlteracaoContagemRepository.save(logAlteracaoContagem);
        return ResponseEntity.created(new URI("/api/log-alteracao-contagems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /log-alteracao-contagems : Updates an existing logAlteracaoContagem.
     *
     * @param logAlteracaoContagem the logAlteracaoContagem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated logAlteracaoContagem,
     * or with status 400 (Bad Request) if the logAlteracaoContagem is not valid,
     * or with status 500 (Internal Server Error) if the logAlteracaoContagem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/log-alteracao-contagems")
    @Timed
    public ResponseEntity<LogAlteracaoContagem> updateLogAlteracaoContagem(@RequestBody LogAlteracaoContagem logAlteracaoContagem) throws URISyntaxException {
        log.debug("REST request to update LogAlteracaoContagem : {}", logAlteracaoContagem);
        if (logAlteracaoContagem.getId() == null) {
            return createLogAlteracaoContagem(logAlteracaoContagem);
        }
        LogAlteracaoContagem result = logAlteracaoContagemRepository.save(logAlteracaoContagem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, logAlteracaoContagem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /log-alteracao-contagems : get all the logAlteracaoContagems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of logAlteracaoContagems in body
     */
    @GetMapping("/log-alteracao-contagems")
    @Timed
    public List<LogAlteracaoContagem> getAllLogAlteracaoContagems() {
        log.debug("REST request to get all LogAlteracaoContagems");
        return logAlteracaoContagemRepository.findAll();
        }

    /**
     * GET  /log-alteracao-contagems/:id : get the "id" logAlteracaoContagem.
     *
     * @param id the id of the logAlteracaoContagem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the logAlteracaoContagem, or with status 404 (Not Found)
     */
    @GetMapping("/log-alteracao-contagems/{id}")
    @Timed
    public ResponseEntity<LogAlteracaoContagem> getLogAlteracaoContagem(@PathVariable Long id) {
        log.debug("REST request to get LogAlteracaoContagem : {}", id);
        LogAlteracaoContagem logAlteracaoContagem = logAlteracaoContagemRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(logAlteracaoContagem));
    }

    /**
     * DELETE  /log-alteracao-contagems/:id : delete the "id" logAlteracaoContagem.
     *
     * @param id the id of the logAlteracaoContagem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/log-alteracao-contagems/{id}")
    @Timed
    public ResponseEntity<Void> deleteLogAlteracaoContagem(@PathVariable Long id) {
        log.debug("REST request to delete LogAlteracaoContagem : {}", id);
        logAlteracaoContagemRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
