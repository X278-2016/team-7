package org.jhipster.com.web.rest;

import org.jhipster.com.Team7App;

import org.jhipster.com.domain.Threshold;
import org.jhipster.com.repository.ThresholdRepository;
import org.jhipster.com.repository.search.ThresholdSearchRepository;

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
 * Test class for the ThresholdResource REST controller.
 *
 * @see ThresholdResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Team7App.class)
public class ThresholdResourceIntTest {

    private static final Double DEFAULT_VALUE = 1D;
    private static final Double UPDATED_VALUE = 2D;

    @Inject
    private ThresholdRepository thresholdRepository;

    @Inject
    private ThresholdSearchRepository thresholdSearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restThresholdMockMvc;

    private Threshold threshold;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ThresholdResource thresholdResource = new ThresholdResource();
        ReflectionTestUtils.setField(thresholdResource, "thresholdSearchRepository", thresholdSearchRepository);
        ReflectionTestUtils.setField(thresholdResource, "thresholdRepository", thresholdRepository);
        this.restThresholdMockMvc = MockMvcBuilders.standaloneSetup(thresholdResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Threshold createEntity(EntityManager em) {
        Threshold threshold = new Threshold()
                .value(DEFAULT_VALUE);
        return threshold;
    }

    @Before
    public void initTest() {
        thresholdSearchRepository.deleteAll();
        threshold = createEntity(em);
    }

    @Test
    @Transactional
    public void createThreshold() throws Exception {
        int databaseSizeBeforeCreate = thresholdRepository.findAll().size();

        // Create the Threshold

        restThresholdMockMvc.perform(post("/api/thresholds")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(threshold)))
                .andExpect(status().isCreated());

        // Validate the Threshold in the database
        List<Threshold> thresholds = thresholdRepository.findAll();
        assertThat(thresholds).hasSize(databaseSizeBeforeCreate + 1);
        Threshold testThreshold = thresholds.get(thresholds.size() - 1);
        assertThat(testThreshold.getValue()).isEqualTo(DEFAULT_VALUE);

        // Validate the Threshold in ElasticSearch
        Threshold thresholdEs = thresholdSearchRepository.findOne(testThreshold.getId());
        assertThat(thresholdEs).isEqualToComparingFieldByField(testThreshold);
    }

    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = thresholdRepository.findAll().size();
        // set the field null
        threshold.setValue(null);

        // Create the Threshold, which fails.

        restThresholdMockMvc.perform(post("/api/thresholds")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(threshold)))
                .andExpect(status().isBadRequest());

        List<Threshold> thresholds = thresholdRepository.findAll();
        assertThat(thresholds).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllThresholds() throws Exception {
        // Initialize the database
        thresholdRepository.saveAndFlush(threshold);

        // Get all the thresholds
        restThresholdMockMvc.perform(get("/api/thresholds?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(threshold.getId().intValue())))
                .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())));
    }

    @Test
    @Transactional
    public void getThreshold() throws Exception {
        // Initialize the database
        thresholdRepository.saveAndFlush(threshold);

        // Get the threshold
        restThresholdMockMvc.perform(get("/api/thresholds/{id}", threshold.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(threshold.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingThreshold() throws Exception {
        // Get the threshold
        restThresholdMockMvc.perform(get("/api/thresholds/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateThreshold() throws Exception {
        // Initialize the database
        thresholdRepository.saveAndFlush(threshold);
        thresholdSearchRepository.save(threshold);
        int databaseSizeBeforeUpdate = thresholdRepository.findAll().size();

        // Update the threshold
        Threshold updatedThreshold = thresholdRepository.findOne(threshold.getId());
        updatedThreshold
                .value(UPDATED_VALUE);

        restThresholdMockMvc.perform(put("/api/thresholds")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedThreshold)))
                .andExpect(status().isOk());

        // Validate the Threshold in the database
        List<Threshold> thresholds = thresholdRepository.findAll();
        assertThat(thresholds).hasSize(databaseSizeBeforeUpdate);
        Threshold testThreshold = thresholds.get(thresholds.size() - 1);
        assertThat(testThreshold.getValue()).isEqualTo(UPDATED_VALUE);

        // Validate the Threshold in ElasticSearch
        Threshold thresholdEs = thresholdSearchRepository.findOne(testThreshold.getId());
        assertThat(thresholdEs).isEqualToComparingFieldByField(testThreshold);
    }

    @Test
    @Transactional
    public void deleteThreshold() throws Exception {
        // Initialize the database
        thresholdRepository.saveAndFlush(threshold);
        thresholdSearchRepository.save(threshold);
        int databaseSizeBeforeDelete = thresholdRepository.findAll().size();

        // Get the threshold
        restThresholdMockMvc.perform(delete("/api/thresholds/{id}", threshold.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean thresholdExistsInEs = thresholdSearchRepository.exists(threshold.getId());
        assertThat(thresholdExistsInEs).isFalse();

        // Validate the database is empty
        List<Threshold> thresholds = thresholdRepository.findAll();
        assertThat(thresholds).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchThreshold() throws Exception {
        // Initialize the database
        thresholdRepository.saveAndFlush(threshold);
        thresholdSearchRepository.save(threshold);

        // Search the threshold
        restThresholdMockMvc.perform(get("/api/_search/thresholds?query=id:" + threshold.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(threshold.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())));
    }
}
