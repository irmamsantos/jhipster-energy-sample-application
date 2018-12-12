package io.github.jhipster.energy.web.rest;

import io.github.jhipster.energy.JhipsterEnergySampleApplicationApp;

import io.github.jhipster.energy.domain.UserSAC;
import io.github.jhipster.energy.repository.UserSACRepository;
import io.github.jhipster.energy.service.UserSACService;
import io.github.jhipster.energy.service.dto.UserSACDTO;
import io.github.jhipster.energy.service.mapper.UserSACMapper;
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
 * Test class for the UserSACResource REST controller.
 *
 * @see UserSACResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterEnergySampleApplicationApp.class)
public class UserSACResourceIntTest {

    private static final Integer DEFAULT_USER_SAC_ID = 1;
    private static final Integer UPDATED_USER_SAC_ID = 2;

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_USER_EMAIL = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private UserSACRepository userSACRepository;

    @Autowired
    private UserSACMapper userSACMapper;

    @Autowired
    private UserSACService userSACService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserSACMockMvc;

    private UserSAC userSAC;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserSACResource userSACResource = new UserSACResource(userSACService);
        this.restUserSACMockMvc = MockMvcBuilders.standaloneSetup(userSACResource)
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
    public static UserSAC createEntity(EntityManager em) {
        UserSAC userSAC = new UserSAC()
            .userSACId(DEFAULT_USER_SAC_ID)
            .userName(DEFAULT_USER_NAME)
            .userEmail(DEFAULT_USER_EMAIL)
            .updateDate(DEFAULT_UPDATE_DATE);
        return userSAC;
    }

