package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Coletor;
import io.github.jhipster.application.repository.ColetorRepository;
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
 * Test class for the ColetorResource REST controller.
 *
 * @see ColetorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ColetorResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private ColetorRepository coletorRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restColetorMockMvc;

    private Coletor coletor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ColetorResource coletorResource = new ColetorResource(coletorRepository);
        this.restColetorMockMvc = MockMvcBuilders.standaloneSetup(coletorResource)
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
    public static Coletor createEntity(EntityManager em) {
        Coletor coletor = new Coletor()
            .descricao(DEFAULT_DESCRICAO);
        return coletor;
    }

    @Before
    public void initTest() {
        coletor = createEntity(em);
    }

    @Test
    @Transactional
    public void createColetor() throws Exception {
        int databaseSizeBeforeCreate = coletorRepository.findAll().size();

        // Create the Coletor
        restColetorMockMvc.perform(post("/api/coletors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coletor)))
            .andExpect(status().isCreated());

        // Validate the Coletor in the database
        List<Coletor> coletorList = coletorRepository.findAll();
        assertThat(coletorList).hasSize(databaseSizeBeforeCreate + 1);
        Coletor testColetor = coletorList.get(coletorList.size() - 1);
        assertThat(testColetor.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createColetorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = coletorRepository.findAll().size();

        // Create the Coletor with an existing ID
        coletor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restColetorMockMvc.perform(post("/api/coletors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coletor)))
            .andExpect(status().isBadRequest());

        // Validate the Coletor in the database
        List<Coletor> coletorList = coletorRepository.findAll();
        assertThat(coletorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllColetors() throws Exception {
        // Initialize the database
        coletorRepository.saveAndFlush(coletor);

        // Get all the coletorList
        restColetorMockMvc.perform(get("/api/coletors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coletor.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }

    @Test
    @Transactional
    public void getColetor() throws Exception {
        // Initialize the database
        coletorRepository.saveAndFlush(coletor);

        // Get the coletor
        restColetorMockMvc.perform(get("/api/coletors/{id}", coletor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coletor.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingColetor() throws Exception {
        // Get the coletor
        restColetorMockMvc.perform(get("/api/coletors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateColetor() throws Exception {
        // Initialize the database
        coletorRepository.saveAndFlush(coletor);
        int databaseSizeBeforeUpdate = coletorRepository.findAll().size();

        // Update the coletor
        Coletor updatedColetor = coletorRepository.findOne(coletor.getId());
        // Disconnect from session so that the updates on updatedColetor are not directly saved in db
        em.detach(updatedColetor);
        updatedColetor
            .descricao(UPDATED_DESCRICAO);

        restColetorMockMvc.perform(put("/api/coletors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedColetor)))
            .andExpect(status().isOk());

        // Validate the Coletor in the database
        List<Coletor> coletorList = coletorRepository.findAll();
        assertThat(coletorList).hasSize(databaseSizeBeforeUpdate);
        Coletor testColetor = coletorList.get(coletorList.size() - 1);
        assertThat(testColetor.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingColetor() throws Exception {
        int databaseSizeBeforeUpdate = coletorRepository.findAll().size();

        // Create the Coletor

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restColetorMockMvc.perform(put("/api/coletors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coletor)))
            .andExpect(status().isCreated());

        // Validate the Coletor in the database
        List<Coletor> coletorList = coletorRepository.findAll();
        assertThat(coletorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteColetor() throws Exception {
        // Initialize the database
        coletorRepository.saveAndFlush(coletor);
        int databaseSizeBeforeDelete = coletorRepository.findAll().size();

        // Get the coletor
        restColetorMockMvc.perform(delete("/api/coletors/{id}", coletor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Coletor> coletorList = coletorRepository.findAll();
        assertThat(coletorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Coletor.class);
        Coletor coletor1 = new Coletor();
        coletor1.setId(1L);
        Coletor coletor2 = new Coletor();
        coletor2.setId(coletor1.getId());
        assertThat(coletor1).isEqualTo(coletor2);
        coletor2.setId(2L);
        assertThat(coletor1).isNotEqualTo(coletor2);
        coletor1.setId(null);
        assertThat(coletor1).isNotEqualTo(coletor2);
    }
}
