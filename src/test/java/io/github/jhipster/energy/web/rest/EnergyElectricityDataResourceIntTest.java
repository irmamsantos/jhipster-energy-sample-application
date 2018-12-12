package io.github.jhipster.energy.web.rest;

import io.github.jhipster.energy.JhipsterEnergySampleApplicationApp;

import io.github.jhipster.energy.domain.EnergyElectricityData;
import io.github.jhipster.energy.repository.EnergyElectricityDataRepository;
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
 * Test class for the EnergyElectricityDataResource REST controller.
 *
 * @see EnergyElectricityDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterEnergySampleApplicationApp.class)
public class EnergyElectricityDataResourceIntTest {

    @Autowired
    private EnergyElectricityDataRepository energyElectricityDataRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnergyElectricityDataMockMvc;

    private EnergyElectricityData energyElectricityData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnergyElectricityDataResource energyElectricityDataResource = new EnergyElectricityDataResource(energyElectricityDataRepository);
        this.restEnergyElectricityDataMockMvc = MockMvcBuilders.standaloneSetup(energyElectricityDataResource)
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
    public static EnergyElectricityData createEntity(EntityManager em) {
        EnergyElectricityData energyElectricityData = new EnergyElectricityData();
        return energyElectricityData;
    }

    @Before
    public void initTest() {
        energyElectricityData = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnergyElectricityData() throws Exception {
        int databaseSizeBeforeCreate = energyElectricityDataRepository.findAll().size();

        // Create the EnergyElectricityData
        restEnergyElectricityDataMockMvc.perform(post("/api/energy-electricity-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyElectricityData)))
            .andExpect(status().isCreated());

        // Validate the EnergyElectricityData in the database
        List<EnergyElectricityData> energyElectricityDataList = energyElectricityDataRepository.findAll();
        assertThat(energyElectricityDataList).hasSize(databaseSizeBeforeCreate + 1);
        EnergyElectricityData testEnergyElectricityData = energyElectricityDataList.get(energyElectricityDataList.size() - 1);
    }

    @Test
    @Transactional
    public void createEnergyElectricityDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = energyElectricityDataRepository.findAll().size();

        // Create the EnergyElectricityData with an existing ID
        energyElectricityData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnergyElectricityDataMockMvc.perform(post("/api/energy-electricity-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyElectricityData)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyElectricityData in the database
        List<EnergyElectricityData> energyElectricityDataList = energyElectricityDataRepository.findAll();
        assertThat(energyElectricityDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEnergyElectricityData() throws Exception {
        // Initialize the database
        energyElectricityDataRepository.saveAndFlush(energyElectricityData);

        // Get all the energyElectricityDataList
        restEnergyElectricityDataMockMvc.perform(get("/api/energy-electricity-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(energyElectricityData.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getEnergyElectricityData() throws Exception {
        // Initialize the database
        energyElectricityDataRepository.saveAndFlush(energyElectricityData);

        // Get the energyElectricityData
        restEnergyElectricityDataMockMvc.perform(get("/api/energy-electricity-data/{id}", energyElectricityData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(energyElectricityData.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEnergyElectricityData() throws Exception {
        // Get the energyElectricityData
        restEnergyElectricityDataMockMvc.perform(get("/api/energy-electricity-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnergyElectricityData() throws Exception {
        // Initialize the database
        energyElectricityDataRepository.saveAndFlush(energyElectricityData);

        int databaseSizeBeforeUpdate = energyElectricityDataRepository.findAll().size();

        // Update the energyElectricityData
        EnergyElectricityData updatedEnergyElectricityData = energyElectricityDataRepository.findById(energyElectricityData.getId()).get();
        // Disconnect from session so that the updates on updatedEnergyElectricityData are not directly saved in db
        em.detach(updatedEnergyElectricityData);

        restEnergyElectricityDataMockMvc.perform(put("/api/energy-electricity-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEnergyElectricityData)))
            .andExpect(status().isOk());

        // Validate the EnergyElectricityData in the database
        List<EnergyElectricityData> energyElectricityDataList = energyElectricityDataRepository.findAll();
        assertThat(energyElectricityDataList).hasSize(databaseSizeBeforeUpdate);
        EnergyElectricityData testEnergyElectricityData = energyElectricityDataList.get(energyElectricityDataList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingEnergyElectricityData() throws Exception {
        int databaseSizeBeforeUpdate = energyElectricityDataRepository.findAll().size();

        // Create the EnergyElectricityData

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnergyElectricityDataMockMvc.perform(put("/api/energy-electricity-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyElectricityData)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyElectricityData in the database
        List<EnergyElectricityData> energyElectricityDataList = energyElectricityDataRepository.findAll();
        assertThat(energyElectricityDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnergyElectricityData() throws Exception {
        // Initialize the database
        energyElectricityDataRepository.saveAndFlush(energyElectricityData);

        int databaseSizeBeforeDelete = energyElectricityDataRepository.findAll().size();

        // Get the energyElectricityData
        restEnergyElectricityDataMockMvc.perform(delete("/api/energy-electricity-data/{id}", energyElectricityData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EnergyElectricityData> energyElectricityDataList = energyElectricityDataRepository.findAll();
        assertThat(energyElectricityDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyElectricityData.class);
        EnergyElectricityData energyElectricityData1 = new EnergyElectricityData();
        energyElectricityData1.setId(1L);
        EnergyElectricityData energyElectricityData2 = new EnergyElectricityData();
        energyElectricityData2.setId(energyElectricityData1.getId());
        assertThat(energyElectricityData1).isEqualTo(energyElectricityData2);
        energyElectricityData2.setId(2L);
        assertThat(energyElectricityData1).isNotEqualTo(energyElectricityData2);
        energyElectricityData1.setId(null);
        assertThat(energyElectricityData1).isNotEqualTo(energyElectricityData2);
    }
}
