package io.github.jhipster.energy.web.rest;

import io.github.jhipster.energy.JhipsterEnergySampleApplicationApp;

import io.github.jhipster.energy.domain.EnergyVoltageData;
import io.github.jhipster.energy.repository.EnergyVoltageDataRepository;
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
 * Test class for the EnergyVoltageDataResource REST controller.
 *
 * @see EnergyVoltageDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterEnergySampleApplicationApp.class)
public class EnergyVoltageDataResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private EnergyVoltageDataRepository energyVoltageDataRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnergyVoltageDataMockMvc;

    private EnergyVoltageData energyVoltageData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnergyVoltageDataResource energyVoltageDataResource = new EnergyVoltageDataResource(energyVoltageDataRepository);
        this.restEnergyVoltageDataMockMvc = MockMvcBuilders.standaloneSetup(energyVoltageDataResource)
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
    public static EnergyVoltageData createEntity(EntityManager em) {
        EnergyVoltageData energyVoltageData = new EnergyVoltageData()
            .description(DEFAULT_DESCRIPTION);
        return energyVoltageData;
    }

    @Before
    public void initTest() {
        energyVoltageData = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnergyVoltageData() throws Exception {
        int databaseSizeBeforeCreate = energyVoltageDataRepository.findAll().size();

        // Create the EnergyVoltageData
        restEnergyVoltageDataMockMvc.perform(post("/api/energy-voltage-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyVoltageData)))
            .andExpect(status().isCreated());

        // Validate the EnergyVoltageData in the database
        List<EnergyVoltageData> energyVoltageDataList = energyVoltageDataRepository.findAll();
        assertThat(energyVoltageDataList).hasSize(databaseSizeBeforeCreate + 1);
        EnergyVoltageData testEnergyVoltageData = energyVoltageDataList.get(energyVoltageDataList.size() - 1);
        assertThat(testEnergyVoltageData.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createEnergyVoltageDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = energyVoltageDataRepository.findAll().size();

        // Create the EnergyVoltageData with an existing ID
        energyVoltageData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnergyVoltageDataMockMvc.perform(post("/api/energy-voltage-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyVoltageData)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyVoltageData in the database
        List<EnergyVoltageData> energyVoltageDataList = energyVoltageDataRepository.findAll();
        assertThat(energyVoltageDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyVoltageDataRepository.findAll().size();
        // set the field null
        energyVoltageData.setDescription(null);

        // Create the EnergyVoltageData, which fails.

        restEnergyVoltageDataMockMvc.perform(post("/api/energy-voltage-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyVoltageData)))
            .andExpect(status().isBadRequest());

        List<EnergyVoltageData> energyVoltageDataList = energyVoltageDataRepository.findAll();
        assertThat(energyVoltageDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEnergyVoltageData() throws Exception {
        // Initialize the database
        energyVoltageDataRepository.saveAndFlush(energyVoltageData);

        // Get all the energyVoltageDataList
        restEnergyVoltageDataMockMvc.perform(get("/api/energy-voltage-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(energyVoltageData.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getEnergyVoltageData() throws Exception {
        // Initialize the database
        energyVoltageDataRepository.saveAndFlush(energyVoltageData);

        // Get the energyVoltageData
        restEnergyVoltageDataMockMvc.perform(get("/api/energy-voltage-data/{id}", energyVoltageData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(energyVoltageData.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnergyVoltageData() throws Exception {
        // Get the energyVoltageData
        restEnergyVoltageDataMockMvc.perform(get("/api/energy-voltage-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnergyVoltageData() throws Exception {
        // Initialize the database
        energyVoltageDataRepository.saveAndFlush(energyVoltageData);

        int databaseSizeBeforeUpdate = energyVoltageDataRepository.findAll().size();

        // Update the energyVoltageData
        EnergyVoltageData updatedEnergyVoltageData = energyVoltageDataRepository.findById(energyVoltageData.getId()).get();
        // Disconnect from session so that the updates on updatedEnergyVoltageData are not directly saved in db
        em.detach(updatedEnergyVoltageData);
        updatedEnergyVoltageData
            .description(UPDATED_DESCRIPTION);

        restEnergyVoltageDataMockMvc.perform(put("/api/energy-voltage-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEnergyVoltageData)))
            .andExpect(status().isOk());

        // Validate the EnergyVoltageData in the database
        List<EnergyVoltageData> energyVoltageDataList = energyVoltageDataRepository.findAll();
        assertThat(energyVoltageDataList).hasSize(databaseSizeBeforeUpdate);
        EnergyVoltageData testEnergyVoltageData = energyVoltageDataList.get(energyVoltageDataList.size() - 1);
        assertThat(testEnergyVoltageData.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingEnergyVoltageData() throws Exception {
        int databaseSizeBeforeUpdate = energyVoltageDataRepository.findAll().size();

        // Create the EnergyVoltageData

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnergyVoltageDataMockMvc.perform(put("/api/energy-voltage-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyVoltageData)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyVoltageData in the database
        List<EnergyVoltageData> energyVoltageDataList = energyVoltageDataRepository.findAll();
        assertThat(energyVoltageDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnergyVoltageData() throws Exception {
        // Initialize the database
        energyVoltageDataRepository.saveAndFlush(energyVoltageData);

        int databaseSizeBeforeDelete = energyVoltageDataRepository.findAll().size();

        // Get the energyVoltageData
        restEnergyVoltageDataMockMvc.perform(delete("/api/energy-voltage-data/{id}", energyVoltageData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EnergyVoltageData> energyVoltageDataList = energyVoltageDataRepository.findAll();
        assertThat(energyVoltageDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyVoltageData.class);
        EnergyVoltageData energyVoltageData1 = new EnergyVoltageData();
        energyVoltageData1.setId(1L);
        EnergyVoltageData energyVoltageData2 = new EnergyVoltageData();
        energyVoltageData2.setId(energyVoltageData1.getId());
        assertThat(energyVoltageData1).isEqualTo(energyVoltageData2);
        energyVoltageData2.setId(2L);
        assertThat(energyVoltageData1).isNotEqualTo(energyVoltageData2);
        energyVoltageData1.setId(null);
        assertThat(energyVoltageData1).isNotEqualTo(energyVoltageData2);
    }
}
