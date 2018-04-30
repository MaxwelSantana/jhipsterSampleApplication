package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Configuracao;
import io.github.jhipster.application.repository.ConfiguracaoRepository;
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
 * Test class for the ConfiguracaoResource REST controller.
 *
 * @see ConfiguracaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ConfiguracaoResourceIntTest {

    private static final String DEFAULT_CODIGO_INTERNO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_INTERNO = "BBBBBBBBBB";

    private static final String DEFAULT_EAN = "AAAAAAAAAA";
    private static final String UPDATED_EAN = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final String DEFAULT_QUANTIDADE = "AAAAAAAAAA";
    private static final String UPDATED_QUANTIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_PRECO = "AAAAAAAAAA";
    private static final String UPDATED_PRECO = "BBBBBBBBBB";

    private static final String DEFAULT_SETOR = "AAAAAAAAAA";
    private static final String UPDATED_SETOR = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_PRECO_CUSTO = "AAAAAAAAAA";
    private static final String UPDATED_PRECO_CUSTO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_POSICIONAL = false;
    private static final Boolean UPDATED_POSICIONAL = true;

    private static final String DEFAULT_DELIMITADOR = "AAAAAAAAAA";
    private static final String UPDATED_DELIMITADOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_DESCONSIDERAR_LINHA_INICIAL = 1;
    private static final Integer UPDATED_DESCONSIDERAR_LINHA_INICIAL = 2;

    private static final Integer DEFAULT_DESCONSIDERAR_LINHA_FINAL = 1;
    private static final Integer UPDATED_DESCONSIDERAR_LINHA_FINAL = 2;

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

    private static final String DEFAULT_INT_CUSTOMIZADO_1 = "AAAAAAAAAA";
    private static final String UPDATED_INT_CUSTOMIZADO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_INT_CUSTOMIZADO_2 = "AAAAAAAAAA";
    private static final String UPDATED_INT_CUSTOMIZADO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_INT_CUSTOMIZADO_3 = "AAAAAAAAAA";
    private static final String UPDATED_INT_CUSTOMIZADO_3 = "BBBBBBBBBB";

    private static final String DEFAULT_INT_CUSTOMIZADO_4 = "AAAAAAAAAA";
    private static final String UPDATED_INT_CUSTOMIZADO_4 = "BBBBBBBBBB";

    private static final String DEFAULT_INT_CUSTOMIZADO_5 = "AAAAAAAAAA";
    private static final String UPDATED_INT_CUSTOMIZADO_5 = "BBBBBBBBBB";

    private static final String DEFAULT_INT_CUSTOMIZADO_6 = "AAAAAAAAAA";
    private static final String UPDATED_INT_CUSTOMIZADO_6 = "BBBBBBBBBB";

    private static final String DEFAULT_INT_CUSTOMIZADO_7 = "AAAAAAAAAA";
    private static final String UPDATED_INT_CUSTOMIZADO_7 = "BBBBBBBBBB";

    private static final String DEFAULT_INT_CUSTOMIZADO_8 = "AAAAAAAAAA";
    private static final String UPDATED_INT_CUSTOMIZADO_8 = "BBBBBBBBBB";

    private static final String DEFAULT_INT_CUSTOMIZADO_9 = "AAAAAAAAAA";
    private static final String UPDATED_INT_CUSTOMIZADO_9 = "BBBBBBBBBB";

    private static final String DEFAULT_INT_CUSTOMIZADO_10 = "AAAAAAAAAA";
    private static final String UPDATED_INT_CUSTOMIZADO_10 = "BBBBBBBBBB";

    private static final String DEFAULT_BIN_CUSTOMIZADO_1 = "AAAAAAAAAA";
    private static final String UPDATED_BIN_CUSTOMIZADO_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BIN_CUSTOMIZADO_2 = "AAAAAAAAAA";
    private static final String UPDATED_BIN_CUSTOMIZADO_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BIN_CUSTOMIZADO_3 = "AAAAAAAAAA";
    private static final String UPDATED_BIN_CUSTOMIZADO_3 = "BBBBBBBBBB";

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConfiguracaoMockMvc;

    private Configuracao configuracao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConfiguracaoResource configuracaoResource = new ConfiguracaoResource(configuracaoRepository);
        this.restConfiguracaoMockMvc = MockMvcBuilders.standaloneSetup(configuracaoResource)
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
    public static Configuracao createEntity(EntityManager em) {
        Configuracao configuracao = new Configuracao()
            .codigoInterno(DEFAULT_CODIGO_INTERNO)
            .ean(DEFAULT_EAN)
            .descricao(DEFAULT_DESCRICAO)
            .quantidade(DEFAULT_QUANTIDADE)
            .preco(DEFAULT_PRECO)
            .setor(DEFAULT_SETOR)
            .departamento(DEFAULT_DEPARTAMENTO)
            .precoCusto(DEFAULT_PRECO_CUSTO)
            .posicional(DEFAULT_POSICIONAL)
            .delimitador(DEFAULT_DELIMITADOR)
            .desconsiderarLinhaInicial(DEFAULT_DESCONSIDERAR_LINHA_INICIAL)
            .desconsiderarLinhaFinal(DEFAULT_DESCONSIDERAR_LINHA_FINAL)
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
        return configuracao;
    }

    @Before
    public void initTest() {
        configuracao = createEntity(em);
    }

    @Test
    @Transactional
    public void createConfiguracao() throws Exception {
        int databaseSizeBeforeCreate = configuracaoRepository.findAll().size();

        // Create the Configuracao
        restConfiguracaoMockMvc.perform(post("/api/configuracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracao)))
            .andExpect(status().isCreated());

        // Validate the Configuracao in the database
        List<Configuracao> configuracaoList = configuracaoRepository.findAll();
        assertThat(configuracaoList).hasSize(databaseSizeBeforeCreate + 1);
        Configuracao testConfiguracao = configuracaoList.get(configuracaoList.size() - 1);
        assertThat(testConfiguracao.getCodigoInterno()).isEqualTo(DEFAULT_CODIGO_INTERNO);
        assertThat(testConfiguracao.getEan()).isEqualTo(DEFAULT_EAN);
        assertThat(testConfiguracao.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testConfiguracao.getQuantidade()).isEqualTo(DEFAULT_QUANTIDADE);
        assertThat(testConfiguracao.getPreco()).isEqualTo(DEFAULT_PRECO);
        assertThat(testConfiguracao.getSetor()).isEqualTo(DEFAULT_SETOR);
        assertThat(testConfiguracao.getDepartamento()).isEqualTo(DEFAULT_DEPARTAMENTO);
        assertThat(testConfiguracao.getPrecoCusto()).isEqualTo(DEFAULT_PRECO_CUSTO);
        assertThat(testConfiguracao.isPosicional()).isEqualTo(DEFAULT_POSICIONAL);
        assertThat(testConfiguracao.getDelimitador()).isEqualTo(DEFAULT_DELIMITADOR);
        assertThat(testConfiguracao.getDesconsiderarLinhaInicial()).isEqualTo(DEFAULT_DESCONSIDERAR_LINHA_INICIAL);
        assertThat(testConfiguracao.getDesconsiderarLinhaFinal()).isEqualTo(DEFAULT_DESCONSIDERAR_LINHA_FINAL);
        assertThat(testConfiguracao.getStringCustomizado1()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_1);
        assertThat(testConfiguracao.getStringCustomizado2()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_2);
        assertThat(testConfiguracao.getStringCustomizado3()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_3);
        assertThat(testConfiguracao.getStringCustomizado4()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_4);
        assertThat(testConfiguracao.getStringCustomizado5()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_5);
        assertThat(testConfiguracao.getStringCustomizado6()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_6);
        assertThat(testConfiguracao.getStringCustomizado7()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_7);
        assertThat(testConfiguracao.getStringCustomizado8()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_8);
        assertThat(testConfiguracao.getStringCustomizado9()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_9);
        assertThat(testConfiguracao.getStringCustomizado10()).isEqualTo(DEFAULT_STRING_CUSTOMIZADO_10);
        assertThat(testConfiguracao.getIntCustomizado1()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_1);
        assertThat(testConfiguracao.getIntCustomizado2()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_2);
        assertThat(testConfiguracao.getIntCustomizado3()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_3);
        assertThat(testConfiguracao.getIntCustomizado4()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_4);
        assertThat(testConfiguracao.getIntCustomizado5()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_5);
        assertThat(testConfiguracao.getIntCustomizado6()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_6);
        assertThat(testConfiguracao.getIntCustomizado7()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_7);
        assertThat(testConfiguracao.getIntCustomizado8()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_8);
        assertThat(testConfiguracao.getIntCustomizado9()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_9);
        assertThat(testConfiguracao.getIntCustomizado10()).isEqualTo(DEFAULT_INT_CUSTOMIZADO_10);
        assertThat(testConfiguracao.getBinCustomizado1()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_1);
        assertThat(testConfiguracao.getBinCustomizado2()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_2);
        assertThat(testConfiguracao.getBinCustomizado3()).isEqualTo(DEFAULT_BIN_CUSTOMIZADO_3);
    }

    @Test
    @Transactional
    public void createConfiguracaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = configuracaoRepository.findAll().size();

        // Create the Configuracao with an existing ID
        configuracao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfiguracaoMockMvc.perform(post("/api/configuracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracao)))
            .andExpect(status().isBadRequest());

        // Validate the Configuracao in the database
        List<Configuracao> configuracaoList = configuracaoRepository.findAll();
        assertThat(configuracaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllConfiguracaos() throws Exception {
        // Initialize the database
        configuracaoRepository.saveAndFlush(configuracao);

        // Get all the configuracaoList
        restConfiguracaoMockMvc.perform(get("/api/configuracaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(configuracao.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigoInterno").value(hasItem(DEFAULT_CODIGO_INTERNO.toString())))
            .andExpect(jsonPath("$.[*].ean").value(hasItem(DEFAULT_EAN.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].quantidade").value(hasItem(DEFAULT_QUANTIDADE.toString())))
            .andExpect(jsonPath("$.[*].preco").value(hasItem(DEFAULT_PRECO.toString())))
            .andExpect(jsonPath("$.[*].setor").value(hasItem(DEFAULT_SETOR.toString())))
            .andExpect(jsonPath("$.[*].departamento").value(hasItem(DEFAULT_DEPARTAMENTO.toString())))
            .andExpect(jsonPath("$.[*].precoCusto").value(hasItem(DEFAULT_PRECO_CUSTO.toString())))
            .andExpect(jsonPath("$.[*].posicional").value(hasItem(DEFAULT_POSICIONAL.booleanValue())))
            .andExpect(jsonPath("$.[*].delimitador").value(hasItem(DEFAULT_DELIMITADOR.toString())))
            .andExpect(jsonPath("$.[*].desconsiderarLinhaInicial").value(hasItem(DEFAULT_DESCONSIDERAR_LINHA_INICIAL)))
            .andExpect(jsonPath("$.[*].desconsiderarLinhaFinal").value(hasItem(DEFAULT_DESCONSIDERAR_LINHA_FINAL)))
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
            .andExpect(jsonPath("$.[*].intCustomizado1").value(hasItem(DEFAULT_INT_CUSTOMIZADO_1.toString())))
            .andExpect(jsonPath("$.[*].intCustomizado2").value(hasItem(DEFAULT_INT_CUSTOMIZADO_2.toString())))
            .andExpect(jsonPath("$.[*].intCustomizado3").value(hasItem(DEFAULT_INT_CUSTOMIZADO_3.toString())))
            .andExpect(jsonPath("$.[*].intCustomizado4").value(hasItem(DEFAULT_INT_CUSTOMIZADO_4.toString())))
            .andExpect(jsonPath("$.[*].intCustomizado5").value(hasItem(DEFAULT_INT_CUSTOMIZADO_5.toString())))
            .andExpect(jsonPath("$.[*].intCustomizado6").value(hasItem(DEFAULT_INT_CUSTOMIZADO_6.toString())))
            .andExpect(jsonPath("$.[*].intCustomizado7").value(hasItem(DEFAULT_INT_CUSTOMIZADO_7.toString())))
            .andExpect(jsonPath("$.[*].intCustomizado8").value(hasItem(DEFAULT_INT_CUSTOMIZADO_8.toString())))
            .andExpect(jsonPath("$.[*].intCustomizado9").value(hasItem(DEFAULT_INT_CUSTOMIZADO_9.toString())))
            .andExpect(jsonPath("$.[*].intCustomizado10").value(hasItem(DEFAULT_INT_CUSTOMIZADO_10.toString())))
            .andExpect(jsonPath("$.[*].binCustomizado1").value(hasItem(DEFAULT_BIN_CUSTOMIZADO_1.toString())))
            .andExpect(jsonPath("$.[*].binCustomizado2").value(hasItem(DEFAULT_BIN_CUSTOMIZADO_2.toString())))
            .andExpect(jsonPath("$.[*].binCustomizado3").value(hasItem(DEFAULT_BIN_CUSTOMIZADO_3.toString())));
    }

    @Test
    @Transactional
    public void getConfiguracao() throws Exception {
        // Initialize the database
        configuracaoRepository.saveAndFlush(configuracao);

        // Get the configuracao
        restConfiguracaoMockMvc.perform(get("/api/configuracaos/{id}", configuracao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(configuracao.getId().intValue()))
            .andExpect(jsonPath("$.codigoInterno").value(DEFAULT_CODIGO_INTERNO.toString()))
            .andExpect(jsonPath("$.ean").value(DEFAULT_EAN.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.quantidade").value(DEFAULT_QUANTIDADE.toString()))
            .andExpect(jsonPath("$.preco").value(DEFAULT_PRECO.toString()))
            .andExpect(jsonPath("$.setor").value(DEFAULT_SETOR.toString()))
            .andExpect(jsonPath("$.departamento").value(DEFAULT_DEPARTAMENTO.toString()))
            .andExpect(jsonPath("$.precoCusto").value(DEFAULT_PRECO_CUSTO.toString()))
            .andExpect(jsonPath("$.posicional").value(DEFAULT_POSICIONAL.booleanValue()))
            .andExpect(jsonPath("$.delimitador").value(DEFAULT_DELIMITADOR.toString()))
            .andExpect(jsonPath("$.desconsiderarLinhaInicial").value(DEFAULT_DESCONSIDERAR_LINHA_INICIAL))
            .andExpect(jsonPath("$.desconsiderarLinhaFinal").value(DEFAULT_DESCONSIDERAR_LINHA_FINAL))
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
            .andExpect(jsonPath("$.intCustomizado1").value(DEFAULT_INT_CUSTOMIZADO_1.toString()))
            .andExpect(jsonPath("$.intCustomizado2").value(DEFAULT_INT_CUSTOMIZADO_2.toString()))
            .andExpect(jsonPath("$.intCustomizado3").value(DEFAULT_INT_CUSTOMIZADO_3.toString()))
            .andExpect(jsonPath("$.intCustomizado4").value(DEFAULT_INT_CUSTOMIZADO_4.toString()))
            .andExpect(jsonPath("$.intCustomizado5").value(DEFAULT_INT_CUSTOMIZADO_5.toString()))
            .andExpect(jsonPath("$.intCustomizado6").value(DEFAULT_INT_CUSTOMIZADO_6.toString()))
            .andExpect(jsonPath("$.intCustomizado7").value(DEFAULT_INT_CUSTOMIZADO_7.toString()))
            .andExpect(jsonPath("$.intCustomizado8").value(DEFAULT_INT_CUSTOMIZADO_8.toString()))
            .andExpect(jsonPath("$.intCustomizado9").value(DEFAULT_INT_CUSTOMIZADO_9.toString()))
            .andExpect(jsonPath("$.intCustomizado10").value(DEFAULT_INT_CUSTOMIZADO_10.toString()))
            .andExpect(jsonPath("$.binCustomizado1").value(DEFAULT_BIN_CUSTOMIZADO_1.toString()))
            .andExpect(jsonPath("$.binCustomizado2").value(DEFAULT_BIN_CUSTOMIZADO_2.toString()))
            .andExpect(jsonPath("$.binCustomizado3").value(DEFAULT_BIN_CUSTOMIZADO_3.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingConfiguracao() throws Exception {
        // Get the configuracao
        restConfiguracaoMockMvc.perform(get("/api/configuracaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConfiguracao() throws Exception {
        // Initialize the database
        configuracaoRepository.saveAndFlush(configuracao);
        int databaseSizeBeforeUpdate = configuracaoRepository.findAll().size();

        // Update the configuracao
        Configuracao updatedConfiguracao = configuracaoRepository.findOne(configuracao.getId());
        // Disconnect from session so that the updates on updatedConfiguracao are not directly saved in db
        em.detach(updatedConfiguracao);
        updatedConfiguracao
            .codigoInterno(UPDATED_CODIGO_INTERNO)
            .ean(UPDATED_EAN)
            .descricao(UPDATED_DESCRICAO)
            .quantidade(UPDATED_QUANTIDADE)
            .preco(UPDATED_PRECO)
            .setor(UPDATED_SETOR)
            .departamento(UPDATED_DEPARTAMENTO)
            .precoCusto(UPDATED_PRECO_CUSTO)
            .posicional(UPDATED_POSICIONAL)
            .delimitador(UPDATED_DELIMITADOR)
            .desconsiderarLinhaInicial(UPDATED_DESCONSIDERAR_LINHA_INICIAL)
            .desconsiderarLinhaFinal(UPDATED_DESCONSIDERAR_LINHA_FINAL)
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

        restConfiguracaoMockMvc.perform(put("/api/configuracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConfiguracao)))
            .andExpect(status().isOk());

        // Validate the Configuracao in the database
        List<Configuracao> configuracaoList = configuracaoRepository.findAll();
        assertThat(configuracaoList).hasSize(databaseSizeBeforeUpdate);
        Configuracao testConfiguracao = configuracaoList.get(configuracaoList.size() - 1);
        assertThat(testConfiguracao.getCodigoInterno()).isEqualTo(UPDATED_CODIGO_INTERNO);
        assertThat(testConfiguracao.getEan()).isEqualTo(UPDATED_EAN);
        assertThat(testConfiguracao.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testConfiguracao.getQuantidade()).isEqualTo(UPDATED_QUANTIDADE);
        assertThat(testConfiguracao.getPreco()).isEqualTo(UPDATED_PRECO);
        assertThat(testConfiguracao.getSetor()).isEqualTo(UPDATED_SETOR);
        assertThat(testConfiguracao.getDepartamento()).isEqualTo(UPDATED_DEPARTAMENTO);
        assertThat(testConfiguracao.getPrecoCusto()).isEqualTo(UPDATED_PRECO_CUSTO);
        assertThat(testConfiguracao.isPosicional()).isEqualTo(UPDATED_POSICIONAL);
        assertThat(testConfiguracao.getDelimitador()).isEqualTo(UPDATED_DELIMITADOR);
        assertThat(testConfiguracao.getDesconsiderarLinhaInicial()).isEqualTo(UPDATED_DESCONSIDERAR_LINHA_INICIAL);
        assertThat(testConfiguracao.getDesconsiderarLinhaFinal()).isEqualTo(UPDATED_DESCONSIDERAR_LINHA_FINAL);
        assertThat(testConfiguracao.getStringCustomizado1()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_1);
        assertThat(testConfiguracao.getStringCustomizado2()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_2);
        assertThat(testConfiguracao.getStringCustomizado3()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_3);
        assertThat(testConfiguracao.getStringCustomizado4()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_4);
        assertThat(testConfiguracao.getStringCustomizado5()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_5);
        assertThat(testConfiguracao.getStringCustomizado6()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_6);
        assertThat(testConfiguracao.getStringCustomizado7()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_7);
        assertThat(testConfiguracao.getStringCustomizado8()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_8);
        assertThat(testConfiguracao.getStringCustomizado9()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_9);
        assertThat(testConfiguracao.getStringCustomizado10()).isEqualTo(UPDATED_STRING_CUSTOMIZADO_10);
        assertThat(testConfiguracao.getIntCustomizado1()).isEqualTo(UPDATED_INT_CUSTOMIZADO_1);
        assertThat(testConfiguracao.getIntCustomizado2()).isEqualTo(UPDATED_INT_CUSTOMIZADO_2);
        assertThat(testConfiguracao.getIntCustomizado3()).isEqualTo(UPDATED_INT_CUSTOMIZADO_3);
        assertThat(testConfiguracao.getIntCustomizado4()).isEqualTo(UPDATED_INT_CUSTOMIZADO_4);
        assertThat(testConfiguracao.getIntCustomizado5()).isEqualTo(UPDATED_INT_CUSTOMIZADO_5);
        assertThat(testConfiguracao.getIntCustomizado6()).isEqualTo(UPDATED_INT_CUSTOMIZADO_6);
        assertThat(testConfiguracao.getIntCustomizado7()).isEqualTo(UPDATED_INT_CUSTOMIZADO_7);
        assertThat(testConfiguracao.getIntCustomizado8()).isEqualTo(UPDATED_INT_CUSTOMIZADO_8);
        assertThat(testConfiguracao.getIntCustomizado9()).isEqualTo(UPDATED_INT_CUSTOMIZADO_9);
        assertThat(testConfiguracao.getIntCustomizado10()).isEqualTo(UPDATED_INT_CUSTOMIZADO_10);
        assertThat(testConfiguracao.getBinCustomizado1()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_1);
        assertThat(testConfiguracao.getBinCustomizado2()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_2);
        assertThat(testConfiguracao.getBinCustomizado3()).isEqualTo(UPDATED_BIN_CUSTOMIZADO_3);
    }

    @Test
    @Transactional
    public void updateNonExistingConfiguracao() throws Exception {
        int databaseSizeBeforeUpdate = configuracaoRepository.findAll().size();

        // Create the Configuracao

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConfiguracaoMockMvc.perform(put("/api/configuracaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracao)))
            .andExpect(status().isCreated());

        // Validate the Configuracao in the database
        List<Configuracao> configuracaoList = configuracaoRepository.findAll();
        assertThat(configuracaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteConfiguracao() throws Exception {
        // Initialize the database
        configuracaoRepository.saveAndFlush(configuracao);
        int databaseSizeBeforeDelete = configuracaoRepository.findAll().size();

        // Get the configuracao
        restConfiguracaoMockMvc.perform(delete("/api/configuracaos/{id}", configuracao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Configuracao> configuracaoList = configuracaoRepository.findAll();
        assertThat(configuracaoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Configuracao.class);
        Configuracao configuracao1 = new Configuracao();
        configuracao1.setId(1L);
        Configuracao configuracao2 = new Configuracao();
        configuracao2.setId(configuracao1.getId());
        assertThat(configuracao1).isEqualTo(configuracao2);
        configuracao2.setId(2L);
        assertThat(configuracao1).isNotEqualTo(configuracao2);
        configuracao1.setId(null);
        assertThat(configuracao1).isNotEqualTo(configuracao2);
    }
}
