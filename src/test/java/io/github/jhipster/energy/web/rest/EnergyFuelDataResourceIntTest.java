package io.github.jhipster.energy.web.rest;

import io.github.jhipster.energy.JhipsterEnergySampleApplicationApp;

import io.github.jhipster.energy.domain.EnergyFuelData;
import io.github.jhipster.energy.repository.EnergyFuelDataRepository;
import io.github.jhipster.energy.service.EnergyFuelDataService;
import io.github.jhipster.energy.service.dto.EnergyFuelDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyFuelDataMapper;
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
 * Test class for the EnergyFuelDataResource REST controller.
 *
 * @see EnergyFuelDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterEnergySampleApplicationApp.class)
public class EnergyFuelDataResourceIntTest {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT = "BBBBBBBBBB";

    @Autowired
    private EnergyFuelDataRepository energyFuelDataRepository;

    @Autowired
    private EnergyFuelDataMapper energyFuelDataMapper;

    @Autowired
    private EnergyFuelDataService energyFuelDataService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnergyFuelDataMockMvc;

    private EnergyFuelData energyFuelData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnergyFuelDataResource energyFuelDataResource = new EnergyFuelDataResource(energyFuelDataService);
        this.restEnergyFuelDataMockMvc = MockMvcBuilders.standaloneSetup(energyFuelDataResource)
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
    public static EnergyFuelData createEntity(EntityManager em) {
        EnergyFuelData energyFuelData = new EnergyFuelData()
            .type(DEFAULT_TYPE)
            .product(DEFAULT_PRODUCT);
        return energyFuelData;
    }

