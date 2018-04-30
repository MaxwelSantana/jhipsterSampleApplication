package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Transmissao;
import io.github.jhipster.application.repository.TransmissaoRepository;
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
 * Test class for the TransmissaoResource REST controller.
 *
 * @see TransmissaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class TransmissaoResourceIntTest {

    private static final Instant DEFAULT_TIME_STAMP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_STAMP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_VERSAO_COLETOR = "AAAAAAAAAA";
    private static final String UPDATED_VERSAO_COLETOR = "BBBBBBBBBB";

    @Autowired
    private TransmissaoRepository transmissaoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTransmissaoMockMvc;

    private Transmissao transmissao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransmissaoResource transmissaoResource = new TransmissaoResource(transmissaoRepository);
        this.restTransmissaoMockMvc = MockMvcBuilders.standaloneSetup(transmissaoResource)
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
    public static Transmissao createEntity(EntityManager em) {
        Transmissao transmissao = new Transmissao()
            .timeStamp(DEFAULT_TIME_STAMP)
            .versaoColetor(DEFAULT_VERSAO_COLETOR);
        return transmissao;
    }

    @Before
    public void initTest() {
        transmissao = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransmissao() throws Exception {
        int databaseSizeBeforeCreate = transmissaoRepository.findAll().size();

        // Create the Transmissao
        restTransmissaoMockMvc.perform(post("/api/transmissaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transmissao)))
            .andExpect(status().isCreated());

        // Validate the Transmissao in the database
        List<Transmissao> transmissaoList = transmissaoRepository.findAll();
        assertThat(transmissaoList).hasSize(databaseSizeBeforeCreate + 1);
        Transmissao testTransmissao = transmissaoList.get(transmissaoList.size() - 1);
        assertThat(testTransmissao.getTimeStamp()).isEqualTo(DEFAULT_TIME_STAMP);
        assertThat(testTransmissao.getVersaoColetor()).isEqualTo(DEFAULT_VERSAO_COLETOR);
    }

    @Test
    @Transactional
    public void createTransmissaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transmissaoRepository.findAll().size();

        // Create the Transmissao with an existing ID
        transmissao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransmissaoMockMvc.perform(post("/api/transmissaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transmissao)))
            .andExpect(status().isBadRequest());

        // Validate the Transmissao in the database
        List<Transmissao> transmissaoList = transmissaoRepository.findAll();
        assertThat(transmissaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTransmissaos() throws Exception {
        // Initialize the database
        transmissaoRepository.saveAndFlush(transmissao);

        // Get all the transmissaoList
        restTransmissaoMockMvc.perform(get("/api/transmissaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transmissao.getId().intValue())))
            .andExpect(jsonPath("$.[*].timeStamp").value(hasItem(DEFAULT_TIME_STAMP.toString())))
            .andExpect(jsonPath("$.[*].versaoColetor").value(hasItem(DEFAULT_VERSAO_COLETOR.toString())));
    }

    @Test
    @Transactional
    public void getTransmissao() throws Exception {
        // Initialize the database
        transmissaoRepository.saveAndFlush(transmissao);

        // Get the transmissao
        restTransmissaoMockMvc.perform(get("/api/transmissaos/{id}", transmissao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transmissao.getId().intValue()))
            .andExpect(jsonPath("$.timeStamp").value(DEFAULT_TIME_STAMP.toString()))
            .andExpect(jsonPath("$.versaoColetor").value(DEFAULT_VERSAO_COLETOR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTransmissao() throws Exception {
        // Get the transmissao
        restTransmissaoMockMvc.perform(get("/api/transmissaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransmissao() throws Exception {
        // Initialize the database
        transmissaoRepository.saveAndFlush(transmissao);
        int databaseSizeBeforeUpdate = transmissaoRepository.findAll().size();

        // Update the transmissao
        Transmissao updatedTransmissao = transmissaoRepository.findOne(transmissao.getId());
        // Disconnect from session so that the updates on updatedTransmissao are not directly saved in db
        em.detach(updatedTransmissao);
        updatedTransmissao
            .timeStamp(UPDATED_TIME_STAMP)
            .versaoColetor(UPDATED_VERSAO_COLETOR);

        restTransmissaoMockMvc.perform(put("/api/transmissaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransmissao)))
            .andExpect(status().isOk());

        // Validate the Transmissao in the database
        List<Transmissao> transmissaoList = transmissaoRepository.findAll();
        assertThat(transmissaoList).hasSize(databaseSizeBeforeUpdate);
        Transmissao testTransmissao = transmissaoList.get(transmissaoList.size() - 1);
        assertThat(testTransmissao.getTimeStamp()).isEqualTo(UPDATED_TIME_STAMP);
        assertThat(testTransmissao.getVersaoColetor()).isEqualTo(UPDATED_VERSAO_COLETOR);
    }

    @Test
    @Transactional
    public void updateNonExistingTransmissao() throws Exception {
        int databaseSizeBeforeUpdate = transmissaoRepository.findAll().size();

        // Create the Transmissao

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTransmissaoMockMvc.perform(put("/api/transmissaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transmissao)))
            .andExpect(status().isCreated());

        // Validate the Transmissao in the database
        List<Transmissao> transmissaoList = transmissaoRepository.findAll();
        assertThat(transmissaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTransmissao() throws Exception {
        // Initialize the database
        transmissaoRepository.saveAndFlush(transmissao);
        int databaseSizeBeforeDelete = transmissaoRepository.findAll().size();

        // Get the transmissao
        restTransmissaoMockMvc.perform(delete("/api/transmissaos/{id}", transmissao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Transmissao> transmissaoList = transmissaoRepository.findAll();
        assertThat(transmissaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transmissao.class);
        Transmissao transmissao1 = new Transmissao();
        transmissao1.setId(1L);
        Transmissao transmissao2 = new Transmissao();
        transmissao2.setId(transmissao1.getId());
        assertThat(transmissao1).isEqualTo(transmissao2);
        transmissao2.setId(2L);
        assertThat(transmissao1).isNotEqualTo(transmissao2);
        transmissao1.setId(null);
        assertThat(transmissao1).isNotEqualTo(transmissao2);
    }
}
