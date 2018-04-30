package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Inventario;
import io.github.jhipster.application.repository.InventarioRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the InventarioResource REST controller.
 *
 * @see InventarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class InventarioResourceIntTest {

    private static final String DEFAULT_NUMERO_LOJA = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_LOJA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_INVENTARIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_INVENTARIO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ORDEM_SERVICO = "AAAAAAAAAA";
    private static final String UPDATED_ORDEM_SERVICO = "BBBBBBBBBB";

    private static final String DEFAULT_GERENTE_LOJA = "AAAAAAAAAA";
    private static final String UPDATED_GERENTE_LOJA = "BBBBBBBBBB";

    private static final String DEFAULT_LIDER_INVENTARIO = "AAAAAAAAAA";
    private static final String UPDATED_LIDER_INVENTARIO = "BBBBBBBBBB";

    private static final Integer DEFAULT_QTD_PESSOAS = 1;
    private static final Integer UPDATED_QTD_PESSOAS = 2;

    private static final String DEFAULT_NOME_LOJA = "AAAAAAAAAA";
    private static final String UPDATED_NOME_LOJA = "BBBBBBBBBB";

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInventarioMockMvc;

    private Inventario inventario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InventarioResource inventarioResource = new InventarioResource(inventarioRepository);
        this.restInventarioMockMvc = MockMvcBuilders.standaloneSetup(inventarioResource)
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
    public static Inventario createEntity(EntityManager em) {
        Inventario inventario = new Inventario()
            .numeroLoja(DEFAULT_NUMERO_LOJA)
            .dataInventario(DEFAULT_DATA_INVENTARIO)
            .ordemServico(DEFAULT_ORDEM_SERVICO)
            .gerenteLoja(DEFAULT_GERENTE_LOJA)
            .liderInventario(DEFAULT_LIDER_INVENTARIO)
            .qtdPessoas(DEFAULT_QTD_PESSOAS)
            .nomeLoja(DEFAULT_NOME_LOJA);
        return inventario;
    }

    @Before
    public void initTest() {
        inventario = createEntity(em);
    }

    @Test
    @Transactional
    public void createInventario() throws Exception {
        int databaseSizeBeforeCreate = inventarioRepository.findAll().size();

        // Create the Inventario
        restInventarioMockMvc.perform(post("/api/inventarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inventario)))
            .andExpect(status().isCreated());

        // Validate the Inventario in the database
        List<Inventario> inventarioList = inventarioRepository.findAll();
        assertThat(inventarioList).hasSize(databaseSizeBeforeCreate + 1);
        Inventario testInventario = inventarioList.get(inventarioList.size() - 1);
        assertThat(testInventario.getNumeroLoja()).isEqualTo(DEFAULT_NUMERO_LOJA);
        assertThat(testInventario.getDataInventario()).isEqualTo(DEFAULT_DATA_INVENTARIO);
        assertThat(testInventario.getOrdemServico()).isEqualTo(DEFAULT_ORDEM_SERVICO);
        assertThat(testInventario.getGerenteLoja()).isEqualTo(DEFAULT_GERENTE_LOJA);
        assertThat(testInventario.getLiderInventario()).isEqualTo(DEFAULT_LIDER_INVENTARIO);
        assertThat(testInventario.getQtdPessoas()).isEqualTo(DEFAULT_QTD_PESSOAS);
        assertThat(testInventario.getNomeLoja()).isEqualTo(DEFAULT_NOME_LOJA);
    }

    @Test
    @Transactional
    public void createInventarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = inventarioRepository.findAll().size();

        // Create the Inventario with an existing ID
        inventario.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInventarioMockMvc.perform(post("/api/inventarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inventario)))
            .andExpect(status().isBadRequest());

        // Validate the Inventario in the database
        List<Inventario> inventarioList = inventarioRepository.findAll();
        assertThat(inventarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInventarios() throws Exception {
        // Initialize the database
        inventarioRepository.saveAndFlush(inventario);

        // Get all the inventarioList
        restInventarioMockMvc.perform(get("/api/inventarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inventario.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroLoja").value(hasItem(DEFAULT_NUMERO_LOJA.toString())))
            .andExpect(jsonPath("$.[*].dataInventario").value(hasItem(DEFAULT_DATA_INVENTARIO.toString())))
            .andExpect(jsonPath("$.[*].ordemServico").value(hasItem(DEFAULT_ORDEM_SERVICO.toString())))
            .andExpect(jsonPath("$.[*].gerenteLoja").value(hasItem(DEFAULT_GERENTE_LOJA.toString())))
            .andExpect(jsonPath("$.[*].liderInventario").value(hasItem(DEFAULT_LIDER_INVENTARIO.toString())))
            .andExpect(jsonPath("$.[*].qtdPessoas").value(hasItem(DEFAULT_QTD_PESSOAS)))
            .andExpect(jsonPath("$.[*].nomeLoja").value(hasItem(DEFAULT_NOME_LOJA.toString())));
    }

    @Test
    @Transactional
    public void getInventario() throws Exception {
        // Initialize the database
        inventarioRepository.saveAndFlush(inventario);

        // Get the inventario
        restInventarioMockMvc.perform(get("/api/inventarios/{id}", inventario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(inventario.getId().intValue()))
            .andExpect(jsonPath("$.numeroLoja").value(DEFAULT_NUMERO_LOJA.toString()))
            .andExpect(jsonPath("$.dataInventario").value(DEFAULT_DATA_INVENTARIO.toString()))
            .andExpect(jsonPath("$.ordemServico").value(DEFAULT_ORDEM_SERVICO.toString()))
            .andExpect(jsonPath("$.gerenteLoja").value(DEFAULT_GERENTE_LOJA.toString()))
            .andExpect(jsonPath("$.liderInventario").value(DEFAULT_LIDER_INVENTARIO.toString()))
            .andExpect(jsonPath("$.qtdPessoas").value(DEFAULT_QTD_PESSOAS))
            .andExpect(jsonPath("$.nomeLoja").value(DEFAULT_NOME_LOJA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInventario() throws Exception {
        // Get the inventario
        restInventarioMockMvc.perform(get("/api/inventarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInventario() throws Exception {
        // Initialize the database
        inventarioRepository.saveAndFlush(inventario);
        int databaseSizeBeforeUpdate = inventarioRepository.findAll().size();

        // Update the inventario
        Inventario updatedInventario = inventarioRepository.findOne(inventario.getId());
        // Disconnect from session so that the updates on updatedInventario are not directly saved in db
        em.detach(updatedInventario);
        updatedInventario
            .numeroLoja(UPDATED_NUMERO_LOJA)
            .dataInventario(UPDATED_DATA_INVENTARIO)
            .ordemServico(UPDATED_ORDEM_SERVICO)
            .gerenteLoja(UPDATED_GERENTE_LOJA)
            .liderInventario(UPDATED_LIDER_INVENTARIO)
            .qtdPessoas(UPDATED_QTD_PESSOAS)
            .nomeLoja(UPDATED_NOME_LOJA);

        restInventarioMockMvc.perform(put("/api/inventarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedInventario)))
            .andExpect(status().isOk());

        // Validate the Inventario in the database
        List<Inventario> inventarioList = inventarioRepository.findAll();
        assertThat(inventarioList).hasSize(databaseSizeBeforeUpdate);
        Inventario testInventario = inventarioList.get(inventarioList.size() - 1);
        assertThat(testInventario.getNumeroLoja()).isEqualTo(UPDATED_NUMERO_LOJA);
        assertThat(testInventario.getDataInventario()).isEqualTo(UPDATED_DATA_INVENTARIO);
        assertThat(testInventario.getOrdemServico()).isEqualTo(UPDATED_ORDEM_SERVICO);
        assertThat(testInventario.getGerenteLoja()).isEqualTo(UPDATED_GERENTE_LOJA);
        assertThat(testInventario.getLiderInventario()).isEqualTo(UPDATED_LIDER_INVENTARIO);
        assertThat(testInventario.getQtdPessoas()).isEqualTo(UPDATED_QTD_PESSOAS);
        assertThat(testInventario.getNomeLoja()).isEqualTo(UPDATED_NOME_LOJA);
    }

    @Test
    @Transactional
    public void updateNonExistingInventario() throws Exception {
        int databaseSizeBeforeUpdate = inventarioRepository.findAll().size();

        // Create the Inventario

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInventarioMockMvc.perform(put("/api/inventarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inventario)))
            .andExpect(status().isCreated());

        // Validate the Inventario in the database
        List<Inventario> inventarioList = inventarioRepository.findAll();
        assertThat(inventarioList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteInventario() throws Exception {
        // Initialize the database
        inventarioRepository.saveAndFlush(inventario);
        int databaseSizeBeforeDelete = inventarioRepository.findAll().size();

        // Get the inventario
        restInventarioMockMvc.perform(delete("/api/inventarios/{id}", inventario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Inventario> inventarioList = inventarioRepository.findAll();
        assertThat(inventarioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Inventario.class);
        Inventario inventario1 = new Inventario();
        inventario1.setId(1L);
        Inventario inventario2 = new Inventario();
        inventario2.setId(inventario1.getId());
        assertThat(inventario1).isEqualTo(inventario2);
        inventario2.setId(2L);
        assertThat(inventario1).isNotEqualTo(inventario2);
        inventario1.setId(null);
        assertThat(inventario1).isNotEqualTo(inventario2);
    }
}
