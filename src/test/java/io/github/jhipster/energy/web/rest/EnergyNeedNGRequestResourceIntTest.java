package io.github.jhipster.energy.web.rest;

import io.github.jhipster.energy.JhipsterEnergySampleApplicationApp;

import io.github.jhipster.energy.domain.EnergyNeedNGRequest;
import io.github.jhipster.energy.repository.EnergyNeedNGRequestRepository;
import io.github.jhipster.energy.service.EnergyNeedNGRequestService;
import io.github.jhipster.energy.service.dto.EnergyNeedNGRequestDTO;
import io.github.jhipster.energy.service.mapper.EnergyNeedNGRequestMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static io.github.jhipster.energy.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EnergyNeedNGRequestResource REST controller.
 *
 * @see EnergyNeedNGRequestResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterEnergySampleApplicationApp.class)
public class EnergyNeedNGRequestResourceIntTest {

    private static final String DEFAULT_ENERGY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ENERGY_TYPE = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private EnergyNeedNGRequestRepository energyNeedNGRequestRepository;

    @Autowired
    private EnergyNeedNGRequestMapper energyNeedNGRequestMapper;

    @Autowired
    private EnergyNeedNGRequestService energyNeedNGRequestService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnergyNeedNGRequestMockMvc;

    private EnergyNeedNGRequest energyNeedNGRequest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EnergyNeedNGRequestResource energyNeedNGRequestResource = new EnergyNeedNGRequestResource(energyNeedNGRequestService);
        this.restEnergyNeedNGRequestMockMvc = MockMvcBuilders.standaloneSetup(energyNeedNGRequestResource)
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
    public static EnergyNeedNGRequest createEntity(EntityManager em) {
        EnergyNeedNGRequest energyNeedNGRequest = new EnergyNeedNGRequest()
            .energyType(DEFAULT_ENERGY_TYPE)
            .updateDate(DEFAULT_UPDATE_DATE);
        return energyNeedNGRequest;
    }

