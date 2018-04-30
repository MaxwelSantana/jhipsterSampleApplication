package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.LogAlteracaoContagem;
import io.github.jhipster.application.repository.LogAlteracaoContagemRepository;
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
 * Test class for the LogAlteracaoContagemResource REST controller.
 *
 * @see LogAlteracaoContagemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class LogAlteracaoContagemResourceIntTest {

    private static final String DEFAULT_CAMPO = "AAAAAAAAAA";
    private static final String UPDATED_CAMPO = "BBBBBBBBBB";

    private static final String DEFAULT_VALOR_ANTIGO = "AAAAAAAAAA";
    private static final String UPDATED_VALOR_ANTIGO = "BBBBBBBBBB";

    private static final String DEFAULT_VALOR_NOVO = "AAAAAAAAAA";
    private static final String UPDATED_VALOR_NOVO = "BBBBBBBBBB";

    private static final Instant DEFAULT_TIME_STAMP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_STAMP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private LogAlteracaoContagemRepository logAlteracaoContagemRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLogAlteracaoContagemMockMvc;

    private LogAlteracaoContagem logAlteracaoContagem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LogAlteracaoContagemResource logAlteracaoContagemResource = new LogAlteracaoContagemResource(logAlteracaoContagemRepository);
        this.restLogAlteracaoContagemMockMvc = MockMvcBuilders.standaloneSetup(logAlteracaoContagemResource)
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
    public static LogAlteracaoContagem createEntity(EntityManager em) {
        LogAlteracaoContagem logAlteracaoContagem = new LogAlteracaoContagem()
            .campo(DEFAULT_CAMPO)
            .valorAntigo(DEFAULT_VALOR_ANTIGO)
            .valorNovo(DEFAULT_VALOR_NOVO)
            .timeStamp(DEFAULT_TIME_STAMP);
        return logAlteracaoContagem;
    }

    @Before
    public void initTest() {
        logAlteracaoContagem = createEntity(em);
    }

    @Test
    @Transactional
    public void createLogAlteracaoContagem() throws Exception {
        int databaseSizeBeforeCreate = logAlteracaoContagemRepository.findAll().size();

        // Create the LogAlteracaoContagem
        restLogAlteracaoContagemMockMvc.perform(post("/api/log-alteracao-contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logAlteracaoContagem)))
            .andExpect(status().isCreated());

        // Validate the LogAlteracaoContagem in the database
        List<LogAlteracaoContagem> logAlteracaoContagemList = logAlteracaoContagemRepository.findAll();
        assertThat(logAlteracaoContagemList).hasSize(databaseSizeBeforeCreate + 1);
        LogAlteracaoContagem testLogAlteracaoContagem = logAlteracaoContagemList.get(logAlteracaoContagemList.size() - 1);
        assertThat(testLogAlteracaoContagem.getCampo()).isEqualTo(DEFAULT_CAMPO);
        assertThat(testLogAlteracaoContagem.getValorAntigo()).isEqualTo(DEFAULT_VALOR_ANTIGO);
        assertThat(testLogAlteracaoContagem.getValorNovo()).isEqualTo(DEFAULT_VALOR_NOVO);
        assertThat(testLogAlteracaoContagem.getTimeStamp()).isEqualTo(DEFAULT_TIME_STAMP);
    }

    @Test
    @Transactional
    public void createLogAlteracaoContagemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = logAlteracaoContagemRepository.findAll().size();

        // Create the LogAlteracaoContagem with an existing ID
        logAlteracaoContagem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLogAlteracaoContagemMockMvc.perform(post("/api/log-alteracao-contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logAlteracaoContagem)))
            .andExpect(status().isBadRequest());

        // Validate the LogAlteracaoContagem in the database
        List<LogAlteracaoContagem> logAlteracaoContagemList = logAlteracaoContagemRepository.findAll();
        assertThat(logAlteracaoContagemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLogAlteracaoContagems() throws Exception {
        // Initialize the database
        logAlteracaoContagemRepository.saveAndFlush(logAlteracaoContagem);

        // Get all the logAlteracaoContagemList
        restLogAlteracaoContagemMockMvc.perform(get("/api/log-alteracao-contagems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(logAlteracaoContagem.getId().intValue())))
            .andExpect(jsonPath("$.[*].campo").value(hasItem(DEFAULT_CAMPO.toString())))
            .andExpect(jsonPath("$.[*].valorAntigo").value(hasItem(DEFAULT_VALOR_ANTIGO.toString())))
            .andExpect(jsonPath("$.[*].valorNovo").value(hasItem(DEFAULT_VALOR_NOVO.toString())))
            .andExpect(jsonPath("$.[*].timeStamp").value(hasItem(DEFAULT_TIME_STAMP.toString())));
    }

    @Test
    @Transactional
    public void getLogAlteracaoContagem() throws Exception {
        // Initialize the database
        logAlteracaoContagemRepository.saveAndFlush(logAlteracaoContagem);

        // Get the logAlteracaoContagem
        restLogAlteracaoContagemMockMvc.perform(get("/api/log-alteracao-contagems/{id}", logAlteracaoContagem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(logAlteracaoContagem.getId().intValue()))
            .andExpect(jsonPath("$.campo").value(DEFAULT_CAMPO.toString()))
            .andExpect(jsonPath("$.valorAntigo").value(DEFAULT_VALOR_ANTIGO.toString()))
            .andExpect(jsonPath("$.valorNovo").value(DEFAULT_VALOR_NOVO.toString()))
            .andExpect(jsonPath("$.timeStamp").value(DEFAULT_TIME_STAMP.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLogAlteracaoContagem() throws Exception {
        // Get the logAlteracaoContagem
        restLogAlteracaoContagemMockMvc.perform(get("/api/log-alteracao-contagems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLogAlteracaoContagem() throws Exception {
        // Initialize the database
        logAlteracaoContagemRepository.saveAndFlush(logAlteracaoContagem);
        int databaseSizeBeforeUpdate = logAlteracaoContagemRepository.findAll().size();

        // Update the logAlteracaoContagem
        LogAlteracaoContagem updatedLogAlteracaoContagem = logAlteracaoContagemRepository.findOne(logAlteracaoContagem.getId());
        // Disconnect from session so that the updates on updatedLogAlteracaoContagem are not directly saved in db
        em.detach(updatedLogAlteracaoContagem);
        updatedLogAlteracaoContagem
            .campo(UPDATED_CAMPO)
            .valorAntigo(UPDATED_VALOR_ANTIGO)
            .valorNovo(UPDATED_VALOR_NOVO)
            .timeStamp(UPDATED_TIME_STAMP);

        restLogAlteracaoContagemMockMvc.perform(put("/api/log-alteracao-contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLogAlteracaoContagem)))
            .andExpect(status().isOk());

        // Validate the LogAlteracaoContagem in the database
        List<LogAlteracaoContagem> logAlteracaoContagemList = logAlteracaoContagemRepository.findAll();
        assertThat(logAlteracaoContagemList).hasSize(databaseSizeBeforeUpdate);
        LogAlteracaoContagem testLogAlteracaoContagem = logAlteracaoContagemList.get(logAlteracaoContagemList.size() - 1);
        assertThat(testLogAlteracaoContagem.getCampo()).isEqualTo(UPDATED_CAMPO);
        assertThat(testLogAlteracaoContagem.getValorAntigo()).isEqualTo(UPDATED_VALOR_ANTIGO);
        assertThat(testLogAlteracaoContagem.getValorNovo()).isEqualTo(UPDATED_VALOR_NOVO);
        assertThat(testLogAlteracaoContagem.getTimeStamp()).isEqualTo(UPDATED_TIME_STAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingLogAlteracaoContagem() throws Exception {
        int databaseSizeBeforeUpdate = logAlteracaoContagemRepository.findAll().size();

        // Create the LogAlteracaoContagem

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLogAlteracaoContagemMockMvc.perform(put("/api/log-alteracao-contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logAlteracaoContagem)))
            .andExpect(status().isCreated());

        // Validate the LogAlteracaoContagem in the database
        List<LogAlteracaoContagem> logAlteracaoContagemList = logAlteracaoContagemRepository.findAll();
        assertThat(logAlteracaoContagemList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLogAlteracaoContagem() throws Exception {
        // Initialize the database
        logAlteracaoContagemRepository.saveAndFlush(logAlteracaoContagem);
        int databaseSizeBeforeDelete = logAlteracaoContagemRepository.findAll().size();

        // Get the logAlteracaoContagem
        restLogAlteracaoContagemMockMvc.perform(delete("/api/log-alteracao-contagems/{id}", logAlteracaoContagem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LogAlteracaoContagem> logAlteracaoContagemList = logAlteracaoContagemRepository.findAll();
        assertThat(logAlteracaoContagemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LogAlteracaoContagem.class);
        LogAlteracaoContagem logAlteracaoContagem1 = new LogAlteracaoContagem();
        logAlteracaoContagem1.setId(1L);
        LogAlteracaoContagem logAlteracaoContagem2 = new LogAlteracaoContagem();
        logAlteracaoContagem2.setId(logAlteracaoContagem1.getId());
        assertThat(logAlteracaoContagem1).isEqualTo(logAlteracaoContagem2);
        logAlteracaoContagem2.setId(2L);
        assertThat(logAlteracaoContagem1).isNotEqualTo(logAlteracaoContagem2);
        logAlteracaoContagem1.setId(null);
        assertThat(logAlteracaoContagem1).isNotEqualTo(logAlteracaoContagem2);
    }
}
