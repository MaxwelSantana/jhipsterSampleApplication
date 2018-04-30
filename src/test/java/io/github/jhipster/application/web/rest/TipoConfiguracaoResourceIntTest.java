package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.TipoConfiguracao;
import io.github.jhipster.application.repository.TipoConfiguracaoRepository;
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
 * Test class for the TipoConfiguracaoResource REST controller.
 *
 * @see TipoConfiguracaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class TipoConfiguracaoResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private TipoConfiguracaoRepository tipoConfiguracaoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoConfiguracaoMockMvc;

    private TipoConfiguracao tipoConfiguracao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoConfiguracaoResource tipoConfiguracaoResource = new TipoConfiguracaoResource(tipoConfiguracaoRepository);
        this.restTipoConfiguracaoMockMvc = MockMvcBuilders.standaloneSetup(tipoConfiguracaoResource)
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
    public static TipoConfiguracao createEntity(EntityManager em) {
        TipoConfiguracao tipoConfiguracao = new TipoConfiguracao()
            .descricao(DEFAULT_DESCRICAO);
        return tipoConfiguracao;
    }

    @Before
    public void initTest() {
        tipoConfiguracao = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoConfiguracao() throws Exception {
        int databaseSizeBeforeCreate = tipoConfiguracaoRepository.findAll().size();

        // Create the TipoConfiguracao
        restTipoConfiguracaoMockMvc.perform(post("/api/tipo-configuracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoConfiguracao)))
            .andExpect(status().isCreated());

        // Validate the TipoConfiguracao in the database
        List<TipoConfiguracao> tipoConfiguracaoList = tipoConfiguracaoRepository.findAll();
        assertThat(tipoConfiguracaoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoConfiguracao testTipoConfiguracao = tipoConfiguracaoList.get(tipoConfiguracaoList.size() - 1);
        assertThat(testTipoConfiguracao.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createTipoConfiguracaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoConfiguracaoRepository.findAll().size();

        // Create the TipoConfiguracao with an existing ID
        tipoConfiguracao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoConfiguracaoMockMvc.perform(post("/api/tipo-configuracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoConfiguracao)))
            .andExpect(status().isBadRequest());

        // Validate the TipoConfiguracao in the database
        List<TipoConfiguracao> tipoConfiguracaoList = tipoConfiguracaoRepository.findAll();
        assertThat(tipoConfiguracaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipoConfiguracaos() throws Exception {
        // Initialize the database
        tipoConfiguracaoRepository.saveAndFlush(tipoConfiguracao);

        // Get all the tipoConfiguracaoList
        restTipoConfiguracaoMockMvc.perform(get("/api/tipo-configuracaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoConfiguracao.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }

    @Test
    @Transactional
    public void getTipoConfiguracao() throws Exception {
        // Initialize the database
        tipoConfiguracaoRepository.saveAndFlush(tipoConfiguracao);

        // Get the tipoConfiguracao
        restTipoConfiguracaoMockMvc.perform(get("/api/tipo-configuracaos/{id}", tipoConfiguracao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoConfiguracao.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoConfiguracao() throws Exception {
        // Get the tipoConfiguracao
        restTipoConfiguracaoMockMvc.perform(get("/api/tipo-configuracaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoConfiguracao() throws Exception {
        // Initialize the database
        tipoConfiguracaoRepository.saveAndFlush(tipoConfiguracao);
        int databaseSizeBeforeUpdate = tipoConfiguracaoRepository.findAll().size();

        // Update the tipoConfiguracao
        TipoConfiguracao updatedTipoConfiguracao = tipoConfiguracaoRepository.findOne(tipoConfiguracao.getId());
        // Disconnect from session so that the updates on updatedTipoConfiguracao are not directly saved in db
        em.detach(updatedTipoConfiguracao);
        updatedTipoConfiguracao
            .descricao(UPDATED_DESCRICAO);

        restTipoConfiguracaoMockMvc.perform(put("/api/tipo-configuracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoConfiguracao)))
            .andExpect(status().isOk());

        // Validate the TipoConfiguracao in the database
        List<TipoConfiguracao> tipoConfiguracaoList = tipoConfiguracaoRepository.findAll();
        assertThat(tipoConfiguracaoList).hasSize(databaseSizeBeforeUpdate);
        TipoConfiguracao testTipoConfiguracao = tipoConfiguracaoList.get(tipoConfiguracaoList.size() - 1);
        assertThat(testTipoConfiguracao.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoConfiguracao() throws Exception {
        int databaseSizeBeforeUpdate = tipoConfiguracaoRepository.findAll().size();

        // Create the TipoConfiguracao

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTipoConfiguracaoMockMvc.perform(put("/api/tipo-configuracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoConfiguracao)))
            .andExpect(status().isCreated());

        // Validate the TipoConfiguracao in the database
        List<TipoConfiguracao> tipoConfiguracaoList = tipoConfiguracaoRepository.findAll();
        assertThat(tipoConfiguracaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTipoConfiguracao() throws Exception {
        // Initialize the database
        tipoConfiguracaoRepository.saveAndFlush(tipoConfiguracao);
        int databaseSizeBeforeDelete = tipoConfiguracaoRepository.findAll().size();

        // Get the tipoConfiguracao
        restTipoConfiguracaoMockMvc.perform(delete("/api/tipo-configuracaos/{id}", tipoConfiguracao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoConfiguracao> tipoConfiguracaoList = tipoConfiguracaoRepository.findAll();
        assertThat(tipoConfiguracaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoConfiguracao.class);
        TipoConfiguracao tipoConfiguracao1 = new TipoConfiguracao();
        tipoConfiguracao1.setId(1L);
        TipoConfiguracao tipoConfiguracao2 = new TipoConfiguracao();
        tipoConfiguracao2.setId(tipoConfiguracao1.getId());
        assertThat(tipoConfiguracao1).isEqualTo(tipoConfiguracao2);
        tipoConfiguracao2.setId(2L);
        assertThat(tipoConfiguracao1).isNotEqualTo(tipoConfiguracao2);
        tipoConfiguracao1.setId(null);
        assertThat(tipoConfiguracao1).isNotEqualTo(tipoConfiguracao2);
    }
}