    @Before
    public void initTest() {
        energyNeedNGRequest = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnergyNeedNGRequest() throws Exception {
        int databaseSizeBeforeCreate = energyNeedNGRequestRepository.findAll().size();

        // Create the EnergyNeedNGRequest
        EnergyNeedNGRequestDTO energyNeedNGRequestDTO = energyNeedNGRequestMapper.toDto(energyNeedNGRequest);
        restEnergyNeedNGRequestMockMvc.perform(post("/api/energy-need-ng-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNeedNGRequestDTO)))
            .andExpect(status().isCreated());

        // Validate the EnergyNeedNGRequest in the database
        List<EnergyNeedNGRequest> energyNeedNGRequestList = energyNeedNGRequestRepository.findAll();
        assertThat(energyNeedNGRequestList).hasSize(databaseSizeBeforeCreate + 1);
        EnergyNeedNGRequest testEnergyNeedNGRequest = energyNeedNGRequestList.get(energyNeedNGRequestList.size() - 1);
        assertThat(testEnergyNeedNGRequest.getEnergyType()).isEqualTo(DEFAULT_ENERGY_TYPE);
        assertThat(testEnergyNeedNGRequest.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void createEnergyNeedNGRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = energyNeedNGRequestRepository.findAll().size();

        // Create the EnergyNeedNGRequest with an existing ID
        energyNeedNGRequest.setId(1L);
        EnergyNeedNGRequestDTO energyNeedNGRequestDTO = energyNeedNGRequestMapper.toDto(energyNeedNGRequest);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnergyNeedNGRequestMockMvc.perform(post("/api/energy-need-ng-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNeedNGRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyNeedNGRequest in the database
        List<EnergyNeedNGRequest> energyNeedNGRequestList = energyNeedNGRequestRepository.findAll();
        assertThat(energyNeedNGRequestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkEnergyTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyNeedNGRequestRepository.findAll().size();
        // set the field null
        energyNeedNGRequest.setEnergyType(null);

        // Create the EnergyNeedNGRequest, which fails.
        EnergyNeedNGRequestDTO energyNeedNGRequestDTO = energyNeedNGRequestMapper.toDto(energyNeedNGRequest);

        restEnergyNeedNGRequestMockMvc.perform(post("/api/energy-need-ng-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNeedNGRequestDTO)))
            .andExpect(status().isBadRequest());

        List<EnergyNeedNGRequest> energyNeedNGRequestList = energyNeedNGRequestRepository.findAll();
        assertThat(energyNeedNGRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = energyNeedNGRequestRepository.findAll().size();
        // set the field null
        energyNeedNGRequest.setUpdateDate(null);

        // Create the EnergyNeedNGRequest, which fails.
        EnergyNeedNGRequestDTO energyNeedNGRequestDTO = energyNeedNGRequestMapper.toDto(energyNeedNGRequest);

        restEnergyNeedNGRequestMockMvc.perform(post("/api/energy-need-ng-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNeedNGRequestDTO)))
            .andExpect(status().isBadRequest());

        List<EnergyNeedNGRequest> energyNeedNGRequestList = energyNeedNGRequestRepository.findAll();
        assertThat(energyNeedNGRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEnergyNeedNGRequests() throws Exception {
        // Initialize the database
        energyNeedNGRequestRepository.saveAndFlush(energyNeedNGRequest);

        // Get all the energyNeedNGRequestList
        restEnergyNeedNGRequestMockMvc.perform(get("/api/energy-need-ng-requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(energyNeedNGRequest.getId().intValue())))
            .andExpect(jsonPath("$.[*].energyType").value(hasItem(DEFAULT_ENERGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getEnergyNeedNGRequest() throws Exception {
        // Initialize the database
        energyNeedNGRequestRepository.saveAndFlush(energyNeedNGRequest);

        // Get the energyNeedNGRequest
        restEnergyNeedNGRequestMockMvc.perform(get("/api/energy-need-ng-requests/{id}", energyNeedNGRequest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(energyNeedNGRequest.getId().intValue()))
            .andExpect(jsonPath("$.energyType").value(DEFAULT_ENERGY_TYPE.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnergyNeedNGRequest() throws Exception {
        // Get the energyNeedNGRequest
        restEnergyNeedNGRequestMockMvc.perform(get("/api/energy-need-ng-requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnergyNeedNGRequest() throws Exception {
        // Initialize the database
        energyNeedNGRequestRepository.saveAndFlush(energyNeedNGRequest);

        int databaseSizeBeforeUpdate = energyNeedNGRequestRepository.findAll().size();

        // Update the energyNeedNGRequest
        EnergyNeedNGRequest updatedEnergyNeedNGRequest = energyNeedNGRequestRepository.findById(energyNeedNGRequest.getId()).get();
        // Disconnect from session so that the updates on updatedEnergyNeedNGRequest are not directly saved in db
        em.detach(updatedEnergyNeedNGRequest);
        updatedEnergyNeedNGRequest
            .energyType(UPDATED_ENERGY_TYPE)
            .updateDate(UPDATED_UPDATE_DATE);
        EnergyNeedNGRequestDTO energyNeedNGRequestDTO = energyNeedNGRequestMapper.toDto(updatedEnergyNeedNGRequest);

        restEnergyNeedNGRequestMockMvc.perform(put("/api/energy-need-ng-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNeedNGRequestDTO)))
            .andExpect(status().isOk());

        // Validate the EnergyNeedNGRequest in the database
        List<EnergyNeedNGRequest> energyNeedNGRequestList = energyNeedNGRequestRepository.findAll();
        assertThat(energyNeedNGRequestList).hasSize(databaseSizeBeforeUpdate);
        EnergyNeedNGRequest testEnergyNeedNGRequest = energyNeedNGRequestList.get(energyNeedNGRequestList.size() - 1);
        assertThat(testEnergyNeedNGRequest.getEnergyType()).isEqualTo(UPDATED_ENERGY_TYPE);
        assertThat(testEnergyNeedNGRequest.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingEnergyNeedNGRequest() throws Exception {
        int databaseSizeBeforeUpdate = energyNeedNGRequestRepository.findAll().size();

        // Create the EnergyNeedNGRequest
        EnergyNeedNGRequestDTO energyNeedNGRequestDTO = energyNeedNGRequestMapper.toDto(energyNeedNGRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnergyNeedNGRequestMockMvc.perform(put("/api/energy-need-ng-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(energyNeedNGRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EnergyNeedNGRequest in the database
        List<EnergyNeedNGRequest> energyNeedNGRequestList = energyNeedNGRequestRepository.findAll();
        assertThat(energyNeedNGRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnergyNeedNGRequest() throws Exception {
        // Initialize the database
        energyNeedNGRequestRepository.saveAndFlush(energyNeedNGRequest);

        int databaseSizeBeforeDelete = energyNeedNGRequestRepository.findAll().size();

        // Get the energyNeedNGRequest
        restEnergyNeedNGRequestMockMvc.perform(delete("/api/energy-need-ng-requests/{id}", energyNeedNGRequest.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EnergyNeedNGRequest> energyNeedNGRequestList = energyNeedNGRequestRepository.findAll();
        assertThat(energyNeedNGRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyNeedNGRequest.class);
        EnergyNeedNGRequest energyNeedNGRequest1 = new EnergyNeedNGRequest();
        energyNeedNGRequest1.setId(1L);
        EnergyNeedNGRequest energyNeedNGRequest2 = new EnergyNeedNGRequest();
        energyNeedNGRequest2.setId(energyNeedNGRequest1.getId());
        assertThat(energyNeedNGRequest1).isEqualTo(energyNeedNGRequest2);
        energyNeedNGRequest2.setId(2L);
        assertThat(energyNeedNGRequest1).isNotEqualTo(energyNeedNGRequest2);
        energyNeedNGRequest1.setId(null);
        assertThat(energyNeedNGRequest1).isNotEqualTo(energyNeedNGRequest2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnergyNeedNGRequestDTO.class);
        EnergyNeedNGRequestDTO energyNeedNGRequestDTO1 = new EnergyNeedNGRequestDTO();
        energyNeedNGRequestDTO1.setId(1L);
        EnergyNeedNGRequestDTO energyNeedNGRequestDTO2 = new EnergyNeedNGRequestDTO();
        assertThat(energyNeedNGRequestDTO1).isNotEqualTo(energyNeedNGRequestDTO2);
        energyNeedNGRequestDTO2.setId(energyNeedNGRequestDTO1.getId());
        assertThat(energyNeedNGRequestDTO1).isEqualTo(energyNeedNGRequestDTO2);
        energyNeedNGRequestDTO2.setId(2L);
        assertThat(energyNeedNGRequestDTO1).isNotEqualTo(energyNeedNGRequestDTO2);
        energyNeedNGRequestDTO1.setId(null);
        assertThat(energyNeedNGRequestDTO1).isNotEqualTo(energyNeedNGRequestDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(energyNeedNGRequestMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(energyNeedNGRequestMapper.fromId(null)).isNull();
    }
}
