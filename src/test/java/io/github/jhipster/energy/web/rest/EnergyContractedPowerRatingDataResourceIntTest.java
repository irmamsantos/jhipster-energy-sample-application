package io.github.jhipster.energy.web.rest;

import io.github.jhipster.energy.JhipsterEnergySampleApplicationApp;

import io.github.jhipster.energy.domain.EnergyContractedPowerRatingData;
import io.github.jhipster.energy.repository.EnergyContractedPowerRatingDataRepository;
import io.github.jhipster.energy.service.EnergyContractedPowerRatingDataService;
import io.github.jhipster.energy.service.dto.EnergyContractedPowerRatingDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyContractedPowerRatingDataMapper;
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
 * Test class for the EnergyContractedPowerRatingDataResource REST controller.
 *
 * @see EnergyContractedPowerRatingDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterEnergySampleApplicationApp.class)
public class EnergyContractedPowerRatingDataResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private EnergyContractedPowerRatingDataRepository energyContractedPowerRatingDataRepository;

    @Autowired
    private EnergyContractedPowerRatingDataMapper energyContractedPowerRatingDataMapper;

    @Autowired
    private EnergyContractedPowerRatingDataService energyContractedPowerRatingDataService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnergyContractedPowerRatingDataMockMvc;

    private EnergyContractedPowerRatingData energyContractedPowerRatingData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnergyContractedPowerRatingDataResource energyContractedPowerRatingDataResource = new EnergyContractedPowerRatingDataResource(energyContractedPowerRatingDataService);
        this.restEnergyContractedPowerRatingDataMockMvc = MockMvcBuilders.standaloneSetup(energyContractedPowerRatingDataResource)
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
    public static EnergyContractedPowerRatingData createEntity(EntityManager em) {
        EnergyContractedPowerRatingData energyContractedPowerRatingData = new EnergyContractedPowerRatingData()
            .description(DEFAULT_DESCRIPTION);
        return energyContractedPowerRatingData;
    }

    @Before
    public void initTest() {
        energyContractedPowerRatingData = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnergyContractedPowerRatingData() throws Exception {
        int databaseSizeBeforeCreate = energyContractedPowerRatingDataRepository.findAll().size();

        // Create the EnergyContractedPowerRatingData
        EnergyContractedPowerRatingDataDTO energyContractedPowerRatingDataDTO = energyContractedPowerRatingDataMapper.toDto(energyContractedPowerRatingData);
        restEnergyContractedPowerRatingDataMockMvc.perform(post("/api/energy-contracted-power-rating-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyContractedPowerRatingDataDTO)))
            .andExpect(status().isCreated());

        // Validate the EnergyContractedPowerRatingData in the database
        List<EnergyContractedPowerRatingData> energyContractedPowerRatingDataList = energyContractedPowerRatingDataRepository.findAll();
        assertThat(energyContractedPowerRatingDataList).hasSize(databaseSizeBeforeCreate + 1);
        EnergyContractedPowerRatingData testEnergyContractedPowerRatingData = energyContractedPowerRatingDataList.get(energyContractedPowerRatingDataList.size() - 1);
        assertThat(testEnergyContractedPowerRatingData.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createEnergyContractedPowerRatingDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = energyContractedPowerRatingDataRepository.findAll().size();

        // Create the EnergyContractedPowerRatingData with an existing ID
        energyContractedPowerRatingData.setId(1L);
        EnergyContractedPowerRatingDataDTO energyContractedPowerRatingDataDTO = energyContractedPowerRatingDataMapper.toDto(energyContractedPowerRatingData);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnergyContractedPowerRatingDataMockMvc.perform(post("/api/energy-contracted-power-rating-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyContractedPowerRatingDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyContractedPowerRatingData in the database
        List<EnergyContractedPowerRatingData> energyContractedPowerRatingDataList = energyContractedPowerRatingDataRepository.findAll();
        assertThat(energyContractedPowerRatingDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyContractedPowerRatingDataRepository.findAll().size();
        // set the field null
        energyContractedPowerRatingData.setDescription(null);

        // Create the EnergyContractedPowerRatingData, which fails.
        EnergyContractedPowerRatingDataDTO energyContractedPowerRatingDataDTO = energyContractedPowerRatingDataMapper.toDto(energyContractedPowerRatingData);

        restEnergyContractedPowerRatingDataMockMvc.perform(post("/api/energy-contracted-power-rating-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyContractedPowerRatingDataDTO)))
            .andExpect(status().isBadRequest());

        List<EnergyContractedPowerRatingData> energyContractedPowerRatingDataList = energyContractedPowerRatingDataRepository.findAll();
        assertThat(energyContractedPowerRatingDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEnergyContractedPowerRatingData() throws Exception {
        // Initialize the database
        energyContractedPowerRatingDataRepository.saveAndFlush(energyContractedPowerRatingData);

        // Get all the energyContractedPowerRatingDataList
        restEnergyContractedPowerRatingDataMockMvc.perform(get("/api/energy-contracted-power-rating-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(energyContractedPowerRatingData.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getEnergyContractedPowerRatingData() throws Exception {
        // Initialize the database
        energyContractedPowerRatingDataRepository.saveAndFlush(energyContractedPowerRatingData);

        // Get the energyContractedPowerRatingData
        restEnergyContractedPowerRatingDataMockMvc.perform(get("/api/energy-contracted-power-rating-data/{id}", energyContractedPowerRatingData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(energyContractedPowerRatingData.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnergyContractedPowerRatingData() throws Exception {
        // Get the energyContractedPowerRatingData
        restEnergyContractedPowerRatingDataMockMvc.perform(get("/api/energy-contracted-power-rating-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnergyContractedPowerRatingData() throws Exception {
        // Initialize the database
        energyContractedPowerRatingDataRepository.saveAndFlush(energyContractedPowerRatingData);

        int databaseSizeBeforeUpdate = energyContractedPowerRatingDataRepository.findAll().size();

        // Update the energyContractedPowerRatingData
        EnergyContractedPowerRatingData updatedEnergyContractedPowerRatingData = energyContractedPowerRatingDataRepository.findById(energyContractedPowerRatingData.getId()).get();
        // Disconnect from session so that the updates on updatedEnergyContractedPowerRatingData are not directly saved in db
        em.detach(updatedEnergyContractedPowerRatingData);
        updatedEnergyContractedPowerRatingData
            .description(UPDATED_DESCRIPTION);
        EnergyContractedPowerRatingDataDTO energyContractedPowerRatingDataDTO = energyContractedPowerRatingDataMapper.toDto(updatedEnergyContractedPowerRatingData);

        restEnergyContractedPowerRatingDataMockMvc.perform(put("/api/energy-contracted-power-rating-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyContractedPowerRatingDataDTO)))
            .andExpect(status().isOk());

        // Validate the EnergyContractedPowerRatingData in the database
        List<EnergyContractedPowerRatingData> energyContractedPowerRatingDataList = energyContractedPowerRatingDataRepository.findAll();
        assertThat(energyContractedPowerRatingDataList).hasSize(databaseSizeBeforeUpdate);
        EnergyContractedPowerRatingData testEnergyContractedPowerRatingData = energyContractedPowerRatingDataList.get(energyContractedPowerRatingDataList.size() - 1);
        assertThat(testEnergyContractedPowerRatingData.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingEnergyContractedPowerRatingData() throws Exception {
        int databaseSizeBeforeUpdate = energyContractedPowerRatingDataRepository.findAll().size();

        // Create the EnergyContractedPowerRatingData
        EnergyContractedPowerRatingDataDTO energyContractedPowerRatingDataDTO = energyContractedPowerRatingDataMapper.toDto(energyContractedPowerRatingData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnergyContractedPowerRatingDataMockMvc.perform(put("/api/energy-contracted-power-rating-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyContractedPowerRatingDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyContractedPowerRatingData in the database
        List<EnergyContractedPowerRatingData> energyContractedPowerRatingDataList = energyContractedPowerRatingDataRepository.findAll();
        assertThat(energyContractedPowerRatingDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnergyContractedPowerRatingData() throws Exception {
        // Initialize the database
        energyContractedPowerRatingDataRepository.saveAndFlush(energyContractedPowerRatingData);

        int databaseSizeBeforeDelete = energyContractedPowerRatingDataRepository.findAll().size();

        // Get the energyContractedPowerRatingData
        restEnergyContractedPowerRatingDataMockMvc.perform(delete("/api/energy-contracted-power-rating-data/{id}", energyContractedPowerRatingData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EnergyContractedPowerRatingData> energyContractedPowerRatingDataList = energyContractedPowerRatingDataRepository.findAll();
        assertThat(energyContractedPowerRatingDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyContractedPowerRatingData.class);
        EnergyContractedPowerRatingData energyContractedPowerRatingData1 = new EnergyContractedPowerRatingData();
        energyContractedPowerRatingData1.setId(1L);
        EnergyContractedPowerRatingData energyContractedPowerRatingData2 = new EnergyContractedPowerRatingData();
        energyContractedPowerRatingData2.setId(energyContractedPowerRatingData1.getId());
        assertThat(energyContractedPowerRatingData1).isEqualTo(energyContractedPowerRatingData2);
        energyContractedPowerRatingData2.setId(2L);
        assertThat(energyContractedPowerRatingData1).isNotEqualTo(energyContractedPowerRatingData2);
        energyContractedPowerRatingData1.setId(null);
        assertThat(energyContractedPowerRatingData1).isNotEqualTo(energyContractedPowerRatingData2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyContractedPowerRatingDataDTO.class);
        EnergyContractedPowerRatingDataDTO energyContractedPowerRatingDataDTO1 = new EnergyContractedPowerRatingDataDTO();
        energyContractedPowerRatingDataDTO1.setId(1L);
        EnergyContractedPowerRatingDataDTO energyContractedPowerRatingDataDTO2 = new EnergyContractedPowerRatingDataDTO();
        assertThat(energyContractedPowerRatingDataDTO1).isNotEqualTo(energyContractedPowerRatingDataDTO2);
        energyContractedPowerRatingDataDTO2.setId(energyContractedPowerRatingDataDTO1.getId());
        assertThat(energyContractedPowerRatingDataDTO1).isEqualTo(energyContractedPowerRatingDataDTO2);
        energyContractedPowerRatingDataDTO2.setId(2L);
        assertThat(energyContractedPowerRatingDataDTO1).isNotEqualTo(energyContractedPowerRatingDataDTO2);
        energyContractedPowerRatingDataDTO1.setId(null);
        assertThat(energyContractedPowerRatingDataDTO1).isNotEqualTo(energyContractedPowerRatingDataDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(energyContractedPowerRatingDataMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(energyContractedPowerRatingDataMapper.fromId(null)).isNull();
    }
}
