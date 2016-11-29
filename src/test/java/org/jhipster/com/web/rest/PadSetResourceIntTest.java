package org.jhipster.com.web.rest;

import org.jhipster.com.Team7App;

import org.jhipster.com.domain.PadSet;
import org.jhipster.com.repository.PadSetRepository;
import org.jhipster.com.repository.search.PadSetSearchRepository;

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
 * Test class for the PadSetResource REST controller.
 *
 * @see PadSetResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Team7App.class)
public class PadSetResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    @Inject
    private PadSetRepository padSetRepository;

    @Inject
    private PadSetSearchRepository padSetSearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restPadSetMockMvc;

    private PadSet padSet;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PadSetResource padSetResource = new PadSetResource();
        ReflectionTestUtils.setField(padSetResource, "padSetSearchRepository", padSetSearchRepository);
        ReflectionTestUtils.setField(padSetResource, "padSetRepository", padSetRepository);
        this.restPadSetMockMvc = MockMvcBuilders.standaloneSetup(padSetResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PadSet createEntity(EntityManager em) {
        PadSet padSet = new PadSet()
                .name(DEFAULT_NAME)
                .latitude(DEFAULT_LATITUDE)
                .longitude(DEFAULT_LONGITUDE);
        return padSet;
    }

    @Before
    public void initTest() {
        padSetSearchRepository.deleteAll();
        padSet = createEntity(em);
    }

    @Test
    @Transactional
    public void createPadSet() throws Exception {
        int databaseSizeBeforeCreate = padSetRepository.findAll().size();

        // Create the PadSet

        restPadSetMockMvc.perform(post("/api/pad-sets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(padSet)))
                .andExpect(status().isCreated());

        // Validate the PadSet in the database
        List<PadSet> padSets = padSetRepository.findAll();
        assertThat(padSets).hasSize(databaseSizeBeforeCreate + 1);
        PadSet testPadSet = padSets.get(padSets.size() - 1);
        assertThat(testPadSet.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPadSet.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testPadSet.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);

        // Validate the PadSet in ElasticSearch
        PadSet padSetEs = padSetSearchRepository.findOne(testPadSet.getId());
        assertThat(padSetEs).isEqualToComparingFieldByField(testPadSet);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = padSetRepository.findAll().size();
        // set the field null
        padSet.setName(null);

        // Create the PadSet, which fails.

        restPadSetMockMvc.perform(post("/api/pad-sets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(padSet)))
                .andExpect(status().isBadRequest());

        List<PadSet> padSets = padSetRepository.findAll();
        assertThat(padSets).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = padSetRepository.findAll().size();
        // set the field null
        padSet.setLatitude(null);

        // Create the PadSet, which fails.

        restPadSetMockMvc.perform(post("/api/pad-sets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(padSet)))
                .andExpect(status().isBadRequest());

        List<PadSet> padSets = padSetRepository.findAll();
        assertThat(padSets).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = padSetRepository.findAll().size();
        // set the field null
        padSet.setLongitude(null);

        // Create the PadSet, which fails.

        restPadSetMockMvc.perform(post("/api/pad-sets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(padSet)))
                .andExpect(status().isBadRequest());

        List<PadSet> padSets = padSetRepository.findAll();
        assertThat(padSets).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPadSets() throws Exception {
        // Initialize the database
        padSetRepository.saveAndFlush(padSet);

        // Get all the padSets
        restPadSetMockMvc.perform(get("/api/pad-sets?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(padSet.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
                .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())));
    }

    @Test
    @Transactional
    public void getPadSet() throws Exception {
        // Initialize the database
        padSetRepository.saveAndFlush(padSet);

        // Get the padSet
        restPadSetMockMvc.perform(get("/api/pad-sets/{id}", padSet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(padSet.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPadSet() throws Exception {
        // Get the padSet
        restPadSetMockMvc.perform(get("/api/pad-sets/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePadSet() throws Exception {
        // Initialize the database
        padSetRepository.saveAndFlush(padSet);
        padSetSearchRepository.save(padSet);
        int databaseSizeBeforeUpdate = padSetRepository.findAll().size();

        // Update the padSet
        PadSet updatedPadSet = padSetRepository.findOne(padSet.getId());
        updatedPadSet
                .name(UPDATED_NAME)
                .latitude(UPDATED_LATITUDE)
                .longitude(UPDATED_LONGITUDE);

        restPadSetMockMvc.perform(put("/api/pad-sets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedPadSet)))
                .andExpect(status().isOk());

        // Validate the PadSet in the database
        List<PadSet> padSets = padSetRepository.findAll();
        assertThat(padSets).hasSize(databaseSizeBeforeUpdate);
        PadSet testPadSet = padSets.get(padSets.size() - 1);
        assertThat(testPadSet.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPadSet.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testPadSet.getLongitude()).isEqualTo(UPDATED_LONGITUDE);

        // Validate the PadSet in ElasticSearch
        PadSet padSetEs = padSetSearchRepository.findOne(testPadSet.getId());
        assertThat(padSetEs).isEqualToComparingFieldByField(testPadSet);
    }

    @Test
    @Transactional
    public void deletePadSet() throws Exception {
        // Initialize the database
        padSetRepository.saveAndFlush(padSet);
        padSetSearchRepository.save(padSet);
        int databaseSizeBeforeDelete = padSetRepository.findAll().size();

        // Get the padSet
        restPadSetMockMvc.perform(delete("/api/pad-sets/{id}", padSet.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean padSetExistsInEs = padSetSearchRepository.exists(padSet.getId());
        assertThat(padSetExistsInEs).isFalse();

        // Validate the database is empty
        List<PadSet> padSets = padSetRepository.findAll();
        assertThat(padSets).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchPadSet() throws Exception {
        // Initialize the database
        padSetRepository.saveAndFlush(padSet);
        padSetSearchRepository.save(padSet);

        // Search the padSet
        restPadSetMockMvc.perform(get("/api/_search/pad-sets?query=id:" + padSet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(padSet.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())));
    }
}
