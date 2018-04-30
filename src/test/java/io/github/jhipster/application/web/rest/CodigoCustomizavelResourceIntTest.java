package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.CodigoCustomizavel;
import io.github.jhipster.application.repository.CodigoCustomizavelRepository;
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
 * Test class for the CodigoCustomizavelResource REST controller.
 *
 * @see CodigoCustomizavelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class CodigoCustomizavelResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final String DEFAULT_CAMINHO_JAR = "AAAAAAAAAA";
    private static final String UPDATED_CAMINHO_JAR = "BBBBBBBBBB";

    @Autowired
    private CodigoCustomizavelRepository codigoCustomizavelRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCodigoCustomizavelMockMvc;

    private CodigoCustomizavel codigoCustomizavel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CodigoCustomizavelResource codigoCustomizavelResource = new CodigoCustomizavelResource(codigoCustomizavelRepository);
        this.restCodigoCustomizavelMockMvc = MockMvcBuilders.standaloneSetup(codigoCustomizavelResource)
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
    public static CodigoCustomizavel createEntity(EntityManager em) {
        CodigoCustomizavel codigoCustomizavel = new CodigoCustomizavel()
            .descricao(DEFAULT_DESCRICAO)
            .codigo(DEFAULT_CODIGO)
            .caminhoJar(DEFAULT_CAMINHO_JAR);
        return codigoCustomizavel;
    }

    @Before
    public void initTest() {
        codigoCustomizavel = createEntity(em);
    }

    @Test
    @Transactional
    public void createCodigoCustomizavel() throws Exception {
        int databaseSizeBeforeCreate = codigoCustomizavelRepository.findAll().size();

        // Create the CodigoCustomizavel
        restCodigoCustomizavelMockMvc.perform(post("/api/codigo-customizavels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codigoCustomizavel)))
            .andExpect(status().isCreated());

        // Validate the CodigoCustomizavel in the database
        List<CodigoCustomizavel> codigoCustomizavelList = codigoCustomizavelRepository.findAll();
        assertThat(codigoCustomizavelList).hasSize(databaseSizeBeforeCreate + 1);
        CodigoCustomizavel testCodigoCustomizavel = codigoCustomizavelList.get(codigoCustomizavelList.size() - 1);
        assertThat(testCodigoCustomizavel.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testCodigoCustomizavel.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testCodigoCustomizavel.getCaminhoJar()).isEqualTo(DEFAULT_CAMINHO_JAR);
    }

    @Test
    @Transactional
    public void createCodigoCustomizavelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = codigoCustomizavelRepository.findAll().size();

        // Create the CodigoCustomizavel with an existing ID
        codigoCustomizavel.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCodigoCustomizavelMockMvc.perform(post("/api/codigo-customizavels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codigoCustomizavel)))
            .andExpect(status().isBadRequest());

        // Validate the CodigoCustomizavel in the database
        List<CodigoCustomizavel> codigoCustomizavelList = codigoCustomizavelRepository.findAll();
        assertThat(codigoCustomizavelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCodigoCustomizavels() throws Exception {
        // Initialize the database
        codigoCustomizavelRepository.saveAndFlush(codigoCustomizavel);

        // Get all the codigoCustomizavelList
        restCodigoCustomizavelMockMvc.perform(get("/api/codigo-customizavels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(codigoCustomizavel.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO.toString())))
            .andExpect(jsonPath("$.[*].caminhoJar").value(hasItem(DEFAULT_CAMINHO_JAR.toString())));
    }

    @Test
    @Transactional
    public void getCodigoCustomizavel() throws Exception {
        // Initialize the database
        codigoCustomizavelRepository.saveAndFlush(codigoCustomizavel);

        // Get the codigoCustomizavel
        restCodigoCustomizavelMockMvc.perform(get("/api/codigo-customizavels/{id}", codigoCustomizavel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(codigoCustomizavel.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO.toString()))
            .andExpect(jsonPath("$.caminhoJar").value(DEFAULT_CAMINHO_JAR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCodigoCustomizavel() throws Exception {
        // Get the codigoCustomizavel
        restCodigoCustomizavelMockMvc.perform(get("/api/codigo-customizavels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCodigoCustomizavel() throws Exception {
        // Initialize the database
        codigoCustomizavelRepository.saveAndFlush(codigoCustomizavel);
        int databaseSizeBeforeUpdate = codigoCustomizavelRepository.findAll().size();

        // Update the codigoCustomizavel
        CodigoCustomizavel updatedCodigoCustomizavel = codigoCustomizavelRepository.findOne(codigoCustomizavel.getId());
        // Disconnect from session so that the updates on updatedCodigoCustomizavel are not directly saved in db
        em.detach(updatedCodigoCustomizavel);
        updatedCodigoCustomizavel
            .descricao(UPDATED_DESCRICAO)
            .codigo(UPDATED_CODIGO)
            .caminhoJar(UPDATED_CAMINHO_JAR);

        restCodigoCustomizavelMockMvc.perform(put("/api/codigo-customizavels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCodigoCustomizavel)))
            .andExpect(status().isOk());

        // Validate the CodigoCustomizavel in the database
        List<CodigoCustomizavel> codigoCustomizavelList = codigoCustomizavelRepository.findAll();
        assertThat(codigoCustomizavelList).hasSize(databaseSizeBeforeUpdate);
        CodigoCustomizavel testCodigoCustomizavel = codigoCustomizavelList.get(codigoCustomizavelList.size() - 1);
        assertThat(testCodigoCustomizavel.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testCodigoCustomizavel.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testCodigoCustomizavel.getCaminhoJar()).isEqualTo(UPDATED_CAMINHO_JAR);
    }

    @Test
    @Transactional
    public void updateNonExistingCodigoCustomizavel() throws Exception {
        int databaseSizeBeforeUpdate = codigoCustomizavelRepository.findAll().size();

        // Create the CodigoCustomizavel

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCodigoCustomizavelMockMvc.perform(put("/api/codigo-customizavels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(codigoCustomizavel)))
            .andExpect(status().isCreated());

        // Validate the CodigoCustomizavel in the database
        List<CodigoCustomizavel> codigoCustomizavelList = codigoCustomizavelRepository.findAll();
        assertThat(codigoCustomizavelList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCodigoCustomizavel() throws Exception {
        // Initialize the database
        codigoCustomizavelRepository.saveAndFlush(codigoCustomizavel);
        int databaseSizeBeforeDelete = codigoCustomizavelRepository.findAll().size();

        // Get the codigoCustomizavel
        restCodigoCustomizavelMockMvc.perform(delete("/api/codigo-customizavels/{id}", codigoCustomizavel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CodigoCustomizavel> codigoCustomizavelList = codigoCustomizavelRepository.findAll();
        assertThat(codigoCustomizavelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CodigoCustomizavel.class);
        CodigoCustomizavel codigoCustomizavel1 = new CodigoCustomizavel();
        codigoCustomizavel1.setId(1L);
        CodigoCustomizavel codigoCustomizavel2 = new CodigoCustomizavel();
        codigoCustomizavel2.setId(codigoCustomizavel1.getId());
        assertThat(codigoCustomizavel1).isEqualTo(codigoCustomizavel2);
        codigoCustomizavel2.setId(2L);
        assertThat(codigoCustomizavel1).isNotEqualTo(codigoCustomizavel2);
        codigoCustomizavel1.setId(null);
        assertThat(codigoCustomizavel1).isNotEqualTo(codigoCustomizavel2);
    }
}
