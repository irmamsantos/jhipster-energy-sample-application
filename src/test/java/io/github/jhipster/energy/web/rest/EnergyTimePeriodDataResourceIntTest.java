package io.github.jhipster.energy.web.rest;

import io.github.jhipster.energy.JhipsterEnergySampleApplicationApp;

import io.github.jhipster.energy.domain.EnergyTimePeriodData;
import io.github.jhipster.energy.repository.EnergyTimePeriodDataRepository;
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
import java.util.List;


import static io.github.jhipster.energy.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EnergyTimePeriodDataResource REST controller.
 *
 * @see EnergyTimePeriodDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterEnergySampleApplicationApp.class)
public class EnergyTimePeriodDataResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private EnergyTimePeriodDataRepository energyTimePeriodDataRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnergyTimePeriodDataMockMvc;

    private EnergyTimePeriodData energyTimePeriodData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnergyTimePeriodDataResource energyTimePeriodDataResource = new EnergyTimePeriodDataResource(energyTimePeriodDataRepository);
        this.restEnergyTimePeriodDataMockMvc = MockMvcBuilders.standaloneSetup(energyTimePeriodDataResource)
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
    public static EnergyTimePeriodData createEntity(EntityManager em) {
        EnergyTimePeriodData energyTimePeriodData = new EnergyTimePeriodData()
            .description(DEFAULT_DESCRIPTION);
        return energyTimePeriodData;
    }

    @Before
    public void initTest() {
        energyTimePeriodData = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnergyTimePeriodData() throws Exception {
        int databaseSizeBeforeCreate = energyTimePeriodDataRepository.findAll().size();

        // Create the EnergyTimePeriodData
        restEnergyTimePeriodDataMockMvc.perform(post("/api/energy-time-period-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyTimePeriodData)))
            .andExpect(status().isCreated());

        // Validate the EnergyTimePeriodData in the database
        List<EnergyTimePeriodData> energyTimePeriodDataList = energyTimePeriodDataRepository.findAll();
        assertThat(energyTimePeriodDataList).hasSize(databaseSizeBeforeCreate + 1);
        EnergyTimePeriodData testEnergyTimePeriodData = energyTimePeriodDataList.get(energyTimePeriodDataList.size() - 1);
        assertThat(testEnergyTimePeriodData.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createEnergyTimePeriodDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = energyTimePeriodDataRepository.findAll().size();

        // Create the EnergyTimePeriodData with an existing ID
        energyTimePeriodData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnergyTimePeriodDataMockMvc.perform(post("/api/energy-time-period-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyTimePeriodData)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyTimePeriodData in the database
        List<EnergyTimePeriodData> energyTimePeriodDataList = energyTimePeriodDataRepository.findAll();
        assertThat(energyTimePeriodDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyTimePeriodDataRepository.findAll().size();
        // set the field null
        energyTimePeriodData.setDescription(null);

        // Create the EnergyTimePeriodData, which fails.

        restEnergyTimePeriodDataMockMvc.perform(post("/api/energy-time-period-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyTimePeriodData)))
            .andExpect(status().isBadRequest());

        List<EnergyTimePeriodData> energyTimePeriodDataList = energyTimePeriodDataRepository.findAll();
        assertThat(energyTimePeriodDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEnergyTimePeriodData() throws Exception {
        // Initialize the database
        energyTimePeriodDataRepository.saveAndFlush(energyTimePeriodData);

        // Get all the energyTimePeriodDataList
        restEnergyTimePeriodDataMockMvc.perform(get("/api/energy-time-period-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(energyTimePeriodData.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getEnergyTimePeriodData() throws Exception {
        // Initialize the database
        energyTimePeriodDataRepository.saveAndFlush(energyTimePeriodData);

        // Get the energyTimePeriodData
        restEnergyTimePeriodDataMockMvc.perform(get("/api/energy-time-period-data/{id}", energyTimePeriodData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(energyTimePeriodData.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnergyTimePeriodData() throws Exception {
        // Get the energyTimePeriodData
        restEnergyTimePeriodDataMockMvc.perform(get("/api/energy-time-period-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnergyTimePeriodData() throws Exception {
        // Initialize the database
        energyTimePeriodDataRepository.saveAndFlush(energyTimePeriodData);

        int databaseSizeBeforeUpdate = energyTimePeriodDataRepository.findAll().size();

        // Update the energyTimePeriodData
        EnergyTimePeriodData updatedEnergyTimePeriodData = energyTimePeriodDataRepository.findById(energyTimePeriodData.getId()).get();
        // Disconnect from session so that the updates on updatedEnergyTimePeriodData are not directly saved in db
        em.detach(updatedEnergyTimePeriodData);
        updatedEnergyTimePeriodData
            .description(UPDATED_DESCRIPTION);

        restEnergyTimePeriodDataMockMvc.perform(put("/api/energy-time-period-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEnergyTimePeriodData)))
            .andExpect(status().isOk());

        // Validate the EnergyTimePeriodData in the database
        List<EnergyTimePeriodData> energyTimePeriodDataList = energyTimePeriodDataRepository.findAll();
        assertThat(energyTimePeriodDataList).hasSize(databaseSizeBeforeUpdate);
        EnergyTimePeriodData testEnergyTimePeriodData = energyTimePeriodDataList.get(energyTimePeriodDataList.size() - 1);
        assertThat(testEnergyTimePeriodData.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingEnergyTimePeriodData() throws Exception {
        int databaseSizeBeforeUpdate = energyTimePeriodDataRepository.findAll().size();

        // Create the EnergyTimePeriodData

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnergyTimePeriodDataMockMvc.perform(put("/api/energy-time-period-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyTimePeriodData)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyTimePeriodData in the database
        List<EnergyTimePeriodData> energyTimePeriodDataList = energyTimePeriodDataRepository.findAll();
        assertThat(energyTimePeriodDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnergyTimePeriodData() throws Exception {
        // Initialize the database
        energyTimePeriodDataRepository.saveAndFlush(energyTimePeriodData);

        int databaseSizeBeforeDelete = energyTimePeriodDataRepository.findAll().size();

        // Get the energyTimePeriodData
        restEnergyTimePeriodDataMockMvc.perform(delete("/api/energy-time-period-data/{id}", energyTimePeriodData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EnergyTimePeriodData> energyTimePeriodDataList = energyTimePeriodDataRepository.findAll();
        assertThat(energyTimePeriodDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyTimePeriodData.class);
        EnergyTimePeriodData energyTimePeriodData1 = new EnergyTimePeriodData();
        energyTimePeriodData1.setId(1L);
        EnergyTimePeriodData energyTimePeriodData2 = new EnergyTimePeriodData();
        energyTimePeriodData2.setId(energyTimePeriodData1.getId());
        assertThat(energyTimePeriodData1).isEqualTo(energyTimePeriodData2);
        energyTimePeriodData2.setId(2L);
        assertThat(energyTimePeriodData1).isNotEqualTo(energyTimePeriodData2);
        energyTimePeriodData1.setId(null);
        assertThat(energyTimePeriodData1).isNotEqualTo(energyTimePeriodData2);
    }
}
