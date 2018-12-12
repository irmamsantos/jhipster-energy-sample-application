package io.github.jhipster.energy.web.rest;

import io.github.jhipster.energy.JhipsterEnergySampleApplicationApp;

import io.github.jhipster.energy.domain.EnergyVehicleBrandData;
import io.github.jhipster.energy.repository.EnergyVehicleBrandDataRepository;
import io.github.jhipster.energy.service.EnergyVehicleBrandDataService;
import io.github.jhipster.energy.service.dto.EnergyVehicleBrandDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyVehicleBrandDataMapper;
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
 * Test class for the EnergyVehicleBrandDataResource REST controller.
 *
 * @see EnergyVehicleBrandDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterEnergySampleApplicationApp.class)
public class EnergyVehicleBrandDataResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private EnergyVehicleBrandDataRepository energyVehicleBrandDataRepository;

    @Autowired
    private EnergyVehicleBrandDataMapper energyVehicleBrandDataMapper;

    @Autowired
    private EnergyVehicleBrandDataService energyVehicleBrandDataService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnergyVehicleBrandDataMockMvc;

    private EnergyVehicleBrandData energyVehicleBrandData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnergyVehicleBrandDataResource energyVehicleBrandDataResource = new EnergyVehicleBrandDataResource(energyVehicleBrandDataService);
        this.restEnergyVehicleBrandDataMockMvc = MockMvcBuilders.standaloneSetup(energyVehicleBrandDataResource)
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
    public static EnergyVehicleBrandData createEntity(EntityManager em) {
        EnergyVehicleBrandData energyVehicleBrandData = new EnergyVehicleBrandData()
            .description(DEFAULT_DESCRIPTION);
        return energyVehicleBrandData;
    }

    @Before
    public void initTest() {
        energyVehicleBrandData = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnergyVehicleBrandData() throws Exception {
        int databaseSizeBeforeCreate = energyVehicleBrandDataRepository.findAll().size();

        // Create the EnergyVehicleBrandData
        EnergyVehicleBrandDataDTO energyVehicleBrandDataDTO = energyVehicleBrandDataMapper.toDto(energyVehicleBrandData);
        restEnergyVehicleBrandDataMockMvc.perform(post("/api/energy-vehicle-brand-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyVehicleBrandDataDTO)))
            .andExpect(status().isCreated());

        // Validate the EnergyVehicleBrandData in the database
        List<EnergyVehicleBrandData> energyVehicleBrandDataList = energyVehicleBrandDataRepository.findAll();
        assertThat(energyVehicleBrandDataList).hasSize(databaseSizeBeforeCreate + 1);
        EnergyVehicleBrandData testEnergyVehicleBrandData = energyVehicleBrandDataList.get(energyVehicleBrandDataList.size() - 1);
        assertThat(testEnergyVehicleBrandData.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createEnergyVehicleBrandDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = energyVehicleBrandDataRepository.findAll().size();

        // Create the EnergyVehicleBrandData with an existing ID
        energyVehicleBrandData.setId(1L);
        EnergyVehicleBrandDataDTO energyVehicleBrandDataDTO = energyVehicleBrandDataMapper.toDto(energyVehicleBrandData);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnergyVehicleBrandDataMockMvc.perform(post("/api/energy-vehicle-brand-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyVehicleBrandDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyVehicleBrandData in the database
        List<EnergyVehicleBrandData> energyVehicleBrandDataList = energyVehicleBrandDataRepository.findAll();
        assertThat(energyVehicleBrandDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyVehicleBrandDataRepository.findAll().size();
        // set the field null
        energyVehicleBrandData.setDescription(null);

        // Create the EnergyVehicleBrandData, which fails.
        EnergyVehicleBrandDataDTO energyVehicleBrandDataDTO = energyVehicleBrandDataMapper.toDto(energyVehicleBrandData);

        restEnergyVehicleBrandDataMockMvc.perform(post("/api/energy-vehicle-brand-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyVehicleBrandDataDTO)))
            .andExpect(status().isBadRequest());

        List<EnergyVehicleBrandData> energyVehicleBrandDataList = energyVehicleBrandDataRepository.findAll();
        assertThat(energyVehicleBrandDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEnergyVehicleBrandData() throws Exception {
        // Initialize the database
        energyVehicleBrandDataRepository.saveAndFlush(energyVehicleBrandData);

        // Get all the energyVehicleBrandDataList
        restEnergyVehicleBrandDataMockMvc.perform(get("/api/energy-vehicle-brand-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(energyVehicleBrandData.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getEnergyVehicleBrandData() throws Exception {
        // Initialize the database
        energyVehicleBrandDataRepository.saveAndFlush(energyVehicleBrandData);

        // Get the energyVehicleBrandData
        restEnergyVehicleBrandDataMockMvc.perform(get("/api/energy-vehicle-brand-data/{id}", energyVehicleBrandData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(energyVehicleBrandData.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnergyVehicleBrandData() throws Exception {
        // Get the energyVehicleBrandData
        restEnergyVehicleBrandDataMockMvc.perform(get("/api/energy-vehicle-brand-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnergyVehicleBrandData() throws Exception {
        // Initialize the database
        energyVehicleBrandDataRepository.saveAndFlush(energyVehicleBrandData);

        int databaseSizeBeforeUpdate = energyVehicleBrandDataRepository.findAll().size();

        // Update the energyVehicleBrandData
        EnergyVehicleBrandData updatedEnergyVehicleBrandData = energyVehicleBrandDataRepository.findById(energyVehicleBrandData.getId()).get();
        // Disconnect from session so that the updates on updatedEnergyVehicleBrandData are not directly saved in db
        em.detach(updatedEnergyVehicleBrandData);
        updatedEnergyVehicleBrandData
            .description(UPDATED_DESCRIPTION);
        EnergyVehicleBrandDataDTO energyVehicleBrandDataDTO = energyVehicleBrandDataMapper.toDto(updatedEnergyVehicleBrandData);

        restEnergyVehicleBrandDataMockMvc.perform(put("/api/energy-vehicle-brand-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyVehicleBrandDataDTO)))
            .andExpect(status().isOk());

        // Validate the EnergyVehicleBrandData in the database
        List<EnergyVehicleBrandData> energyVehicleBrandDataList = energyVehicleBrandDataRepository.findAll();
        assertThat(energyVehicleBrandDataList).hasSize(databaseSizeBeforeUpdate);
        EnergyVehicleBrandData testEnergyVehicleBrandData = energyVehicleBrandDataList.get(energyVehicleBrandDataList.size() - 1);
        assertThat(testEnergyVehicleBrandData.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingEnergyVehicleBrandData() throws Exception {
        int databaseSizeBeforeUpdate = energyVehicleBrandDataRepository.findAll().size();

        // Create the EnergyVehicleBrandData
        EnergyVehicleBrandDataDTO energyVehicleBrandDataDTO = energyVehicleBrandDataMapper.toDto(energyVehicleBrandData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnergyVehicleBrandDataMockMvc.perform(put("/api/energy-vehicle-brand-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyVehicleBrandDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyVehicleBrandData in the database
        List<EnergyVehicleBrandData> energyVehicleBrandDataList = energyVehicleBrandDataRepository.findAll();
        assertThat(energyVehicleBrandDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnergyVehicleBrandData() throws Exception {
        // Initialize the database
        energyVehicleBrandDataRepository.saveAndFlush(energyVehicleBrandData);

        int databaseSizeBeforeDelete = energyVehicleBrandDataRepository.findAll().size();

        // Get the energyVehicleBrandData
        restEnergyVehicleBrandDataMockMvc.perform(delete("/api/energy-vehicle-brand-data/{id}", energyVehicleBrandData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EnergyVehicleBrandData> energyVehicleBrandDataList = energyVehicleBrandDataRepository.findAll();
        assertThat(energyVehicleBrandDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyVehicleBrandData.class);
        EnergyVehicleBrandData energyVehicleBrandData1 = new EnergyVehicleBrandData();
        energyVehicleBrandData1.setId(1L);
        EnergyVehicleBrandData energyVehicleBrandData2 = new EnergyVehicleBrandData();
        energyVehicleBrandData2.setId(energyVehicleBrandData1.getId());
        assertThat(energyVehicleBrandData1).isEqualTo(energyVehicleBrandData2);
        energyVehicleBrandData2.setId(2L);
        assertThat(energyVehicleBrandData1).isNotEqualTo(energyVehicleBrandData2);
        energyVehicleBrandData1.setId(null);
        assertThat(energyVehicleBrandData1).isNotEqualTo(energyVehicleBrandData2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyVehicleBrandDataDTO.class);
        EnergyVehicleBrandDataDTO energyVehicleBrandDataDTO1 = new EnergyVehicleBrandDataDTO();
        energyVehicleBrandDataDTO1.setId(1L);
        EnergyVehicleBrandDataDTO energyVehicleBrandDataDTO2 = new EnergyVehicleBrandDataDTO();
        assertThat(energyVehicleBrandDataDTO1).isNotEqualTo(energyVehicleBrandDataDTO2);
        energyVehicleBrandDataDTO2.setId(energyVehicleBrandDataDTO1.getId());
        assertThat(energyVehicleBrandDataDTO1).isEqualTo(energyVehicleBrandDataDTO2);
        energyVehicleBrandDataDTO2.setId(2L);
        assertThat(energyVehicleBrandDataDTO1).isNotEqualTo(energyVehicleBrandDataDTO2);
        energyVehicleBrandDataDTO1.setId(null);
        assertThat(energyVehicleBrandDataDTO1).isNotEqualTo(energyVehicleBrandDataDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(energyVehicleBrandDataMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(energyVehicleBrandDataMapper.fromId(null)).isNull();
    }
}
