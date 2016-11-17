package org.jhipster.com.web.rest;

import org.jhipster.com.Team7App;

import org.jhipster.com.domain.Padset;
import org.jhipster.com.repository.PadsetRepository;
import org.jhipster.com.repository.search.PadsetSearchRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PadsetResource REST controller.
 *
 * @see PadsetResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Team7App.class)
public class PadsetResourceIntTest {

    private static final String DEFAULT_M_PAD_SET_NAME = "AAAAA";
    private static final String UPDATED_M_PAD_SET_NAME = "BBBBB";

    private static final Double DEFAULT_M_LATITUDE = 1D;
    private static final Double UPDATED_M_LATITUDE = 2D;

    private static final Double DEFAULT_M_LONGITUDE = 1D;
    private static final Double UPDATED_M_LONGITUDE = 2D;

    @Inject
    private PadsetRepository padsetRepository;

    @Inject
    private PadsetSearchRepository padsetSearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restPadsetMockMvc;

    private Padset padset;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PadsetResource padsetResource = new PadsetResource();
        ReflectionTestUtils.setField(padsetResource, "padsetSearchRepository", padsetSearchRepository);
        ReflectionTestUtils.setField(padsetResource, "padsetRepository", padsetRepository);
        this.restPadsetMockMvc = MockMvcBuilders.standaloneSetup(padsetResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Padset createEntity(EntityManager em) {
        Padset padset = new Padset()
                .mPadSetName(DEFAULT_M_PAD_SET_NAME)
                .mLatitude(DEFAULT_M_LATITUDE)
                .mLongitude(DEFAULT_M_LONGITUDE);
        return padset;
    }

    @Before
    public void initTest() {
        padsetSearchRepository.deleteAll();
        padset = createEntity(em);
    }

    @Test
    @Transactional
    public void createPadset() throws Exception {
        int databaseSizeBeforeCreate = padsetRepository.findAll().size();

        // Create the Padset

        restPadsetMockMvc.perform(post("/api/padsets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(padset)))
                .andExpect(status().isCreated());

        // Validate the Padset in the database
        List<Padset> padsets = padsetRepository.findAll();
        assertThat(padsets).hasSize(databaseSizeBeforeCreate + 1);
        Padset testPadset = padsets.get(padsets.size() - 1);
        assertThat(testPadset.getmPadSetName()).isEqualTo(DEFAULT_M_PAD_SET_NAME);
        assertThat(testPadset.getmLatitude()).isEqualTo(DEFAULT_M_LATITUDE);
        assertThat(testPadset.getmLongitude()).isEqualTo(DEFAULT_M_LONGITUDE);

        // Validate the Padset in ElasticSearch
        Padset padsetEs = padsetSearchRepository.findOne(testPadset.getId());
        assertThat(padsetEs).isEqualToComparingFieldByField(testPadset);
    }

    @Test
    @Transactional
    public void checkmPadSetNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = padsetRepository.findAll().size();
        // set the field null
        padset.setmPadSetName(null);

        // Create the Padset, which fails.

        restPadsetMockMvc.perform(post("/api/padsets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(padset)))
                .andExpect(status().isBadRequest());

        List<Padset> padsets = padsetRepository.findAll();
        assertThat(padsets).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkmLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = padsetRepository.findAll().size();
        // set the field null
        padset.setmLatitude(null);

        // Create the Padset, which fails.

        restPadsetMockMvc.perform(post("/api/padsets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(padset)))
                .andExpect(status().isBadRequest());

        List<Padset> padsets = padsetRepository.findAll();
        assertThat(padsets).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkmLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = padsetRepository.findAll().size();
        // set the field null
        padset.setmLongitude(null);

        // Create the Padset, which fails.

        restPadsetMockMvc.perform(post("/api/padsets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(padset)))
                .andExpect(status().isBadRequest());

        List<Padset> padsets = padsetRepository.findAll();
        assertThat(padsets).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPadsets() throws Exception {
        // Initialize the database
        padsetRepository.saveAndFlush(padset);

        // Get all the padsets
        restPadsetMockMvc.perform(get("/api/padsets?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(padset.getId().intValue())))
                .andExpect(jsonPath("$.[*].mPadSetName").value(hasItem(DEFAULT_M_PAD_SET_NAME.toString())))
                .andExpect(jsonPath("$.[*].mLatitude").value(hasItem(DEFAULT_M_LATITUDE.doubleValue())))
                .andExpect(jsonPath("$.[*].mLongitude").value(hasItem(DEFAULT_M_LONGITUDE.doubleValue())));
    }

    @Test
    @Transactional
    public void getPadset() throws Exception {
        // Initialize the database
        padsetRepository.saveAndFlush(padset);

        // Get the padset
        restPadsetMockMvc.perform(get("/api/padsets/{id}", padset.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(padset.getId().intValue()))
            .andExpect(jsonPath("$.mPadSetName").value(DEFAULT_M_PAD_SET_NAME.toString()))
            .andExpect(jsonPath("$.mLatitude").value(DEFAULT_M_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.mLongitude").value(DEFAULT_M_LONGITUDE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPadset() throws Exception {
        // Get the padset
        restPadsetMockMvc.perform(get("/api/padsets/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePadset() throws Exception {
        // Initialize the database
        padsetRepository.saveAndFlush(padset);
        padsetSearchRepository.save(padset);
        int databaseSizeBeforeUpdate = padsetRepository.findAll().size();

        // Update the padset
        Padset updatedPadset = padsetRepository.findOne(padset.getId());
        updatedPadset
                .mPadSetName(UPDATED_M_PAD_SET_NAME)
                .mLatitude(UPDATED_M_LATITUDE)
                .mLongitude(UPDATED_M_LONGITUDE);

        restPadsetMockMvc.perform(put("/api/padsets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedPadset)))
                .andExpect(status().isOk());

        // Validate the Padset in the database
        List<Padset> padsets = padsetRepository.findAll();
        assertThat(padsets).hasSize(databaseSizeBeforeUpdate);
        Padset testPadset = padsets.get(padsets.size() - 1);
        assertThat(testPadset.getmPadSetName()).isEqualTo(UPDATED_M_PAD_SET_NAME);
        assertThat(testPadset.getmLatitude()).isEqualTo(UPDATED_M_LATITUDE);
        assertThat(testPadset.getmLongitude()).isEqualTo(UPDATED_M_LONGITUDE);

        // Validate the Padset in ElasticSearch
        Padset padsetEs = padsetSearchRepository.findOne(testPadset.getId());
        assertThat(padsetEs).isEqualToComparingFieldByField(testPadset);
    }

    @Test
    @Transactional
    public void deletePadset() throws Exception {
        // Initialize the database
        padsetRepository.saveAndFlush(padset);
        padsetSearchRepository.save(padset);
        int databaseSizeBeforeDelete = padsetRepository.findAll().size();

        // Get the padset
        restPadsetMockMvc.perform(delete("/api/padsets/{id}", padset.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean padsetExistsInEs = padsetSearchRepository.exists(padset.getId());
        assertThat(padsetExistsInEs).isFalse();

        // Validate the database is empty
        List<Padset> padsets = padsetRepository.findAll();
        assertThat(padsets).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchPadset() throws Exception {
        // Initialize the database
        padsetRepository.saveAndFlush(padset);
        padsetSearchRepository.save(padset);

        // Search the padset
        restPadsetMockMvc.perform(get("/api/_search/padsets?query=id:" + padset.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(padset.getId().intValue())))
            .andExpect(jsonPath("$.[*].mPadSetName").value(hasItem(DEFAULT_M_PAD_SET_NAME.toString())))
            .andExpect(jsonPath("$.[*].mLatitude").value(hasItem(DEFAULT_M_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].mLongitude").value(hasItem(DEFAULT_M_LONGITUDE.doubleValue())));
    }
}
