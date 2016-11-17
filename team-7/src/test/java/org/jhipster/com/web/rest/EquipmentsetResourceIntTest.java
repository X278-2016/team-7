package org.jhipster.com.web.rest;

import org.jhipster.com.Team7App;

import org.jhipster.com.domain.Equipmentset;
import org.jhipster.com.repository.EquipmentsetRepository;
import org.jhipster.com.repository.search.EquipmentsetSearchRepository;

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
 * Test class for the EquipmentsetResource REST controller.
 *
 * @see EquipmentsetResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Team7App.class)
public class EquipmentsetResourceIntTest {

    private static final String DEFAULT_M_EQUIPMENT_SET_NAME = "AAAAA";
    private static final String UPDATED_M_EQUIPMENT_SET_NAME = "BBBBB";

    @Inject
    private EquipmentsetRepository equipmentsetRepository;

    @Inject
    private EquipmentsetSearchRepository equipmentsetSearchRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restEquipmentsetMockMvc;

    private Equipmentset equipmentset;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EquipmentsetResource equipmentsetResource = new EquipmentsetResource();
        ReflectionTestUtils.setField(equipmentsetResource, "equipmentsetSearchRepository", equipmentsetSearchRepository);
        ReflectionTestUtils.setField(equipmentsetResource, "equipmentsetRepository", equipmentsetRepository);
        this.restEquipmentsetMockMvc = MockMvcBuilders.standaloneSetup(equipmentsetResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Equipmentset createEntity(EntityManager em) {
        Equipmentset equipmentset = new Equipmentset()
                .mEquipmentSetName(DEFAULT_M_EQUIPMENT_SET_NAME);
        return equipmentset;
    }

    @Before
    public void initTest() {
        equipmentsetSearchRepository.deleteAll();
        equipmentset = createEntity(em);
    }

    @Test
    @Transactional
    public void createEquipmentset() throws Exception {
        int databaseSizeBeforeCreate = equipmentsetRepository.findAll().size();

        // Create the Equipmentset

        restEquipmentsetMockMvc.perform(post("/api/equipmentsets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(equipmentset)))
                .andExpect(status().isCreated());

        // Validate the Equipmentset in the database
        List<Equipmentset> equipmentsets = equipmentsetRepository.findAll();
        assertThat(equipmentsets).hasSize(databaseSizeBeforeCreate + 1);
        Equipmentset testEquipmentset = equipmentsets.get(equipmentsets.size() - 1);
        assertThat(testEquipmentset.getmEquipmentSetName()).isEqualTo(DEFAULT_M_EQUIPMENT_SET_NAME);

        // Validate the Equipmentset in ElasticSearch
        Equipmentset equipmentsetEs = equipmentsetSearchRepository.findOne(testEquipmentset.getId());
        assertThat(equipmentsetEs).isEqualToComparingFieldByField(testEquipmentset);
    }

    @Test
    @Transactional
    public void checkmEquipmentSetNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = equipmentsetRepository.findAll().size();
        // set the field null
        equipmentset.setmEquipmentSetName(null);

        // Create the Equipmentset, which fails.

        restEquipmentsetMockMvc.perform(post("/api/equipmentsets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(equipmentset)))
                .andExpect(status().isBadRequest());

        List<Equipmentset> equipmentsets = equipmentsetRepository.findAll();
        assertThat(equipmentsets).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEquipmentsets() throws Exception {
        // Initialize the database
        equipmentsetRepository.saveAndFlush(equipmentset);

        // Get all the equipmentsets
        restEquipmentsetMockMvc.perform(get("/api/equipmentsets?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(equipmentset.getId().intValue())))
                .andExpect(jsonPath("$.[*].mEquipmentSetName").value(hasItem(DEFAULT_M_EQUIPMENT_SET_NAME.toString())));
    }

    @Test
    @Transactional
    public void getEquipmentset() throws Exception {
        // Initialize the database
        equipmentsetRepository.saveAndFlush(equipmentset);

        // Get the equipmentset
        restEquipmentsetMockMvc.perform(get("/api/equipmentsets/{id}", equipmentset.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(equipmentset.getId().intValue()))
            .andExpect(jsonPath("$.mEquipmentSetName").value(DEFAULT_M_EQUIPMENT_SET_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEquipmentset() throws Exception {
        // Get the equipmentset
        restEquipmentsetMockMvc.perform(get("/api/equipmentsets/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEquipmentset() throws Exception {
        // Initialize the database
        equipmentsetRepository.saveAndFlush(equipmentset);
        equipmentsetSearchRepository.save(equipmentset);
        int databaseSizeBeforeUpdate = equipmentsetRepository.findAll().size();

        // Update the equipmentset
        Equipmentset updatedEquipmentset = equipmentsetRepository.findOne(equipmentset.getId());
        updatedEquipmentset
                .mEquipmentSetName(UPDATED_M_EQUIPMENT_SET_NAME);

        restEquipmentsetMockMvc.perform(put("/api/equipmentsets")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedEquipmentset)))
                .andExpect(status().isOk());

        // Validate the Equipmentset in the database
        List<Equipmentset> equipmentsets = equipmentsetRepository.findAll();
        assertThat(equipmentsets).hasSize(databaseSizeBeforeUpdate);
        Equipmentset testEquipmentset = equipmentsets.get(equipmentsets.size() - 1);
        assertThat(testEquipmentset.getmEquipmentSetName()).isEqualTo(UPDATED_M_EQUIPMENT_SET_NAME);

        // Validate the Equipmentset in ElasticSearch
        Equipmentset equipmentsetEs = equipmentsetSearchRepository.findOne(testEquipmentset.getId());
        assertThat(equipmentsetEs).isEqualToComparingFieldByField(testEquipmentset);
    }

    @Test
    @Transactional
    public void deleteEquipmentset() throws Exception {
        // Initialize the database
        equipmentsetRepository.saveAndFlush(equipmentset);
        equipmentsetSearchRepository.save(equipmentset);
        int databaseSizeBeforeDelete = equipmentsetRepository.findAll().size();

        // Get the equipmentset
        restEquipmentsetMockMvc.perform(delete("/api/equipmentsets/{id}", equipmentset.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate ElasticSearch is empty
        boolean equipmentsetExistsInEs = equipmentsetSearchRepository.exists(equipmentset.getId());
        assertThat(equipmentsetExistsInEs).isFalse();

        // Validate the database is empty
        List<Equipmentset> equipmentsets = equipmentsetRepository.findAll();
        assertThat(equipmentsets).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchEquipmentset() throws Exception {
        // Initialize the database
        equipmentsetRepository.saveAndFlush(equipmentset);
        equipmentsetSearchRepository.save(equipmentset);

        // Search the equipmentset
        restEquipmentsetMockMvc.perform(get("/api/_search/equipmentsets?query=id:" + equipmentset.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(equipmentset.getId().intValue())))
            .andExpect(jsonPath("$.[*].mEquipmentSetName").value(hasItem(DEFAULT_M_EQUIPMENT_SET_NAME.toString())));
    }
}
