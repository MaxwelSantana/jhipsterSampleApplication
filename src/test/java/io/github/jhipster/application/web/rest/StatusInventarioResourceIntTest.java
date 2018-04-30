package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.StatusInventario;
import io.github.jhipster.application.repository.StatusInventarioRepository;
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
 * Test class for the StatusInventarioResource REST controller.
 *
 * @see StatusInventarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class StatusInventarioResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private StatusInventarioRepository statusInventarioRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStatusInventarioMockMvc;

    private StatusInventario statusInventario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StatusInventarioResource statusInventarioResource = new StatusInventarioResource(statusInventarioRepository);
        this.restStatusInventarioMockMvc = MockMvcBuilders.standaloneSetup(statusInventarioResource)
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
    public static StatusInventario createEntity(EntityManager em) {
        StatusInventario statusInventario = new StatusInventario()
            .descricao(DEFAULT_DESCRICAO);
        return statusInventario;
    }

    @Before
    public void initTest() {
        statusInventario = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatusInventario() throws Exception {
        int databaseSizeBeforeCreate = statusInventarioRepository.findAll().size();

        // Create the StatusInventario
        restStatusInventarioMockMvc.perform(post("/api/status-inventarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusInventario)))
            .andExpect(status().isCreated());

        // Validate the StatusInventario in the database
        List<StatusInventario> statusInventarioList = statusInventarioRepository.findAll();
        assertThat(statusInventarioList).hasSize(databaseSizeBeforeCreate + 1);
        StatusInventario testStatusInventario = statusInventarioList.get(statusInventarioList.size() - 1);
        assertThat(testStatusInventario.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createStatusInventarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statusInventarioRepository.findAll().size();

        // Create the StatusInventario with an existing ID
        statusInventario.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatusInventarioMockMvc.perform(post("/api/status-inventarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusInventario)))
            .andExpect(status().isBadRequest());

        // Validate the StatusInventario in the database
        List<StatusInventario> statusInventarioList = statusInventarioRepository.findAll();
        assertThat(statusInventarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStatusInventarios() throws Exception {
        // Initialize the database
        statusInventarioRepository.saveAndFlush(statusInventario);

        // Get all the statusInventarioList
        restStatusInventarioMockMvc.perform(get("/api/status-inventarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statusInventario.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }

    @Test
    @Transactional
    public void getStatusInventario() throws Exception {
        // Initialize the database
        statusInventarioRepository.saveAndFlush(statusInventario);

        // Get the statusInventario
        restStatusInventarioMockMvc.perform(get("/api/status-inventarios/{id}", statusInventario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(statusInventario.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStatusInventario() throws Exception {
        // Get the statusInventario
        restStatusInventarioMockMvc.perform(get("/api/status-inventarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatusInventario() throws Exception {
        // Initialize the database
        statusInventarioRepository.saveAndFlush(statusInventario);
        int databaseSizeBeforeUpdate = statusInventarioRepository.findAll().size();

        // Update the statusInventario
        StatusInventario updatedStatusInventario = statusInventarioRepository.findOne(statusInventario.getId());
        // Disconnect from session so that the updates on updatedStatusInventario are not directly saved in db
        em.detach(updatedStatusInventario);
        updatedStatusInventario
            .descricao(UPDATED_DESCRICAO);

        restStatusInventarioMockMvc.perform(put("/api/status-inventarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStatusInventario)))
            .andExpect(status().isOk());

        // Validate the StatusInventario in the database
        List<StatusInventario> statusInventarioList = statusInventarioRepository.findAll();
        assertThat(statusInventarioList).hasSize(databaseSizeBeforeUpdate);
        StatusInventario testStatusInventario = statusInventarioList.get(statusInventarioList.size() - 1);
        assertThat(testStatusInventario.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingStatusInventario() throws Exception {
        int databaseSizeBeforeUpdate = statusInventarioRepository.findAll().size();

        // Create the StatusInventario

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStatusInventarioMockMvc.perform(put("/api/status-inventarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusInventario)))
            .andExpect(status().isCreated());

        // Validate the StatusInventario in the database
        List<StatusInventario> statusInventarioList = statusInventarioRepository.findAll();
        assertThat(statusInventarioList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteStatusInventario() throws Exception {
        // Initialize the database
        statusInventarioRepository.saveAndFlush(statusInventario);
        int databaseSizeBeforeDelete = statusInventarioRepository.findAll().size();

        // Get the statusInventario
        restStatusInventarioMockMvc.perform(delete("/api/status-inventarios/{id}", statusInventario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StatusInventario> statusInventarioList = statusInventarioRepository.findAll();
        assertThat(statusInventarioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatusInventario.class);
        StatusInventario statusInventario1 = new StatusInventario();
        statusInventario1.setId(1L);
        StatusInventario statusInventario2 = new StatusInventario();
        statusInventario2.setId(statusInventario1.getId());
        assertThat(statusInventario1).isEqualTo(statusInventario2);
        statusInventario2.setId(2L);
        assertThat(statusInventario1).isNotEqualTo(statusInventario2);
        statusInventario1.setId(null);
        assertThat(statusInventario1).isNotEqualTo(statusInventario2);
    }
}
