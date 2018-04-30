package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.CampoCustomizavelCadastro;
import io.github.jhipster.application.repository.CampoCustomizavelCadastroRepository;
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
import java.math.BigDecimal;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CampoCustomizavelCadastroResource REST controller.
 *
 * @see CampoCustomizavelCadastroResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class CampoCustomizavelCadastroResourceIntTest {

    private static final String DEFAULT_STRING_CUSTOMIZADO_1 = "AAAAAAAAAA";
    private static final String UPDATED_STRING_CUSTOMIZADO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_CUSTOMIZADO_2 = "AAAAAAAAAA";
    private static final String UPDATED_STRING_CUSTOMIZADO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_CUSTOMIZADO_3 = "AAAAAAAAAA";
    private static final String UPDATED_STRING_CUSTOMIZADO_3 = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_CUSTOMIZADO_4 = "AAAAAAAAAA";
    private static final String UPDATED_STRING_CUSTOMIZADO_4 = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_CUSTOMIZADO_5 = "AAAAAAAAAA";
    private static final String UPDATED_STRING_CUSTOMIZADO_5 = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_CUSTOMIZADO_6 = "AAAAAAAAAA";
    private static final String UPDATED_STRING_CUSTOMIZADO_6 = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_CUSTOMIZADO_7 = "AAAAAAAAAA";
    private static final String UPDATED_STRING_CUSTOMIZADO_7 = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_CUSTOMIZADO_8 = "AAAAAAAAAA";
    private static final String UPDATED_STRING_CUSTOMIZADO_8 = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_CUSTOMIZADO_9 = "AAAAAAAAAA";
    private static final String UPDATED_STRING_CUSTOMIZADO_9 = "BBBBBBBBBB";

    private static final String DEFAULT_STRING_CUSTOMIZADO_10 = "AAAAAAAAAA";
    private static final String UPDATED_STRING_CUSTOMIZADO_10 = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_INT_CUSTOMIZADO_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_INT_CUSTOMIZADO_1 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INT_CUSTOMIZADO_2 = new BigDecimal(1);
    private static final BigDecimal UPDATED_INT_CUSTOMIZADO_2 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INT_CUSTOMIZADO_3 = new BigDecimal(1);
    private static final BigDecimal UPDATED_INT_CUSTOMIZADO_3 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INT_CUSTOMIZADO_4 = new BigDecimal(1);
    private static final BigDecimal UPDATED_INT_CUSTOMIZADO_4 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INT_CUSTOMIZADO_5 = new BigDecimal(1);
    private static final BigDecimal UPDATED_INT_CUSTOMIZADO_5 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INT_CUSTOMIZADO_6 = new BigDecimal(1);
    private static final BigDecimal UPDATED_INT_CUSTOMIZADO_6 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INT_CUSTOMIZADO_7 = new BigDecimal(1);
    private static final BigDecimal UPDATED_INT_CUSTOMIZADO_7 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INT_CUSTOMIZADO_8 = new BigDecimal(1);
    private static final BigDecimal UPDATED_INT_CUSTOMIZADO_8 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INT_CUSTOMIZADO_9 = new BigDecimal(1);
    private static final BigDecimal UPDATED_INT_CUSTOMIZADO_9 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INT_CUSTOMIZADO_10 = new BigDecimal(1);
    private static final BigDecimal UPDATED_INT_CUSTOMIZADO_10 = new BigDecimal(2);

    private static final Boolean DEFAULT_BIN_CUSTOMIZADO_1 = false;
    private static final Boolean UPDATED_BIN_CUSTOMIZADO_1 = true;

    private static final Boolean DEFAULT_BIN_CUSTOMIZADO_2 = false;
    private static final Boolean UPDATED_BIN_CUSTOMIZADO_2 = true;

    private static final Boolean DEFAULT_BIN_CUSTOMIZADO_3 = false;
    private static final Boolean UPDATED_BIN_CUSTOMIZADO_3 = true;

    @Autowired
    private CampoCustomizavelCadastroRepository campoCustomizavelCadastroRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCampoCustomizavelCadastroMockMvc;

    private CampoCustomizavelCadastro campoCustomizavelCadastro;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CampoCustomizavelCadastroResource campoCustomizavelCadastroResource = new CampoCustomizavelCadastroResource(campoCustomizavelCadastroRepository);
        this.restCampoCustomizavelCadastroMockMvc = MockMvcBuilders.standaloneSetup(campoCustomizavelCadastroResource)
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
    public static CampoCustomizavelCadastro createEntity(EntityManager em) {
        CampoCustomizavelCadastro campoCustomizavelCadastro = new CampoCustomizavelCadastro()
            .stringCustomizado1(DEFAULT_STRING_CUSTOMIZADO_1)
            .stringCustomizado2(DEFAULT_STRING_CUSTOMIZADO_2)
            .stringCustomizado3(DEFAULT_STRING_CUSTOMIZADO_3)
            .stringCustomizado4(DEFAULT_STRING_CUSTOMIZADO_4)
            .stringCustomizado5(DEFAULT_STRING_CUSTOMIZADO_5)
            .stringCustomizado6(DEFAULT_STRING_CUSTOMIZADO_6)
            .stringCustomizado7(DEFAULT_STRING_CUSTOMIZADO_7)
            .stringCustomizado8(DEFAULT_STRING_CUSTOMIZADO_8)
            .stringCustomizado9(DEFAULT_STRING_CUSTOMIZADO_9)
            .stringCustomizado10(DEFAULT_STRING_CUSTOMIZADO_10)
            .intCustomizado1(DEFAULT_INT_CUSTOMIZADO_1)
            .intCustomizado2(DEFAULT_INT_CUSTOMIZADO_2)
            .intCustomizado3(DEFAULT_INT_CUSTOMIZADO_3)
            .intCustomizado4(DEFAULT_INT_CUSTOMIZADO_4)
            .intCustomizado5(DEFAULT_INT_CUSTOMIZADO_5)
            .intCustomizado6(DEFAULT_INT_CUSTOMIZADO_6)
            .intCustomizado7(DEFAULT_INT_CUSTOMIZADO_7)
            .intCustomizado8(DEFAULT_INT_CUSTOMIZADO_8)
            .intCustomizado9(DEFAULT_INT_CUSTOMIZADO_9)
            .intCustomizado10(DEFAULT_INT_CUSTOMIZADO_10)
            .binCustomizado1(DEFAULT_BIN_CUSTOMIZADO_1)
            .binCustomizado2(DEFAULT_BIN_CUSTOMIZADO_2)
            .binCustomizado3(DEFAULT_BIN_CUSTOMIZADO_3);
        return campoCustomizavelCadastro;
    }

    @Before
    public void initTest() {
        campoCustomizavelCadastro = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampoCustomizavelCadastro() throws Exception {
        int databaseSizeBeforeCreate = campoCustomizavelCadastroRepository.findAll().size();

        // Create the CampoCustomizavelCadastro
        restCampoCustomizavelCadastroMockMvc.perform(post("/api/campo-customizavel-cadastros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campoCustomizavelCadastro)))
            .andExpect(status().isCreated());

        // Validate the CampoCustomizavelCadastro in the database
        List<CampoCustomizavelCadastro> campoCustomizavelCadastroList = campoCustomizavelCadastroRepository.findAll();
        assertThat(campoCustomizavelCadastroList).hasSize(databaseSizeBeforeCreate + 1);
        CampoCustomizavelCadastro testCampoCustomizavelCadastro = campoCustomizavelCadastroList.get(campoCustomizavelCadastroList.size() - 1);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado1()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_1);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado2()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_2);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado3()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_3);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado4()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_4);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado5()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_5);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado6()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_6);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado7()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_7);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado8()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_8);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado9()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_9);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado10()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_10);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado1()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_1);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado2()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_2);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado3()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_3);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado4()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_4);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado5()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_5);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado6()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_6);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado7()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_7);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado8()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_8);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado9()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_9);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado10()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_10);
        assertThat(testCampoCustomizavelCadastro.isBinCustomizado1()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_1);
        assertThat(testCampoCustomizavelCadastro.isBinCustomizado2()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_2);
        assertThat(testCampoCustomizavelCadastro.isBinCustomizado3()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_3);
    }

    @Test
    @Transactional
    public void createCampoCustomizavelCadastroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campoCustomizavelCadastroRepository.findAll().size();

        // Create the CampoCustomizavelCadastro with an existing ID
        campoCustomizavelCadastro.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampoCustomizavelCadastroMockMvc.perform(post("/api/campo-customizavel-cadastros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campoCustomizavelCadastro)))
            .andExpect(status().isBadRequest());

        // Validate the CampoCustomizavelCadastro in the database
        List<CampoCustomizavelCadastro> campoCustomizavelCadastroList = campoCustomizavelCadastroRepository.findAll();
        assertThat(campoCustomizavelCadastroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCampoCustomizavelCadastros() throws Exception {
        // Initialize the database
        campoCustomizavelCadastroRepository.saveAndFlush(campoCustomizavelCadastro);

        // Get all the campoCustomizavelCadastroList
        restCampoCustomizavelCadastroMockMvc.perform(get("/api/campo-customizavel-cadastros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campoCustomizavelCadastro.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringCustomizado1").value(hasItem(DEFAULT_STRING_CUSTOMIZADO_1.toString())))
            .andExpect(jsonPath("$.[*].stringCustomizado2").value(hasItem(DEFAULT_STRING_CUSTOMIZADO_2.toString())))
            .andExpect(jsonPath("$.[*].stringCustomizado3").value(hasItem(DEFAULT_STRING_CUSTOMIZADO_3.toString())))
            .andExpect(jsonPath("$.[*].stringCustomizado4").value(hasItem(DEFAULT_STRING_CUSTOMIZADO_4.toString())))
            .andExpect(jsonPath("$.[*].stringCustomizado5").value(hasItem(DEFAULT_STRING_CUSTOMIZADO_5.toString())))
            .andExpect(jsonPath("$.[*].stringCustomizado6").value(hasItem(DEFAULT_STRING_CUSTOMIZADO_6.toString())))
            .andExpect(jsonPath("$.[*].stringCustomizado7").value(hasItem(DEFAULT_STRING_CUSTOMIZADO_7.toString())))
            .andExpect(jsonPath("$.[*].stringCustomizado8").value(hasItem(DEFAULT_STRING_CUSTOMIZADO_8.toString())))
            .andExpect(jsonPath("$.[*].stringCustomizado9").value(hasItem(DEFAULT_STRING_CUSTOMIZADO_9.toString())))
            .andExpect(jsonPath("$.[*].stringCustomizado10").value(hasItem(DEFAULT_STRING_CUSTOMIZADO_10.toString())))
            .andExpect(jsonPath("$.[*].intCustomizado1").value(hasItem(DEFAULT_INT_CUSTOMIZADO_1.intValue())))
            .andExpect(jsonPath("$.[*].intCustomizado2").value(hasItem(DEFAULT_INT_CUSTOMIZADO_2.intValue())))
            .andExpect(jsonPath("$.[*].intCustomizado3").value(hasItem(DEFAULT_INT_CUSTOMIZADO_3.intValue())))
            .andExpect(jsonPath("$.[*].intCustomizado4").value(hasItem(DEFAULT_INT_CUSTOMIZADO_4.intValue())))
            .andExpect(jsonPath("$.[*].intCustomizado5").value(hasItem(DEFAULT_INT_CUSTOMIZADO_5.intValue())))
            .andExpect(jsonPath("$.[*].intCustomizado6").value(hasItem(DEFAULT_INT_CUSTOMIZADO_6.intValue())))
            .andExpect(jsonPath("$.[*].intCustomizado7").value(hasItem(DEFAULT_INT_CUSTOMIZADO_7.intValue())))
            .andExpect(jsonPath("$.[*].intCustomizado8").value(hasItem(DEFAULT_INT_CUSTOMIZADO_8.intValue())))
            .andExpect(jsonPath("$.[*].intCustomizado9").value(hasItem(DEFAULT_INT_CUSTOMIZADO_9.intValue())))
            .andExpect(jsonPath("$.[*].intCustomizado10").value(hasItem(DEFAULT_INT_CUSTOMIZADO_10.intValue())))
            .andExpect(jsonPath("$.[*].binCustomizado1").value(hasItem(DEFAULT_BIN_CUSTOMIZADO_1.booleanValue())))
            .andExpect(jsonPath("$.[*].binCustomizado2").value(hasItem(DEFAULT_BIN_CUSTOMIZADO_2.booleanValue())))
            .andExpect(jsonPath("$.[*].binCustomizado3").value(hasItem(DEFAULT_BIN_CUSTOMIZADO_3.booleanValue())));
    }

    @Test
    @Transactional
    public void getCampoCustomizavelCadastro() throws Exception {
        // Initialize the database
        campoCustomizavelCadastroRepository.saveAndFlush(campoCustomizavelCadastro);

        // Get the campoCustomizavelCadastro
        restCampoCustomizavelCadastroMockMvc.perform(get("/api/campo-customizavel-cadastros/{id}", campoCustomizavelCadastro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(campoCustomizavelCadastro.getId().intValue()))
            .andExpect(jsonPath("$.stringCustomizado1").value(DEFAULT_STRING_CUSTOMIZADO_1.toString()))
            .andExpect(jsonPath("$.stringCustomizado2").value(DEFAULT_STRING_CUSTOMIZADO_2.toString()))
            .andExpect(jsonPath("$.stringCustomizado3").value(DEFAULT_STRING_CUSTOMIZADO_3.toString()))
            .andExpect(jsonPath("$.stringCustomizado4").value(DEFAULT_STRING_CUSTOMIZADO_4.toString()))
            .andExpect(jsonPath("$.stringCustomizado5").value(DEFAULT_STRING_CUSTOMIZADO_5.toString()))
            .andExpect(jsonPath("$.stringCustomizado6").value(DEFAULT_STRING_CUSTOMIZADO_6.toString()))
            .andExpect(jsonPath("$.stringCustomizado7").value(DEFAULT_STRING_CUSTOMIZADO_7.toString()))
            .andExpect(jsonPath("$.stringCustomizado8").value(DEFAULT_STRING_CUSTOMIZADO_8.toString()))
            .andExpect(jsonPath("$.stringCustomizado9").value(DEFAULT_STRING_CUSTOMIZADO_9.toString()))
            .andExpect(jsonPath("$.stringCustomizado10").value(DEFAULT_STRING_CUSTOMIZADO_10.toString()))
            .andExpect(jsonPath("$.intCustomizado1").value(DEFAULT_INT_CUSTOMIZADO_1.intValue()))
            .andExpect(jsonPath("$.intCustomizado2").value(DEFAULT_INT_CUSTOMIZADO_2.intValue()))
            .andExpect(jsonPath("$.intCustomizado3").value(DEFAULT_INT_CUSTOMIZADO_3.intValue()))
            .andExpect(jsonPath("$.intCustomizado4").value(DEFAULT_INT_CUSTOMIZADO_4.intValue()))
            .andExpect(jsonPath("$.intCustomizado5").value(DEFAULT_INT_CUSTOMIZADO_5.intValue()))
            .andExpect(jsonPath("$.intCustomizado6").value(DEFAULT_INT_CUSTOMIZADO_6.intValue()))
            .andExpect(jsonPath("$.intCustomizado7").value(DEFAULT_INT_CUSTOMIZADO_7.intValue()))
            .andExpect(jsonPath("$.intCustomizado8").value(DEFAULT_INT_CUSTOMIZADO_8.intValue()))
            .andExpect(jsonPath("$.intCustomizado9").value(DEFAULT_INT_CUSTOMIZADO_9.intValue()))
            .andExpect(jsonPath("$.intCustomizado10").value(DEFAULT_INT_CUSTOMIZADO_10.intValue()))
            .andExpect(jsonPath("$.binCustomizado1").value(DEFAULT_BIN_CUSTOMIZADO_1.booleanValue()))
            .andExpect(jsonPath("$.binCustomizado2").value(DEFAULT_BIN_CUSTOMIZADO_2.booleanValue()))
            .andExpect(jsonPath("$.binCustomizado3").value(DEFAULT_BIN_CUSTOMIZADO_3.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCampoCustomizavelCadastro() throws Exception {
        // Get the campoCustomizavelCadastro
        restCampoCustomizavelCadastroMockMvc.perform(get("/api/campo-customizavel-cadastros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampoCustomizavelCadastro() throws Exception {
        // Initialize the database
        campoCustomizavelCadastroRepository.saveAndFlush(campoCustomizavelCadastro);
        int databaseSizeBeforeUpdate = campoCustomizavelCadastroRepository.findAll().size();

        // Update the campoCustomizavelCadastro
        CampoCustomizavelCadastro updatedCampoCustomizavelCadastro = campoCustomizavelCadastroRepository.findOne(campoCustomizavelCadastro.getId());
        // Disconnect from session so that the updates on updatedCampoCustomizavelCadastro are not directly saved in db
        em.detach(updatedCampoCustomizavelCadastro);
        updatedCampoCustomizavelCadastro
            .stringCustomizado1(UPDATED_STRING_CUSTOMIZADO_1)
            .stringCustomizado2(UPDATED_STRING_CUSTOMIZADO_2)
            .stringCustomizado3(UPDATED_STRING_CUSTOMIZADO_3)
            .stringCustomizado4(UPDATED_STRING_CUSTOMIZADO_4)
            .stringCustomizado5(UPDATED_STRING_CUSTOMIZADO_5)
            .stringCustomizado6(UPDATED_STRING_CUSTOMIZADO_6)
            .stringCustomizado7(UPDATED_STRING_CUSTOMIZADO_7)
            .stringCustomizado8(UPDATED_STRING_CUSTOMIZADO_8)
            .stringCustomizado9(UPDATED_STRING_CUSTOMIZADO_9)
            .stringCustomizado10(UPDATED_STRING_CUSTOMIZADO_10)
            .intCustomizado1(UPDATED_INT_CUSTOMIZADO_1)
            .intCustomizado2(UPDATED_INT_CUSTOMIZADO_2)
            .intCustomizado3(UPDATED_INT_CUSTOMIZADO_3)
            .intCustomizado4(UPDATED_INT_CUSTOMIZADO_4)
            .intCustomizado5(UPDATED_INT_CUSTOMIZADO_5)
            .intCustomizado6(UPDATED_INT_CUSTOMIZADO_6)
            .intCustomizado7(UPDATED_INT_CUSTOMIZADO_7)
            .intCustomizado8(UPDATED_INT_CUSTOMIZADO_8)
            .intCustomizado9(UPDATED_INT_CUSTOMIZADO_9)
            .intCustomizado10(UPDATED_INT_CUSTOMIZADO_10)
            .binCustomizado1(UPDATED_BIN_CUSTOMIZADO_1)
            .binCustomizado2(UPDATED_BIN_CUSTOMIZADO_2)
            .binCustomizado3(UPDATED_BIN_CUSTOMIZADO_3);

        restCampoCustomizavelCadastroMockMvc.perform(put("/api/campo-customizavel-cadastros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCampoCustomizavelCadastro)))
            .andExpect(status().isOk());

        // Validate the CampoCustomizavelCadastro in the database
        List<CampoCustomizavelCadastro> campoCustomizavelCadastroList = campoCustomizavelCadastroRepository.findAll();
        assertThat(campoCustomizavelCadastroList).hasSize(databaseSizeBeforeUpdate);
        CampoCustomizavelCadastro testCampoCustomizavelCadastro = campoCustomizavelCadastroList.get(campoCustomizavelCadastroList.size() - 1);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado1()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_1);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado2()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_2);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado3()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_3);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado4()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_4);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado5()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_5);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado6()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_6);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado7()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_7);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado8()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_8);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado9()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_9);
        assertThat(testCampoCustomizavelCadastro.getStringCustomizado10()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_10);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado1()).isEqualTo(UPDATED_INT_CUSTOMIZADO_1);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado2()).isEqualTo(UPDATED_INT_CUSTOMIZADO_2);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado3()).isEqualTo(UPDATED_INT_CUSTOMIZADO_3);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado4()).isEqualTo(UPDATED_INT_CUSTOMIZADO_4);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado5()).isEqualTo(UPDATED_INT_CUSTOMIZADO_5);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado6()).isEqualTo(UPDATED_INT_CUSTOMIZADO_6);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado7()).isEqualTo(UPDATED_INT_CUSTOMIZADO_7);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado8()).isEqualTo(UPDATED_INT_CUSTOMIZADO_8);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado9()).isEqualTo(UPDATED_INT_CUSTOMIZADO_9);
        assertThat(testCampoCustomizavelCadastro.getIntCustomizado10()).isEqualTo(UPDATED_INT_CUSTOMIZADO_10);
        assertThat(testCampoCustomizavelCadastro.isBinCustomizado1()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_1);
        assertThat(testCampoCustomizavelCadastro.isBinCustomizado2()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_2);
        assertThat(testCampoCustomizavelCadastro.isBinCustomizado3()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_3);
    }

    @Test
    @Transactional
    public void updateNonExistingCampoCustomizavelCadastro() throws Exception {
        int databaseSizeBeforeUpdate = campoCustomizavelCadastroRepository.findAll().size();

        // Create the CampoCustomizavelCadastro

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCampoCustomizavelCadastroMockMvc.perform(put("/api/campo-customizavel-cadastros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campoCustomizavelCadastro)))
            .andExpect(status().isCreated());

        // Validate the CampoCustomizavelCadastro in the database
        List<CampoCustomizavelCadastro> campoCustomizavelCadastroList = campoCustomizavelCadastroRepository.findAll();
        assertThat(campoCustomizavelCadastroList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCampoCustomizavelCadastro() throws Exception {
        // Initialize the database
        campoCustomizavelCadastroRepository.saveAndFlush(campoCustomizavelCadastro);
        int databaseSizeBeforeDelete = campoCustomizavelCadastroRepository.findAll().size();

        // Get the campoCustomizavelCadastro
        restCampoCustomizavelCadastroMockMvc.perform(delete("/api/campo-customizavel-cadastros/{id}", campoCustomizavelCadastro.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CampoCustomizavelCadastro> campoCustomizavelCadastroList = campoCustomizavelCadastroRepository.findAll();
        assertThat(campoCustomizavelCadastroList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampoCustomizavelCadastro.class);
        CampoCustomizavelCadastro campoCustomizavelCadastro1 = new CampoCustomizavelCadastro();
        campoCustomizavelCadastro1.setId(1L);
        CampoCustomizavelCadastro campoCustomizavelCadastro2 = new CampoCustomizavelCadastro();
        campoCustomizavelCadastro2.setId(campoCustomizavelCadastro1.getId());
        assertThat(campoCustomizavelCadastro1).isEqualTo(campoCustomizavelCadastro2);
        campoCustomizavelCadastro2.setId(2L);
        assertThat(campoCustomizavelCadastro1).isNotEqualTo(campoCustomizavelCadastro2);
        campoCustomizavelCadastro1.setId(null);
        assertThat(campoCustomizavelCadastro1).isNotEqualTo(campoCustomizavelCadastro2);
    }
}
