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

    private static final Double DEFAULT_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO = 1D;
    private static final Double UPDATED_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO = 2D;

    private static final Double DEFAULT_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO_THRESHOLD = 1D;
    private static final Double UPDATED_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO_THRESHOLD = 2D;

    private static final Double DEFAULT_COOLING_COILS_NOMINAL_TOTAL_CAPACITY = 1D;
    private static final Double UPDATED_COOLING_COILS_NOMINAL_TOTAL_CAPACITY = 2D;

    private static final Double DEFAULT_COOLING_COILS_NOMINAL_TOTAL_CAPACITY_THRESHOLD = 1D;
    private static final Double UPDATED_COOLING_COILS_NOMINAL_TOTAL_CAPACITY_THRESHOLD = 2D;

    private static final Double DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1 = 1D;
    private static final Double UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1 = 2D;

    private static final Double DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1_THRESHOLD = 1D;
    private static final Double UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1_THRESHOLD = 2D;

    private static final Double DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2 = 1D;
    private static final Double UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2 = 2D;

    private static final Double DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2_THRESHOLD = 1D;
    private static final Double UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2_THRESHOLD = 2D;

    private static final Double DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3 = 1D;
    private static final Double UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3 = 2D;

    private static final Double DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3_THRESHOLD = 1D;
    private static final Double UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3_THRESHOLD = 2D;

    private static final Double DEFAULT_FAN_MAX_AIR_FLOW_RATE = 1D;
    private static final Double UPDATED_FAN_MAX_AIR_FLOW_RATE = 2D;

    private static final Double DEFAULT_FAN_MAX_AIR_FLOW_RATE_THRESHOLD = 1D;
    private static final Double UPDATED_FAN_MAX_AIR_FLOW_RATE_THRESHOLD = 2D;

    private static final Double DEFAULT_FAN_RATED_ELECTRIC_POWER = 1D;
    private static final Double UPDATED_FAN_RATED_ELECTRIC_POWER = 2D;

    private static final Double DEFAULT_FAN_RATED_ELECTRIC_POWER_THRESHOLD = 1D;
    private static final Double UPDATED_FAN_RATED_ELECTRIC_POWER_THRESHOLD = 2D;

    private static final Double DEFAULT_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE = 1D;
    private static final Double UPDATED_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE = 2D;

    private static final Double DEFAULT_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE_THRESHOLD = 1D;
    private static final Double UPDATED_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE_THRESHOLD = 2D;

    private static final Double DEFAULT_FAN_MOTOR_HEAT_IN_AIR_FRACTION = 1D;
    private static final Double UPDATED_FAN_MOTOR_HEAT_IN_AIR_FRACTION = 2D;

    private static final Double DEFAULT_FAN_MOTOR_HEAT_IN_AIR_FRACTION_THRESHOLD = 1D;
    private static final Double UPDATED_FAN_MOTOR_HEAT_IN_AIR_FRACTION_THRESHOLD = 2D;

    private static final Double DEFAULT_FAN_TOTAL_EFFICIENCY = 1D;
    private static final Double UPDATED_FAN_TOTAL_EFFICIENCY = 2D;

    private static final Double DEFAULT_FAN_TOTAL_EFFICIENCY_THRESHOLD = 1D;
    private static final Double UPDATED_FAN_TOTAL_EFFICIENCY_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_HEAD_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_HEAD_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_HEAD_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_HEAD_CIRC_PUMP_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_HEAD_HW_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_HEAD_HW_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_HEAD_HW_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_HEAD_HW_CIRC_PUMP_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_HEAD_COND_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_HEAD_COND_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_HEAD_COND_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_HEAD_COND_CIRC_PUMP_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_ELECTRIC_POWER_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_ELECTRIC_POWER_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_ELECTRIC_POWER_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_ELECTRIC_POWER_CIRC_PUMP_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_WATER_FLOW_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_WATER_FLOW_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_WATER_FLOW_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_WATER_FLOW_CIRC_PUMP_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_WATER_FLOW_HW_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_WATER_FLOW_HW_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_WATER_FLOW_HW_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_WATER_FLOW_HW_CIRC_PUMP_THRESHOLD = 2D;

    private static final Double DEFAULT_PUMPS_WATER_FLOW_COND_CIRC_PUMP = 1D;
    private static final Double UPDATED_PUMPS_WATER_FLOW_COND_CIRC_PUMP = 2D;

    private static final Double DEFAULT_PUMPS_WATER_FLOW_COND_CIRC_PUMP_THRESHOLD = 1D;
    private static final Double UPDATED_PUMPS_WATER_FLOW_COND_CIRC_PUMP_THRESHOLD = 2D;

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
                .mLongitude(DEFAULT_M_LONGITUDE)
                .coolingCoilsNominalSensibleHeatRatio(DEFAULT_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO)
                .coolingCoilsNominalSensibleHeatRatioThreshold(DEFAULT_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO_THRESHOLD)
                .coolingCoilsNominalTotalCapacity(DEFAULT_COOLING_COILS_NOMINAL_TOTAL_CAPACITY)
                .coolingCoilsNominalTotalCapacityThreshold(DEFAULT_COOLING_COILS_NOMINAL_TOTAL_CAPACITY_THRESHOLD)
                .heatingCoilsNominalTotalCapacityReheatCoilZone1(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1)
                .heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1_THRESHOLD)
                .heatingCoilsNominalTotalCapacityReheatCoilZone2(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2)
                .heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2_THRESHOLD)
                .heatingCoilsNominalTotalCapacityReheatCoilZone3(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3)
                .heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3_THRESHOLD)
                .fanMaxAirFlowRate(DEFAULT_FAN_MAX_AIR_FLOW_RATE)
                .fanMaxAirFlowRateThreshold(DEFAULT_FAN_MAX_AIR_FLOW_RATE_THRESHOLD)
                .fanRatedElectricPower(DEFAULT_FAN_RATED_ELECTRIC_POWER)
                .fanRatedElectricPowerThreshold(DEFAULT_FAN_RATED_ELECTRIC_POWER_THRESHOLD)
                .fanRatedPowerPerMaxAirFlowRate(DEFAULT_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE)
                .fanRatedPowerPerMaxAirFlowRateThreshold(DEFAULT_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE_THRESHOLD)
                .fanMotorHeatInAirFraction(DEFAULT_FAN_MOTOR_HEAT_IN_AIR_FRACTION)
                .fanMotorHeatInAirFractionThreshold(DEFAULT_FAN_MOTOR_HEAT_IN_AIR_FRACTION_THRESHOLD)
                .fanTotalEfficiency(DEFAULT_FAN_TOTAL_EFFICIENCY)
                .fanTotalEfficiencyThreshold(DEFAULT_FAN_TOTAL_EFFICIENCY_THRESHOLD)
                .pumpsPowerPerWaterFlowRateCircPump(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP)
                .pumpsPowerPerWaterFlowRateCircPumpThreshold(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP_THRESHOLD)
                .pumpsPowerPerWaterFlowRateHwCircPump(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP)
                .pumpsPowerPerWaterFlowRateHwCircPumpThreshold(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP_THRESHOLD)
                .pumpsPowerPerWaterFlowRateCondCircPump(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP)
                .pumpsPowerPerWaterFlowRateCondCircPumpThreshold(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP_THRESHOLD)
                .pumpsHeadCircPump(DEFAULT_PUMPS_HEAD_CIRC_PUMP)
                .pumpsHeadCircPumpThreshold(DEFAULT_PUMPS_HEAD_CIRC_PUMP_THRESHOLD)
                .pumpsHeadHwCircPump(DEFAULT_PUMPS_HEAD_HW_CIRC_PUMP)
                .pumpsHeadHwCircPumpThreshold(DEFAULT_PUMPS_HEAD_HW_CIRC_PUMP_THRESHOLD)
                .pumpsHeadCondCircPump(DEFAULT_PUMPS_HEAD_COND_CIRC_PUMP)
                .pumpsHeadCondCircPumpThreshold(DEFAULT_PUMPS_HEAD_COND_CIRC_PUMP_THRESHOLD)
                .pumpsElectricPowerCircPump(DEFAULT_PUMPS_ELECTRIC_POWER_CIRC_PUMP)
                .pumpsElectricPowerCircPumpThreshold(DEFAULT_PUMPS_ELECTRIC_POWER_CIRC_PUMP_THRESHOLD)
                .pumpsElectricPowerHwCircPump(DEFAULT_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP)
                .pumpsElectricPowerHwCircPumpThreshold(DEFAULT_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP_THRESHOLD)
                .pumpsElectricPowerCondCircPump(DEFAULT_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP)
                .pumpsElectricPowerCondCircPumpThreshold(DEFAULT_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP_THRESHOLD)
                .pumpsMotorEfficiencyCircPump(DEFAULT_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP)
                .pumpsMotorEfficiencyCircPumpThreshold(DEFAULT_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP_THRESHOLD)
                .pumpsMotorEfficiencyHwCircPump(DEFAULT_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP)
                .pumpsMotorEfficiencyHwCircPumpThreshold(DEFAULT_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP_THRESHOLD)
                .pumpsMotorEfficiencyCondCircPump(DEFAULT_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP)
                .pumpsMotorEfficiencyCondCircPumpThreshold(DEFAULT_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP_THRESHOLD)
                .pumpsWaterFlowCircPump(DEFAULT_PUMPS_WATER_FLOW_CIRC_PUMP)
                .pumpsWaterFlowCircPumpThreshold(DEFAULT_PUMPS_WATER_FLOW_CIRC_PUMP_THRESHOLD)
                .pumpsWaterFlowHwCircPump(DEFAULT_PUMPS_WATER_FLOW_HW_CIRC_PUMP)
                .pumpsWaterFlowHwCircPumpThreshold(DEFAULT_PUMPS_WATER_FLOW_HW_CIRC_PUMP_THRESHOLD)
                .pumpsWaterFlowCondCircPump(DEFAULT_PUMPS_WATER_FLOW_COND_CIRC_PUMP)
                .pumpsWaterFlowCondCircPumpThreshold(DEFAULT_PUMPS_WATER_FLOW_COND_CIRC_PUMP_THRESHOLD);
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
        assertThat(testPadset.getCoolingCoilsNominalSensibleHeatRatio()).isEqualTo(DEFAULT_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO);
        assertThat(testPadset.getCoolingCoilsNominalSensibleHeatRatioThreshold()).isEqualTo(DEFAULT_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO_THRESHOLD);
        assertThat(testPadset.getCoolingCoilsNominalTotalCapacity()).isEqualTo(DEFAULT_COOLING_COILS_NOMINAL_TOTAL_CAPACITY);
        assertThat(testPadset.getCoolingCoilsNominalTotalCapacityThreshold()).isEqualTo(DEFAULT_COOLING_COILS_NOMINAL_TOTAL_CAPACITY_THRESHOLD);
        assertThat(testPadset.getHeatingCoilsNominalTotalCapacityReheatCoilZone1()).isEqualTo(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1);
        assertThat(testPadset.getHeatingCoilsNominalTotalCapacityReheatCoilZone1Threshold()).isEqualTo(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1_THRESHOLD);
        assertThat(testPadset.getHeatingCoilsNominalTotalCapacityReheatCoilZone2()).isEqualTo(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2);
        assertThat(testPadset.getHeatingCoilsNominalTotalCapacityReheatCoilZone2Threshold()).isEqualTo(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2_THRESHOLD);
        assertThat(testPadset.getHeatingCoilsNominalTotalCapacityReheatCoilZone3()).isEqualTo(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3);
        assertThat(testPadset.getHeatingCoilsNominalTotalCapacityReheatCoilZone3Threshold()).isEqualTo(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3_THRESHOLD);
        assertThat(testPadset.getFanMaxAirFlowRate()).isEqualTo(DEFAULT_FAN_MAX_AIR_FLOW_RATE);
        assertThat(testPadset.getFanMaxAirFlowRateThreshold()).isEqualTo(DEFAULT_FAN_MAX_AIR_FLOW_RATE_THRESHOLD);
        assertThat(testPadset.getFanRatedElectricPower()).isEqualTo(DEFAULT_FAN_RATED_ELECTRIC_POWER);
        assertThat(testPadset.getFanRatedElectricPowerThreshold()).isEqualTo(DEFAULT_FAN_RATED_ELECTRIC_POWER_THRESHOLD);
        assertThat(testPadset.getFanRatedPowerPerMaxAirFlowRate()).isEqualTo(DEFAULT_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE);
        assertThat(testPadset.getFanRatedPowerPerMaxAirFlowRateThreshold()).isEqualTo(DEFAULT_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE_THRESHOLD);
        assertThat(testPadset.getFanMotorHeatInAirFraction()).isEqualTo(DEFAULT_FAN_MOTOR_HEAT_IN_AIR_FRACTION);
        assertThat(testPadset.getFanMotorHeatInAirFractionThreshold()).isEqualTo(DEFAULT_FAN_MOTOR_HEAT_IN_AIR_FRACTION_THRESHOLD);
        assertThat(testPadset.getFanTotalEfficiency()).isEqualTo(DEFAULT_FAN_TOTAL_EFFICIENCY);
        assertThat(testPadset.getFanTotalEfficiencyThreshold()).isEqualTo(DEFAULT_FAN_TOTAL_EFFICIENCY_THRESHOLD);
        assertThat(testPadset.getPumpsPowerPerWaterFlowRateCircPump()).isEqualTo(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP);
        assertThat(testPadset.getPumpsPowerPerWaterFlowRateCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsPowerPerWaterFlowRateHwCircPump()).isEqualTo(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP);
        assertThat(testPadset.getPumpsPowerPerWaterFlowRateHwCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsPowerPerWaterFlowRateCondCircPump()).isEqualTo(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP);
        assertThat(testPadset.getPumpsPowerPerWaterFlowRateCondCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsHeadCircPump()).isEqualTo(DEFAULT_PUMPS_HEAD_CIRC_PUMP);
        assertThat(testPadset.getPumpsHeadCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_HEAD_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsHeadHwCircPump()).isEqualTo(DEFAULT_PUMPS_HEAD_HW_CIRC_PUMP);
        assertThat(testPadset.getPumpsHeadHwCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_HEAD_HW_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsHeadCondCircPump()).isEqualTo(DEFAULT_PUMPS_HEAD_COND_CIRC_PUMP);
        assertThat(testPadset.getPumpsHeadCondCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_HEAD_COND_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsElectricPowerCircPump()).isEqualTo(DEFAULT_PUMPS_ELECTRIC_POWER_CIRC_PUMP);
        assertThat(testPadset.getPumpsElectricPowerCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_ELECTRIC_POWER_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsElectricPowerHwCircPump()).isEqualTo(DEFAULT_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP);
        assertThat(testPadset.getPumpsElectricPowerHwCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsElectricPowerCondCircPump()).isEqualTo(DEFAULT_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP);
        assertThat(testPadset.getPumpsElectricPowerCondCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsMotorEfficiencyCircPump()).isEqualTo(DEFAULT_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP);
        assertThat(testPadset.getPumpsMotorEfficiencyCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsMotorEfficiencyHwCircPump()).isEqualTo(DEFAULT_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP);
        assertThat(testPadset.getPumpsMotorEfficiencyHwCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsMotorEfficiencyCondCircPump()).isEqualTo(DEFAULT_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP);
        assertThat(testPadset.getPumpsMotorEfficiencyCondCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsWaterFlowCircPump()).isEqualTo(DEFAULT_PUMPS_WATER_FLOW_CIRC_PUMP);
        assertThat(testPadset.getPumpsWaterFlowCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_WATER_FLOW_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsWaterFlowHwCircPump()).isEqualTo(DEFAULT_PUMPS_WATER_FLOW_HW_CIRC_PUMP);
        assertThat(testPadset.getPumpsWaterFlowHwCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_WATER_FLOW_HW_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsWaterFlowCondCircPump()).isEqualTo(DEFAULT_PUMPS_WATER_FLOW_COND_CIRC_PUMP);
        assertThat(testPadset.getPumpsWaterFlowCondCircPumpThreshold()).isEqualTo(DEFAULT_PUMPS_WATER_FLOW_COND_CIRC_PUMP_THRESHOLD);

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
                .andExpect(jsonPath("$.[*].mLongitude").value(hasItem(DEFAULT_M_LONGITUDE.doubleValue())))
                .andExpect(jsonPath("$.[*].coolingCoilsNominalSensibleHeatRatio").value(hasItem(DEFAULT_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO.doubleValue())))
                .andExpect(jsonPath("$.[*].coolingCoilsNominalSensibleHeatRatioThreshold").value(hasItem(DEFAULT_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].coolingCoilsNominalTotalCapacity").value(hasItem(DEFAULT_COOLING_COILS_NOMINAL_TOTAL_CAPACITY.doubleValue())))
                .andExpect(jsonPath("$.[*].coolingCoilsNominalTotalCapacityThreshold").value(hasItem(DEFAULT_COOLING_COILS_NOMINAL_TOTAL_CAPACITY_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].heatingCoilsNominalTotalCapacityReheatCoilZone1").value(hasItem(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1.doubleValue())))
                .andExpect(jsonPath("$.[*].heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold").value(hasItem(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].heatingCoilsNominalTotalCapacityReheatCoilZone2").value(hasItem(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2.doubleValue())))
                .andExpect(jsonPath("$.[*].heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold").value(hasItem(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].heatingCoilsNominalTotalCapacityReheatCoilZone3").value(hasItem(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3.doubleValue())))
                .andExpect(jsonPath("$.[*].heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold").value(hasItem(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].fanMaxAirFlowRate").value(hasItem(DEFAULT_FAN_MAX_AIR_FLOW_RATE.doubleValue())))
                .andExpect(jsonPath("$.[*].fanMaxAirFlowRateThreshold").value(hasItem(DEFAULT_FAN_MAX_AIR_FLOW_RATE_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].fanRatedElectricPower").value(hasItem(DEFAULT_FAN_RATED_ELECTRIC_POWER.doubleValue())))
                .andExpect(jsonPath("$.[*].fanRatedElectricPowerThreshold").value(hasItem(DEFAULT_FAN_RATED_ELECTRIC_POWER_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].fanRatedPowerPerMaxAirFlowRate").value(hasItem(DEFAULT_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE.doubleValue())))
                .andExpect(jsonPath("$.[*].fanRatedPowerPerMaxAirFlowRateThreshold").value(hasItem(DEFAULT_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].fanMotorHeatInAirFraction").value(hasItem(DEFAULT_FAN_MOTOR_HEAT_IN_AIR_FRACTION.doubleValue())))
                .andExpect(jsonPath("$.[*].fanMotorHeatInAirFractionThreshold").value(hasItem(DEFAULT_FAN_MOTOR_HEAT_IN_AIR_FRACTION_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].fanTotalEfficiency").value(hasItem(DEFAULT_FAN_TOTAL_EFFICIENCY.doubleValue())))
                .andExpect(jsonPath("$.[*].fanTotalEfficiencyThreshold").value(hasItem(DEFAULT_FAN_TOTAL_EFFICIENCY_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsPowerPerWaterFlowRateCircPump").value(hasItem(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsPowerPerWaterFlowRateCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsPowerPerWaterFlowRateHwCircPump").value(hasItem(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsPowerPerWaterFlowRateHwCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsPowerPerWaterFlowRateCondCircPump").value(hasItem(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsPowerPerWaterFlowRateCondCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsHeadCircPump").value(hasItem(DEFAULT_PUMPS_HEAD_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsHeadCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_HEAD_CIRC_PUMP_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsHeadHwCircPump").value(hasItem(DEFAULT_PUMPS_HEAD_HW_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsHeadHwCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_HEAD_HW_CIRC_PUMP_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsHeadCondCircPump").value(hasItem(DEFAULT_PUMPS_HEAD_COND_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsHeadCondCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_HEAD_COND_CIRC_PUMP_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsElectricPowerCircPump").value(hasItem(DEFAULT_PUMPS_ELECTRIC_POWER_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsElectricPowerCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_ELECTRIC_POWER_CIRC_PUMP_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsElectricPowerHwCircPump").value(hasItem(DEFAULT_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsElectricPowerHwCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsElectricPowerCondCircPump").value(hasItem(DEFAULT_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsElectricPowerCondCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsMotorEfficiencyCircPump").value(hasItem(DEFAULT_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsMotorEfficiencyCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsMotorEfficiencyHwCircPump").value(hasItem(DEFAULT_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsMotorEfficiencyHwCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsMotorEfficiencyCondCircPump").value(hasItem(DEFAULT_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsMotorEfficiencyCondCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsWaterFlowCircPump").value(hasItem(DEFAULT_PUMPS_WATER_FLOW_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsWaterFlowCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_WATER_FLOW_CIRC_PUMP_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsWaterFlowHwCircPump").value(hasItem(DEFAULT_PUMPS_WATER_FLOW_HW_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsWaterFlowHwCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_WATER_FLOW_HW_CIRC_PUMP_THRESHOLD.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsWaterFlowCondCircPump").value(hasItem(DEFAULT_PUMPS_WATER_FLOW_COND_CIRC_PUMP.doubleValue())))
                .andExpect(jsonPath("$.[*].pumpsWaterFlowCondCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_WATER_FLOW_COND_CIRC_PUMP_THRESHOLD.doubleValue())));
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
            .andExpect(jsonPath("$.mLongitude").value(DEFAULT_M_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.coolingCoilsNominalSensibleHeatRatio").value(DEFAULT_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO.doubleValue()))
            .andExpect(jsonPath("$.coolingCoilsNominalSensibleHeatRatioThreshold").value(DEFAULT_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.coolingCoilsNominalTotalCapacity").value(DEFAULT_COOLING_COILS_NOMINAL_TOTAL_CAPACITY.doubleValue()))
            .andExpect(jsonPath("$.coolingCoilsNominalTotalCapacityThreshold").value(DEFAULT_COOLING_COILS_NOMINAL_TOTAL_CAPACITY_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.heatingCoilsNominalTotalCapacityReheatCoilZone1").value(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1.doubleValue()))
            .andExpect(jsonPath("$.heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold").value(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.heatingCoilsNominalTotalCapacityReheatCoilZone2").value(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2.doubleValue()))
            .andExpect(jsonPath("$.heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold").value(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.heatingCoilsNominalTotalCapacityReheatCoilZone3").value(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3.doubleValue()))
            .andExpect(jsonPath("$.heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold").value(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.fanMaxAirFlowRate").value(DEFAULT_FAN_MAX_AIR_FLOW_RATE.doubleValue()))
            .andExpect(jsonPath("$.fanMaxAirFlowRateThreshold").value(DEFAULT_FAN_MAX_AIR_FLOW_RATE_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.fanRatedElectricPower").value(DEFAULT_FAN_RATED_ELECTRIC_POWER.doubleValue()))
            .andExpect(jsonPath("$.fanRatedElectricPowerThreshold").value(DEFAULT_FAN_RATED_ELECTRIC_POWER_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.fanRatedPowerPerMaxAirFlowRate").value(DEFAULT_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE.doubleValue()))
            .andExpect(jsonPath("$.fanRatedPowerPerMaxAirFlowRateThreshold").value(DEFAULT_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.fanMotorHeatInAirFraction").value(DEFAULT_FAN_MOTOR_HEAT_IN_AIR_FRACTION.doubleValue()))
            .andExpect(jsonPath("$.fanMotorHeatInAirFractionThreshold").value(DEFAULT_FAN_MOTOR_HEAT_IN_AIR_FRACTION_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.fanTotalEfficiency").value(DEFAULT_FAN_TOTAL_EFFICIENCY.doubleValue()))
            .andExpect(jsonPath("$.fanTotalEfficiencyThreshold").value(DEFAULT_FAN_TOTAL_EFFICIENCY_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsPowerPerWaterFlowRateCircPump").value(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsPowerPerWaterFlowRateCircPumpThreshold").value(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsPowerPerWaterFlowRateHwCircPump").value(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsPowerPerWaterFlowRateHwCircPumpThreshold").value(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsPowerPerWaterFlowRateCondCircPump").value(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsPowerPerWaterFlowRateCondCircPumpThreshold").value(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsHeadCircPump").value(DEFAULT_PUMPS_HEAD_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsHeadCircPumpThreshold").value(DEFAULT_PUMPS_HEAD_CIRC_PUMP_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsHeadHwCircPump").value(DEFAULT_PUMPS_HEAD_HW_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsHeadHwCircPumpThreshold").value(DEFAULT_PUMPS_HEAD_HW_CIRC_PUMP_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsHeadCondCircPump").value(DEFAULT_PUMPS_HEAD_COND_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsHeadCondCircPumpThreshold").value(DEFAULT_PUMPS_HEAD_COND_CIRC_PUMP_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsElectricPowerCircPump").value(DEFAULT_PUMPS_ELECTRIC_POWER_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsElectricPowerCircPumpThreshold").value(DEFAULT_PUMPS_ELECTRIC_POWER_CIRC_PUMP_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsElectricPowerHwCircPump").value(DEFAULT_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsElectricPowerHwCircPumpThreshold").value(DEFAULT_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsElectricPowerCondCircPump").value(DEFAULT_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsElectricPowerCondCircPumpThreshold").value(DEFAULT_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsMotorEfficiencyCircPump").value(DEFAULT_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsMotorEfficiencyCircPumpThreshold").value(DEFAULT_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsMotorEfficiencyHwCircPump").value(DEFAULT_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsMotorEfficiencyHwCircPumpThreshold").value(DEFAULT_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsMotorEfficiencyCondCircPump").value(DEFAULT_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsMotorEfficiencyCondCircPumpThreshold").value(DEFAULT_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsWaterFlowCircPump").value(DEFAULT_PUMPS_WATER_FLOW_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsWaterFlowCircPumpThreshold").value(DEFAULT_PUMPS_WATER_FLOW_CIRC_PUMP_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsWaterFlowHwCircPump").value(DEFAULT_PUMPS_WATER_FLOW_HW_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsWaterFlowHwCircPumpThreshold").value(DEFAULT_PUMPS_WATER_FLOW_HW_CIRC_PUMP_THRESHOLD.doubleValue()))
            .andExpect(jsonPath("$.pumpsWaterFlowCondCircPump").value(DEFAULT_PUMPS_WATER_FLOW_COND_CIRC_PUMP.doubleValue()))
            .andExpect(jsonPath("$.pumpsWaterFlowCondCircPumpThreshold").value(DEFAULT_PUMPS_WATER_FLOW_COND_CIRC_PUMP_THRESHOLD.doubleValue()));
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
                .mLongitude(UPDATED_M_LONGITUDE)
                .coolingCoilsNominalSensibleHeatRatio(UPDATED_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO)
                .coolingCoilsNominalSensibleHeatRatioThreshold(UPDATED_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO_THRESHOLD)
                .coolingCoilsNominalTotalCapacity(UPDATED_COOLING_COILS_NOMINAL_TOTAL_CAPACITY)
                .coolingCoilsNominalTotalCapacityThreshold(UPDATED_COOLING_COILS_NOMINAL_TOTAL_CAPACITY_THRESHOLD)
                .heatingCoilsNominalTotalCapacityReheatCoilZone1(UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1)
                .heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold(UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1_THRESHOLD)
                .heatingCoilsNominalTotalCapacityReheatCoilZone2(UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2)
                .heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold(UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2_THRESHOLD)
                .heatingCoilsNominalTotalCapacityReheatCoilZone3(UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3)
                .heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold(UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3_THRESHOLD)
                .fanMaxAirFlowRate(UPDATED_FAN_MAX_AIR_FLOW_RATE)
                .fanMaxAirFlowRateThreshold(UPDATED_FAN_MAX_AIR_FLOW_RATE_THRESHOLD)
                .fanRatedElectricPower(UPDATED_FAN_RATED_ELECTRIC_POWER)
                .fanRatedElectricPowerThreshold(UPDATED_FAN_RATED_ELECTRIC_POWER_THRESHOLD)
                .fanRatedPowerPerMaxAirFlowRate(UPDATED_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE)
                .fanRatedPowerPerMaxAirFlowRateThreshold(UPDATED_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE_THRESHOLD)
                .fanMotorHeatInAirFraction(UPDATED_FAN_MOTOR_HEAT_IN_AIR_FRACTION)
                .fanMotorHeatInAirFractionThreshold(UPDATED_FAN_MOTOR_HEAT_IN_AIR_FRACTION_THRESHOLD)
                .fanTotalEfficiency(UPDATED_FAN_TOTAL_EFFICIENCY)
                .fanTotalEfficiencyThreshold(UPDATED_FAN_TOTAL_EFFICIENCY_THRESHOLD)
                .pumpsPowerPerWaterFlowRateCircPump(UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP)
                .pumpsPowerPerWaterFlowRateCircPumpThreshold(UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP_THRESHOLD)
                .pumpsPowerPerWaterFlowRateHwCircPump(UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP)
                .pumpsPowerPerWaterFlowRateHwCircPumpThreshold(UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP_THRESHOLD)
                .pumpsPowerPerWaterFlowRateCondCircPump(UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP)
                .pumpsPowerPerWaterFlowRateCondCircPumpThreshold(UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP_THRESHOLD)
                .pumpsHeadCircPump(UPDATED_PUMPS_HEAD_CIRC_PUMP)
                .pumpsHeadCircPumpThreshold(UPDATED_PUMPS_HEAD_CIRC_PUMP_THRESHOLD)
                .pumpsHeadHwCircPump(UPDATED_PUMPS_HEAD_HW_CIRC_PUMP)
                .pumpsHeadHwCircPumpThreshold(UPDATED_PUMPS_HEAD_HW_CIRC_PUMP_THRESHOLD)
                .pumpsHeadCondCircPump(UPDATED_PUMPS_HEAD_COND_CIRC_PUMP)
                .pumpsHeadCondCircPumpThreshold(UPDATED_PUMPS_HEAD_COND_CIRC_PUMP_THRESHOLD)
                .pumpsElectricPowerCircPump(UPDATED_PUMPS_ELECTRIC_POWER_CIRC_PUMP)
                .pumpsElectricPowerCircPumpThreshold(UPDATED_PUMPS_ELECTRIC_POWER_CIRC_PUMP_THRESHOLD)
                .pumpsElectricPowerHwCircPump(UPDATED_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP)
                .pumpsElectricPowerHwCircPumpThreshold(UPDATED_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP_THRESHOLD)
                .pumpsElectricPowerCondCircPump(UPDATED_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP)
                .pumpsElectricPowerCondCircPumpThreshold(UPDATED_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP_THRESHOLD)
                .pumpsMotorEfficiencyCircPump(UPDATED_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP)
                .pumpsMotorEfficiencyCircPumpThreshold(UPDATED_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP_THRESHOLD)
                .pumpsMotorEfficiencyHwCircPump(UPDATED_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP)
                .pumpsMotorEfficiencyHwCircPumpThreshold(UPDATED_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP_THRESHOLD)
                .pumpsMotorEfficiencyCondCircPump(UPDATED_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP)
                .pumpsMotorEfficiencyCondCircPumpThreshold(UPDATED_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP_THRESHOLD)
                .pumpsWaterFlowCircPump(UPDATED_PUMPS_WATER_FLOW_CIRC_PUMP)
                .pumpsWaterFlowCircPumpThreshold(UPDATED_PUMPS_WATER_FLOW_CIRC_PUMP_THRESHOLD)
                .pumpsWaterFlowHwCircPump(UPDATED_PUMPS_WATER_FLOW_HW_CIRC_PUMP)
                .pumpsWaterFlowHwCircPumpThreshold(UPDATED_PUMPS_WATER_FLOW_HW_CIRC_PUMP_THRESHOLD)
                .pumpsWaterFlowCondCircPump(UPDATED_PUMPS_WATER_FLOW_COND_CIRC_PUMP)
                .pumpsWaterFlowCondCircPumpThreshold(UPDATED_PUMPS_WATER_FLOW_COND_CIRC_PUMP_THRESHOLD);

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
        assertThat(testPadset.getCoolingCoilsNominalSensibleHeatRatio()).isEqualTo(UPDATED_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO);
        assertThat(testPadset.getCoolingCoilsNominalSensibleHeatRatioThreshold()).isEqualTo(UPDATED_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO_THRESHOLD);
        assertThat(testPadset.getCoolingCoilsNominalTotalCapacity()).isEqualTo(UPDATED_COOLING_COILS_NOMINAL_TOTAL_CAPACITY);
        assertThat(testPadset.getCoolingCoilsNominalTotalCapacityThreshold()).isEqualTo(UPDATED_COOLING_COILS_NOMINAL_TOTAL_CAPACITY_THRESHOLD);
        assertThat(testPadset.getHeatingCoilsNominalTotalCapacityReheatCoilZone1()).isEqualTo(UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1);
        assertThat(testPadset.getHeatingCoilsNominalTotalCapacityReheatCoilZone1Threshold()).isEqualTo(UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1_THRESHOLD);
        assertThat(testPadset.getHeatingCoilsNominalTotalCapacityReheatCoilZone2()).isEqualTo(UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2);
        assertThat(testPadset.getHeatingCoilsNominalTotalCapacityReheatCoilZone2Threshold()).isEqualTo(UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2_THRESHOLD);
        assertThat(testPadset.getHeatingCoilsNominalTotalCapacityReheatCoilZone3()).isEqualTo(UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3);
        assertThat(testPadset.getHeatingCoilsNominalTotalCapacityReheatCoilZone3Threshold()).isEqualTo(UPDATED_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3_THRESHOLD);
        assertThat(testPadset.getFanMaxAirFlowRate()).isEqualTo(UPDATED_FAN_MAX_AIR_FLOW_RATE);
        assertThat(testPadset.getFanMaxAirFlowRateThreshold()).isEqualTo(UPDATED_FAN_MAX_AIR_FLOW_RATE_THRESHOLD);
        assertThat(testPadset.getFanRatedElectricPower()).isEqualTo(UPDATED_FAN_RATED_ELECTRIC_POWER);
        assertThat(testPadset.getFanRatedElectricPowerThreshold()).isEqualTo(UPDATED_FAN_RATED_ELECTRIC_POWER_THRESHOLD);
        assertThat(testPadset.getFanRatedPowerPerMaxAirFlowRate()).isEqualTo(UPDATED_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE);
        assertThat(testPadset.getFanRatedPowerPerMaxAirFlowRateThreshold()).isEqualTo(UPDATED_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE_THRESHOLD);
        assertThat(testPadset.getFanMotorHeatInAirFraction()).isEqualTo(UPDATED_FAN_MOTOR_HEAT_IN_AIR_FRACTION);
        assertThat(testPadset.getFanMotorHeatInAirFractionThreshold()).isEqualTo(UPDATED_FAN_MOTOR_HEAT_IN_AIR_FRACTION_THRESHOLD);
        assertThat(testPadset.getFanTotalEfficiency()).isEqualTo(UPDATED_FAN_TOTAL_EFFICIENCY);
        assertThat(testPadset.getFanTotalEfficiencyThreshold()).isEqualTo(UPDATED_FAN_TOTAL_EFFICIENCY_THRESHOLD);
        assertThat(testPadset.getPumpsPowerPerWaterFlowRateCircPump()).isEqualTo(UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP);
        assertThat(testPadset.getPumpsPowerPerWaterFlowRateCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsPowerPerWaterFlowRateHwCircPump()).isEqualTo(UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP);
        assertThat(testPadset.getPumpsPowerPerWaterFlowRateHwCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsPowerPerWaterFlowRateCondCircPump()).isEqualTo(UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP);
        assertThat(testPadset.getPumpsPowerPerWaterFlowRateCondCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsHeadCircPump()).isEqualTo(UPDATED_PUMPS_HEAD_CIRC_PUMP);
        assertThat(testPadset.getPumpsHeadCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_HEAD_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsHeadHwCircPump()).isEqualTo(UPDATED_PUMPS_HEAD_HW_CIRC_PUMP);
        assertThat(testPadset.getPumpsHeadHwCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_HEAD_HW_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsHeadCondCircPump()).isEqualTo(UPDATED_PUMPS_HEAD_COND_CIRC_PUMP);
        assertThat(testPadset.getPumpsHeadCondCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_HEAD_COND_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsElectricPowerCircPump()).isEqualTo(UPDATED_PUMPS_ELECTRIC_POWER_CIRC_PUMP);
        assertThat(testPadset.getPumpsElectricPowerCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_ELECTRIC_POWER_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsElectricPowerHwCircPump()).isEqualTo(UPDATED_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP);
        assertThat(testPadset.getPumpsElectricPowerHwCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsElectricPowerCondCircPump()).isEqualTo(UPDATED_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP);
        assertThat(testPadset.getPumpsElectricPowerCondCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsMotorEfficiencyCircPump()).isEqualTo(UPDATED_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP);
        assertThat(testPadset.getPumpsMotorEfficiencyCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsMotorEfficiencyHwCircPump()).isEqualTo(UPDATED_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP);
        assertThat(testPadset.getPumpsMotorEfficiencyHwCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsMotorEfficiencyCondCircPump()).isEqualTo(UPDATED_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP);
        assertThat(testPadset.getPumpsMotorEfficiencyCondCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsWaterFlowCircPump()).isEqualTo(UPDATED_PUMPS_WATER_FLOW_CIRC_PUMP);
        assertThat(testPadset.getPumpsWaterFlowCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_WATER_FLOW_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsWaterFlowHwCircPump()).isEqualTo(UPDATED_PUMPS_WATER_FLOW_HW_CIRC_PUMP);
        assertThat(testPadset.getPumpsWaterFlowHwCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_WATER_FLOW_HW_CIRC_PUMP_THRESHOLD);
        assertThat(testPadset.getPumpsWaterFlowCondCircPump()).isEqualTo(UPDATED_PUMPS_WATER_FLOW_COND_CIRC_PUMP);
        assertThat(testPadset.getPumpsWaterFlowCondCircPumpThreshold()).isEqualTo(UPDATED_PUMPS_WATER_FLOW_COND_CIRC_PUMP_THRESHOLD);

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
            .andExpect(jsonPath("$.[*].mLongitude").value(hasItem(DEFAULT_M_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].coolingCoilsNominalSensibleHeatRatio").value(hasItem(DEFAULT_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO.doubleValue())))
            .andExpect(jsonPath("$.[*].coolingCoilsNominalSensibleHeatRatioThreshold").value(hasItem(DEFAULT_COOLING_COILS_NOMINAL_SENSIBLE_HEAT_RATIO_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].coolingCoilsNominalTotalCapacity").value(hasItem(DEFAULT_COOLING_COILS_NOMINAL_TOTAL_CAPACITY.doubleValue())))
            .andExpect(jsonPath("$.[*].coolingCoilsNominalTotalCapacityThreshold").value(hasItem(DEFAULT_COOLING_COILS_NOMINAL_TOTAL_CAPACITY_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].heatingCoilsNominalTotalCapacityReheatCoilZone1").value(hasItem(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1.doubleValue())))
            .andExpect(jsonPath("$.[*].heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold").value(hasItem(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_1_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].heatingCoilsNominalTotalCapacityReheatCoilZone2").value(hasItem(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2.doubleValue())))
            .andExpect(jsonPath("$.[*].heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold").value(hasItem(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_2_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].heatingCoilsNominalTotalCapacityReheatCoilZone3").value(hasItem(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3.doubleValue())))
            .andExpect(jsonPath("$.[*].heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold").value(hasItem(DEFAULT_HEATING_COILS_NOMINAL_TOTAL_CAPACITY_REHEAT_COIL_ZONE_3_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].fanMaxAirFlowRate").value(hasItem(DEFAULT_FAN_MAX_AIR_FLOW_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].fanMaxAirFlowRateThreshold").value(hasItem(DEFAULT_FAN_MAX_AIR_FLOW_RATE_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].fanRatedElectricPower").value(hasItem(DEFAULT_FAN_RATED_ELECTRIC_POWER.doubleValue())))
            .andExpect(jsonPath("$.[*].fanRatedElectricPowerThreshold").value(hasItem(DEFAULT_FAN_RATED_ELECTRIC_POWER_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].fanRatedPowerPerMaxAirFlowRate").value(hasItem(DEFAULT_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].fanRatedPowerPerMaxAirFlowRateThreshold").value(hasItem(DEFAULT_FAN_RATED_POWER_PER_MAX_AIR_FLOW_RATE_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].fanMotorHeatInAirFraction").value(hasItem(DEFAULT_FAN_MOTOR_HEAT_IN_AIR_FRACTION.doubleValue())))
            .andExpect(jsonPath("$.[*].fanMotorHeatInAirFractionThreshold").value(hasItem(DEFAULT_FAN_MOTOR_HEAT_IN_AIR_FRACTION_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].fanTotalEfficiency").value(hasItem(DEFAULT_FAN_TOTAL_EFFICIENCY.doubleValue())))
            .andExpect(jsonPath("$.[*].fanTotalEfficiencyThreshold").value(hasItem(DEFAULT_FAN_TOTAL_EFFICIENCY_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsPowerPerWaterFlowRateCircPump").value(hasItem(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsPowerPerWaterFlowRateCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_CIRC_PUMP_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsPowerPerWaterFlowRateHwCircPump").value(hasItem(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsPowerPerWaterFlowRateHwCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_HW_CIRC_PUMP_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsPowerPerWaterFlowRateCondCircPump").value(hasItem(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsPowerPerWaterFlowRateCondCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_POWER_PER_WATER_FLOW_RATE_COND_CIRC_PUMP_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsHeadCircPump").value(hasItem(DEFAULT_PUMPS_HEAD_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsHeadCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_HEAD_CIRC_PUMP_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsHeadHwCircPump").value(hasItem(DEFAULT_PUMPS_HEAD_HW_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsHeadHwCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_HEAD_HW_CIRC_PUMP_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsHeadCondCircPump").value(hasItem(DEFAULT_PUMPS_HEAD_COND_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsHeadCondCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_HEAD_COND_CIRC_PUMP_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsElectricPowerCircPump").value(hasItem(DEFAULT_PUMPS_ELECTRIC_POWER_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsElectricPowerCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_ELECTRIC_POWER_CIRC_PUMP_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsElectricPowerHwCircPump").value(hasItem(DEFAULT_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsElectricPowerHwCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_ELECTRIC_POWER_HW_CIRC_PUMP_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsElectricPowerCondCircPump").value(hasItem(DEFAULT_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsElectricPowerCondCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_ELECTRIC_POWER_COND_CIRC_PUMP_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsMotorEfficiencyCircPump").value(hasItem(DEFAULT_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsMotorEfficiencyCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_MOTOR_EFFICIENCY_CIRC_PUMP_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsMotorEfficiencyHwCircPump").value(hasItem(DEFAULT_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsMotorEfficiencyHwCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_MOTOR_EFFICIENCY_HW_CIRC_PUMP_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsMotorEfficiencyCondCircPump").value(hasItem(DEFAULT_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsMotorEfficiencyCondCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_MOTOR_EFFICIENCY_COND_CIRC_PUMP_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsWaterFlowCircPump").value(hasItem(DEFAULT_PUMPS_WATER_FLOW_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsWaterFlowCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_WATER_FLOW_CIRC_PUMP_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsWaterFlowHwCircPump").value(hasItem(DEFAULT_PUMPS_WATER_FLOW_HW_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsWaterFlowHwCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_WATER_FLOW_HW_CIRC_PUMP_THRESHOLD.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsWaterFlowCondCircPump").value(hasItem(DEFAULT_PUMPS_WATER_FLOW_COND_CIRC_PUMP.doubleValue())))
            .andExpect(jsonPath("$.[*].pumpsWaterFlowCondCircPumpThreshold").value(hasItem(DEFAULT_PUMPS_WATER_FLOW_COND_CIRC_PUMP_THRESHOLD.doubleValue())));
    }
}