    @Before
    public void initTest() {
        energyFuelData = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnergyFuelData() throws Exception {
        int databaseSizeBeforeCreate = energyFuelDataRepository.findAll().size();

        // Create the EnergyFuelData
        EnergyFuelDataDTO energyFuelDataDTO = energyFuelDataMapper.toDto(energyFuelData);
        restEnergyFuelDataMockMvc.perform(post("/api/energy-fuel-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyFuelDataDTO)))
            .andExpect(status().isCreated());

        // Validate the EnergyFuelData in the database
        List<EnergyFuelData> energyFuelDataList = energyFuelDataRepository.findAll();
        assertThat(energyFuelDataList).hasSize(databaseSizeBeforeCreate + 1);
        EnergyFuelData testEnergyFuelData = energyFuelDataList.get(energyFuelDataList.size() - 1);
        assertThat(testEnergyFuelData.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testEnergyFuelData.getProduct()).isEqualTo(DEFAULT_PRODUCT);
    }

    @Test
    @Transactional
    public void createEnergyFuelDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = energyFuelDataRepository.findAll().size();

        // Create the EnergyFuelData with an existing ID
        energyFuelData.setId(1L);
        EnergyFuelDataDTO energyFuelDataDTO = energyFuelDataMapper.toDto(energyFuelData);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnergyFuelDataMockMvc.perform(post("/api/energy-fuel-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyFuelDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyFuelData in the database
        List<EnergyFuelData> energyFuelDataList = energyFuelDataRepository.findAll();
        assertThat(energyFuelDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyFuelDataRepository.findAll().size();
        // set the field null
        energyFuelData.setType(null);

        // Create the EnergyFuelData, which fails.
        EnergyFuelDataDTO energyFuelDataDTO = energyFuelDataMapper.toDto(energyFuelData);

        restEnergyFuelDataMockMvc.perform(post("/api/energy-fuel-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyFuelDataDTO)))
            .andExpect(status().isBadRequest());

        List<EnergyFuelData> energyFuelDataList = energyFuelDataRepository.findAll();
        assertThat(energyFuelDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProductIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyFuelDataRepository.findAll().size();
        // set the field null
        energyFuelData.setProduct(null);

        // Create the EnergyFuelData, which fails.
        EnergyFuelDataDTO energyFuelDataDTO = energyFuelDataMapper.toDto(energyFuelData);

        restEnergyFuelDataMockMvc.perform(post("/api/energy-fuel-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyFuelDataDTO)))
            .andExpect(status().isBadRequest());

        List<EnergyFuelData> energyFuelDataList = energyFuelDataRepository.findAll();
        assertThat(energyFuelDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEnergyFuelData() throws Exception {
        // Initialize the database
        energyFuelDataRepository.saveAndFlush(energyFuelData);

        // Get all the energyFuelDataList
        restEnergyFuelDataMockMvc.perform(get("/api/energy-fuel-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(energyFuelData.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].product").value(hasItem(DEFAULT_PRODUCT.toString())));
    }
    
    @Test
    @Transactional
    public void getEnergyFuelData() throws Exception {
        // Initialize the database
        energyFuelDataRepository.saveAndFlush(energyFuelData);

        // Get the energyFuelData
        restEnergyFuelDataMockMvc.perform(get("/api/energy-fuel-data/{id}", energyFuelData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(energyFuelData.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.product").value(DEFAULT_PRODUCT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnergyFuelData() throws Exception {
        // Get the energyFuelData
        restEnergyFuelDataMockMvc.perform(get("/api/energy-fuel-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnergyFuelData() throws Exception {
        // Initialize the database
        energyFuelDataRepository.saveAndFlush(energyFuelData);

        int databaseSizeBeforeUpdate = energyFuelDataRepository.findAll().size();

        // Update the energyFuelData
        EnergyFuelData updatedEnergyFuelData = energyFuelDataRepository.findById(energyFuelData.getId()).get();
        // Disconnect from session so that the updates on updatedEnergyFuelData are not directly saved in db
        em.detach(updatedEnergyFuelData);
        updatedEnergyFuelData
            .type(UPDATED_TYPE)
            .product(UPDATED_PRODUCT);
        EnergyFuelDataDTO energyFuelDataDTO = energyFuelDataMapper.toDto(updatedEnergyFuelData);

        restEnergyFuelDataMockMvc.perform(put("/api/energy-fuel-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyFuelDataDTO)))
            .andExpect(status().isOk());

        // Validate the EnergyFuelData in the database
        List<EnergyFuelData> energyFuelDataList = energyFuelDataRepository.findAll();
        assertThat(energyFuelDataList).hasSize(databaseSizeBeforeUpdate);
        EnergyFuelData testEnergyFuelData = energyFuelDataList.get(energyFuelDataList.size() - 1);
        assertThat(testEnergyFuelData.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testEnergyFuelData.getProduct()).isEqualTo(UPDATED_PRODUCT);
    }

    @Test
    @Transactional
    public void updateNonExistingEnergyFuelData() throws Exception {
        int databaseSizeBeforeUpdate = energyFuelDataRepository.findAll().size();

        // Create the EnergyFuelData
        EnergyFuelDataDTO energyFuelDataDTO = energyFuelDataMapper.toDto(energyFuelData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnergyFuelDataMockMvc.perform(put("/api/energy-fuel-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyFuelDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyFuelData in the database
        List<EnergyFuelData> energyFuelDataList = energyFuelDataRepository.findAll();
        assertThat(energyFuelDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnergyFuelData() throws Exception {
        // Initialize the database
        energyFuelDataRepository.saveAndFlush(energyFuelData);

        int databaseSizeBeforeDelete = energyFuelDataRepository.findAll().size();

        // Get the energyFuelData
        restEnergyFuelDataMockMvc.perform(delete("/api/energy-fuel-data/{id}", energyFuelData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EnergyFuelData> energyFuelDataList = energyFuelDataRepository.findAll();
        assertThat(energyFuelDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyFuelData.class);
        EnergyFuelData energyFuelData1 = new EnergyFuelData();
        energyFuelData1.setId(1L);
        EnergyFuelData energyFuelData2 = new EnergyFuelData();
        energyFuelData2.setId(energyFuelData1.getId());
        assertThat(energyFuelData1).isEqualTo(energyFuelData2);
        energyFuelData2.setId(2L);
        assertThat(energyFuelData1).isNotEqualTo(energyFuelData2);
        energyFuelData1.setId(null);
        assertThat(energyFuelData1).isNotEqualTo(energyFuelData2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyFuelDataDTO.class);
        EnergyFuelDataDTO energyFuelDataDTO1 = new EnergyFuelDataDTO();
        energyFuelDataDTO1.setId(1L);
        EnergyFuelDataDTO energyFuelDataDTO2 = new EnergyFuelDataDTO();
        assertThat(energyFuelDataDTO1).isNotEqualTo(energyFuelDataDTO2);
        energyFuelDataDTO2.setId(energyFuelDataDTO1.getId());
        assertThat(energyFuelDataDTO1).isEqualTo(energyFuelDataDTO2);
        energyFuelDataDTO2.setId(2L);
        assertThat(energyFuelDataDTO1).isNotEqualTo(energyFuelDataDTO2);
        energyFuelDataDTO1.setId(null);
        assertThat(energyFuelDataDTO1).isNotEqualTo(energyFuelDataDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(energyFuelDataMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(energyFuelDataMapper.fromId(null)).isNull();
    }
}