    @Before
    public void initTest() {
        userSAC = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserSAC() throws Exception {
        int databaseSizeBeforeCreate = userSACRepository.findAll().size();

        // Create the UserSAC
        UserSACDTO userSACDTO = userSACMapper.toDto(userSAC);
        restUserSACMockMvc.perform(post("/api/user-sacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userSACDTO)))
            .andExpect(status().isCreated());

        // Validate the UserSAC in the database
        List<UserSAC> userSACList = userSACRepository.findAll();
        assertThat(userSACList).hasSize(databaseSizeBeforeCreate + 1);
        UserSAC testUserSAC = userSACList.get(userSACList.size() - 1);
        assertThat(testUserSAC.getUserSACId()).isEqualTo(DEFAULT_USER_SAC_ID);
        assertThat(testUserSAC.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testUserSAC.getUserEmail()).isEqualTo(DEFAULT_USER_EMAIL);
        assertThat(testUserSAC.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void createUserSACWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userSACRepository.findAll().size();

        // Create the UserSAC with an existing ID
        userSAC.setId(1L);
        UserSACDTO userSACDTO = userSACMapper.toDto(userSAC);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserSACMockMvc.perform(post("/api/user-sacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userSACDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserSAC in the database
        List<UserSAC> userSACList = userSACRepository.findAll();
        assertThat(userSACList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUserSACIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = userSACRepository.findAll().size();
        // set the field null
        userSAC.setUserSACId(null);

        // Create the UserSAC, which fails.
        UserSACDTO userSACDTO = userSACMapper.toDto(userSAC);

        restUserSACMockMvc.perform(post("/api/user-sacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userSACDTO)))
            .andExpect(status().isBadRequest());

        List<UserSAC> userSACList = userSACRepository.findAll();
        assertThat(userSACList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = userSACRepository.findAll().size();
        // set the field null
        userSAC.setUserName(null);

        // Create the UserSAC, which fails.
        UserSACDTO userSACDTO = userSACMapper.toDto(userSAC);

        restUserSACMockMvc.perform(post("/api/user-sacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userSACDTO)))
            .andExpect(status().isBadRequest());

        List<UserSAC> userSACList = userSACRepository.findAll();
        assertThat(userSACList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = userSACRepository.findAll().size();
        // set the field null
        userSAC.setUserEmail(null);

        // Create the UserSAC, which fails.
        UserSACDTO userSACDTO = userSACMapper.toDto(userSAC);

        restUserSACMockMvc.perform(post("/api/user-sacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userSACDTO)))
            .andExpect(status().isBadRequest());

        List<UserSAC> userSACList = userSACRepository.findAll();
        assertThat(userSACList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpdateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = userSACRepository.findAll().size();
        // set the field null
        userSAC.setUpdateDate(null);

        // Create the UserSAC, which fails.
        UserSACDTO userSACDTO = userSACMapper.toDto(userSAC);

        restUserSACMockMvc.perform(post("/api/user-sacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userSACDTO)))
            .andExpect(status().isBadRequest());

        List<UserSAC> userSACList = userSACRepository.findAll();
        assertThat(userSACList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUserSACS() throws Exception {
        // Initialize the database
        userSACRepository.saveAndFlush(userSAC);

        // Get all the userSACList
        restUserSACMockMvc.perform(get("/api/user-sacs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userSAC.getId().intValue())))
            .andExpect(jsonPath("$.[*].userSACId").value(hasItem(DEFAULT_USER_SAC_ID)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].userEmail").value(hasItem(DEFAULT_USER_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getUserSAC() throws Exception {
        // Initialize the database
        userSACRepository.saveAndFlush(userSAC);

        // Get the userSAC
        restUserSACMockMvc.perform(get("/api/user-sacs/{id}", userSAC.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userSAC.getId().intValue()))
            .andExpect(jsonPath("$.userSACId").value(DEFAULT_USER_SAC_ID))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.userEmail").value(DEFAULT_USER_EMAIL.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserSAC() throws Exception {
        // Get the userSAC
        restUserSACMockMvc.perform(get("/api/user-sacs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserSAC() throws Exception {
        // Initialize the database
        userSACRepository.saveAndFlush(userSAC);

        int databaseSizeBeforeUpdate = userSACRepository.findAll().size();

        // Update the userSAC
        UserSAC updatedUserSAC = userSACRepository.findById(userSAC.getId()).get();
        // Disconnect from session so that the updates on updatedUserSAC are not directly saved in db
        em.detach(updatedUserSAC);
        updatedUserSAC
            .userSACId(UPDATED_USER_SAC_ID)
            .userName(UPDATED_USER_NAME)
            .userEmail(UPDATED_USER_EMAIL)
            .updateDate(UPDATED_UPDATE_DATE);
        UserSACDTO userSACDTO = userSACMapper.toDto(updatedUserSAC);

        restUserSACMockMvc.perform(put("/api/user-sacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userSACDTO)))
            .andExpect(status().isOk());

        // Validate the UserSAC in the database
        List<UserSAC> userSACList = userSACRepository.findAll();
        assertThat(userSACList).hasSize(databaseSizeBeforeUpdate);
        UserSAC testUserSAC = userSACList.get(userSACList.size() - 1);
        assertThat(testUserSAC.getUserSACId()).isEqualTo(UPDATED_USER_SAC_ID);
        assertThat(testUserSAC.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testUserSAC.getUserEmail()).isEqualTo(UPDATED_USER_EMAIL);
        assertThat(testUserSAC.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserSAC() throws Exception {
        int databaseSizeBeforeUpdate = userSACRepository.findAll().size();

        // Create the UserSAC
        UserSACDTO userSACDTO = userSACMapper.toDto(userSAC);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserSACMockMvc.perform(put("/api/user-sacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userSACDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserSAC in the database
        List<UserSAC> userSACList = userSACRepository.findAll();
        assertThat(userSACList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserSAC() throws Exception {
        // Initialize the database
        userSACRepository.saveAndFlush(userSAC);

        int databaseSizeBeforeDelete = userSACRepository.findAll().size();

        // Get the userSAC
        restUserSACMockMvc.perform(delete("/api/user-sacs/{id}", userSAC.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserSAC> userSACList = userSACRepository.findAll();
        assertThat(userSACList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserSAC.class);
        UserSAC userSAC1 = new UserSAC();
        userSAC1.setId(1L);
        UserSAC userSAC2 = new UserSAC();
        userSAC2.setId(userSAC1.getId());
        assertThat(userSAC1).isEqualTo(userSAC2);
        userSAC2.setId(2L);
        assertThat(userSAC1).isNotEqualTo(userSAC2);
        userSAC1.setId(null);
        assertThat(userSAC1).isNotEqualTo(userSAC2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserSACDTO.class);
        UserSACDTO userSACDTO1 = new UserSACDTO();
        userSACDTO1.setId(1L);
        UserSACDTO userSACDTO2 = new UserSACDTO();
        assertThat(userSACDTO1).isNotEqualTo(userSACDTO2);
        userSACDTO2.setId(userSACDTO1.getId());
        assertThat(userSACDTO1).isEqualTo(userSACDTO2);
        userSACDTO2.setId(2L);
        assertThat(userSACDTO1).isNotEqualTo(userSACDTO2);
        userSACDTO1.setId(null);
        assertThat(userSACDTO1).isNotEqualTo(userSACDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userSACMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userSACMapper.fromId(null)).isNull();
    }
}
