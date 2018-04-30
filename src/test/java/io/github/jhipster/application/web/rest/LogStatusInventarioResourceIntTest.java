package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.LogStatusInventario;
import io.github.jhipster.application.repository.LogStatusInventarioRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LogStatusInventarioResource REST controller.
 *
 * @see LogStatusInventarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class LogStatusInventarioResourceIntTest {

    private static final Instant DEFAULT_TIME_STAMP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_STAMP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private LogStatusInventarioRepository logStatusInventarioRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLogStatusInventarioMockMvc;

    private LogStatusInventario logStatusInventario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LogStatusInventarioResource logStatusInventarioResource = new LogStatusInventarioResource(logStatusInventarioRepository);
        this.restLogStatusInventarioMockMvc = MockMvcBuilders.standaloneSetup(logStatusInventarioResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LogStatusInventario createEntity(EntityManager em) {
        LogStatusInventario logStatusInventario = new LogStatusInventario()
            .timeStamp(DEFAULT_TIME_STAMP);
        return logStatusInventario;
    }

    @Before
    public void initTest() {
        logStatusInventario = createEntity(em);
    }

    @Test
    @Transactional
    public void createLogStatusInventario() throws Exception {
        int databaseSizeBeforeCreate = logStatusInventarioRepository.findAll().size();

        // Create the LogStatusInventario
        restLogStatusInventarioMockMvc.perform(post("/api/log-status-inventarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logStatusInventario)))
            .andExpect(status().isCreated());

        // Validate the LogStatusInventario in the database
        List<LogStatusInventario> logStatusInventarioList = logStatusInventarioRepository.findAll();
        assertThat(logStatusInventarioList).hasSize(databaseSizeBeforeCreate + 1);
        LogStatusInventario testLogStatusInventario = logStatusInventarioList.get(logStatusInventarioList.size() - 1);
        assertThat(testLogStatusInventario.getTimeStamp()).isEqualTo(DEFAULT_TIME_STAMP);
    }

    @Test
    @Transactional
    public void createLogStatusInventarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = logStatusInventarioRepository.findAll().size();

        // Create the LogStatusInventario with an existing ID
        logStatusInventario.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLogStatusInventarioMockMvc.perform(post("/api/log-status-inventarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logStatusInventario)))
            .andExpect(status().isBadRequest());

        // Validate the LogStatusInventario in the database
        List<LogStatusInventario> logStatusInventarioList = logStatusInventarioRepository.findAll();
        assertThat(logStatusInventarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLogStatusInventarios() throws Exception {
        // Initialize the database
        logStatusInventarioRepository.saveAndFlush(logStatusInventario);

        // Get all the logStatusInventarioList
        restLogStatusInventarioMockMvc.perform(get("/api/log-status-inventarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(logStatusInventario.getId().intValue())))
            .andExpect(jsonPath("$.[*].timeStamp").value(hasItem(DEFAULT_TIME_STAMP.toString())));
    }

    @Test
    @Transactional
    public void getLogStatusInventario() throws Exception {
        // Initialize the database
        logStatusInventarioRepository.saveAndFlush(logStatusInventario);

        // Get the logStatusInventario
        restLogStatusInventarioMockMvc.perform(get("/api/log-status-inventarios/{id}", logStatusInventario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(logStatusInventario.getId().intValue()))
            .andExpect(jsonPath("$.timeStamp").value(DEFAULT_TIME_STAMP.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLogStatusInventario() throws Exception {
        // Get the logStatusInventario
        restLogStatusInventarioMockMvc.perform(get("/api/log-status-inventarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLogStatusInventario() throws Exception {
        // Initialize the database
        logStatusInventarioRepository.saveAndFlush(logStatusInventario);
        int databaseSizeBeforeUpdate = logStatusInventarioRepository.findAll().size();

        // Update the logStatusInventario
        LogStatusInventario updatedLogStatusInventario = logStatusInventarioRepository.findOne(logStatusInventario.getId());
        // Disconnect from session so that the updates on updatedLogStatusInventario are not directly saved in db
        em.detach(updatedLogStatusInventario);
        updatedLogStatusInventario
            .timeStamp(UPDATED_TIME_STAMP);

        restLogStatusInventarioMockMvc.perform(put("/api/log-status-inventarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLogStatusInventario)))
            .andExpect(status().isOk());

        // Validate the LogStatusInventario in the database
        List<LogStatusInventario> logStatusInventarioList = logStatusInventarioRepository.findAll();
        assertThat(logStatusInventarioList).hasSize(databaseSizeBeforeUpdate);
        LogStatusInventario testLogStatusInventario = logStatusInventarioList.get(logStatusInventarioList.size() - 1);
        assertThat(testLogStatusInventario.getTimeStamp()).isEqualTo(UPDATED_TIME_STAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingLogStatusInventario() throws Exception {
        int databaseSizeBeforeUpdate = logStatusInventarioRepository.findAll().size();

        // Create the LogStatusInventario

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLogStatusInventarioMockMvc.perform(put("/api/log-status-inventarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logStatusInventario)))
            .andExpect(status().isCreated());

        // Validate the LogStatusInventario in the database
        List<LogStatusInventario> logStatusInventarioList = logStatusInventarioRepository.findAll();
        assertThat(logStatusInventarioList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLogStatusInventario() throws Exception {
        // Initialize the database
        logStatusInventarioRepository.saveAndFlush(logStatusInventario);
        int databaseSizeBeforeDelete = logStatusInventarioRepository.findAll().size();

        // Get the logStatusInventario
        restLogStatusInventarioMockMvc.perform(delete("/api/log-status-inventarios/{id}", logStatusInventario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LogStatusInventario> logStatusInventarioList = logStatusInventarioRepository.findAll();
        assertThat(logStatusInventarioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LogStatusInventario.class);
        LogStatusInventario logStatusInventario1 = new LogStatusInventario();
        logStatusInventario1.setId(1L);
        LogStatusInventario logStatusInventario2 = new LogStatusInventario();
        logStatusInventario2.setId(logStatusInventario1.getId());
        assertThat(logStatusInventario1).isEqualTo(logStatusInventario2);
        logStatusInventario2.setId(2L);
        assertThat(logStatusInventario1).isNotEqualTo(logStatusInventario2);
        logStatusInventario1.setId(null);
        assertThat(logStatusInventario1).isNotEqualTo(logStatusInventario2);
    }
}
