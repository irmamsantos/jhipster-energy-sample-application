package io.github.jhipster.energy.web.rest;

import io.github.jhipster.energy.JhipsterEnergySampleApplicationApp;

import io.github.jhipster.energy.domain.EnergyNaturalGasData;
import io.github.jhipster.energy.repository.EnergyNaturalGasDataRepository;
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
 * Test class for the EnergyNaturalGasDataResource REST controller.
 *
 * @see EnergyNaturalGasDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterEnergySampleApplicationApp.class)
public class EnergyNaturalGasDataResourceIntTest {

    private static final String DEFAULT_PRESSURE = "AAAAAAAAAA";
    private static final String UPDATED_PRESSURE = "BBBBBBBBBB";

    private static final String DEFAULT_ECHELON = "AAAAAAAAAA";
    private static final String UPDATED_ECHELON = "BBBBBBBBBB";

    private static final String DEFAULT_PRESSURE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PRESSURE_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private EnergyNaturalGasDataRepository energyNaturalGasDataRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnergyNaturalGasDataMockMvc;

    private EnergyNaturalGasData energyNaturalGasData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnergyNaturalGasDataResource energyNaturalGasDataResource = new EnergyNaturalGasDataResource(energyNaturalGasDataRepository);
        this.restEnergyNaturalGasDataMockMvc = MockMvcBuilders.standaloneSetup(energyNaturalGasDataResource)
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
    public static EnergyNaturalGasData createEntity(EntityManager em) {
        EnergyNaturalGasData energyNaturalGasData = new EnergyNaturalGasData()
            .pressure(DEFAULT_PRESSURE)
            .echelon(DEFAULT_ECHELON)
            .pressureDescription(DEFAULT_PRESSURE_DESCRIPTION);
        return energyNaturalGasData;
    }

