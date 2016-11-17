package org.jhipster.com.web.rest;

import org.jhipster.com.Team7App;

import org.jhipster.com.domain.Metric;
import org.jhipster.com.repository.MetricRepository;
import org.jhipster.com.repository.search.MetricSearchRepository;

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
 * Test class for the MetricResource REST controller.
 *
 * @see MetricResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Team7App.class)
public class MetricResourceIntTest {

    private static final String DEFAULT_M_METRIC_NAME = "AAAAA";
    private static final String UPDATED_M_METRIC_NAME = "BBBBB";

    private static final Double DEFAULT_M_METRIC_VALUE = 1D;
    private static final Double UPDATED_M_METRIC_VALUE = 2D;

    @Inject
    private MetricRepository metricRepository;

    @Inject
    private MetricSearchRepository metricSearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restMetricMockMvc;

    private Metric metric;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MetricResource metricResource = new MetricResource();
        ReflectionTestUtils.setField(metricResource, "metricSearchRepository", metricSearchRepository);
        ReflectionTestUtils.setField(metricResource, "metricRepository", metricRepository);
        this.restMetricMockMvc = MockMvcBuilders.standaloneSetup(metricResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Metric createEntity(EntityManager em) {
        Metric metric = new Metric()
                .mMetricName(DEFAULT_M_METRIC_NAME)
                .mMetricValue(DEFAULT_M_METRIC_VALUE);
        return metric;
    }

    @Before
    public void initTest() {
        metricSearchRepository.deleteAll();
        metric = createEntity(em);
    }

    @Test
    @Transactional
    public void createMetric() throws Exception {
        int databaseSizeBeforeCreate = metricRepository.findAll().size();

        // Create the Metric

        restMetricMockMvc.perform(post("/api/metrics")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(metric)))
                .andExpect(status().isCreated());

        // Validate the Metric in the database
        List<Metric> metrics = metricRepository.findAll();
        assertThat(metrics).hasSize(databaseSizeBeforeCreate + 1);
        Metric testMetric = metrics.get(metrics.size() - 1);
        assertThat(testMetric.getmMetricName()).isEqualTo(DEFAULT_M_METRIC_NAME);
        assertThat(testMetric.getmMetricValue()).isEqualTo(DEFAULT_M_METRIC_VALUE);

        // Validate the Metric in ElasticSearch
        Metric metricEs = metricSearchRepository.findOne(testMetric.getId());
        assertThat(metricEs).isEqualToComparingFieldByField(testMetric);
    }

    @Test
    @Transactional
    public void checkmMetricNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = metricRepository.findAll().size();
        // set the field null
        metric.setmMetricName(null);

        // Create the Metric, which fails.

        restMetricMockMvc.perform(post("/api/metrics")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(metric)))
                .andExpect(status().isBadRequest());

        List<Metric> metrics = metricRepository.findAll();
        assertThat(metrics).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkmMetricValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = metricRepository.findAll().size();
        // set the field null
        metric.setmMetricValue(null);

        // Create the Metric, which fails.

        restMetricMockMvc.perform(post("/api/metrics")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(metric)))
                .andExpect(status().isBadRequest());

        List<Metric> metrics = metricRepository.findAll();
        assertThat(metrics).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMetrics() throws Exception {
        // Initialize the database
        metricRepository.saveAndFlush(metric);

        // Get all the metrics
        restMetricMockMvc.perform(get("/api/metrics?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(metric.getId().intValue())))
                .andExpect(jsonPath("$.[*].mMetricName").value(hasItem(DEFAULT_M_METRIC_NAME.toString())))
                .andExpect(jsonPath("$.[*].mMetricValue").value(hasItem(DEFAULT_M_METRIC_VALUE.doubleValue())));
    }

    @Test
    @Transactional
    public void getMetric() throws Exception {
        // Initialize the database
        metricRepository.saveAndFlush(metric);

        // Get the metric
        restMetricMockMvc.perform(get("/api/metrics/{id}", metric.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(metric.getId().intValue()))
            .andExpect(jsonPath("$.mMetricName").value(DEFAULT_M_METRIC_NAME.toString()))
            .andExpect(jsonPath("$.mMetricValue").value(DEFAULT_M_METRIC_VALUE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMetric() throws Exception {
        // Get the metric
        restMetricMockMvc.perform(get("/api/metrics/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMetric() throws Exception {
        // Initialize the database
        metricRepository.saveAndFlush(metric);
        metricSearchRepository.save(metric);
        int databaseSizeBeforeUpdate = metricRepository.findAll().size();

        // Update the metric
        Metric updatedMetric = metricRepository.findOne(metric.getId());
        updatedMetric
                .mMetricName(UPDATED_M_METRIC_NAME)
                .mMetricValue(UPDATED_M_METRIC_VALUE);

        restMetricMockMvc.perform(put("/api/metrics")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedMetric)))
                .andExpect(status().isOk());

        // Validate the Metric in the database
        List<Metric> metrics = metricRepository.findAll();
        assertThat(metrics).hasSize(databaseSizeBeforeUpdate);
        Metric testMetric = metrics.get(metrics.size() - 1);
        assertThat(testMetric.getmMetricName()).isEqualTo(UPDATED_M_METRIC_NAME);
        assertThat(testMetric.getmMetricValue()).isEqualTo(UPDATED_M_METRIC_VALUE);

        // Validate the Metric in ElasticSearch
        Metric metricEs = metricSearchRepository.findOne(testMetric.getId());
        assertThat(metricEs).isEqualToComparingFieldByField(testMetric);
    }

    @Test
    @Transactional
    public void deleteMetric() throws Exception {
        // Initialize the database
        metricRepository.saveAndFlush(metric);
        metricSearchRepository.save(metric);
        int databaseSizeBeforeDelete = metricRepository.findAll().size();

        // Get the metric
        restMetricMockMvc.perform(delete("/api/metrics/{id}", metric.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean metricExistsInEs = metricSearchRepository.exists(metric.getId());
        assertThat(metricExistsInEs).isFalse();

        // Validate the database is empty
        List<Metric> metrics = metricRepository.findAll();
        assertThat(metrics).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchMetric() throws Exception {
        // Initialize the database
        metricRepository.saveAndFlush(metric);
        metricSearchRepository.save(metric);

        // Search the metric
        restMetricMockMvc.perform(get("/api/_search/metrics?query=id:" + metric.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(metric.getId().intValue())))
            .andExpect(jsonPath("$.[*].mMetricName").value(hasItem(DEFAULT_M_METRIC_NAME.toString())))
            .andExpect(jsonPath("$.[*].mMetricValue").value(hasItem(DEFAULT_M_METRIC_VALUE.doubleValue())));
    }
}
