package io.github.jhipster.energy.web.rest;

import io.github.jhipster.energy.JhipsterEnergySampleApplicationApp;

import io.github.jhipster.energy.domain.EnergyVehicleModelData;
import io.github.jhipster.energy.repository.EnergyVehicleModelDataRepository;
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
 * Test class for the EnergyVehicleModelDataResource REST controller.
 *
 * @see EnergyVehicleModelDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterEnergySampleApplicationApp.class)
public class EnergyVehicleModelDataResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private EnergyVehicleModelDataRepository energyVehicleModelDataRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnergyVehicleModelDataMockMvc;

    private EnergyVehicleModelData energyVehicleModelData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnergyVehicleModelDataResource energyVehicleModelDataResource = new EnergyVehicleModelDataResource(energyVehicleModelDataRepository);
        this.restEnergyVehicleModelDataMockMvc = MockMvcBuilders.standaloneSetup(energyVehicleModelDataResource)
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
    public static EnergyVehicleModelData createEntity(EntityManager em) {
        EnergyVehicleModelData energyVehicleModelData = new EnergyVehicleModelData()
            .description(DEFAULT_DESCRIPTION);
        return energyVehicleModelData;
    }

    @Before
    public void initTest() {
        energyVehicleModelData = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnergyVehicleModelData() throws Exception {
        int databaseSizeBeforeCreate = energyVehicleModelDataRepository.findAll().size();

        // Create the EnergyVehicleModelData
        restEnergyVehicleModelDataMockMvc.perform(post("/api/energy-vehicle-model-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyVehicleModelData)))
            .andExpect(status().isCreated());

        // Validate the EnergyVehicleModelData in the database
        List<EnergyVehicleModelData> energyVehicleModelDataList = energyVehicleModelDataRepository.findAll();
        assertThat(energyVehicleModelDataList).hasSize(databaseSizeBeforeCreate + 1);
        EnergyVehicleModelData testEnergyVehicleModelData = energyVehicleModelDataList.get(energyVehicleModelDataList.size() - 1);
        assertThat(testEnergyVehicleModelData.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createEnergyVehicleModelDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = energyVehicleModelDataRepository.findAll().size();

        // Create the EnergyVehicleModelData with an existing ID
        energyVehicleModelData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnergyVehicleModelDataMockMvc.perform(post("/api/energy-vehicle-model-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyVehicleModelData)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyVehicleModelData in the database
        List<EnergyVehicleModelData> energyVehicleModelDataList = energyVehicleModelDataRepository.findAll();
        assertThat(energyVehicleModelDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyVehicleModelDataRepository.findAll().size();
        // set the field null
        energyVehicleModelData.setDescription(null);

        // Create the EnergyVehicleModelData, which fails.

        restEnergyVehicleModelDataMockMvc.perform(post("/api/energy-vehicle-model-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyVehicleModelData)))
            .andExpect(status().isBadRequest());

        List<EnergyVehicleModelData> energyVehicleModelDataList = energyVehicleModelDataRepository.findAll();
        assertThat(energyVehicleModelDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEnergyVehicleModelData() throws Exception {
        // Initialize the database
        energyVehicleModelDataRepository.saveAndFlush(energyVehicleModelData);

        // Get all the energyVehicleModelDataList
        restEnergyVehicleModelDataMockMvc.perform(get("/api/energy-vehicle-model-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(energyVehicleModelData.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getEnergyVehicleModelData() throws Exception {
        // Initialize the database
        energyVehicleModelDataRepository.saveAndFlush(energyVehicleModelData);

        // Get the energyVehicleModelData
        restEnergyVehicleModelDataMockMvc.perform(get("/api/energy-vehicle-model-data/{id}", energyVehicleModelData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(energyVehicleModelData.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnergyVehicleModelData() throws Exception {
        // Get the energyVehicleModelData
        restEnergyVehicleModelDataMockMvc.perform(get("/api/energy-vehicle-model-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnergyVehicleModelData() throws Exception {
        // Initialize the database
        energyVehicleModelDataRepository.saveAndFlush(energyVehicleModelData);

        int databaseSizeBeforeUpdate = energyVehicleModelDataRepository.findAll().size();

        // Update the energyVehicleModelData
        EnergyVehicleModelData updatedEnergyVehicleModelData = energyVehicleModelDataRepository.findById(energyVehicleModelData.getId()).get();
        // Disconnect from session so that the updates on updatedEnergyVehicleModelData are not directly saved in db
        em.detach(updatedEnergyVehicleModelData);
        updatedEnergyVehicleModelData
            .description(UPDATED_DESCRIPTION);

        restEnergyVehicleModelDataMockMvc.perform(put("/api/energy-vehicle-model-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEnergyVehicleModelData)))
            .andExpect(status().isOk());

        // Validate the EnergyVehicleModelData in the database
        List<EnergyVehicleModelData> energyVehicleModelDataList = energyVehicleModelDataRepository.findAll();
        assertThat(energyVehicleModelDataList).hasSize(databaseSizeBeforeUpdate);
        EnergyVehicleModelData testEnergyVehicleModelData = energyVehicleModelDataList.get(energyVehicleModelDataList.size() - 1);
        assertThat(testEnergyVehicleModelData.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingEnergyVehicleModelData() throws Exception {
        int databaseSizeBeforeUpdate = energyVehicleModelDataRepository.findAll().size();

        // Create the EnergyVehicleModelData

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnergyVehicleModelDataMockMvc.perform(put("/api/energy-vehicle-model-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyVehicleModelData)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyVehicleModelData in the database
        List<EnergyVehicleModelData> energyVehicleModelDataList = energyVehicleModelDataRepository.findAll();
        assertThat(energyVehicleModelDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnergyVehicleModelData() throws Exception {
        // Initialize the database
        energyVehicleModelDataRepository.saveAndFlush(energyVehicleModelData);

        int databaseSizeBeforeDelete = energyVehicleModelDataRepository.findAll().size();

        // Get the energyVehicleModelData
        restEnergyVehicleModelDataMockMvc.perform(delete("/api/energy-vehicle-model-data/{id}", energyVehicleModelData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EnergyVehicleModelData> energyVehicleModelDataList = energyVehicleModelDataRepository.findAll();
        assertThat(energyVehicleModelDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyVehicleModelData.class);
        EnergyVehicleModelData energyVehicleModelData1 = new EnergyVehicleModelData();
        energyVehicleModelData1.setId(1L);
        EnergyVehicleModelData energyVehicleModelData2 = new EnergyVehicleModelData();
        energyVehicleModelData2.setId(energyVehicleModelData1.getId());
        assertThat(energyVehicleModelData1).isEqualTo(energyVehicleModelData2);
        energyVehicleModelData2.setId(2L);
        assertThat(energyVehicleModelData1).isNotEqualTo(energyVehicleModelData2);
        energyVehicleModelData1.setId(null);
        assertThat(energyVehicleModelData1).isNotEqualTo(energyVehicleModelData2);
    }
}
