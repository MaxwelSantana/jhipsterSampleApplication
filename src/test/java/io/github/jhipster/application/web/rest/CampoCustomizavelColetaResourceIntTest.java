package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.CampoCustomizavelColeta;
import io.github.jhipster.application.repository.CampoCustomizavelColetaRepository;
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
 * Test class for the CampoCustomizavelColetaResource REST controller.
 *
 * @see CampoCustomizavelColetaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class CampoCustomizavelColetaResourceIntTest {

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
    private CampoCustomizavelColetaRepository campoCustomizavelColetaRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCampoCustomizavelColetaMockMvc;

    private CampoCustomizavelColeta campoCustomizavelColeta;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CampoCustomizavelColetaResource campoCustomizavelColetaResource = new CampoCustomizavelColetaResource(campoCustomizavelColetaRepository);
        this.restCampoCustomizavelColetaMockMvc = MockMvcBuilders.standaloneSetup(campoCustomizavelColetaResource)
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
    public static CampoCustomizavelColeta createEntity(EntityManager em) {
        CampoCustomizavelColeta campoCustomizavelColeta = new CampoCustomizavelColeta()
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
        return campoCustomizavelColeta;
    }

    @Before
    public void initTest() {
        campoCustomizavelColeta = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampoCustomizavelColeta() throws Exception {
        int databaseSizeBeforeCreate = campoCustomizavelColetaRepository.findAll().size();

        // Create the CampoCustomizavelColeta
        restCampoCustomizavelColetaMockMvc.perform(post("/api/campo-customizavel-coletas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campoCustomizavelColeta)))
            .andExpect(status().isCreated());

        // Validate the CampoCustomizavelColeta in the database
        List<CampoCustomizavelColeta> campoCustomizavelColetaList = campoCustomizavelColetaRepository.findAll();
        assertThat(campoCustomizavelColetaList).hasSize(databaseSizeBeforeCreate + 1);
        CampoCustomizavelColeta testCampoCustomizavelColeta = campoCustomizavelColetaList.get(campoCustomizavelColetaList.size() - 1);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado1()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_1);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado2()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_2);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado3()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_3);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado4()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_4);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado5()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_5);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado6()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_6);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado7()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_7);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado8()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_8);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado9()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_9);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado10()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_10);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado1()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_1);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado2()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_2);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado3()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_3);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado4()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_4);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado5()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_5);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado6()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_6);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado7()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_7);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado8()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_8);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado9()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_9);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado10()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_10);
        assertThat(testCampoCustomizavelColeta.isBinCustomizado1()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_1);
        assertThat(testCampoCustomizavelColeta.isBinCustomizado2()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_2);
        assertThat(testCampoCustomizavelColeta.isBinCustomizado3()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_3);
    }

    @Test
    @Transactional
    public void createCampoCustomizavelColetaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campoCustomizavelColetaRepository.findAll().size();

        // Create the CampoCustomizavelColeta with an existing ID
        campoCustomizavelColeta.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampoCustomizavelColetaMockMvc.perform(post("/api/campo-customizavel-coletas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campoCustomizavelColeta)))
            .andExpect(status().isBadRequest());

        // Validate the CampoCustomizavelColeta in the database
        List<CampoCustomizavelColeta> campoCustomizavelColetaList = campoCustomizavelColetaRepository.findAll();
        assertThat(campoCustomizavelColetaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCampoCustomizavelColetas() throws Exception {
        // Initialize the database
        campoCustomizavelColetaRepository.saveAndFlush(campoCustomizavelColeta);

        // Get all the campoCustomizavelColetaList
        restCampoCustomizavelColetaMockMvc.perform(get("/api/campo-customizavel-coletas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campoCustomizavelColeta.getId().intValue())))
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
    public void getCampoCustomizavelColeta() throws Exception {
        // Initialize the database
        campoCustomizavelColetaRepository.saveAndFlush(campoCustomizavelColeta);

        // Get the campoCustomizavelColeta
        restCampoCustomizavelColetaMockMvc.perform(get("/api/campo-customizavel-coletas/{id}", campoCustomizavelColeta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(campoCustomizavelColeta.getId().intValue()))
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
    public void getNonExistingCampoCustomizavelColeta() throws Exception {
        // Get the campoCustomizavelColeta
        restCampoCustomizavelColetaMockMvc.perform(get("/api/campo-customizavel-coletas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampoCustomizavelColeta() throws Exception {
        // Initialize the database
        campoCustomizavelColetaRepository.saveAndFlush(campoCustomizavelColeta);
        int databaseSizeBeforeUpdate = campoCustomizavelColetaRepository.findAll().size();

        // Update the campoCustomizavelColeta
        CampoCustomizavelColeta updatedCampoCustomizavelColeta = campoCustomizavelColetaRepository.findOne(campoCustomizavelColeta.getId());
        // Disconnect from session so that the updates on updatedCampoCustomizavelColeta are not directly saved in db
        em.detach(updatedCampoCustomizavelColeta);
        updatedCampoCustomizavelColeta
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

        restCampoCustomizavelColetaMockMvc.perform(put("/api/campo-customizavel-coletas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCampoCustomizavelColeta)))
            .andExpect(status().isOk());

        // Validate the CampoCustomizavelColeta in the database
        List<CampoCustomizavelColeta> campoCustomizavelColetaList = campoCustomizavelColetaRepository.findAll();
        assertThat(campoCustomizavelColetaList).hasSize(databaseSizeBeforeUpdate);
        CampoCustomizavelColeta testCampoCustomizavelColeta = campoCustomizavelColetaList.get(campoCustomizavelColetaList.size() - 1);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado1()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_1);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado2()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_2);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado3()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_3);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado4()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_4);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado5()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_5);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado6()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_6);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado7()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_7);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado8()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_8);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado9()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_9);
        assertThat(testCampoCustomizavelColeta.getStringCustomizado10()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_10);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado1()).isEqualTo(UPDATED_INT_CUSTOMIZADO_1);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado2()).isEqualTo(UPDATED_INT_CUSTOMIZADO_2);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado3()).isEqualTo(UPDATED_INT_CUSTOMIZADO_3);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado4()).isEqualTo(UPDATED_INT_CUSTOMIZADO_4);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado5()).isEqualTo(UPDATED_INT_CUSTOMIZADO_5);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado6()).isEqualTo(UPDATED_INT_CUSTOMIZADO_6);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado7()).isEqualTo(UPDATED_INT_CUSTOMIZADO_7);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado8()).isEqualTo(UPDATED_INT_CUSTOMIZADO_8);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado9()).isEqualTo(UPDATED_INT_CUSTOMIZADO_9);
        assertThat(testCampoCustomizavelColeta.getIntCustomizado10()).isEqualTo(UPDATED_INT_CUSTOMIZADO_10);
        assertThat(testCampoCustomizavelColeta.isBinCustomizado1()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_1);
        assertThat(testCampoCustomizavelColeta.isBinCustomizado2()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_2);
        assertThat(testCampoCustomizavelColeta.isBinCustomizado3()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_3);
    }

    @Test
    @Transactional
    public void updateNonExistingCampoCustomizavelColeta() throws Exception {
        int databaseSizeBeforeUpdate = campoCustomizavelColetaRepository.findAll().size();

        // Create the CampoCustomizavelColeta

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCampoCustomizavelColetaMockMvc.perform(put("/api/campo-customizavel-coletas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campoCustomizavelColeta)))
            .andExpect(status().isCreated());

        // Validate the CampoCustomizavelColeta in the database
        List<CampoCustomizavelColeta> campoCustomizavelColetaList = campoCustomizavelColetaRepository.findAll();
        assertThat(campoCustomizavelColetaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCampoCustomizavelColeta() throws Exception {
        // Initialize the database
        campoCustomizavelColetaRepository.saveAndFlush(campoCustomizavelColeta);
        int databaseSizeBeforeDelete = campoCustomizavelColetaRepository.findAll().size();

        // Get the campoCustomizavelColeta
        restCampoCustomizavelColetaMockMvc.perform(delete("/api/campo-customizavel-coletas/{id}", campoCustomizavelColeta.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CampoCustomizavelColeta> campoCustomizavelColetaList = campoCustomizavelColetaRepository.findAll();
        assertThat(campoCustomizavelColetaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampoCustomizavelColeta.class);
        CampoCustomizavelColeta campoCustomizavelColeta1 = new CampoCustomizavelColeta();
        campoCustomizavelColeta1.setId(1L);
        CampoCustomizavelColeta campoCustomizavelColeta2 = new CampoCustomizavelColeta();
        campoCustomizavelColeta2.setId(campoCustomizavelColeta1.getId());
        assertThat(campoCustomizavelColeta1).isEqualTo(campoCustomizavelColeta2);
        campoCustomizavelColeta2.setId(2L);
        assertThat(campoCustomizavelColeta1).isNotEqualTo(campoCustomizavelColeta2);
        campoCustomizavelColeta1.setId(null);
        assertThat(campoCustomizavelColeta1).isNotEqualTo(campoCustomizavelColeta2);
    }
}
