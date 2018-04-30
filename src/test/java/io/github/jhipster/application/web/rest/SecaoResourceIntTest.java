package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Secao;
import io.github.jhipster.application.repository.SecaoRepository;
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
 * Test class for the SecaoResource REST controller.
 *
 * @see SecaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class SecaoResourceIntTest {

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    @Autowired
    private SecaoRepository secaoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSecaoMockMvc;

    private Secao secao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SecaoResource secaoResource = new SecaoResource(secaoRepository);
        this.restSecaoMockMvc = MockMvcBuilders.standaloneSetup(secaoResource)
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
    public static Secao createEntity(EntityManager em) {
        Secao secao = new Secao()
            .codigo(DEFAULT_CODIGO);
        return secao;
    }

    @Before
    public void initTest() {
        secao = createEntity(em);
    }

    @Test
    @Transactional
    public void createSecao() throws Exception {
        int databaseSizeBeforeCreate = secaoRepository.findAll().size();

        // Create the Secao
        restSecaoMockMvc.perform(post("/api/secaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(secao)))
            .andExpect(status().isCreated());

        // Validate the Secao in the database
        List<Secao> secaoList = secaoRepository.findAll();
        assertThat(secaoList).hasSize(databaseSizeBeforeCreate + 1);
        Secao testSecao = secaoList.get(secaoList.size() - 1);
        assertThat(testSecao.getCodigo()).isEqualTo(DEFAULT_CODIGO);
    }

    @Test
    @Transactional
    public void createSecaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = secaoRepository.findAll().size();

        // Create the Secao with an existing ID
        secao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSecaoMockMvc.perform(post("/api/secaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(secao)))
            .andExpect(status().isBadRequest());

        // Validate the Secao in the database
        List<Secao> secaoList = secaoRepository.findAll();
        assertThat(secaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSecaos() throws Exception {
        // Initialize the database
        secaoRepository.saveAndFlush(secao);

        // Get all the secaoList
        restSecaoMockMvc.perform(get("/api/secaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(secao.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO.toString())));
    }

    @Test
    @Transactional
    public void getSecao() throws Exception {
        // Initialize the database
        secaoRepository.saveAndFlush(secao);

        // Get the secao
        restSecaoMockMvc.perform(get("/api/secaos/{id}", secao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(secao.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSecao() throws Exception {
        // Get the secao
        restSecaoMockMvc.perform(get("/api/secaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSecao() throws Exception {
        // Initialize the database
        secaoRepository.saveAndFlush(secao);
        int databaseSizeBeforeUpdate = secaoRepository.findAll().size();

        // Update the secao
        Secao updatedSecao = secaoRepository.findOne(secao.getId());
        // Disconnect from session so that the updates on updatedSecao are not directly saved in db
        em.detach(updatedSecao);
        updatedSecao
            .codigo(UPDATED_CODIGO);

        restSecaoMockMvc.perform(put("/api/secaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSecao)))
            .andExpect(status().isOk());

        // Validate the Secao in the database
        List<Secao> secaoList = secaoRepository.findAll();
        assertThat(secaoList).hasSize(databaseSizeBeforeUpdate);
        Secao testSecao = secaoList.get(secaoList.size() - 1);
        assertThat(testSecao.getCodigo()).isEqualTo(UPDATED_CODIGO);
    }

    @Test
    @Transactional
    public void updateNonExistingSecao() throws Exception {
        int databaseSizeBeforeUpdate = secaoRepository.findAll().size();

        // Create the Secao

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSecaoMockMvc.perform(put("/api/secaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(secao)))
            .andExpect(status().isCreated());

        // Validate the Secao in the database
        List<Secao> secaoList = secaoRepository.findAll();
        assertThat(secaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSecao() throws Exception {
        // Initialize the database
        secaoRepository.saveAndFlush(secao);
        int databaseSizeBeforeDelete = secaoRepository.findAll().size();

        // Get the secao
        restSecaoMockMvc.perform(delete("/api/secaos/{id}", secao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Secao> secaoList = secaoRepository.findAll();
        assertThat(secaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Secao.class);
        Secao secao1 = new Secao();
        secao1.setId(1L);
        Secao secao2 = new Secao();
        secao2.setId(secao1.getId());
        assertThat(secao1).isEqualTo(secao2);
        secao2.setId(2L);
        assertThat(secao1).isNotEqualTo(secao2);
        secao1.setId(null);
        assertThat(secao1).isNotEqualTo(secao2);
    }
}
