package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Cadastro;
import io.github.jhipster.application.repository.CadastroRepository;
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
 * Test class for the CadastroResource REST controller.
 *
 * @see CadastroResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class CadastroResourceIntTest {

    private static final String DEFAULT_CODIGO_INTERNO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_INTERNO = "BBBBBBBBBB";

    private static final String DEFAULT_EAN = "AAAAAAAAAA";
    private static final String UPDATED_EAN = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_QUANTIDADE = new BigDecimal(1);
    private static final BigDecimal UPDATED_QUANTIDADE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRECO = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRECO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRECO_CUSTO = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRECO_CUSTO = new BigDecimal(2);

    private static final String DEFAULT_SETOR = "AAAAAAAAAA";
    private static final String UPDATED_SETOR = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTAMENTO = "BBBBBBBBBB";

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
    private CadastroRepository cadastroRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCadastroMockMvc;

    private Cadastro cadastro;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CadastroResource cadastroResource = new CadastroResource(cadastroRepository);
        this.restCadastroMockMvc = MockMvcBuilders.standaloneSetup(cadastroResource)
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
    public static Cadastro createEntity(EntityManager em) {
        Cadastro cadastro = new Cadastro()
            .codigoInterno(DEFAULT_CODIGO_INTERNO)
            .ean(DEFAULT_EAN)
            .descricao(DEFAULT_DESCRICAO)
            .quantidade(DEFAULT_QUANTIDADE)
            .preco(DEFAULT_PRECO)
            .precoCusto(DEFAULT_PRECO_CUSTO)
            .setor(DEFAULT_SETOR)
            .departamento(DEFAULT_DEPARTAMENTO)
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
        return cadastro;
    }

    @Before
    public void initTest() {
        cadastro = createEntity(em);
    }

    @Test
    @Transactional
    public void createCadastro() throws Exception {
        int databaseSizeBeforeCreate = cadastroRepository.findAll().size();

        // Create the Cadastro
        restCadastroMockMvc.perform(post("/api/cadastros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cadastro)))
            .andExpect(status().isCreated());

        // Validate the Cadastro in the database
        List<Cadastro> cadastroList = cadastroRepository.findAll();
        assertThat(cadastroList).hasSize(databaseSizeBeforeCreate + 1);
        Cadastro testCadastro = cadastroList.get(cadastroList.size() - 1);
        assertThat(testCadastro.getCodigoInterno()).isEqualTo(DEFAULT_CODIGO_INTERNO);
        assertThat(testCadastro.getEan()).isEqualTo(DEFAULT_EAN);
        assertThat(testCadastro.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testCadastro.getQuantidade()).isEqualTo(DEFAULT_QUANTIDADE);
        assertThat(testCadastro.getPreco()).isEqualTo(DEFAULT_PRECO);
        assertThat(testCadastro.getPrecoCusto()).isEqualTo(DEFAULT_PRECO_CUSTO);
        assertThat(testCadastro.getSetor()).isEqualTo(DEFAULT_SETOR);
        assertThat(testCadastro.getDepartamento()).isEqualTo(DEFAULT_DEPARTAMENTO);
        assertThat(testCadastro.getStringCustomizado1()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_1);
        assertThat(testCadastro.getStringCustomizado2()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_2);
        assertThat(testCadastro.getStringCustomizado3()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_3);
        assertThat(testCadastro.getStringCustomizado4()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_4);
        assertThat(testCadastro.getStringCustomizado5()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_5);
        assertThat(testCadastro.getStringCustomizado6()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_6);
        assertThat(testCadastro.getStringCustomizado7()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_7);
        assertThat(testCadastro.getStringCustomizado8()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_8);
        assertThat(testCadastro.getStringCustomizado9()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_9);
        assertThat(testCadastro.getStringCustomizado10()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_10);
        assertThat(testCadastro.getIntCustomizado1()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_1);
        assertThat(testCadastro.getIntCustomizado2()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_2);
        assertThat(testCadastro.getIntCustomizado3()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_3);
        assertThat(testCadastro.getIntCustomizado4()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_4);
        assertThat(testCadastro.getIntCustomizado5()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_5);
        assertThat(testCadastro.getIntCustomizado6()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_6);
        assertThat(testCadastro.getIntCustomizado7()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_7);
        assertThat(testCadastro.getIntCustomizado8()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_8);
        assertThat(testCadastro.getIntCustomizado9()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_9);
        assertThat(testCadastro.getIntCustomizado10()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_10);
        assertThat(testCadastro.isBinCustomizado1()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_1);
        assertThat(testCadastro.isBinCustomizado2()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_2);
        assertThat(testCadastro.isBinCustomizado3()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_3);
    }

    @Test
    @Transactional
    public void createCadastroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cadastroRepository.findAll().size();

        // Create the Cadastro with an existing ID
        cadastro.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCadastroMockMvc.perform(post("/api/cadastros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cadastro)))
            .andExpect(status().isBadRequest());

        // Validate the Cadastro in the database
        List<Cadastro> cadastroList = cadastroRepository.findAll();
        assertThat(cadastroList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCadastros() throws Exception {
        // Initialize the database
        cadastroRepository.saveAndFlush(cadastro);

        // Get all the cadastroList
        restCadastroMockMvc.perform(get("/api/cadastros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cadastro.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigoInterno").value(hasItem(DEFAULT_CODIGO_INTERNO.toString())))
            .andExpect(jsonPath("$.[*].ean").value(hasItem(DEFAULT_EAN.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].quantidade").value(hasItem(DEFAULT_QUANTIDADE.intValue())))
            .andExpect(jsonPath("$.[*].preco").value(hasItem(DEFAULT_PRECO.intValue())))
            .andExpect(jsonPath("$.[*].precoCusto").value(hasItem(DEFAULT_PRECO_CUSTO.intValue())))
            .andExpect(jsonPath("$.[*].setor").value(hasItem(DEFAULT_SETOR.toString())))
            .andExpect(jsonPath("$.[*].departamento").value(hasItem(DEFAULT_DEPARTAMENTO.toString())))
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
    public void getCadastro() throws Exception {
        // Initialize the database
        cadastroRepository.saveAndFlush(cadastro);

        // Get the cadastro
        restCadastroMockMvc.perform(get("/api/cadastros/{id}", cadastro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cadastro.getId().intValue()))
            .andExpect(jsonPath("$.codigoInterno").value(DEFAULT_CODIGO_INTERNO.toString()))
            .andExpect(jsonPath("$.ean").value(DEFAULT_EAN.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.quantidade").value(DEFAULT_QUANTIDADE.intValue()))
            .andExpect(jsonPath("$.preco").value(DEFAULT_PRECO.intValue()))
            .andExpect(jsonPath("$.precoCusto").value(DEFAULT_PRECO_CUSTO.intValue()))
            .andExpect(jsonPath("$.setor").value(DEFAULT_SETOR.toString()))
            .andExpect(jsonPath("$.departamento").value(DEFAULT_DEPARTAMENTO.toString()))
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
    public void getNonExistingCadastro() throws Exception {
        // Get the cadastro
        restCadastroMockMvc.perform(get("/api/cadastros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCadastro() throws Exception {
        // Initialize the database
        cadastroRepository.saveAndFlush(cadastro);
        int databaseSizeBeforeUpdate = cadastroRepository.findAll().size();

        // Update the cadastro
        Cadastro updatedCadastro = cadastroRepository.findOne(cadastro.getId());
        // Disconnect from session so that the updates on updatedCadastro are not directly saved in db
        em.detach(updatedCadastro);
        updatedCadastro
            .codigoInterno(UPDATED_CODIGO_INTERNO)
            .ean(UPDATED_EAN)
            .descricao(UPDATED_DESCRICAO)
            .quantidade(UPDATED_QUANTIDADE)
            .preco(UPDATED_PRECO)
            .precoCusto(UPDATED_PRECO_CUSTO)
            .setor(UPDATED_SETOR)
            .departamento(UPDATED_DEPARTAMENTO)
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

        restCadastroMockMvc.perform(put("/api/cadastros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCadastro)))
            .andExpect(status().isOk());

        // Validate the Cadastro in the database
        List<Cadastro> cadastroList = cadastroRepository.findAll();
        assertThat(cadastroList).hasSize(databaseSizeBeforeUpdate);
        Cadastro testCadastro = cadastroList.get(cadastroList.size() - 1);
        assertThat(testCadastro.getCodigoInterno()).isEqualTo(UPDATED_CODIGO_INTERNO);
        assertThat(testCadastro.getEan()).isEqualTo(UPDATED_EAN);
        assertThat(testCadastro.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testCadastro.getQuantidade()).isEqualTo(UPDATED_QUANTIDADE);
        assertThat(testCadastro.getPreco()).isEqualTo(UPDATED_PRECO);
        assertThat(testCadastro.getPrecoCusto()).isEqualTo(UPDATED_PRECO_CUSTO);
        assertThat(testCadastro.getSetor()).isEqualTo(UPDATED_SETOR);
        assertThat(testCadastro.getDepartamento()).isEqualTo(UPDATED_DEPARTAMENTO);
        assertThat(testCadastro.getStringCustomizado1()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_1);
        assertThat(testCadastro.getStringCustomizado2()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_2);
        assertThat(testCadastro.getStringCustomizado3()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_3);
        assertThat(testCadastro.getStringCustomizado4()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_4);
        assertThat(testCadastro.getStringCustomizado5()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_5);
        assertThat(testCadastro.getStringCustomizado6()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_6);
        assertThat(testCadastro.getStringCustomizado7()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_7);
        assertThat(testCadastro.getStringCustomizado8()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_8);
        assertThat(testCadastro.getStringCustomizado9()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_9);
        assertThat(testCadastro.getStringCustomizado10()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_10);
        assertThat(testCadastro.getIntCustomizado1()).isEqualTo(UPDATED_INT_CUSTOMIZADO_1);
        assertThat(testCadastro.getIntCustomizado2()).isEqualTo(UPDATED_INT_CUSTOMIZADO_2);
        assertThat(testCadastro.getIntCustomizado3()).isEqualTo(UPDATED_INT_CUSTOMIZADO_3);
        assertThat(testCadastro.getIntCustomizado4()).isEqualTo(UPDATED_INT_CUSTOMIZADO_4);
        assertThat(testCadastro.getIntCustomizado5()).isEqualTo(UPDATED_INT_CUSTOMIZADO_5);
        assertThat(testCadastro.getIntCustomizado6()).isEqualTo(UPDATED_INT_CUSTOMIZADO_6);
        assertThat(testCadastro.getIntCustomizado7()).isEqualTo(UPDATED_INT_CUSTOMIZADO_7);
        assertThat(testCadastro.getIntCustomizado8()).isEqualTo(UPDATED_INT_CUSTOMIZADO_8);
        assertThat(testCadastro.getIntCustomizado9()).isEqualTo(UPDATED_INT_CUSTOMIZADO_9);
        assertThat(testCadastro.getIntCustomizado10()).isEqualTo(UPDATED_INT_CUSTOMIZADO_10);
        assertThat(testCadastro.isBinCustomizado1()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_1);
        assertThat(testCadastro.isBinCustomizado2()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_2);
        assertThat(testCadastro.isBinCustomizado3()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_3);
    }

    @Test
    @Transactional
    public void updateNonExistingCadastro() throws Exception {
        int databaseSizeBeforeUpdate = cadastroRepository.findAll().size();

        // Create the Cadastro

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCadastroMockMvc.perform(put("/api/cadastros")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cadastro)))
            .andExpect(status().isCreated());

        // Validate the Cadastro in the database
        List<Cadastro> cadastroList = cadastroRepository.findAll();
        assertThat(cadastroList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCadastro() throws Exception {
        // Initialize the database
        cadastroRepository.saveAndFlush(cadastro);
        int databaseSizeBeforeDelete = cadastroRepository.findAll().size();

        // Get the cadastro
        restCadastroMockMvc.perform(delete("/api/cadastros/{id}", cadastro.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Cadastro> cadastroList = cadastroRepository.findAll();
        assertThat(cadastroList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cadastro.class);
        Cadastro cadastro1 = new Cadastro();
        cadastro1.setId(1L);
        Cadastro cadastro2 = new Cadastro();
        cadastro2.setId(cadastro1.getId());
        assertThat(cadastro1).isEqualTo(cadastro2);
        cadastro2.setId(2L);
        assertThat(cadastro1).isNotEqualTo(cadastro2);
        cadastro1.setId(null);
        assertThat(cadastro1).isNotEqualTo(cadastro2);
    }
}
