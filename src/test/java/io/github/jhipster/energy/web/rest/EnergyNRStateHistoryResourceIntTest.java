package io.github.jhipster.energy.web.rest;

import io.github.jhipster.energy.JhipsterEnergySampleApplicationApp;

import io.github.jhipster.energy.domain.EnergyNRStateHistory;
import io.github.jhipster.energy.repository.EnergyNRStateHistoryRepository;
import io.github.jhipster.energy.service.EnergyNRStateHistoryService;
import io.github.jhipster.energy.service.dto.EnergyNRStateHistoryDTO;
import io.github.jhipster.energy.service.mapper.EnergyNRStateHistoryMapper;
import io.github.jhipster.energy.web.rest.errors.ExceptionTranslator;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static io.github.jhipster.energy.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EnergyNRStateHistoryResource REST controller.
 *
 * @see EnergyNRStateHistoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterEnergySampleApplicationApp.class)
public class EnergyNRStateHistoryResourceIntTest {

    private static final String DEFAULT_ENERGY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ENERGY_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_JUSTIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_JUSTIFICATION = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_STATE_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_STATE_VALUE = "BBBBBBBBBB";

    @Autowired
    private EnergyNRStateHistoryRepository energyNRStateHistoryRepository;

    @Autowired
    private EnergyNRStateHistoryMapper energyNRStateHistoryMapper;

    @Autowired
    private EnergyNRStateHistoryService energyNRStateHistoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnergyNRStateHistoryMockMvc;

    private EnergyNRStateHistory energyNRStateHistory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnergyNRStateHistoryResource energyNRStateHistoryResource = new EnergyNRStateHistoryResource(energyNRStateHistoryService);
        this.restEnergyNRStateHistoryMockMvc = MockMvcBuilders.standaloneSetup(energyNRStateHistoryResource)
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
    public static EnergyNRStateHistory createEntity(EntityManager em) {
        EnergyNRStateHistory energyNRStateHistory = new EnergyNRStateHistory()
            .energyType(DEFAULT_ENERGY_TYPE)
            .justification(DEFAULT_JUSTIFICATION)
            .updateDate(DEFAULT_UPDATE_DATE)
            .stateValue(DEFAULT_STATE_VALUE);
        return energyNRStateHistory;
    }