    @Before
    public void initTest() {
        energyNaturalGasData = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnergyNaturalGasData() throws Exception {
        int databaseSizeBeforeCreate = energyNaturalGasDataRepository.findAll().size();

        // Create the EnergyNaturalGasData
        restEnergyNaturalGasDataMockMvc.perform(post("/api/energy-natural-gas-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNaturalGasData)))
            .andExpect(status().isCreated());

        // Validate the EnergyNaturalGasData in the database
        List<EnergyNaturalGasData> energyNaturalGasDataList = energyNaturalGasDataRepository.findAll();
        assertThat(energyNaturalGasDataList).hasSize(databaseSizeBeforeCreate + 1);
        EnergyNaturalGasData testEnergyNaturalGasData = energyNaturalGasDataList.get(energyNaturalGasDataList.size() - 1);
        assertThat(testEnergyNaturalGasData.getPressure()).isEqualTo(DEFAULT_PRESSURE);
        assertThat(testEnergyNaturalGasData.getEchelon()).isEqualTo(DEFAULT_ECHELON);
        assertThat(testEnergyNaturalGasData.getPressureDescription()).isEqualTo(DEFAULT_PRESSURE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createEnergyNaturalGasDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = energyNaturalGasDataRepository.findAll().size();

        // Create the EnergyNaturalGasData with an existing ID
        energyNaturalGasData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnergyNaturalGasDataMockMvc.perform(post("/api/energy-natural-gas-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNaturalGasData)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyNaturalGasData in the database
        List<EnergyNaturalGasData> energyNaturalGasDataList = energyNaturalGasDataRepository.findAll();
        assertThat(energyNaturalGasDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPressureIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyNaturalGasDataRepository.findAll().size();
        // set the field null
        energyNaturalGasData.setPressure(null);

        // Create the EnergyNaturalGasData, which fails.

        restEnergyNaturalGasDataMockMvc.perform(post("/api/energy-natural-gas-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNaturalGasData)))
            .andExpect(status().isBadRequest());

        List<EnergyNaturalGasData> energyNaturalGasDataList = energyNaturalGasDataRepository.findAll();
        assertThat(energyNaturalGasDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEchelonIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyNaturalGasDataRepository.findAll().size();
        // set the field null
        energyNaturalGasData.setEchelon(null);

        // Create the EnergyNaturalGasData, which fails.

        restEnergyNaturalGasDataMockMvc.perform(post("/api/energy-natural-gas-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNaturalGasData)))
            .andExpect(status().isBadRequest());

        List<EnergyNaturalGasData> energyNaturalGasDataList = energyNaturalGasDataRepository.findAll();
        assertThat(energyNaturalGasDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPressureDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyNaturalGasDataRepository.findAll().size();
        // set the field null
        energyNaturalGasData.setPressureDescription(null);

        // Create the EnergyNaturalGasData, which fails.

        restEnergyNaturalGasDataMockMvc.perform(post("/api/energy-natural-gas-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNaturalGasData)))
            .andExpect(status().isBadRequest());

        List<EnergyNaturalGasData> energyNaturalGasDataList = energyNaturalGasDataRepository.findAll();
        assertThat(energyNaturalGasDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEnergyNaturalGasData() throws Exception {
        // Initialize the database
        energyNaturalGasDataRepository.saveAndFlush(energyNaturalGasData);

        // Get all the energyNaturalGasDataList
        restEnergyNaturalGasDataMockMvc.perform(get("/api/energy-natural-gas-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(energyNaturalGasData.getId().intValue())))
            .andExpect(jsonPath("$.[*].pressure").value(hasItem(DEFAULT_PRESSURE.toString())))
            .andExpect(jsonPath("$.[*].echelon").value(hasItem(DEFAULT_ECHELON.toString())))
            .andExpect(jsonPath("$.[*].pressureDescription").value(hasItem(DEFAULT_PRESSURE_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getEnergyNaturalGasData() throws Exception {
        // Initialize the database
        energyNaturalGasDataRepository.saveAndFlush(energyNaturalGasData);

        // Get the energyNaturalGasData
        restEnergyNaturalGasDataMockMvc.perform(get("/api/energy-natural-gas-data/{id}", energyNaturalGasData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(energyNaturalGasData.getId().intValue()))
            .andExpect(jsonPath("$.pressure").value(DEFAULT_PRESSURE.toString()))
            .andExpect(jsonPath("$.echelon").value(DEFAULT_ECHELON.toString()))
            .andExpect(jsonPath("$.pressureDescription").value(DEFAULT_PRESSURE_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnergyNaturalGasData() throws Exception {
        // Get the energyNaturalGasData
        restEnergyNaturalGasDataMockMvc.perform(get("/api/energy-natural-gas-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnergyNaturalGasData() throws Exception {
        // Initialize the database
        energyNaturalGasDataRepository.saveAndFlush(energyNaturalGasData);

        int databaseSizeBeforeUpdate = energyNaturalGasDataRepository.findAll().size();

        // Update the energyNaturalGasData
        EnergyNaturalGasData updatedEnergyNaturalGasData = energyNaturalGasDataRepository.findById(energyNaturalGasData.getId()).get();
        // Disconnect from session so that the updates on updatedEnergyNaturalGasData are not directly saved in db
        em.detach(updatedEnergyNaturalGasData);
        updatedEnergyNaturalGasData
            .pressure(UPDATED_PRESSURE)
            .echelon(UPDATED_ECHELON)
            .pressureDescription(UPDATED_PRESSURE_DESCRIPTION);

        restEnergyNaturalGasDataMockMvc.perform(put("/api/energy-natural-gas-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEnergyNaturalGasData)))
            .andExpect(status().isOk());

        // Validate the EnergyNaturalGasData in the database
        List<EnergyNaturalGasData> energyNaturalGasDataList = energyNaturalGasDataRepository.findAll();
        assertThat(energyNaturalGasDataList).hasSize(databaseSizeBeforeUpdate);
        EnergyNaturalGasData testEnergyNaturalGasData = energyNaturalGasDataList.get(energyNaturalGasDataList.size() - 1);
        assertThat(testEnergyNaturalGasData.getPressure()).isEqualTo(UPDATED_PRESSURE);
        assertThat(testEnergyNaturalGasData.getEchelon()).isEqualTo(UPDATED_ECHELON);
        assertThat(testEnergyNaturalGasData.getPressureDescription()).isEqualTo(UPDATED_PRESSURE_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingEnergyNaturalGasData() throws Exception {
        int databaseSizeBeforeUpdate = energyNaturalGasDataRepository.findAll().size();

        // Create the EnergyNaturalGasData

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnergyNaturalGasDataMockMvc.perform(put("/api/energy-natural-gas-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNaturalGasData)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyNaturalGasData in the database
        List<EnergyNaturalGasData> energyNaturalGasDataList = energyNaturalGasDataRepository.findAll();
        assertThat(energyNaturalGasDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnergyNaturalGasData() throws Exception {
        // Initialize the database
        energyNaturalGasDataRepository.saveAndFlush(energyNaturalGasData);

        int databaseSizeBeforeDelete = energyNaturalGasDataRepository.findAll().size();

        // Get the energyNaturalGasData
        restEnergyNaturalGasDataMockMvc.perform(delete("/api/energy-natural-gas-data/{id}", energyNaturalGasData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EnergyNaturalGasData> energyNaturalGasDataList = energyNaturalGasDataRepository.findAll();
        assertThat(energyNaturalGasDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyNaturalGasData.class);
        EnergyNaturalGasData energyNaturalGasData1 = new EnergyNaturalGasData();
        energyNaturalGasData1.setId(1L);
        EnergyNaturalGasData energyNaturalGasData2 = new EnergyNaturalGasData();
        energyNaturalGasData2.setId(energyNaturalGasData1.getId());
        assertThat(energyNaturalGasData1).isEqualTo(energyNaturalGasData2);
        energyNaturalGasData2.setId(2L);
        assertThat(energyNaturalGasData1).isNotEqualTo(energyNaturalGasData2);
        energyNaturalGasData1.setId(null);
        assertThat(energyNaturalGasData1).isNotEqualTo(energyNaturalGasData2);
    }
}
