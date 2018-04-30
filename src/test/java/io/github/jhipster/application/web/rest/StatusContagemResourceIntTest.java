package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.StatusContagem;
import io.github.jhipster.application.repository.StatusContagemRepository;
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
 * Test class for the StatusContagemResource REST controller.
 *
 * @see StatusContagemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class StatusContagemResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private StatusContagemRepository statusContagemRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStatusContagemMockMvc;

    private StatusContagem statusContagem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StatusContagemResource statusContagemResource = new StatusContagemResource(statusContagemRepository);
        this.restStatusContagemMockMvc = MockMvcBuilders.standaloneSetup(statusContagemResource)
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
    public static StatusContagem createEntity(EntityManager em) {
        StatusContagem statusContagem = new StatusContagem()
            .descricao(DEFAULT_DESCRICAO);
        return statusContagem;
    }

    @Before
    public void initTest() {
        statusContagem = createEntity(em);
    }

    @Test
    @Transactional
    public void createStatusContagem() throws Exception {
        int databaseSizeBeforeCreate = statusContagemRepository.findAll().size();

        // Create the StatusContagem
        restStatusContagemMockMvc.perform(post("/api/status-contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusContagem)))
            .andExpect(status().isCreated());

        // Validate the StatusContagem in the database
        List<StatusContagem> statusContagemList = statusContagemRepository.findAll();
        assertThat(statusContagemList).hasSize(databaseSizeBeforeCreate + 1);
        StatusContagem testStatusContagem = statusContagemList.get(statusContagemList.size() - 1);
        assertThat(testStatusContagem.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createStatusContagemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = statusContagemRepository.findAll().size();

        // Create the StatusContagem with an existing ID
        statusContagem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStatusContagemMockMvc.perform(post("/api/status-contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusContagem)))
            .andExpect(status().isBadRequest());

        // Validate the StatusContagem in the database
        List<StatusContagem> statusContagemList = statusContagemRepository.findAll();
        assertThat(statusContagemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStatusContagems() throws Exception {
        // Initialize the database
        statusContagemRepository.saveAndFlush(statusContagem);

        // Get all the statusContagemList
        restStatusContagemMockMvc.perform(get("/api/status-contagems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(statusContagem.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())));
    }

    @Test
    @Transactional
    public void getStatusContagem() throws Exception {
        // Initialize the database
        statusContagemRepository.saveAndFlush(statusContagem);

        // Get the statusContagem
        restStatusContagemMockMvc.perform(get("/api/status-contagems/{id}", statusContagem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(statusContagem.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStatusContagem() throws Exception {
        // Get the statusContagem
        restStatusContagemMockMvc.perform(get("/api/status-contagems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatusContagem() throws Exception {
        // Initialize the database
        statusContagemRepository.saveAndFlush(statusContagem);
        int databaseSizeBeforeUpdate = statusContagemRepository.findAll().size();

        // Update the statusContagem
        StatusContagem updatedStatusContagem = statusContagemRepository.findOne(statusContagem.getId());
        // Disconnect from session so that the updates on updatedStatusContagem are not directly saved in db
        em.detach(updatedStatusContagem);
        updatedStatusContagem
            .descricao(UPDATED_DESCRICAO);

        restStatusContagemMockMvc.perform(put("/api/status-contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStatusContagem)))
            .andExpect(status().isOk());

        // Validate the StatusContagem in the database
        List<StatusContagem> statusContagemList = statusContagemRepository.findAll();
        assertThat(statusContagemList).hasSize(databaseSizeBeforeUpdate);
        StatusContagem testStatusContagem = statusContagemList.get(statusContagemList.size() - 1);
        assertThat(testStatusContagem.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingStatusContagem() throws Exception {
        int databaseSizeBeforeUpdate = statusContagemRepository.findAll().size();

        // Create the StatusContagem

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStatusContagemMockMvc.perform(put("/api/status-contagems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(statusContagem)))
            .andExpect(status().isCreated());

        // Validate the StatusContagem in the database
        List<StatusContagem> statusContagemList = statusContagemRepository.findAll();
        assertThat(statusContagemList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteStatusContagem() throws Exception {
        // Initialize the database
        statusContagemRepository.saveAndFlush(statusContagem);
        int databaseSizeBeforeDelete = statusContagemRepository.findAll().size();

        // Get the statusContagem
        restStatusContagemMockMvc.perform(delete("/api/status-contagems/{id}", statusContagem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StatusContagem> statusContagemList = statusContagemRepository.findAll();
        assertThat(statusContagemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StatusContagem.class);
        StatusContagem statusContagem1 = new StatusContagem();
        statusContagem1.setId(1L);
        StatusContagem statusContagem2 = new StatusContagem();
        statusContagem2.setId(statusContagem1.getId());
        assertThat(statusContagem1).isEqualTo(statusContagem2);
        statusContagem2.setId(2L);
        assertThat(statusContagem1).isNotEqualTo(statusContagem2);
        statusContagem1.setId(null);
        assertThat(statusContagem1).isNotEqualTo(statusContagem2);
    }
}
