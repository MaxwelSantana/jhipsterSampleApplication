package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Mapeamento;
import io.github.jhipster.application.repository.MapeamentoRepository;
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
 * Test class for the MapeamentoResource REST controller.
 *
 * @see MapeamentoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class MapeamentoResourceIntTest {

    private static final String DEFAULT_SECAO_INICIAL = "AAAAAAAAAA";
    private static final String UPDATED_SECAO_INICIAL = "BBBBBBBBBB";

    private static final String DEFAULT_SECAO_FINAL = "AAAAAAAAAA";
    private static final String UPDATED_SECAO_FINAL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DEPOSITO = false;
    private static final Boolean UPDATED_DEPOSITO = true;

    private static final Boolean DEFAULT_LOJA = false;
    private static final Boolean UPDATED_LOJA = true;

    private static final Integer DEFAULT_INICIAL_INT = 1;
    private static final Integer UPDATED_INICIAL_INT = 2;

    private static final Integer DEFAULT_FINAL_INT = 1;
    private static final Integer UPDATED_FINAL_INT = 2;

    @Autowired
    private MapeamentoRepository mapeamentoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMapeamentoMockMvc;

    private Mapeamento mapeamento;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MapeamentoResource mapeamentoResource = new MapeamentoResource(mapeamentoRepository);
        this.restMapeamentoMockMvc = MockMvcBuilders.standaloneSetup(mapeamentoResource)
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
    public static Mapeamento createEntity(EntityManager em) {
        Mapeamento mapeamento = new Mapeamento()
            .secaoInicial(DEFAULT_SECAO_INICIAL)
            .secaoFinal(DEFAULT_SECAO_FINAL)
            .descricao(DEFAULT_DESCRICAO)
            .deposito(DEFAULT_DEPOSITO)
            .loja(DEFAULT_LOJA)
            .inicialInt(DEFAULT_INICIAL_INT)
            .finalInt(DEFAULT_FINAL_INT);
        return mapeamento;
    }

    @Before
    public void initTest() {
        mapeamento = createEntity(em);
    }

    @Test
    @Transactional
    public void createMapeamento() throws Exception {
        int databaseSizeBeforeCreate = mapeamentoRepository.findAll().size();

        // Create the Mapeamento
        restMapeamentoMockMvc.perform(post("/api/mapeamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapeamento)))
            .andExpect(status().isCreated());

        // Validate the Mapeamento in the database
        List<Mapeamento> mapeamentoList = mapeamentoRepository.findAll();
        assertThat(mapeamentoList).hasSize(databaseSizeBeforeCreate + 1);
        Mapeamento testMapeamento = mapeamentoList.get(mapeamentoList.size() - 1);
        assertThat(testMapeamento.getSecaoInicial()).isEqualTo(DEFAULT_SECAO_INICIAL);
        assertThat(testMapeamento.getSecaoFinal()).isEqualTo(DEFAULT_SECAO_FINAL);
        assertThat(testMapeamento.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testMapeamento.isDeposito()).isEqualTo(DEFAULT_DEPOSITO);
        assertThat(testMapeamento.isLoja()).isEqualTo(DEFAULT_LOJA);
        assertThat(testMapeamento.getInicialInt()).isEqualTo(DEFAULT_INICIAL_INT);
        assertThat(testMapeamento.getFinalInt()).isEqualTo(DEFAULT_FINAL_INT);
    }

    @Test
    @Transactional
    public void createMapeamentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mapeamentoRepository.findAll().size();

        // Create the Mapeamento with an existing ID
        mapeamento.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMapeamentoMockMvc.perform(post("/api/mapeamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapeamento)))
            .andExpect(status().isBadRequest());

        // Validate the Mapeamento in the database
        List<Mapeamento> mapeamentoList = mapeamentoRepository.findAll();
        assertThat(mapeamentoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMapeamentos() throws Exception {
        // Initialize the database
        mapeamentoRepository.saveAndFlush(mapeamento);

        // Get all the mapeamentoList
        restMapeamentoMockMvc.perform(get("/api/mapeamentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mapeamento.getId().intValue())))
            .andExpect(jsonPath("$.[*].secaoInicial").value(hasItem(DEFAULT_SECAO_INICIAL.toString())))
            .andExpect(jsonPath("$.[*].secaoFinal").value(hasItem(DEFAULT_SECAO_FINAL.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].deposito").value(hasItem(DEFAULT_DEPOSITO.booleanValue())))
            .andExpect(jsonPath("$.[*].loja").value(hasItem(DEFAULT_LOJA.booleanValue())))
            .andExpect(jsonPath("$.[*].inicialInt").value(hasItem(DEFAULT_INICIAL_INT)))
            .andExpect(jsonPath("$.[*].finalInt").value(hasItem(DEFAULT_FINAL_INT)));
    }

    @Test
    @Transactional
    public void getMapeamento() throws Exception {
        // Initialize the database
        mapeamentoRepository.saveAndFlush(mapeamento);

        // Get the mapeamento
        restMapeamentoMockMvc.perform(get("/api/mapeamentos/{id}", mapeamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mapeamento.getId().intValue()))
            .andExpect(jsonPath("$.secaoInicial").value(DEFAULT_SECAO_INICIAL.toString()))
            .andExpect(jsonPath("$.secaoFinal").value(DEFAULT_SECAO_FINAL.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.deposito").value(DEFAULT_DEPOSITO.booleanValue()))
            .andExpect(jsonPath("$.loja").value(DEFAULT_LOJA.booleanValue()))
            .andExpect(jsonPath("$.inicialInt").value(DEFAULT_INICIAL_INT))
            .andExpect(jsonPath("$.finalInt").value(DEFAULT_FINAL_INT));
    }

    @Test
    @Transactional
    public void getNonExistingMapeamento() throws Exception {
        // Get the mapeamento
        restMapeamentoMockMvc.perform(get("/api/mapeamentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMapeamento() throws Exception {
        // Initialize the database
        mapeamentoRepository.saveAndFlush(mapeamento);
        int databaseSizeBeforeUpdate = mapeamentoRepository.findAll().size();

        // Update the mapeamento
        Mapeamento updatedMapeamento = mapeamentoRepository.findOne(mapeamento.getId());
        // Disconnect from session so that the updates on updatedMapeamento are not directly saved in db
        em.detach(updatedMapeamento);
        updatedMapeamento
            .secaoInicial(UPDATED_SECAO_INICIAL)
            .secaoFinal(UPDATED_SECAO_FINAL)
            .descricao(UPDATED_DESCRICAO)
            .deposito(UPDATED_DEPOSITO)
            .loja(UPDATED_LOJA)
            .inicialInt(UPDATED_INICIAL_INT)
            .finalInt(UPDATED_FINAL_INT);

        restMapeamentoMockMvc.perform(put("/api/mapeamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMapeamento)))
            .andExpect(status().isOk());

        // Validate the Mapeamento in the database
        List<Mapeamento> mapeamentoList = mapeamentoRepository.findAll();
        assertThat(mapeamentoList).hasSize(databaseSizeBeforeUpdate);
        Mapeamento testMapeamento = mapeamentoList.get(mapeamentoList.size() - 1);
        assertThat(testMapeamento.getSecaoInicial()).isEqualTo(UPDATED_SECAO_INICIAL);
        assertThat(testMapeamento.getSecaoFinal()).isEqualTo(UPDATED_SECAO_FINAL);
        assertThat(testMapeamento.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testMapeamento.isDeposito()).isEqualTo(UPDATED_DEPOSITO);
        assertThat(testMapeamento.isLoja()).isEqualTo(UPDATED_LOJA);
        assertThat(testMapeamento.getInicialInt()).isEqualTo(UPDATED_INICIAL_INT);
        assertThat(testMapeamento.getFinalInt()).isEqualTo(UPDATED_FINAL_INT);
    }

    @Test
    @Transactional
    public void updateNonExistingMapeamento() throws Exception {
        int databaseSizeBeforeUpdate = mapeamentoRepository.findAll().size();

        // Create the Mapeamento

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMapeamentoMockMvc.perform(put("/api/mapeamentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mapeamento)))
            .andExpect(status().isCreated());

        // Validate the Mapeamento in the database
        List<Mapeamento> mapeamentoList = mapeamentoRepository.findAll();
        assertThat(mapeamentoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMapeamento() throws Exception {
        // Initialize the database
        mapeamentoRepository.saveAndFlush(mapeamento);
        int databaseSizeBeforeDelete = mapeamentoRepository.findAll().size();

        // Get the mapeamento
        restMapeamentoMockMvc.perform(delete("/api/mapeamentos/{id}", mapeamento.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Mapeamento> mapeamentoList = mapeamentoRepository.findAll();
        assertThat(mapeamentoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mapeamento.class);
        Mapeamento mapeamento1 = new Mapeamento();
        mapeamento1.setId(1L);
        Mapeamento mapeamento2 = new Mapeamento();
        mapeamento2.setId(mapeamento1.getId());
        assertThat(mapeamento1).isEqualTo(mapeamento2);
        mapeamento2.setId(2L);
        assertThat(mapeamento1).isNotEqualTo(mapeamento2);
        mapeamento1.setId(null);
        assertThat(mapeamento1).isNotEqualTo(mapeamento2);
    }
}
