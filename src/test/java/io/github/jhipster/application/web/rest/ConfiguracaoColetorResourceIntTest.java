package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.ConfiguracaoColetor;
import io.github.jhipster.application.repository.ConfiguracaoColetorRepository;
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
 * Test class for the ConfiguracaoColetorResource REST controller.
 *
 * @see ConfiguracaoColetorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ConfiguracaoColetorResourceIntTest {

    @Autowired
    private ConfiguracaoColetorRepository configuracaoColetorRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConfiguracaoColetorMockMvc;

    private ConfiguracaoColetor configuracaoColetor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConfiguracaoColetorResource configuracaoColetorResource = new ConfiguracaoColetorResource(configuracaoColetorRepository);
        this.restConfiguracaoColetorMockMvc = MockMvcBuilders.standaloneSetup(configuracaoColetorResource)
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
    public static ConfiguracaoColetor createEntity(EntityManager em) {
        ConfiguracaoColetor configuracaoColetor = new ConfiguracaoColetor();
        return configuracaoColetor;
    }

    @Before
    public void initTest() {
        configuracaoColetor = createEntity(em);
    }

    @Test
    @Transactional
    public void createConfiguracaoColetor() throws Exception {
        int databaseSizeBeforeCreate = configuracaoColetorRepository.findAll().size();

        // Create the ConfiguracaoColetor
        restConfiguracaoColetorMockMvc.perform(post("/api/configuracao-coletors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracaoColetor)))
            .andExpect(status().isCreated());

        // Validate the ConfiguracaoColetor in the database
        List<ConfiguracaoColetor> configuracaoColetorList = configuracaoColetorRepository.findAll();
        assertThat(configuracaoColetorList).hasSize(databaseSizeBeforeCreate + 1);
        ConfiguracaoColetor testConfiguracaoColetor = configuracaoColetorList.get(configuracaoColetorList.size() - 1);
    }

    @Test
    @Transactional
    public void createConfiguracaoColetorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = configuracaoColetorRepository.findAll().size();

        // Create the ConfiguracaoColetor with an existing ID
        configuracaoColetor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfiguracaoColetorMockMvc.perform(post("/api/configuracao-coletors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracaoColetor)))
            .andExpect(status().isBadRequest());

        // Validate the ConfiguracaoColetor in the database
        List<ConfiguracaoColetor> configuracaoColetorList = configuracaoColetorRepository.findAll();
        assertThat(configuracaoColetorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllConfiguracaoColetors() throws Exception {
        // Initialize the database
        configuracaoColetorRepository.saveAndFlush(configuracaoColetor);

        // Get all the configuracaoColetorList
        restConfiguracaoColetorMockMvc.perform(get("/api/configuracao-coletors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(configuracaoColetor.getId().intValue())));
    }

    @Test
    @Transactional
    public void getConfiguracaoColetor() throws Exception {
        // Initialize the database
        configuracaoColetorRepository.saveAndFlush(configuracaoColetor);

        // Get the configuracaoColetor
        restConfiguracaoColetorMockMvc.perform(get("/api/configuracao-coletors/{id}", configuracaoColetor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(configuracaoColetor.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingConfiguracaoColetor() throws Exception {
        // Get the configuracaoColetor
        restConfiguracaoColetorMockMvc.perform(get("/api/configuracao-coletors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConfiguracaoColetor() throws Exception {
        // Initialize the database
        configuracaoColetorRepository.saveAndFlush(configuracaoColetor);
        int databaseSizeBeforeUpdate = configuracaoColetorRepository.findAll().size();

        // Update the configuracaoColetor
        ConfiguracaoColetor updatedConfiguracaoColetor = configuracaoColetorRepository.findOne(configuracaoColetor.getId());
        // Disconnect from session so that the updates on updatedConfiguracaoColetor are not directly saved in db
        em.detach(updatedConfiguracaoColetor);

        restConfiguracaoColetorMockMvc.perform(put("/api/configuracao-coletors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConfiguracaoColetor)))
            .andExpect(status().isOk());

        // Validate the ConfiguracaoColetor in the database
        List<ConfiguracaoColetor> configuracaoColetorList = configuracaoColetorRepository.findAll();
        assertThat(configuracaoColetorList).hasSize(databaseSizeBeforeUpdate);
        ConfiguracaoColetor testConfiguracaoColetor = configuracaoColetorList.get(configuracaoColetorList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingConfiguracaoColetor() throws Exception {
        int databaseSizeBeforeUpdate = configuracaoColetorRepository.findAll().size();

        // Create the ConfiguracaoColetor

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConfiguracaoColetorMockMvc.perform(put("/api/configuracao-coletors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuracaoColetor)))
            .andExpect(status().isCreated());

        // Validate the ConfiguracaoColetor in the database
        List<ConfiguracaoColetor> configuracaoColetorList = configuracaoColetorRepository.findAll();
        assertThat(configuracaoColetorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteConfiguracaoColetor() throws Exception {
        // Initialize the database
        configuracaoColetorRepository.saveAndFlush(configuracaoColetor);
        int databaseSizeBeforeDelete = configuracaoColetorRepository.findAll().size();

        // Get the configuracaoColetor
        restConfiguracaoColetorMockMvc.perform(delete("/api/configuracao-coletors/{id}", configuracaoColetor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConfiguracaoColetor> configuracaoColetorList = configuracaoColetorRepository.findAll();
        assertThat(configuracaoColetorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfiguracaoColetor.class);
        ConfiguracaoColetor configuracaoColetor1 = new ConfiguracaoColetor();
        configuracaoColetor1.setId(1L);
        ConfiguracaoColetor configuracaoColetor2 = new ConfiguracaoColetor();
        configuracaoColetor2.setId(configuracaoColetor1.getId());
        assertThat(configuracaoColetor1).isEqualTo(configuracaoColetor2);
        configuracaoColetor2.setId(2L);
        assertThat(configuracaoColetor1).isNotEqualTo(configuracaoColetor2);
        configuracaoColetor1.setId(null);
        assertThat(configuracaoColetor1).isNotEqualTo(configuracaoColetor2);
    }
}