    @Before
    public void initTest() {
        energyNRStateHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnergyNRStateHistory() throws Exception {
        int databaseSizeBeforeCreate = energyNRStateHistoryRepository.findAll().size();

        // Create the EnergyNRStateHistory
        EnergyNRStateHistoryDTO energyNRStateHistoryDTO = energyNRStateHistoryMapper.toDto(energyNRStateHistory);
        restEnergyNRStateHistoryMockMvc.perform(post("/api/energy-nr-state-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNRStateHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the EnergyNRStateHistory in the database
        List<EnergyNRStateHistory> energyNRStateHistoryList = energyNRStateHistoryRepository.findAll();
        assertThat(energyNRStateHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        EnergyNRStateHistory testEnergyNRStateHistory = energyNRStateHistoryList.get(energyNRStateHistoryList.size() - 1);
        assertThat(testEnergyNRStateHistory.getEnergyType()).isEqualTo(DEFAULT_ENERGY_TYPE);
        assertThat(testEnergyNRStateHistory.getJustification()).isEqualTo(DEFAULT_JUSTIFICATION);
        assertThat(testEnergyNRStateHistory.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testEnergyNRStateHistory.getStateValue()).isEqualTo(DEFAULT_STATE_VALUE);
    }

    @Test
    @Transactional
    public void createEnergyNRStateHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = energyNRStateHistoryRepository.findAll().size();

        // Create the EnergyNRStateHistory with an existing ID
        energyNRStateHistory.setId(1L);
        EnergyNRStateHistoryDTO energyNRStateHistoryDTO = energyNRStateHistoryMapper.toDto(energyNRStateHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnergyNRStateHistoryMockMvc.perform(post("/api/energy-nr-state-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNRStateHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyNRStateHistory in the database
        List<EnergyNRStateHistory> energyNRStateHistoryList = energyNRStateHistoryRepository.findAll();
        assertThat(energyNRStateHistoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEnergyTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyNRStateHistoryRepository.findAll().size();
        // set the field null
        energyNRStateHistory.setEnergyType(null);

        // Create the EnergyNRStateHistory, which fails.
        EnergyNRStateHistoryDTO energyNRStateHistoryDTO = energyNRStateHistoryMapper.toDto(energyNRStateHistory);

        restEnergyNRStateHistoryMockMvc.perform(post("/api/energy-nr-state-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNRStateHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<EnergyNRStateHistory> energyNRStateHistoryList = energyNRStateHistoryRepository.findAll();
        assertThat(energyNRStateHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkJustificationIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyNRStateHistoryRepository.findAll().size();
        // set the field null
        energyNRStateHistory.setJustification(null);

        // Create the EnergyNRStateHistory, which fails.
        EnergyNRStateHistoryDTO energyNRStateHistoryDTO = energyNRStateHistoryMapper.toDto(energyNRStateHistory);

        restEnergyNRStateHistoryMockMvc.perform(post("/api/energy-nr-state-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNRStateHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<EnergyNRStateHistory> energyNRStateHistoryList = energyNRStateHistoryRepository.findAll();
        assertThat(energyNRStateHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyNRStateHistoryRepository.findAll().size();
        // set the field null
        energyNRStateHistory.setUpdateDate(null);

        // Create the EnergyNRStateHistory, which fails.
        EnergyNRStateHistoryDTO energyNRStateHistoryDTO = energyNRStateHistoryMapper.toDto(energyNRStateHistory);

        restEnergyNRStateHistoryMockMvc.perform(post("/api/energy-nr-state-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNRStateHistoryDTO)))
            .andExpect(status().isBadRequest());

        List<EnergyNRStateHistory> energyNRStateHistoryList = energyNRStateHistoryRepository.findAll();
        assertThat(energyNRStateHistoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEnergyNRStateHistories() throws Exception {
        // Initialize the database
        energyNRStateHistoryRepository.saveAndFlush(energyNRStateHistory);

        // Get all the energyNRStateHistoryList
        restEnergyNRStateHistoryMockMvc.perform(get("/api/energy-nr-state-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(energyNRStateHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].energyType").value(hasItem(DEFAULT_ENERGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].justification").value(hasItem(DEFAULT_JUSTIFICATION.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].stateValue").value(hasItem(DEFAULT_STATE_VALUE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnergyNRStateHistory() throws Exception {
        // Initialize the database
        energyNRStateHistoryRepository.saveAndFlush(energyNRStateHistory);

        // Get the energyNRStateHistory
        restEnergyNRStateHistoryMockMvc.perform(get("/api/energy-nr-state-histories/{id}", energyNRStateHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(energyNRStateHistory.getId().intValue()))
            .andExpect(jsonPath("$.energyType").value(DEFAULT_ENERGY_TYPE.toString()))
            .andExpect(jsonPath("$.justification").value(DEFAULT_JUSTIFICATION.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.stateValue").value(DEFAULT_STATE_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnergyNRStateHistory() throws Exception {
        // Get the energyNRStateHistory
        restEnergyNRStateHistoryMockMvc.perform(get("/api/energy-nr-state-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnergyNRStateHistory() throws Exception {
        // Initialize the database
        energyNRStateHistoryRepository.saveAndFlush(energyNRStateHistory);

        int databaseSizeBeforeUpdate = energyNRStateHistoryRepository.findAll().size();

        // Update the energyNRStateHistory
        EnergyNRStateHistory updatedEnergyNRStateHistory = energyNRStateHistoryRepository.findById(energyNRStateHistory.getId()).get();
        // Disconnect from session so that the updates on updatedEnergyNRStateHistory are not directly saved in db
        em.detach(updatedEnergyNRStateHistory);
        updatedEnergyNRStateHistory
            .energyType(UPDATED_ENERGY_TYPE)
            .justification(UPDATED_JUSTIFICATION)
            .updateDate(UPDATED_UPDATE_DATE)
            .stateValue(UPDATED_STATE_VALUE);
        EnergyNRStateHistoryDTO energyNRStateHistoryDTO = energyNRStateHistoryMapper.toDto(updatedEnergyNRStateHistory);

        restEnergyNRStateHistoryMockMvc.perform(put("/api/energy-nr-state-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNRStateHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the EnergyNRStateHistory in the database
        List<EnergyNRStateHistory> energyNRStateHistoryList = energyNRStateHistoryRepository.findAll();
        assertThat(energyNRStateHistoryList).hasSize(databaseSizeBeforeUpdate);
        EnergyNRStateHistory testEnergyNRStateHistory = energyNRStateHistoryList.get(energyNRStateHistoryList.size() - 1);
        assertThat(testEnergyNRStateHistory.getEnergyType()).isEqualTo(UPDATED_ENERGY_TYPE);
        assertThat(testEnergyNRStateHistory.getJustification()).isEqualTo(UPDATED_JUSTIFICATION);
        assertThat(testEnergyNRStateHistory.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testEnergyNRStateHistory.getStateValue()).isEqualTo(UPDATED_STATE_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnergyNRStateHistory() throws Exception {
        int databaseSizeBeforeUpdate = energyNRStateHistoryRepository.findAll().size();

        // Create the EnergyNRStateHistory
        EnergyNRStateHistoryDTO energyNRStateHistoryDTO = energyNRStateHistoryMapper.toDto(energyNRStateHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnergyNRStateHistoryMockMvc.perform(put("/api/energy-nr-state-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNRStateHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyNRStateHistory in the database
        List<EnergyNRStateHistory> energyNRStateHistoryList = energyNRStateHistoryRepository.findAll();
        assertThat(energyNRStateHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnergyNRStateHistory() throws Exception {
        // Initialize the database
        energyNRStateHistoryRepository.saveAndFlush(energyNRStateHistory);

        int databaseSizeBeforeDelete = energyNRStateHistoryRepository.findAll().size();

        // Get the energyNRStateHistory
        restEnergyNRStateHistoryMockMvc.perform(delete("/api/energy-nr-state-histories/{id}", energyNRStateHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EnergyNRStateHistory> energyNRStateHistoryList = energyNRStateHistoryRepository.findAll();
        assertThat(energyNRStateHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyNRStateHistory.class);
        EnergyNRStateHistory energyNRStateHistory1 = new EnergyNRStateHistory();
        energyNRStateHistory1.setId(1L);
        EnergyNRStateHistory energyNRStateHistory2 = new EnergyNRStateHistory();
        energyNRStateHistory2.setId(energyNRStateHistory1.getId());
        assertThat(energyNRStateHistory1).isEqualTo(energyNRStateHistory2);
        energyNRStateHistory2.setId(2L);
        assertThat(energyNRStateHistory1).isNotEqualTo(energyNRStateHistory2);
        energyNRStateHistory1.setId(null);
        assertThat(energyNRStateHistory1).isNotEqualTo(energyNRStateHistory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyNRStateHistoryDTO.class);
        EnergyNRStateHistoryDTO energyNRStateHistoryDTO1 = new EnergyNRStateHistoryDTO();
        energyNRStateHistoryDTO1.setId(1L);
        EnergyNRStateHistoryDTO energyNRStateHistoryDTO2 = new EnergyNRStateHistoryDTO();
        assertThat(energyNRStateHistoryDTO1).isNotEqualTo(energyNRStateHistoryDTO2);
        energyNRStateHistoryDTO2.setId(energyNRStateHistoryDTO1.getId());
        assertThat(energyNRStateHistoryDTO1).isEqualTo(energyNRStateHistoryDTO2);
        energyNRStateHistoryDTO2.setId(2L);
        assertThat(energyNRStateHistoryDTO1).isNotEqualTo(energyNRStateHistoryDTO2);
        energyNRStateHistoryDTO1.setId(null);
        assertThat(energyNRStateHistoryDTO1).isNotEqualTo(energyNRStateHistoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(energyNRStateHistoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(energyNRStateHistoryMapper.fromId(null)).isNull();
    }
}
