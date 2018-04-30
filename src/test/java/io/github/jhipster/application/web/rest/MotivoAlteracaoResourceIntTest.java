package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.MotivoAlteracao;
import io.github.jhipster.application.repository.MotivoAlteracaoRepository;
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
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MotivoAlteracaoResource REST controller.
 *
 * @see MotivoAlteracaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class MotivoAlteracaoResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private MotivoAlteracaoRepository motivoAlteracaoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMotivoAlteracaoMockMvc;

    private MotivoAlteracao motivoAlteracao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MotivoAlteracaoResource motivoAlteracaoResource = new MotivoAlteracaoResource(motivoAlteracaoRepository);
        this.restMotivoAlteracaoMockMvc = MockMvcBuilders.standaloneSetup(motivoAlteracaoResource)
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
    public static MotivoAlteracao createEntity(EntityManager em) {
        MotivoAlteracao motivoAlteracao = new MotivoAlteracao()
            .descricao(DEFAULT_DESCRICAO);
        return motivoAlteracao;
    }

    @Before
    public void initTest() {
        motivoAlteracao = createEntity(em);
    }

    @Test
    @Transactional
    public void createMotivoAlteracao() throws Exception {
        int databaseSizeBeforeCreate = motivoAlteracaoRepository.findAll().size();

        // Create the MotivoAlteracao
        restMotivoAlteracaoMockMvc.perform(post("/api/motivo-alteracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motivoAlteracao)))
            .andExpect(status().isCreated());

        // Validate the MotivoAlteracao in the database
        List<MotivoAlteracao> motivoAlteracaoList = motivoAlteracaoRepository.findAll();
        assertThat(motivoAlteracaoList).hasSize(databaseSizeBeforeCreate + 1);
        MotivoAlteracao testMotivoAlteracao = motivoAlteracaoList.get(motivoAlteracaoList.size() - 1);
        assertThat(testMotivoAlteracao.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createMotivoAlteracaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = motivoAlteracaoRepository.findAll().size();

        // Create the MotivoAlteracao with an existing ID
        motivoAlteracao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMotivoAlteracaoMockMvc.perform(post("/api/motivo-alteracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motivoAlteracao)))
            .andExpect(status().isBadRequest());

        // Validate the MotivoAlteracao in the database
        List<MotivoAlteracao> motivoAlteracaoList = motivoAlteracaoRepository.findAll();
        assertThat(motivoAlteracaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMotivoAlteracaos() throws Exception {
        // Initialize the database
        motivoAlteracaoRepository.saveAndFlush(motivoAlteracao);

        // Get all the motivoAlteracaoList
        restMotivoAlteracaoMockMvc.perform(get("/api/motivo-alteracaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(motivoAlteracao.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }

    @Test
    @Transactional
    public void getMotivoAlteracao() throws Exception {
        // Initialize the database
        motivoAlteracaoRepository.saveAndFlush(motivoAlteracao);

        // Get the motivoAlteracao
        restMotivoAlteracaoMockMvc.perform(get("/api/motivo-alteracaos/{id}", motivoAlteracao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(motivoAlteracao.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMotivoAlteracao() throws Exception {
        // Get the motivoAlteracao
        restMotivoAlteracaoMockMvc.perform(get("/api/motivo-alteracaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMotivoAlteracao() throws Exception {
        // Initialize the database
        motivoAlteracaoRepository.saveAndFlush(motivoAlteracao);
        int databaseSizeBeforeUpdate = motivoAlteracaoRepository.findAll().size();

        // Update the motivoAlteracao
        MotivoAlteracao updatedMotivoAlteracao = motivoAlteracaoRepository.findOne(motivoAlteracao.getId());
        // Disconnect from session so that the updates on updatedMotivoAlteracao are not directly saved in db
        em.detach(updatedMotivoAlteracao);
        updatedMotivoAlteracao
            .descricao(UPDATED_DESCRICAO);

        restMotivoAlteracaoMockMvc.perform(put("/api/motivo-alteracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMotivoAlteracao)))
            .andExpect(status().isOk());

        // Validate the MotivoAlteracao in the database
        List<MotivoAlteracao> motivoAlteracaoList = motivoAlteracaoRepository.findAll();
        assertThat(motivoAlteracaoList).hasSize(databaseSizeBeforeUpdate);
        MotivoAlteracao testMotivoAlteracao = motivoAlteracaoList.get(motivoAlteracaoList.size() - 1);
        assertThat(testMotivoAlteracao.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingMotivoAlteracao() throws Exception {
        int databaseSizeBeforeUpdate = motivoAlteracaoRepository.findAll().size();

        // Create the MotivoAlteracao

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMotivoAlteracaoMockMvc.perform(put("/api/motivo-alteracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(motivoAlteracao)))
            .andExpect(status().isCreated());

        // Validate the MotivoAlteracao in the database
        List<MotivoAlteracao> motivoAlteracaoList = motivoAlteracaoRepository.findAll();
        assertThat(motivoAlteracaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMotivoAlteracao() throws Exception {
        // Initialize the database
        motivoAlteracaoRepository.saveAndFlush(motivoAlteracao);
        int databaseSizeBeforeDelete = motivoAlteracaoRepository.findAll().size();

        // Get the motivoAlteracao
        restMotivoAlteracaoMockMvc.perform(delete("/api/motivo-alteracaos/{id}", motivoAlteracao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MotivoAlteracao> motivoAlteracaoList = motivoAlteracaoRepository.findAll();
        assertThat(motivoAlteracaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MotivoAlteracao.class);
        MotivoAlteracao motivoAlteracao1 = new MotivoAlteracao();
        motivoAlteracao1.setId(1L);
        MotivoAlteracao motivoAlteracao2 = new MotivoAlteracao();
        motivoAlteracao2.setId(motivoAlteracao1.getId());
        assertThat(motivoAlteracao1).isEqualTo(motivoAlteracao2);
        motivoAlteracao2.setId(2L);
        assertThat(motivoAlteracao1).isNotEqualTo(motivoAlteracao2);
        motivoAlteracao1.setId(null);
        assertThat(motivoAlteracao1).isNotEqualTo(motivoAlteracao2);
    }
}
