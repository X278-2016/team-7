package org.jhipster.com.web.rest;

import org.jhipster.com.Team7App;

import org.jhipster.com.domain.EquipmentSet;
import org.jhipster.com.repository.EquipmentSetRepository;
import org.jhipster.com.repository.search.EquipmentSetSearchRepository;

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
 * Test class for the EquipmentSetResource REST controller.
 *
 * @see EquipmentSetResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Team7App.class)
public class EquipmentSetResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    @Inject
    private EquipmentSetRepository equipmentSetRepository;

    @Inject
    private EquipmentSetSearchRepository equipmentSetSearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restEquipmentSetMockMvc;

    private EquipmentSet equipmentSet;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EquipmentSetResource equipmentSetResource = new EquipmentSetResource();
        ReflectionTestUtils.setField(equipmentSetResource, "equipmentSetSearchRepository", equipmentSetSearchRepository);
        ReflectionTestUtils.setField(equipmentSetResource, "equipmentSetRepository", equipmentSetRepository);
        this.restEquipmentSetMockMvc = MockMvcBuilders.standaloneSetup(equipmentSetResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EquipmentSet createEntity(EntityManager em) {
        EquipmentSet equipmentSet = new EquipmentSet()
                .name(DEFAULT_NAME);
        return equipmentSet;
    }

    @Before
    public void initTest() {
        equipmentSetSearchRepository.deleteAll();
        equipmentSet = createEntity(em);
    }

    @Test
    @Transactional
    public void createEquipmentSet() throws Exception {
        int databaseSizeBeforeCreate = equipmentSetRepository.findAll().size();

        // Create the EquipmentSet

        restEquipmentSetMockMvc.perform(post("/api/equipment-sets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(equipmentSet)))
                .andExpect(status().isCreated());

        // Validate the EquipmentSet in the database
        List<EquipmentSet> equipmentSets = equipmentSetRepository.findAll();
        assertThat(equipmentSets).hasSize(databaseSizeBeforeCreate + 1);
        EquipmentSet testEquipmentSet = equipmentSets.get(equipmentSets.size() - 1);
        assertThat(testEquipmentSet.getName()).isEqualTo(DEFAULT_NAME);

        // Validate the EquipmentSet in ElasticSearch
        EquipmentSet equipmentSetEs = equipmentSetSearchRepository.findOne(testEquipmentSet.getId());
        assertThat(equipmentSetEs).isEqualToComparingFieldByField(testEquipmentSet);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = equipmentSetRepository.findAll().size();
        // set the field null
        equipmentSet.setName(null);

        // Create the EquipmentSet, which fails.

        restEquipmentSetMockMvc.perform(post("/api/equipment-sets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(equipmentSet)))
                .andExpect(status().isBadRequest());

        List<EquipmentSet> equipmentSets = equipmentSetRepository.findAll();
        assertThat(equipmentSets).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEquipmentSets() throws Exception {
        // Initialize the database
        equipmentSetRepository.saveAndFlush(equipmentSet);

        // Get all the equipmentSets
        restEquipmentSetMockMvc.perform(get("/api/equipment-sets?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(equipmentSet.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getEquipmentSet() throws Exception {
        // Initialize the database
        equipmentSetRepository.saveAndFlush(equipmentSet);

        // Get the equipmentSet
        restEquipmentSetMockMvc.perform(get("/api/equipment-sets/{id}", equipmentSet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(equipmentSet.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEquipmentSet() throws Exception {
        // Get the equipmentSet
        restEquipmentSetMockMvc.perform(get("/api/equipment-sets/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEquipmentSet() throws Exception {
        // Initialize the database
        equipmentSetRepository.saveAndFlush(equipmentSet);
        equipmentSetSearchRepository.save(equipmentSet);
        int databaseSizeBeforeUpdate = equipmentSetRepository.findAll().size();

        // Update the equipmentSet
        EquipmentSet updatedEquipmentSet = equipmentSetRepository.findOne(equipmentSet.getId());
        updatedEquipmentSet
                .name(UPDATED_NAME);

        restEquipmentSetMockMvc.perform(put("/api/equipment-sets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedEquipmentSet)))
                .andExpect(status().isOk());

        // Validate the EquipmentSet in the database
        List<EquipmentSet> equipmentSets = equipmentSetRepository.findAll();
        assertThat(equipmentSets).hasSize(databaseSizeBeforeUpdate);
        EquipmentSet testEquipmentSet = equipmentSets.get(equipmentSets.size() - 1);
        assertThat(testEquipmentSet.getName()).isEqualTo(UPDATED_NAME);

        // Validate the EquipmentSet in ElasticSearch
        EquipmentSet equipmentSetEs = equipmentSetSearchRepository.findOne(testEquipmentSet.getId());
        assertThat(equipmentSetEs).isEqualToComparingFieldByField(testEquipmentSet);
    }

    @Test
    @Transactional
    public void deleteEquipmentSet() throws Exception {
        // Initialize the database
        equipmentSetRepository.saveAndFlush(equipmentSet);
        equipmentSetSearchRepository.save(equipmentSet);
        int databaseSizeBeforeDelete = equipmentSetRepository.findAll().size();

        // Get the equipmentSet
        restEquipmentSetMockMvc.perform(delete("/api/equipment-sets/{id}", equipmentSet.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean equipmentSetExistsInEs = equipmentSetSearchRepository.exists(equipmentSet.getId());
        assertThat(equipmentSetExistsInEs).isFalse();

        // Validate the database is empty
        List<EquipmentSet> equipmentSets = equipmentSetRepository.findAll();
        assertThat(equipmentSets).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchEquipmentSet() throws Exception {
        // Initialize the database
        equipmentSetRepository.saveAndFlush(equipmentSet);
        equipmentSetSearchRepository.save(equipmentSet);

        // Search the equipmentSet
        restEquipmentSetMockMvc.perform(get("/api/_search/equipment-sets?query=id:" + equipmentSet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(equipmentSet.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
}
