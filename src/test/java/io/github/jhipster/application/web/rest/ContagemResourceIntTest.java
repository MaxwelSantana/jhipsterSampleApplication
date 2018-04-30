package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Contagem;
import io.github.jhipster.application.repository.ContagemRepository;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ContagemResource REST controller.
 *
 * @see ContagemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ContagemResourceIntTest {

    private static final String DEFAULT_CODIGO_SECAO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_SECAO = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_INTERNO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_INTERNO = "BBBBBBBBBB";

    private static final String DEFAULT_EAN = "AAAAAAAAAA";
    private static final String UPDATED_EAN = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_QUANTIDADE = new BigDecimal(1);
    private static final BigDecimal UPDATED_QUANTIDADE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QUANTIDADE_ORIGINAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_QUANTIDADE_ORIGINAL = new BigDecimal(2);

    private static final Instant DEFAULT_TIME_STAMP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME_STAMP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

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
    private ContagemRepository contagemRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContagemMockMvc;

    private Contagem contagem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContagemResource contagemResource = new ContagemResource(contagemRepository);
        this.restContagemMockMvc = MockMvcBuilders.standaloneSetup(contagemResource)
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
    public static Contagem createEntity(EntityManager em) {
        Contagem contagem = new Contagem()
            .codigoSecao(DEFAULT_CODIGO_SECAO)
            .codigoInterno(DEFAULT_CODIGO_INTERNO)
            .ean(DEFAULT_EAN)
            .quantidade(DEFAULT_QUANTIDADE)
            .quantidadeOriginal(DEFAULT_QUANTIDADE_ORIGINAL)
            .timeStamp(DEFAULT_TIME_STAMP)
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
        return contagem;
    }

    @Before
    public void initTest() {
        contagem = createEntity(em);
    }

    @Test
    @Transactional
    public void createContagem() throws Exception {
        int databaseSizeBeforeCreate = contagemRepository.findAll().size();

        // Create the Contagem
        restContagemMockMvc.perform(post("/api/contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contagem)))
            .andExpect(status().isCreated());

        // Validate the Contagem in the database
        List<Contagem> contagemList = contagemRepository.findAll();
        assertThat(contagemList).hasSize(databaseSizeBeforeCreate + 1);
        Contagem testContagem = contagemList.get(contagemList.size() - 1);
        assertThat(testContagem.getCodigoSecao()).isEqualTo(DEFAULT_CODIGO_SECAO);
        assertThat(testContagem.getCodigoInterno()).isEqualTo(DEFAULT_CODIGO_INTERNO);
        assertThat(testContagem.getEan()).isEqualTo(DEFAULT_EAN);
        assertThat(testContagem.getQuantidade()).isEqualTo(DEFAULT_QUANTIDADE);
        assertThat(testContagem.getQuantidadeOriginal()).isEqualTo(DEFAULT_QUANTIDADE_ORIGINAL);
        assertThat(testContagem.getTimeStamp()).isEqualTo(DEFAULT_TIME_STAMP);
        assertThat(testContagem.getStringCustomizado1()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_1);
        assertThat(testContagem.getStringCustomizado2()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_2);
        assertThat(testContagem.getStringCustomizado3()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_3);
        assertThat(testContagem.getStringCustomizado4()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_4);
        assertThat(testContagem.getStringCustomizado5()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_5);
        assertThat(testContagem.getStringCustomizado6()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_6);
        assertThat(testContagem.getStringCustomizado7()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_7);
        assertThat(testContagem.getStringCustomizado8()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_8);
        assertThat(testContagem.getStringCustomizado9()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_9);
        assertThat(testContagem.getStringCustomizado10()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_10);
        assertThat(testContagem.getIntCustomizado1()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_1);
        assertThat(testContagem.getIntCustomizado2()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_2);
        assertThat(testContagem.getIntCustomizado3()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_3);
        assertThat(testContagem.getIntCustomizado4()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_4);
        assertThat(testContagem.getIntCustomizado5()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_5);
        assertThat(testContagem.getIntCustomizado6()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_6);
        assertThat(testContagem.getIntCustomizado7()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_7);
        assertThat(testContagem.getIntCustomizado8()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_8);
        assertThat(testContagem.getIntCustomizado9()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_9);
        assertThat(testContagem.getIntCustomizado10()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_10);
        assertThat(testContagem.isBinCustomizado1()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_1);
        assertThat(testContagem.isBinCustomizado2()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_2);
        assertThat(testContagem.isBinCustomizado3()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_3);
    }

    @Test
    @Transactional
    public void createContagemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contagemRepository.findAll().size();

        // Create the Contagem with an existing ID
        contagem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContagemMockMvc.perform(post("/api/contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contagem)))
            .andExpect(status().isBadRequest());

        // Validate the Contagem in the database
        List<Contagem> contagemList = contagemRepository.findAll();
        assertThat(contagemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllContagems() throws Exception {
        // Initialize the database
        contagemRepository.saveAndFlush(contagem);

        // Get all the contagemList
        restContagemMockMvc.perform(get("/api/contagems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contagem.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigoSecao").value(hasItem(DEFAULT_CODIGO_SECAO.toString())))
            .andExpect(jsonPath("$.[*].codigoInterno").value(hasItem(DEFAULT_CODIGO_INTERNO.toString())))
            .andExpect(jsonPath("$.[*].ean").value(hasItem(DEFAULT_EAN.toString())))
            .andExpect(jsonPath("$.[*].quantidade").value(hasItem(DEFAULT_QUANTIDADE.intValue())))
            .andExpect(jsonPath("$.[*].quantidadeOriginal").value(hasItem(DEFAULT_QUANTIDADE_ORIGINAL.intValue())))
            .andExpect(jsonPath("$.[*].timeStamp").value(hasItem(DEFAULT_TIME_STAMP.toString())))
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
    public void getContagem() throws Exception {
        // Initialize the database
        contagemRepository.saveAndFlush(contagem);

        // Get the contagem
        restContagemMockMvc.perform(get("/api/contagems/{id}", contagem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contagem.getId().intValue()))
            .andExpect(jsonPath("$.codigoSecao").value(DEFAULT_CODIGO_SECAO.toString()))
            .andExpect(jsonPath("$.codigoInterno").value(DEFAULT_CODIGO_INTERNO.toString()))
            .andExpect(jsonPath("$.ean").value(DEFAULT_EAN.toString()))
            .andExpect(jsonPath("$.quantidade").value(DEFAULT_QUANTIDADE.intValue()))
            .andExpect(jsonPath("$.quantidadeOriginal").value(DEFAULT_QUANTIDADE_ORIGINAL.intValue()))
            .andExpect(jsonPath("$.timeStamp").value(DEFAULT_TIME_STAMP.toString()))
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
    public void getNonExistingContagem() throws Exception {
        // Get the contagem
        restContagemMockMvc.perform(get("/api/contagems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContagem() throws Exception {
        // Initialize the database
        contagemRepository.saveAndFlush(contagem);
        int databaseSizeBeforeUpdate = contagemRepository.findAll().size();

        // Update the contagem
        Contagem updatedContagem = contagemRepository.findOne(contagem.getId());
        // Disconnect from session so that the updates on updatedContagem are not directly saved in db
        em.detach(updatedContagem);
        updatedContagem
            .codigoSecao(UPDATED_CODIGO_SECAO)
            .codigoInterno(UPDATED_CODIGO_INTERNO)
            .ean(UPDATED_EAN)
            .quantidade(UPDATED_QUANTIDADE)
            .quantidadeOriginal(UPDATED_QUANTIDADE_ORIGINAL)
            .timeStamp(UPDATED_TIME_STAMP)
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

        restContagemMockMvc.perform(put("/api/contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedContagem)))
            .andExpect(status().isOk());

        // Validate the Contagem in the database
        List<Contagem> contagemList = contagemRepository.findAll();
        assertThat(contagemList).hasSize(databaseSizeBeforeUpdate);
        Contagem testContagem = contagemList.get(contagemList.size() - 1);
        assertThat(testContagem.getCodigoSecao()).isEqualTo(UPDATED_CODIGO_SECAO);
        assertThat(testContagem.getCodigoInterno()).isEqualTo(UPDATED_CODIGO_INTERNO);
        assertThat(testContagem.getEan()).isEqualTo(UPDATED_EAN);
        assertThat(testContagem.getQuantidade()).isEqualTo(UPDATED_QUANTIDADE);
        assertThat(testContagem.getQuantidadeOriginal()).isEqualTo(UPDATED_QUANTIDADE_ORIGINAL);
        assertThat(testContagem.getTimeStamp()).isEqualTo(UPDATED_TIME_STAMP);
        assertThat(testContagem.getStringCustomizado1()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_1);
        assertThat(testContagem.getStringCustomizado2()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_2);
        assertThat(testContagem.getStringCustomizado3()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_3);
        assertThat(testContagem.getStringCustomizado4()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_4);
        assertThat(testContagem.getStringCustomizado5()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_5);
        assertThat(testContagem.getStringCustomizado6()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_6);
        assertThat(testContagem.getStringCustomizado7()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_7);
        assertThat(testContagem.getStringCustomizado8()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_8);
        assertThat(testContagem.getStringCustomizado9()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_9);
        assertThat(testContagem.getStringCustomizado10()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_10);
        assertThat(testContagem.getIntCustomizado1()).isEqualTo(UPDATED_INT_CUSTOMIZADO_1);
        assertThat(testContagem.getIntCustomizado2()).isEqualTo(UPDATED_INT_CUSTOMIZADO_2);
        assertThat(testContagem.getIntCustomizado3()).isEqualTo(UPDATED_INT_CUSTOMIZADO_3);
        assertThat(testContagem.getIntCustomizado4()).isEqualTo(UPDATED_INT_CUSTOMIZADO_4);
        assertThat(testContagem.getIntCustomizado5()).isEqualTo(UPDATED_INT_CUSTOMIZADO_5);
        assertThat(testContagem.getIntCustomizado6()).isEqualTo(UPDATED_INT_CUSTOMIZADO_6);
        assertThat(testContagem.getIntCustomizado7()).isEqualTo(UPDATED_INT_CUSTOMIZADO_7);
        assertThat(testContagem.getIntCustomizado8()).isEqualTo(UPDATED_INT_CUSTOMIZADO_8);
        assertThat(testContagem.getIntCustomizado9()).isEqualTo(UPDATED_INT_CUSTOMIZADO_9);
        assertThat(testContagem.getIntCustomizado10()).isEqualTo(UPDATED_INT_CUSTOMIZADO_10);
        assertThat(testContagem.isBinCustomizado1()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_1);
        assertThat(testContagem.isBinCustomizado2()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_2);
        assertThat(testContagem.isBinCustomizado3()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_3);
    }

    @Test
    @Transactional
    public void updateNonExistingContagem() throws Exception {
        int databaseSizeBeforeUpdate = contagemRepository.findAll().size();

        // Create the Contagem

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restContagemMockMvc.perform(put("/api/contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contagem)))
            .andExpect(status().isCreated());

        // Validate the Contagem in the database
        List<Contagem> contagemList = contagemRepository.findAll();
        assertThat(contagemList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteContagem() throws Exception {
        // Initialize the database
        contagemRepository.saveAndFlush(contagem);
        int databaseSizeBeforeDelete = contagemRepository.findAll().size();

        // Get the contagem
        restContagemMockMvc.perform(delete("/api/contagems/{id}", contagem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Contagem> contagemList = contagemRepository.findAll();
        assertThat(contagemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contagem.class);
        Contagem contagem1 = new Contagem();
        contagem1.setId(1L);
        Contagem contagem2 = new Contagem();
        contagem2.setId(contagem1.getId());
        assertThat(contagem1).isEqualTo(contagem2);
        contagem2.setId(2L);
        assertThat(contagem1).isNotEqualTo(contagem2);
        contagem1.setId(null);
        assertThat(contagem1).isNotEqualTo(contagem2);
    }
}
