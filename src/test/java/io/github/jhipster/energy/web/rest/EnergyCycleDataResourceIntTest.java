package io.github.jhipster.energy.web.rest;

import io.github.jhipster.energy.JhipsterEnergySampleApplicationApp;

import io.github.jhipster.energy.domain.EnergyCycleData;
import io.github.jhipster.energy.repository.EnergyCycleDataRepository;
import io.github.jhipster.energy.service.EnergyCycleDataService;
import io.github.jhipster.energy.service.dto.EnergyCycleDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyCycleDataMapper;
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
 * Test class for the EnergyCycleDataResource REST controller.
 *
 * @see EnergyCycleDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterEnergySampleApplicationApp.class)
public class EnergyCycleDataResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private EnergyCycleDataRepository energyCycleDataRepository;

    @Autowired
    private EnergyCycleDataMapper energyCycleDataMapper;

    @Autowired
    private EnergyCycleDataService energyCycleDataService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnergyCycleDataMockMvc;

    private EnergyCycleData energyCycleData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnergyCycleDataResource energyCycleDataResource = new EnergyCycleDataResource(energyCycleDataService);
        this.restEnergyCycleDataMockMvc = MockMvcBuilders.standaloneSetup(energyCycleDataResource)
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
    public static EnergyCycleData createEntity(EntityManager em) {
        EnergyCycleData energyCycleData = new EnergyCycleData()
            .description(DEFAULT_DESCRIPTION);
        return energyCycleData;
    }

    @Before
    public void initTest() {
        energyCycleData = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnergyCycleData() throws Exception {
        int databaseSizeBeforeCreate = energyCycleDataRepository.findAll().size();

        // Create the EnergyCycleData
        EnergyCycleDataDTO energyCycleDataDTO = energyCycleDataMapper.toDto(energyCycleData);
        restEnergyCycleDataMockMvc.perform(post("/api/energy-cycle-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyCycleDataDTO)))
            .andExpect(status().isCreated());

        // Validate the EnergyCycleData in the database
        List<EnergyCycleData> energyCycleDataList = energyCycleDataRepository.findAll();
        assertThat(energyCycleDataList).hasSize(databaseSizeBeforeCreate + 1);
        EnergyCycleData testEnergyCycleData = energyCycleDataList.get(energyCycleDataList.size() - 1);
        assertThat(testEnergyCycleData.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createEnergyCycleDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = energyCycleDataRepository.findAll().size();

        // Create the EnergyCycleData with an existing ID
        energyCycleData.setId(1L);
        EnergyCycleDataDTO energyCycleDataDTO = energyCycleDataMapper.toDto(energyCycleData);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnergyCycleDataMockMvc.perform(post("/api/energy-cycle-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyCycleDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyCycleData in the database
        List<EnergyCycleData> energyCycleDataList = energyCycleDataRepository.findAll();
        assertThat(energyCycleDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyCycleDataRepository.findAll().size();
        // set the field null
        energyCycleData.setDescription(null);

        // Create the EnergyCycleData, which fails.
        EnergyCycleDataDTO energyCycleDataDTO = energyCycleDataMapper.toDto(energyCycleData);

        restEnergyCycleDataMockMvc.perform(post("/api/energy-cycle-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyCycleDataDTO)))
            .andExpect(status().isBadRequest());

        List<EnergyCycleData> energyCycleDataList = energyCycleDataRepository.findAll();
        assertThat(energyCycleDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEnergyCycleData() throws Exception {
        // Initialize the database
        energyCycleDataRepository.saveAndFlush(energyCycleData);

        // Get all the energyCycleDataList
        restEnergyCycleDataMockMvc.perform(get("/api/energy-cycle-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(energyCycleData.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getEnergyCycleData() throws Exception {
        // Initialize the database
        energyCycleDataRepository.saveAndFlush(energyCycleData);

        // Get the energyCycleData
        restEnergyCycleDataMockMvc.perform(get("/api/energy-cycle-data/{id}", energyCycleData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(energyCycleData.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnergyCycleData() throws Exception {
        // Get the energyCycleData
        restEnergyCycleDataMockMvc.perform(get("/api/energy-cycle-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnergyCycleData() throws Exception {
        // Initialize the database
        energyCycleDataRepository.saveAndFlush(energyCycleData);

        int databaseSizeBeforeUpdate = energyCycleDataRepository.findAll().size();

        // Update the energyCycleData
        EnergyCycleData updatedEnergyCycleData = energyCycleDataRepository.findById(energyCycleData.getId()).get();
        // Disconnect from session so that the updates on updatedEnergyCycleData are not directly saved in db
        em.detach(updatedEnergyCycleData);
        updatedEnergyCycleData
            .description(UPDATED_DESCRIPTION);
        EnergyCycleDataDTO energyCycleDataDTO = energyCycleDataMapper.toDto(updatedEnergyCycleData);

        restEnergyCycleDataMockMvc.perform(put("/api/energy-cycle-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyCycleDataDTO)))
            .andExpect(status().isOk());

        // Validate the EnergyCycleData in the database
        List<EnergyCycleData> energyCycleDataList = energyCycleDataRepository.findAll();
        assertThat(energyCycleDataList).hasSize(databaseSizeBeforeUpdate);
        EnergyCycleData testEnergyCycleData = energyCycleDataList.get(energyCycleDataList.size() - 1);
        assertThat(testEnergyCycleData.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingEnergyCycleData() throws Exception {
        int databaseSizeBeforeUpdate = energyCycleDataRepository.findAll().size();

        // Create the EnergyCycleData
        EnergyCycleDataDTO energyCycleDataDTO = energyCycleDataMapper.toDto(energyCycleData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnergyCycleDataMockMvc.perform(put("/api/energy-cycle-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyCycleDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyCycleData in the database
        List<EnergyCycleData> energyCycleDataList = energyCycleDataRepository.findAll();
        assertThat(energyCycleDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnergyCycleData() throws Exception {
        // Initialize the database
        energyCycleDataRepository.saveAndFlush(energyCycleData);

        int databaseSizeBeforeDelete = energyCycleDataRepository.findAll().size();

        // Get the energyCycleData
        restEnergyCycleDataMockMvc.perform(delete("/api/energy-cycle-data/{id}", energyCycleData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EnergyCycleData> energyCycleDataList = energyCycleDataRepository.findAll();
        assertThat(energyCycleDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyCycleData.class);
        EnergyCycleData energyCycleData1 = new EnergyCycleData();
        energyCycleData1.setId(1L);
        EnergyCycleData energyCycleData2 = new EnergyCycleData();
        energyCycleData2.setId(energyCycleData1.getId());
        assertThat(energyCycleData1).isEqualTo(energyCycleData2);
        energyCycleData2.setId(2L);
        assertThat(energyCycleData1).isNotEqualTo(energyCycleData2);
        energyCycleData1.setId(null);
        assertThat(energyCycleData1).isNotEqualTo(energyCycleData2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyCycleDataDTO.class);
        EnergyCycleDataDTO energyCycleDataDTO1 = new EnergyCycleDataDTO();
        energyCycleDataDTO1.setId(1L);
        EnergyCycleDataDTO energyCycleDataDTO2 = new EnergyCycleDataDTO();
        assertThat(energyCycleDataDTO1).isNotEqualTo(energyCycleDataDTO2);
        energyCycleDataDTO2.setId(energyCycleDataDTO1.getId());
        assertThat(energyCycleDataDTO1).isEqualTo(energyCycleDataDTO2);
        energyCycleDataDTO2.setId(2L);
        assertThat(energyCycleDataDTO1).isNotEqualTo(energyCycleDataDTO2);
        energyCycleDataDTO1.setId(null);
        assertThat(energyCycleDataDTO1).isNotEqualTo(energyCycleDataDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(energyCycleDataMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(energyCycleDataMapper.fromId(null)).isNull();
    }
}
