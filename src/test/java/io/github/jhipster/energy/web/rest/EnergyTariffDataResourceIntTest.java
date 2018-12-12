package io.github.jhipster.energy.web.rest;

import io.github.jhipster.energy.JhipsterEnergySampleApplicationApp;

import io.github.jhipster.energy.domain.EnergyTariffData;
import io.github.jhipster.energy.repository.EnergyTariffDataRepository;
import io.github.jhipster.energy.service.EnergyTariffDataService;
import io.github.jhipster.energy.service.dto.EnergyTariffDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyTariffDataMapper;
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
 * Test class for the EnergyTariffDataResource REST controller.
 *
 * @see EnergyTariffDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterEnergySampleApplicationApp.class)
public class EnergyTariffDataResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private EnergyTariffDataRepository energyTariffDataRepository;

    @Autowired
    private EnergyTariffDataMapper energyTariffDataMapper;

    @Autowired
    private EnergyTariffDataService energyTariffDataService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnergyTariffDataMockMvc;

    private EnergyTariffData energyTariffData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnergyTariffDataResource energyTariffDataResource = new EnergyTariffDataResource(energyTariffDataService);
        this.restEnergyTariffDataMockMvc = MockMvcBuilders.standaloneSetup(energyTariffDataResource)
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
    public static EnergyTariffData createEntity(EntityManager em) {
        EnergyTariffData energyTariffData = new EnergyTariffData()
            .description(DEFAULT_DESCRIPTION);
        return energyTariffData;
    }

    @Before
    public void initTest() {
        energyTariffData = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnergyTariffData() throws Exception {
        int databaseSizeBeforeCreate = energyTariffDataRepository.findAll().size();

        // Create the EnergyTariffData
        EnergyTariffDataDTO energyTariffDataDTO = energyTariffDataMapper.toDto(energyTariffData);
        restEnergyTariffDataMockMvc.perform(post("/api/energy-tariff-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyTariffDataDTO)))
            .andExpect(status().isCreated());

        // Validate the EnergyTariffData in the database
        List<EnergyTariffData> energyTariffDataList = energyTariffDataRepository.findAll();
        assertThat(energyTariffDataList).hasSize(databaseSizeBeforeCreate + 1);
        EnergyTariffData testEnergyTariffData = energyTariffDataList.get(energyTariffDataList.size() - 1);
        assertThat(testEnergyTariffData.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createEnergyTariffDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = energyTariffDataRepository.findAll().size();

        // Create the EnergyTariffData with an existing ID
        energyTariffData.setId(1L);
        EnergyTariffDataDTO energyTariffDataDTO = energyTariffDataMapper.toDto(energyTariffData);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnergyTariffDataMockMvc.perform(post("/api/energy-tariff-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyTariffDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyTariffData in the database
        List<EnergyTariffData> energyTariffDataList = energyTariffDataRepository.findAll();
        assertThat(energyTariffDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyTariffDataRepository.findAll().size();
        // set the field null
        energyTariffData.setDescription(null);

        // Create the EnergyTariffData, which fails.
        EnergyTariffDataDTO energyTariffDataDTO = energyTariffDataMapper.toDto(energyTariffData);

        restEnergyTariffDataMockMvc.perform(post("/api/energy-tariff-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyTariffDataDTO)))
            .andExpect(status().isBadRequest());

        List<EnergyTariffData> energyTariffDataList = energyTariffDataRepository.findAll();
        assertThat(energyTariffDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEnergyTariffData() throws Exception {
        // Initialize the database
        energyTariffDataRepository.saveAndFlush(energyTariffData);

        // Get all the energyTariffDataList
        restEnergyTariffDataMockMvc.perform(get("/api/energy-tariff-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(energyTariffData.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getEnergyTariffData() throws Exception {
        // Initialize the database
        energyTariffDataRepository.saveAndFlush(energyTariffData);

        // Get the energyTariffData
        restEnergyTariffDataMockMvc.perform(get("/api/energy-tariff-data/{id}", energyTariffData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(energyTariffData.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnergyTariffData() throws Exception {
        // Get the energyTariffData
        restEnergyTariffDataMockMvc.perform(get("/api/energy-tariff-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnergyTariffData() throws Exception {
        // Initialize the database
        energyTariffDataRepository.saveAndFlush(energyTariffData);

        int databaseSizeBeforeUpdate = energyTariffDataRepository.findAll().size();

        // Update the energyTariffData
        EnergyTariffData updatedEnergyTariffData = energyTariffDataRepository.findById(energyTariffData.getId()).get();
        // Disconnect from session so that the updates on updatedEnergyTariffData are not directly saved in db
        em.detach(updatedEnergyTariffData);
        updatedEnergyTariffData
            .description(UPDATED_DESCRIPTION);
        EnergyTariffDataDTO energyTariffDataDTO = energyTariffDataMapper.toDto(updatedEnergyTariffData);

        restEnergyTariffDataMockMvc.perform(put("/api/energy-tariff-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyTariffDataDTO)))
            .andExpect(status().isOk());

        // Validate the EnergyTariffData in the database
        List<EnergyTariffData> energyTariffDataList = energyTariffDataRepository.findAll();
        assertThat(energyTariffDataList).hasSize(databaseSizeBeforeUpdate);
        EnergyTariffData testEnergyTariffData = energyTariffDataList.get(energyTariffDataList.size() - 1);
        assertThat(testEnergyTariffData.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingEnergyTariffData() throws Exception {
        int databaseSizeBeforeUpdate = energyTariffDataRepository.findAll().size();

        // Create the EnergyTariffData
        EnergyTariffDataDTO energyTariffDataDTO = energyTariffDataMapper.toDto(energyTariffData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnergyTariffDataMockMvc.perform(put("/api/energy-tariff-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyTariffDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyTariffData in the database
        List<EnergyTariffData> energyTariffDataList = energyTariffDataRepository.findAll();
        assertThat(energyTariffDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnergyTariffData() throws Exception {
        // Initialize the database
        energyTariffDataRepository.saveAndFlush(energyTariffData);

        int databaseSizeBeforeDelete = energyTariffDataRepository.findAll().size();

        // Get the energyTariffData
        restEnergyTariffDataMockMvc.perform(delete("/api/energy-tariff-data/{id}", energyTariffData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EnergyTariffData> energyTariffDataList = energyTariffDataRepository.findAll();
        assertThat(energyTariffDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyTariffData.class);
        EnergyTariffData energyTariffData1 = new EnergyTariffData();
        energyTariffData1.setId(1L);
        EnergyTariffData energyTariffData2 = new EnergyTariffData();
        energyTariffData2.setId(energyTariffData1.getId());
        assertThat(energyTariffData1).isEqualTo(energyTariffData2);
        energyTariffData2.setId(2L);
        assertThat(energyTariffData1).isNotEqualTo(energyTariffData2);
        energyTariffData1.setId(null);
        assertThat(energyTariffData1).isNotEqualTo(energyTariffData2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyTariffDataDTO.class);
        EnergyTariffDataDTO energyTariffDataDTO1 = new EnergyTariffDataDTO();
        energyTariffDataDTO1.setId(1L);
        EnergyTariffDataDTO energyTariffDataDTO2 = new EnergyTariffDataDTO();
        assertThat(energyTariffDataDTO1).isNotEqualTo(energyTariffDataDTO2);
        energyTariffDataDTO2.setId(energyTariffDataDTO1.getId());
        assertThat(energyTariffDataDTO1).isEqualTo(energyTariffDataDTO2);
        energyTariffDataDTO2.setId(2L);
        assertThat(energyTariffDataDTO1).isNotEqualTo(energyTariffDataDTO2);
        energyTariffDataDTO1.setId(null);
        assertThat(energyTariffDataDTO1).isNotEqualTo(energyTariffDataDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(energyTariffDataMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(energyTariffDataMapper.fromId(null)).isNull();
    }
}
