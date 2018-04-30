package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.TipoContagem;
import io.github.jhipster.application.repository.TipoContagemRepository;
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
 * Test class for the TipoContagemResource REST controller.
 *
 * @see TipoContagemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class TipoContagemResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private TipoContagemRepository tipoContagemRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoContagemMockMvc;

    private TipoContagem tipoContagem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoContagemResource tipoContagemResource = new TipoContagemResource(tipoContagemRepository);
        this.restTipoContagemMockMvc = MockMvcBuilders.standaloneSetup(tipoContagemResource)
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
    public static TipoContagem createEntity(EntityManager em) {
        TipoContagem tipoContagem = new TipoContagem()
            .descricao(DEFAULT_DESCRICAO);
        return tipoContagem;
    }

    @Before
    public void initTest() {
        tipoContagem = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoContagem() throws Exception {
        int databaseSizeBeforeCreate = tipoContagemRepository.findAll().size();

        // Create the TipoContagem
        restTipoContagemMockMvc.perform(post("/api/tipo-contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoContagem)))
            .andExpect(status().isCreated());

        // Validate the TipoContagem in the database
        List<TipoContagem> tipoContagemList = tipoContagemRepository.findAll();
        assertThat(tipoContagemList).hasSize(databaseSizeBeforeCreate + 1);
        TipoContagem testTipoContagem = tipoContagemList.get(tipoContagemList.size() - 1);
        assertThat(testTipoContagem.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createTipoContagemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoContagemRepository.findAll().size();

        // Create the TipoContagem with an existing ID
        tipoContagem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoContagemMockMvc.perform(post("/api/tipo-contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoContagem)))
            .andExpect(status().isBadRequest());

        // Validate the TipoContagem in the database
        List<TipoContagem> tipoContagemList = tipoContagemRepository.findAll();
        assertThat(tipoContagemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipoContagems() throws Exception {
        // Initialize the database
        tipoContagemRepository.saveAndFlush(tipoContagem);

        // Get all the tipoContagemList
        restTipoContagemMockMvc.perform(get("/api/tipo-contagems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoContagem.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }

    @Test
    @Transactional
    public void getTipoContagem() throws Exception {
        // Initialize the database
        tipoContagemRepository.saveAndFlush(tipoContagem);

        // Get the tipoContagem
        restTipoContagemMockMvc.perform(get("/api/tipo-contagems/{id}", tipoContagem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoContagem.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoContagem() throws Exception {
        // Get the tipoContagem
        restTipoContagemMockMvc.perform(get("/api/tipo-contagems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoContagem() throws Exception {
        // Initialize the database
        tipoContagemRepository.saveAndFlush(tipoContagem);
        int databaseSizeBeforeUpdate = tipoContagemRepository.findAll().size();

        // Update the tipoContagem
        TipoContagem updatedTipoContagem = tipoContagemRepository.findOne(tipoContagem.getId());
        // Disconnect from session so that the updates on updatedTipoContagem are not directly saved in db
        em.detach(updatedTipoContagem);
        updatedTipoContagem
            .descricao(UPDATED_DESCRICAO);

        restTipoContagemMockMvc.perform(put("/api/tipo-contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoContagem)))
            .andExpect(status().isOk());

        // Validate the TipoContagem in the database
        List<TipoContagem> tipoContagemList = tipoContagemRepository.findAll();
        assertThat(tipoContagemList).hasSize(databaseSizeBeforeUpdate);
        TipoContagem testTipoContagem = tipoContagemList.get(tipoContagemList.size() - 1);
        assertThat(testTipoContagem.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoContagem() throws Exception {
        int databaseSizeBeforeUpdate = tipoContagemRepository.findAll().size();

        // Create the TipoContagem

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTipoContagemMockMvc.perform(put("/api/tipo-contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoContagem)))
            .andExpect(status().isCreated());

        // Validate the TipoContagem in the database
        List<TipoContagem> tipoContagemList = tipoContagemRepository.findAll();
        assertThat(tipoContagemList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTipoContagem() throws Exception {
        // Initialize the database
        tipoContagemRepository.saveAndFlush(tipoContagem);
        int databaseSizeBeforeDelete = tipoContagemRepository.findAll().size();

        // Get the tipoContagem
        restTipoContagemMockMvc.perform(delete("/api/tipo-contagems/{id}", tipoContagem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoContagem> tipoContagemList = tipoContagemRepository.findAll();
        assertThat(tipoContagemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoContagem.class);
        TipoContagem tipoContagem1 = new TipoContagem();
        tipoContagem1.setId(1L);
        TipoContagem tipoContagem2 = new TipoContagem();
        tipoContagem2.setId(tipoContagem1.getId());
        assertThat(tipoContagem1).isEqualTo(tipoContagem2);
        tipoContagem2.setId(2L);
        assertThat(tipoContagem1).isNotEqualTo(tipoContagem2);
        tipoContagem1.setId(null);
        assertThat(tipoContagem1).isNotEqualTo(tipoContagem2);
    }
}
