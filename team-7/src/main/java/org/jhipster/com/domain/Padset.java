package org.jhipster.com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Padset.
 */
@Entity
@Table(name = "padset")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "padset")
public class Padset implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "m_pad_set_name", nullable = false)
    private String mPadSetName;

    @NotNull
    @Column(name = "m_latitude", nullable = false)
    private Double mLatitude;

    @NotNull
    @Column(name = "m_longitude", nullable = false)
    private Double mLongitude;

    @Column(name = "cooling_coils_nominal_sensible_heat_ratio")
    private Double coolingCoilsNominalSensibleHeatRatio;

    @Column(name = "cooling_coils_nominal_sensible_heat_ratio_threshold")
    private Double coolingCoilsNominalSensibleHeatRatioThreshold;

    @Column(name = "cooling_coils_nominal_total_capacity")
    private Double coolingCoilsNominalTotalCapacity;

    @Column(name = "cooling_coils_nominal_total_capacity_threshold")
    private Double coolingCoilsNominalTotalCapacityThreshold;

    @Column(name = "heating_coils_nominal_total_capacity_reheat_coil_zone_1")
    private Double heatingCoilsNominalTotalCapacityReheatCoilZone1;

    @Column(name = "heating_coils_nominal_total_capacity_reheat_coil_zone_1_threshold")
    private Double heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold;

    @Column(name = "heating_coils_nominal_total_capacity_reheat_coil_zone_2")
    private Double heatingCoilsNominalTotalCapacityReheatCoilZone2;

    @Column(name = "heating_coils_nominal_total_capacity_reheat_coil_zone_2_threshold")
    private Double heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold;

    @Column(name = "heating_coils_nominal_total_capacity_reheat_coil_zone_3")
    private Double heatingCoilsNominalTotalCapacityReheatCoilZone3;

    @Column(name = "heating_coils_nominal_total_capacity_reheat_coil_zone_3_threshold")
    private Double heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold;

    @Column(name = "fan_max_air_flow_rate")
    private Double fanMaxAirFlowRate;

    @Column(name = "fan_max_air_flow_rate_threshold")
    private Double fanMaxAirFlowRateThreshold;

    @Column(name = "fan_rated_electric_power")
    private Double fanRatedElectricPower;

    @Column(name = "fan_rated_electric_power_threshold")
    private Double fanRatedElectricPowerThreshold;

    @Column(name = "fan_rated_power_per_max_air_flow_rate")
    private Double fanRatedPowerPerMaxAirFlowRate;

    @Column(name = "fan_rated_power_per_max_air_flow_rate_threshold")
    private Double fanRatedPowerPerMaxAirFlowRateThreshold;

    @Column(name = "fan_motor_heat_in_air_fraction")
    private Double fanMotorHeatInAirFraction;

    @Column(name = "fan_motor_heat_in_air_fraction_threshold")
    private Double fanMotorHeatInAirFractionThreshold;

    @Column(name = "fan_total_efficiency")
    private Double fanTotalEfficiency;

    @Column(name = "fan_total_efficiency_threshold")
    private Double fanTotalEfficiencyThreshold;

    @Column(name = "pumps_power_per_water_flow_rate_circ_pump")
    private Double pumpsPowerPerWaterFlowRateCircPump;

    @Column(name = "pumps_power_per_water_flow_rate_circ_pump_threshold")
    private Double pumpsPowerPerWaterFlowRateCircPumpThreshold;

    @Column(name = "pumps_power_per_water_flow_rate_hw_circ_pump")
    private Double pumpsPowerPerWaterFlowRateHwCircPump;

    @Column(name = "pumps_power_per_water_flow_rate_hw_circ_pump_threshold")
    private Double pumpsPowerPerWaterFlowRateHwCircPumpThreshold;

    @Column(name = "pumps_power_per_water_flow_rate_cond_circ_pump")
    private Double pumpsPowerPerWaterFlowRateCondCircPump;

    @Column(name = "pumps_power_per_water_flow_rate_cond_circ_pump_threshold")
    private Double pumpsPowerPerWaterFlowRateCondCircPumpThreshold;

    @Column(name = "pumps_head_circ_pump")
    private Double pumpsHeadCircPump;

    @Column(name = "pumps_head_circ_pump_threshold")
    private Double pumpsHeadCircPumpThreshold;

    @Column(name = "pumps_head_hw_circ_pump")
    private Double pumpsHeadHwCircPump;

    @Column(name = "pumps_head_hw_circ_pump_threshold")
    private Double pumpsHeadHwCircPumpThreshold;

    @Column(name = "pumps_head_cond_circ_pump")
    private Double pumpsHeadCondCircPump;

    @Column(name = "pumps_head_cond_circ_pump_threshold")
    private Double pumpsHeadCondCircPumpThreshold;

    @Column(name = "pumps_electric_power_circ_pump")
    private Double pumpsElectricPowerCircPump;

    @Column(name = "pumps_electric_power_circ_pump_threshold")
    private Double pumpsElectricPowerCircPumpThreshold;

    @Column(name = "pumps_electric_power_hw_circ_pump")
    private Double pumpsElectricPowerHwCircPump;

    @Column(name = "pumps_electric_power_hw_circ_pump_threshold")
    private Double pumpsElectricPowerHwCircPumpThreshold;

    @Column(name = "pumps_electric_power_cond_circ_pump")
    private Double pumpsElectricPowerCondCircPump;

    @Column(name = "pumps_electric_power_cond_circ_pump_threshold")
    private Double pumpsElectricPowerCondCircPumpThreshold;

    @Column(name = "pumps_motor_efficiency_circ_pump")
    private Double pumpsMotorEfficiencyCircPump;

    @Column(name = "pumps_motor_efficiency_circ_pump_threshold")
    private Double pumpsMotorEfficiencyCircPumpThreshold;

    @Column(name = "pumps_motor_efficiency_hw_circ_pump")
    private Double pumpsMotorEfficiencyHwCircPump;

    @Column(name = "pumps_motor_efficiency_hw_circ_pump_threshold")
    private Double pumpsMotorEfficiencyHwCircPumpThreshold;

    @Column(name = "pumps_motor_efficiency_cond_circ_pump")
    private Double pumpsMotorEfficiencyCondCircPump;

    @Column(name = "pumps_motor_efficiency_cond_circ_pump_threshold")
    private Double pumpsMotorEfficiencyCondCircPumpThreshold;

    @Column(name = "pumps_water_flow_circ_pump")
    private Double pumpsWaterFlowCircPump;

    @Column(name = "pumps_water_flow_circ_pump_threshold")
    private Double pumpsWaterFlowCircPumpThreshold;

    @Column(name = "pumps_water_flow_hw_circ_pump")
    private Double pumpsWaterFlowHwCircPump;

    @Column(name = "pumps_water_flow_hw_circ_pump_threshold")
    private Double pumpsWaterFlowHwCircPumpThreshold;

    @Column(name = "pumps_water_flow_cond_circ_pump")
    private Double pumpsWaterFlowCondCircPump;

    @Column(name = "pumps_water_flow_cond_circ_pump_threshold")
    private Double pumpsWaterFlowCondCircPumpThreshold;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmPadSetName() {
        return mPadSetName;
    }

    public Padset mPadSetName(String mPadSetName) {
        this.mPadSetName = mPadSetName;
        return this;
    }

    public void setmPadSetName(String mPadSetName) {
        this.mPadSetName = mPadSetName;
    }

    public Double getmLatitude() {
        return mLatitude;
    }

    public Padset mLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
        return this;
    }

    public void setmLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getmLongitude() {
        return mLongitude;
    }

    public Padset mLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
        return this;
    }

    public void setmLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public Double getCoolingCoilsNominalSensibleHeatRatio() {
        return coolingCoilsNominalSensibleHeatRatio;
    }

    public Padset coolingCoilsNominalSensibleHeatRatio(Double coolingCoilsNominalSensibleHeatRatio) {
        this.coolingCoilsNominalSensibleHeatRatio = coolingCoilsNominalSensibleHeatRatio;
        return this;
    }

    public void setCoolingCoilsNominalSensibleHeatRatio(Double coolingCoilsNominalSensibleHeatRatio) {
        this.coolingCoilsNominalSensibleHeatRatio = coolingCoilsNominalSensibleHeatRatio;
    }

    public Double getCoolingCoilsNominalSensibleHeatRatioThreshold() {
        return coolingCoilsNominalSensibleHeatRatioThreshold;
    }

    public Padset coolingCoilsNominalSensibleHeatRatioThreshold(Double coolingCoilsNominalSensibleHeatRatioThreshold) {
        this.coolingCoilsNominalSensibleHeatRatioThreshold = coolingCoilsNominalSensibleHeatRatioThreshold;
        return this;
    }

    public void setCoolingCoilsNominalSensibleHeatRatioThreshold(Double coolingCoilsNominalSensibleHeatRatioThreshold) {
        this.coolingCoilsNominalSensibleHeatRatioThreshold = coolingCoilsNominalSensibleHeatRatioThreshold;
    }

    public Double getCoolingCoilsNominalTotalCapacity() {
        return coolingCoilsNominalTotalCapacity;
    }

    public Padset coolingCoilsNominalTotalCapacity(Double coolingCoilsNominalTotalCapacity) {
        this.coolingCoilsNominalTotalCapacity = coolingCoilsNominalTotalCapacity;
        return this;
    }

    public void setCoolingCoilsNominalTotalCapacity(Double coolingCoilsNominalTotalCapacity) {
        this.coolingCoilsNominalTotalCapacity = coolingCoilsNominalTotalCapacity;
    }

    public Double getCoolingCoilsNominalTotalCapacityThreshold() {
        return coolingCoilsNominalTotalCapacityThreshold;
    }

    public Padset coolingCoilsNominalTotalCapacityThreshold(Double coolingCoilsNominalTotalCapacityThreshold) {
        this.coolingCoilsNominalTotalCapacityThreshold = coolingCoilsNominalTotalCapacityThreshold;
        return this;
    }

    public void setCoolingCoilsNominalTotalCapacityThreshold(Double coolingCoilsNominalTotalCapacityThreshold) {
        this.coolingCoilsNominalTotalCapacityThreshold = coolingCoilsNominalTotalCapacityThreshold;
    }

    public Double getHeatingCoilsNominalTotalCapacityReheatCoilZone1() {
        return heatingCoilsNominalTotalCapacityReheatCoilZone1;
    }

    public Padset heatingCoilsNominalTotalCapacityReheatCoilZone1(Double heatingCoilsNominalTotalCapacityReheatCoilZone1) {
        this.heatingCoilsNominalTotalCapacityReheatCoilZone1 = heatingCoilsNominalTotalCapacityReheatCoilZone1;
        return this;
    }

    public void setHeatingCoilsNominalTotalCapacityReheatCoilZone1(Double heatingCoilsNominalTotalCapacityReheatCoilZone1) {
        this.heatingCoilsNominalTotalCapacityReheatCoilZone1 = heatingCoilsNominalTotalCapacityReheatCoilZone1;
    }

    public Double getHeatingCoilsNominalTotalCapacityReheatCoilZone1Threshold() {
        return heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold;
    }

    public Padset heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold(Double heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold) {
        this.heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold = heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold;
        return this;
    }

    public void setHeatingCoilsNominalTotalCapacityReheatCoilZone1Threshold(Double heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold) {
        this.heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold = heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold;
    }

    public Double getHeatingCoilsNominalTotalCapacityReheatCoilZone2() {
        return heatingCoilsNominalTotalCapacityReheatCoilZone2;
    }

    public Padset heatingCoilsNominalTotalCapacityReheatCoilZone2(Double heatingCoilsNominalTotalCapacityReheatCoilZone2) {
        this.heatingCoilsNominalTotalCapacityReheatCoilZone2 = heatingCoilsNominalTotalCapacityReheatCoilZone2;
        return this;
    }

    public void setHeatingCoilsNominalTotalCapacityReheatCoilZone2(Double heatingCoilsNominalTotalCapacityReheatCoilZone2) {
        this.heatingCoilsNominalTotalCapacityReheatCoilZone2 = heatingCoilsNominalTotalCapacityReheatCoilZone2;
    }

    public Double getHeatingCoilsNominalTotalCapacityReheatCoilZone2Threshold() {
        return heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold;
    }

    public Padset heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold(Double heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold) {
        this.heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold = heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold;
        return this;
    }

    public void setHeatingCoilsNominalTotalCapacityReheatCoilZone2Threshold(Double heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold) {
        this.heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold = heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold;
    }

    public Double getHeatingCoilsNominalTotalCapacityReheatCoilZone3() {
        return heatingCoilsNominalTotalCapacityReheatCoilZone3;
    }

    public Padset heatingCoilsNominalTotalCapacityReheatCoilZone3(Double heatingCoilsNominalTotalCapacityReheatCoilZone3) {
        this.heatingCoilsNominalTotalCapacityReheatCoilZone3 = heatingCoilsNominalTotalCapacityReheatCoilZone3;
        return this;
    }

    public void setHeatingCoilsNominalTotalCapacityReheatCoilZone3(Double heatingCoilsNominalTotalCapacityReheatCoilZone3) {
        this.heatingCoilsNominalTotalCapacityReheatCoilZone3 = heatingCoilsNominalTotalCapacityReheatCoilZone3;
    }

    public Double getHeatingCoilsNominalTotalCapacityReheatCoilZone3Threshold() {
        return heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold;
    }

    public Padset heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold(Double heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold) {
        this.heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold = heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold;
        return this;
    }

    public void setHeatingCoilsNominalTotalCapacityReheatCoilZone3Threshold(Double heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold) {
        this.heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold = heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold;
    }

    public Double getFanMaxAirFlowRate() {
        return fanMaxAirFlowRate;
    }

    public Padset fanMaxAirFlowRate(Double fanMaxAirFlowRate) {
        this.fanMaxAirFlowRate = fanMaxAirFlowRate;
        return this;
    }

    public void setFanMaxAirFlowRate(Double fanMaxAirFlowRate) {
        this.fanMaxAirFlowRate = fanMaxAirFlowRate;
    }

    public Double getFanMaxAirFlowRateThreshold() {
        return fanMaxAirFlowRateThreshold;
    }

    public Padset fanMaxAirFlowRateThreshold(Double fanMaxAirFlowRateThreshold) {
        this.fanMaxAirFlowRateThreshold = fanMaxAirFlowRateThreshold;
        return this;
    }

    public void setFanMaxAirFlowRateThreshold(Double fanMaxAirFlowRateThreshold) {
        this.fanMaxAirFlowRateThreshold = fanMaxAirFlowRateThreshold;
    }

    public Double getFanRatedElectricPower() {
        return fanRatedElectricPower;
    }

    public Padset fanRatedElectricPower(Double fanRatedElectricPower) {
        this.fanRatedElectricPower = fanRatedElectricPower;
        return this;
    }

    public void setFanRatedElectricPower(Double fanRatedElectricPower) {
        this.fanRatedElectricPower = fanRatedElectricPower;
    }

    public Double getFanRatedElectricPowerThreshold() {
        return fanRatedElectricPowerThreshold;
    }

    public Padset fanRatedElectricPowerThreshold(Double fanRatedElectricPowerThreshold) {
        this.fanRatedElectricPowerThreshold = fanRatedElectricPowerThreshold;
        return this;
    }

    public void setFanRatedElectricPowerThreshold(Double fanRatedElectricPowerThreshold) {
        this.fanRatedElectricPowerThreshold = fanRatedElectricPowerThreshold;
    }

    public Double getFanRatedPowerPerMaxAirFlowRate() {
        return fanRatedPowerPerMaxAirFlowRate;
    }

    public Padset fanRatedPowerPerMaxAirFlowRate(Double fanRatedPowerPerMaxAirFlowRate) {
        this.fanRatedPowerPerMaxAirFlowRate = fanRatedPowerPerMaxAirFlowRate;
        return this;
    }

    public void setFanRatedPowerPerMaxAirFlowRate(Double fanRatedPowerPerMaxAirFlowRate) {
        this.fanRatedPowerPerMaxAirFlowRate = fanRatedPowerPerMaxAirFlowRate;
    }

    public Double getFanRatedPowerPerMaxAirFlowRateThreshold() {
        return fanRatedPowerPerMaxAirFlowRateThreshold;
    }

    public Padset fanRatedPowerPerMaxAirFlowRateThreshold(Double fanRatedPowerPerMaxAirFlowRateThreshold) {
        this.fanRatedPowerPerMaxAirFlowRateThreshold = fanRatedPowerPerMaxAirFlowRateThreshold;
        return this;
    }

    public void setFanRatedPowerPerMaxAirFlowRateThreshold(Double fanRatedPowerPerMaxAirFlowRateThreshold) {
        this.fanRatedPowerPerMaxAirFlowRateThreshold = fanRatedPowerPerMaxAirFlowRateThreshold;
    }

    public Double getFanMotorHeatInAirFraction() {
        return fanMotorHeatInAirFraction;
    }

    public Padset fanMotorHeatInAirFraction(Double fanMotorHeatInAirFraction) {
        this.fanMotorHeatInAirFraction = fanMotorHeatInAirFraction;
        return this;
    }

    public void setFanMotorHeatInAirFraction(Double fanMotorHeatInAirFraction) {
        this.fanMotorHeatInAirFraction = fanMotorHeatInAirFraction;
    }

    public Double getFanMotorHeatInAirFractionThreshold() {
        return fanMotorHeatInAirFractionThreshold;
    }

    public Padset fanMotorHeatInAirFractionThreshold(Double fanMotorHeatInAirFractionThreshold) {
        this.fanMotorHeatInAirFractionThreshold = fanMotorHeatInAirFractionThreshold;
        return this;
    }

    public void setFanMotorHeatInAirFractionThreshold(Double fanMotorHeatInAirFractionThreshold) {
        this.fanMotorHeatInAirFractionThreshold = fanMotorHeatInAirFractionThreshold;
    }

    public Double getFanTotalEfficiency() {
        return fanTotalEfficiency;
    }

    public Padset fanTotalEfficiency(Double fanTotalEfficiency) {
        this.fanTotalEfficiency = fanTotalEfficiency;
        return this;
    }

    public void setFanTotalEfficiency(Double fanTotalEfficiency) {
        this.fanTotalEfficiency = fanTotalEfficiency;
    }

    public Double getFanTotalEfficiencyThreshold() {
        return fanTotalEfficiencyThreshold;
    }

    public Padset fanTotalEfficiencyThreshold(Double fanTotalEfficiencyThreshold) {
        this.fanTotalEfficiencyThreshold = fanTotalEfficiencyThreshold;
        return this;
    }

    public void setFanTotalEfficiencyThreshold(Double fanTotalEfficiencyThreshold) {
        this.fanTotalEfficiencyThreshold = fanTotalEfficiencyThreshold;
    }

    public Double getPumpsPowerPerWaterFlowRateCircPump() {
        return pumpsPowerPerWaterFlowRateCircPump;
    }

    public Padset pumpsPowerPerWaterFlowRateCircPump(Double pumpsPowerPerWaterFlowRateCircPump) {
        this.pumpsPowerPerWaterFlowRateCircPump = pumpsPowerPerWaterFlowRateCircPump;
        return this;
    }

    public void setPumpsPowerPerWaterFlowRateCircPump(Double pumpsPowerPerWaterFlowRateCircPump) {
        this.pumpsPowerPerWaterFlowRateCircPump = pumpsPowerPerWaterFlowRateCircPump;
    }

    public Double getPumpsPowerPerWaterFlowRateCircPumpThreshold() {
        return pumpsPowerPerWaterFlowRateCircPumpThreshold;
    }

    public Padset pumpsPowerPerWaterFlowRateCircPumpThreshold(Double pumpsPowerPerWaterFlowRateCircPumpThreshold) {
        this.pumpsPowerPerWaterFlowRateCircPumpThreshold = pumpsPowerPerWaterFlowRateCircPumpThreshold;
        return this;
    }

    public void setPumpsPowerPerWaterFlowRateCircPumpThreshold(Double pumpsPowerPerWaterFlowRateCircPumpThreshold) {
        this.pumpsPowerPerWaterFlowRateCircPumpThreshold = pumpsPowerPerWaterFlowRateCircPumpThreshold;
    }

    public Double getPumpsPowerPerWaterFlowRateHwCircPump() {
        return pumpsPowerPerWaterFlowRateHwCircPump;
    }

    public Padset pumpsPowerPerWaterFlowRateHwCircPump(Double pumpsPowerPerWaterFlowRateHwCircPump) {
        this.pumpsPowerPerWaterFlowRateHwCircPump = pumpsPowerPerWaterFlowRateHwCircPump;
        return this;
    }

    public void setPumpsPowerPerWaterFlowRateHwCircPump(Double pumpsPowerPerWaterFlowRateHwCircPump) {
        this.pumpsPowerPerWaterFlowRateHwCircPump = pumpsPowerPerWaterFlowRateHwCircPump;
    }

    public Double getPumpsPowerPerWaterFlowRateHwCircPumpThreshold() {
        return pumpsPowerPerWaterFlowRateHwCircPumpThreshold;
    }

    public Padset pumpsPowerPerWaterFlowRateHwCircPumpThreshold(Double pumpsPowerPerWaterFlowRateHwCircPumpThreshold) {
        this.pumpsPowerPerWaterFlowRateHwCircPumpThreshold = pumpsPowerPerWaterFlowRateHwCircPumpThreshold;
        return this;
    }

    public void setPumpsPowerPerWaterFlowRateHwCircPumpThreshold(Double pumpsPowerPerWaterFlowRateHwCircPumpThreshold) {
        this.pumpsPowerPerWaterFlowRateHwCircPumpThreshold = pumpsPowerPerWaterFlowRateHwCircPumpThreshold;
    }

    public Double getPumpsPowerPerWaterFlowRateCondCircPump() {
        return pumpsPowerPerWaterFlowRateCondCircPump;
    }

    public Padset pumpsPowerPerWaterFlowRateCondCircPump(Double pumpsPowerPerWaterFlowRateCondCircPump) {
        this.pumpsPowerPerWaterFlowRateCondCircPump = pumpsPowerPerWaterFlowRateCondCircPump;
        return this;
    }

    public void setPumpsPowerPerWaterFlowRateCondCircPump(Double pumpsPowerPerWaterFlowRateCondCircPump) {
        this.pumpsPowerPerWaterFlowRateCondCircPump = pumpsPowerPerWaterFlowRateCondCircPump;
    }

    public Double getPumpsPowerPerWaterFlowRateCondCircPumpThreshold() {
        return pumpsPowerPerWaterFlowRateCondCircPumpThreshold;
    }

    public Padset pumpsPowerPerWaterFlowRateCondCircPumpThreshold(Double pumpsPowerPerWaterFlowRateCondCircPumpThreshold) {
        this.pumpsPowerPerWaterFlowRateCondCircPumpThreshold = pumpsPowerPerWaterFlowRateCondCircPumpThreshold;
        return this;
    }

    public void setPumpsPowerPerWaterFlowRateCondCircPumpThreshold(Double pumpsPowerPerWaterFlowRateCondCircPumpThreshold) {
        this.pumpsPowerPerWaterFlowRateCondCircPumpThreshold = pumpsPowerPerWaterFlowRateCondCircPumpThreshold;
    }

    public Double getPumpsHeadCircPump() {
        return pumpsHeadCircPump;
    }

    public Padset pumpsHeadCircPump(Double pumpsHeadCircPump) {
        this.pumpsHeadCircPump = pumpsHeadCircPump;
        return this;
    }

    public void setPumpsHeadCircPump(Double pumpsHeadCircPump) {
        this.pumpsHeadCircPump = pumpsHeadCircPump;
    }

    public Double getPumpsHeadCircPumpThreshold() {
        return pumpsHeadCircPumpThreshold;
    }

    public Padset pumpsHeadCircPumpThreshold(Double pumpsHeadCircPumpThreshold) {
        this.pumpsHeadCircPumpThreshold = pumpsHeadCircPumpThreshold;
        return this;
    }

    public void setPumpsHeadCircPumpThreshold(Double pumpsHeadCircPumpThreshold) {
        this.pumpsHeadCircPumpThreshold = pumpsHeadCircPumpThreshold;
    }

    public Double getPumpsHeadHwCircPump() {
        return pumpsHeadHwCircPump;
    }

    public Padset pumpsHeadHwCircPump(Double pumpsHeadHwCircPump) {
        this.pumpsHeadHwCircPump = pumpsHeadHwCircPump;
        return this;
    }

    public void setPumpsHeadHwCircPump(Double pumpsHeadHwCircPump) {
        this.pumpsHeadHwCircPump = pumpsHeadHwCircPump;
    }

    public Double getPumpsHeadHwCircPumpThreshold() {
        return pumpsHeadHwCircPumpThreshold;
    }

    public Padset pumpsHeadHwCircPumpThreshold(Double pumpsHeadHwCircPumpThreshold) {
        this.pumpsHeadHwCircPumpThreshold = pumpsHeadHwCircPumpThreshold;
        return this;
    }

    public void setPumpsHeadHwCircPumpThreshold(Double pumpsHeadHwCircPumpThreshold) {
        this.pumpsHeadHwCircPumpThreshold = pumpsHeadHwCircPumpThreshold;
    }

    public Double getPumpsHeadCondCircPump() {
        return pumpsHeadCondCircPump;
    }

    public Padset pumpsHeadCondCircPump(Double pumpsHeadCondCircPump) {
        this.pumpsHeadCondCircPump = pumpsHeadCondCircPump;
        return this;
    }

    public void setPumpsHeadCondCircPump(Double pumpsHeadCondCircPump) {
        this.pumpsHeadCondCircPump = pumpsHeadCondCircPump;
    }

    public Double getPumpsHeadCondCircPumpThreshold() {
        return pumpsHeadCondCircPumpThreshold;
    }

    public Padset pumpsHeadCondCircPumpThreshold(Double pumpsHeadCondCircPumpThreshold) {
        this.pumpsHeadCondCircPumpThreshold = pumpsHeadCondCircPumpThreshold;
        return this;
    }

    public void setPumpsHeadCondCircPumpThreshold(Double pumpsHeadCondCircPumpThreshold) {
        this.pumpsHeadCondCircPumpThreshold = pumpsHeadCondCircPumpThreshold;
    }

    public Double getPumpsElectricPowerCircPump() {
        return pumpsElectricPowerCircPump;
    }

    public Padset pumpsElectricPowerCircPump(Double pumpsElectricPowerCircPump) {
        this.pumpsElectricPowerCircPump = pumpsElectricPowerCircPump;
        return this;
    }

    public void setPumpsElectricPowerCircPump(Double pumpsElectricPowerCircPump) {
        this.pumpsElectricPowerCircPump = pumpsElectricPowerCircPump;
    }

    public Double getPumpsElectricPowerCircPumpThreshold() {
        return pumpsElectricPowerCircPumpThreshold;
    }

    public Padset pumpsElectricPowerCircPumpThreshold(Double pumpsElectricPowerCircPumpThreshold) {
        this.pumpsElectricPowerCircPumpThreshold = pumpsElectricPowerCircPumpThreshold;
        return this;
    }

    public void setPumpsElectricPowerCircPumpThreshold(Double pumpsElectricPowerCircPumpThreshold) {
        this.pumpsElectricPowerCircPumpThreshold = pumpsElectricPowerCircPumpThreshold;
    }

    public Double getPumpsElectricPowerHwCircPump() {
        return pumpsElectricPowerHwCircPump;
    }

    public Padset pumpsElectricPowerHwCircPump(Double pumpsElectricPowerHwCircPump) {
        this.pumpsElectricPowerHwCircPump = pumpsElectricPowerHwCircPump;
        return this;
    }

    public void setPumpsElectricPowerHwCircPump(Double pumpsElectricPowerHwCircPump) {
        this.pumpsElectricPowerHwCircPump = pumpsElectricPowerHwCircPump;
    }

    public Double getPumpsElectricPowerHwCircPumpThreshold() {
        return pumpsElectricPowerHwCircPumpThreshold;
    }

    public Padset pumpsElectricPowerHwCircPumpThreshold(Double pumpsElectricPowerHwCircPumpThreshold) {
        this.pumpsElectricPowerHwCircPumpThreshold = pumpsElectricPowerHwCircPumpThreshold;
        return this;
    }

    public void setPumpsElectricPowerHwCircPumpThreshold(Double pumpsElectricPowerHwCircPumpThreshold) {
        this.pumpsElectricPowerHwCircPumpThreshold = pumpsElectricPowerHwCircPumpThreshold;
    }

    public Double getPumpsElectricPowerCondCircPump() {
        return pumpsElectricPowerCondCircPump;
    }

    public Padset pumpsElectricPowerCondCircPump(Double pumpsElectricPowerCondCircPump) {
        this.pumpsElectricPowerCondCircPump = pumpsElectricPowerCondCircPump;
        return this;
    }

    public void setPumpsElectricPowerCondCircPump(Double pumpsElectricPowerCondCircPump) {
        this.pumpsElectricPowerCondCircPump = pumpsElectricPowerCondCircPump;
    }

    public Double getPumpsElectricPowerCondCircPumpThreshold() {
        return pumpsElectricPowerCondCircPumpThreshold;
    }

    public Padset pumpsElectricPowerCondCircPumpThreshold(Double pumpsElectricPowerCondCircPumpThreshold) {
        this.pumpsElectricPowerCondCircPumpThreshold = pumpsElectricPowerCondCircPumpThreshold;
        return this;
    }

    public void setPumpsElectricPowerCondCircPumpThreshold(Double pumpsElectricPowerCondCircPumpThreshold) {
        this.pumpsElectricPowerCondCircPumpThreshold = pumpsElectricPowerCondCircPumpThreshold;
    }

    public Double getPumpsMotorEfficiencyCircPump() {
        return pumpsMotorEfficiencyCircPump;
    }

    public Padset pumpsMotorEfficiencyCircPump(Double pumpsMotorEfficiencyCircPump) {
        this.pumpsMotorEfficiencyCircPump = pumpsMotorEfficiencyCircPump;
        return this;
    }

    public void setPumpsMotorEfficiencyCircPump(Double pumpsMotorEfficiencyCircPump) {
        this.pumpsMotorEfficiencyCircPump = pumpsMotorEfficiencyCircPump;
    }

    public Double getPumpsMotorEfficiencyCircPumpThreshold() {
        return pumpsMotorEfficiencyCircPumpThreshold;
    }

    public Padset pumpsMotorEfficiencyCircPumpThreshold(Double pumpsMotorEfficiencyCircPumpThreshold) {
        this.pumpsMotorEfficiencyCircPumpThreshold = pumpsMotorEfficiencyCircPumpThreshold;
        return this;
    }

    public void setPumpsMotorEfficiencyCircPumpThreshold(Double pumpsMotorEfficiencyCircPumpThreshold) {
        this.pumpsMotorEfficiencyCircPumpThreshold = pumpsMotorEfficiencyCircPumpThreshold;
    }

    public Double getPumpsMotorEfficiencyHwCircPump() {
        return pumpsMotorEfficiencyHwCircPump;
    }

    public Padset pumpsMotorEfficiencyHwCircPump(Double pumpsMotorEfficiencyHwCircPump) {
        this.pumpsMotorEfficiencyHwCircPump = pumpsMotorEfficiencyHwCircPump;
        return this;
    }

    public void setPumpsMotorEfficiencyHwCircPump(Double pumpsMotorEfficiencyHwCircPump) {
        this.pumpsMotorEfficiencyHwCircPump = pumpsMotorEfficiencyHwCircPump;
    }

    public Double getPumpsMotorEfficiencyHwCircPumpThreshold() {
        return pumpsMotorEfficiencyHwCircPumpThreshold;
    }

    public Padset pumpsMotorEfficiencyHwCircPumpThreshold(Double pumpsMotorEfficiencyHwCircPumpThreshold) {
        this.pumpsMotorEfficiencyHwCircPumpThreshold = pumpsMotorEfficiencyHwCircPumpThreshold;
        return this;
    }

    public void setPumpsMotorEfficiencyHwCircPumpThreshold(Double pumpsMotorEfficiencyHwCircPumpThreshold) {
        this.pumpsMotorEfficiencyHwCircPumpThreshold = pumpsMotorEfficiencyHwCircPumpThreshold;
    }

    public Double getPumpsMotorEfficiencyCondCircPump() {
        return pumpsMotorEfficiencyCondCircPump;
    }

    public Padset pumpsMotorEfficiencyCondCircPump(Double pumpsMotorEfficiencyCondCircPump) {
        this.pumpsMotorEfficiencyCondCircPump = pumpsMotorEfficiencyCondCircPump;
        return this;
    }

    public void setPumpsMotorEfficiencyCondCircPump(Double pumpsMotorEfficiencyCondCircPump) {
        this.pumpsMotorEfficiencyCondCircPump = pumpsMotorEfficiencyCondCircPump;
    }

    public Double getPumpsMotorEfficiencyCondCircPumpThreshold() {
        return pumpsMotorEfficiencyCondCircPumpThreshold;
    }

    public Padset pumpsMotorEfficiencyCondCircPumpThreshold(Double pumpsMotorEfficiencyCondCircPumpThreshold) {
        this.pumpsMotorEfficiencyCondCircPumpThreshold = pumpsMotorEfficiencyCondCircPumpThreshold;
        return this;
    }

    public void setPumpsMotorEfficiencyCondCircPumpThreshold(Double pumpsMotorEfficiencyCondCircPumpThreshold) {
        this.pumpsMotorEfficiencyCondCircPumpThreshold = pumpsMotorEfficiencyCondCircPumpThreshold;
    }

    public Double getPumpsWaterFlowCircPump() {
        return pumpsWaterFlowCircPump;
    }

    public Padset pumpsWaterFlowCircPump(Double pumpsWaterFlowCircPump) {
        this.pumpsWaterFlowCircPump = pumpsWaterFlowCircPump;
        return this;
    }

    public void setPumpsWaterFlowCircPump(Double pumpsWaterFlowCircPump) {
        this.pumpsWaterFlowCircPump = pumpsWaterFlowCircPump;
    }

    public Double getPumpsWaterFlowCircPumpThreshold() {
        return pumpsWaterFlowCircPumpThreshold;
    }

    public Padset pumpsWaterFlowCircPumpThreshold(Double pumpsWaterFlowCircPumpThreshold) {
        this.pumpsWaterFlowCircPumpThreshold = pumpsWaterFlowCircPumpThreshold;
        return this;
    }

    public void setPumpsWaterFlowCircPumpThreshold(Double pumpsWaterFlowCircPumpThreshold) {
        this.pumpsWaterFlowCircPumpThreshold = pumpsWaterFlowCircPumpThreshold;
    }

    public Double getPumpsWaterFlowHwCircPump() {
        return pumpsWaterFlowHwCircPump;
    }

    public Padset pumpsWaterFlowHwCircPump(Double pumpsWaterFlowHwCircPump) {
        this.pumpsWaterFlowHwCircPump = pumpsWaterFlowHwCircPump;
        return this;
    }

    public void setPumpsWaterFlowHwCircPump(Double pumpsWaterFlowHwCircPump) {
        this.pumpsWaterFlowHwCircPump = pumpsWaterFlowHwCircPump;
    }

    public Double getPumpsWaterFlowHwCircPumpThreshold() {
        return pumpsWaterFlowHwCircPumpThreshold;
    }

    public Padset pumpsWaterFlowHwCircPumpThreshold(Double pumpsWaterFlowHwCircPumpThreshold) {
        this.pumpsWaterFlowHwCircPumpThreshold = pumpsWaterFlowHwCircPumpThreshold;
        return this;
    }

    public void setPumpsWaterFlowHwCircPumpThreshold(Double pumpsWaterFlowHwCircPumpThreshold) {
        this.pumpsWaterFlowHwCircPumpThreshold = pumpsWaterFlowHwCircPumpThreshold;
    }

    public Double getPumpsWaterFlowCondCircPump() {
        return pumpsWaterFlowCondCircPump;
    }

    public Padset pumpsWaterFlowCondCircPump(Double pumpsWaterFlowCondCircPump) {
        this.pumpsWaterFlowCondCircPump = pumpsWaterFlowCondCircPump;
        return this;
    }

    public void setPumpsWaterFlowCondCircPump(Double pumpsWaterFlowCondCircPump) {
        this.pumpsWaterFlowCondCircPump = pumpsWaterFlowCondCircPump;
    }

    public Double getPumpsWaterFlowCondCircPumpThreshold() {
        return pumpsWaterFlowCondCircPumpThreshold;
    }

    public Padset pumpsWaterFlowCondCircPumpThreshold(Double pumpsWaterFlowCondCircPumpThreshold) {
        this.pumpsWaterFlowCondCircPumpThreshold = pumpsWaterFlowCondCircPumpThreshold;
        return this;
    }

    public void setPumpsWaterFlowCondCircPumpThreshold(Double pumpsWaterFlowCondCircPumpThreshold) {
        this.pumpsWaterFlowCondCircPumpThreshold = pumpsWaterFlowCondCircPumpThreshold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Padset padset = (Padset) o;
        if(padset.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, padset.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Padset{" +
            "id=" + id +
            ", mPadSetName='" + mPadSetName + "'" +
            ", mLatitude='" + mLatitude + "'" +
            ", mLongitude='" + mLongitude + "'" +
            ", coolingCoilsNominalSensibleHeatRatio='" + coolingCoilsNominalSensibleHeatRatio + "'" +
            ", coolingCoilsNominalSensibleHeatRatioThreshold='" + coolingCoilsNominalSensibleHeatRatioThreshold + "'" +
            ", coolingCoilsNominalTotalCapacity='" + coolingCoilsNominalTotalCapacity + "'" +
            ", coolingCoilsNominalTotalCapacityThreshold='" + coolingCoilsNominalTotalCapacityThreshold + "'" +
            ", heatingCoilsNominalTotalCapacityReheatCoilZone1='" + heatingCoilsNominalTotalCapacityReheatCoilZone1 + "'" +
            ", heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold='" + heatingCoilsNominalTotalCapacityReheatCoilZone1Threshold + "'" +
            ", heatingCoilsNominalTotalCapacityReheatCoilZone2='" + heatingCoilsNominalTotalCapacityReheatCoilZone2 + "'" +
            ", heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold='" + heatingCoilsNominalTotalCapacityReheatCoilZone2Threshold + "'" +
            ", heatingCoilsNominalTotalCapacityReheatCoilZone3='" + heatingCoilsNominalTotalCapacityReheatCoilZone3 + "'" +
            ", heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold='" + heatingCoilsNominalTotalCapacityReheatCoilZone3Threshold + "'" +
            ", fanMaxAirFlowRate='" + fanMaxAirFlowRate + "'" +
            ", fanMaxAirFlowRateThreshold='" + fanMaxAirFlowRateThreshold + "'" +
            ", fanRatedElectricPower='" + fanRatedElectricPower + "'" +
            ", fanRatedElectricPowerThreshold='" + fanRatedElectricPowerThreshold + "'" +
            ", fanRatedPowerPerMaxAirFlowRate='" + fanRatedPowerPerMaxAirFlowRate + "'" +
            ", fanRatedPowerPerMaxAirFlowRateThreshold='" + fanRatedPowerPerMaxAirFlowRateThreshold + "'" +
            ", fanMotorHeatInAirFraction='" + fanMotorHeatInAirFraction + "'" +
            ", fanMotorHeatInAirFractionThreshold='" + fanMotorHeatInAirFractionThreshold + "'" +
            ", fanTotalEfficiency='" + fanTotalEfficiency + "'" +
            ", fanTotalEfficiencyThreshold='" + fanTotalEfficiencyThreshold + "'" +
            ", pumpsPowerPerWaterFlowRateCircPump='" + pumpsPowerPerWaterFlowRateCircPump + "'" +
            ", pumpsPowerPerWaterFlowRateCircPumpThreshold='" + pumpsPowerPerWaterFlowRateCircPumpThreshold + "'" +
            ", pumpsPowerPerWaterFlowRateHwCircPump='" + pumpsPowerPerWaterFlowRateHwCircPump + "'" +
            ", pumpsPowerPerWaterFlowRateHwCircPumpThreshold='" + pumpsPowerPerWaterFlowRateHwCircPumpThreshold + "'" +
            ", pumpsPowerPerWaterFlowRateCondCircPump='" + pumpsPowerPerWaterFlowRateCondCircPump + "'" +
            ", pumpsPowerPerWaterFlowRateCondCircPumpThreshold='" + pumpsPowerPerWaterFlowRateCondCircPumpThreshold + "'" +
            ", pumpsHeadCircPump='" + pumpsHeadCircPump + "'" +
            ", pumpsHeadCircPumpThreshold='" + pumpsHeadCircPumpThreshold + "'" +
            ", pumpsHeadHwCircPump='" + pumpsHeadHwCircPump + "'" +
            ", pumpsHeadHwCircPumpThreshold='" + pumpsHeadHwCircPumpThreshold + "'" +
            ", pumpsHeadCondCircPump='" + pumpsHeadCondCircPump + "'" +
            ", pumpsHeadCondCircPumpThreshold='" + pumpsHeadCondCircPumpThreshold + "'" +
            ", pumpsElectricPowerCircPump='" + pumpsElectricPowerCircPump + "'" +
            ", pumpsElectricPowerCircPumpThreshold='" + pumpsElectricPowerCircPumpThreshold + "'" +
            ", pumpsElectricPowerHwCircPump='" + pumpsElectricPowerHwCircPump + "'" +
            ", pumpsElectricPowerHwCircPumpThreshold='" + pumpsElectricPowerHwCircPumpThreshold + "'" +
            ", pumpsElectricPowerCondCircPump='" + pumpsElectricPowerCondCircPump + "'" +
            ", pumpsElectricPowerCondCircPumpThreshold='" + pumpsElectricPowerCondCircPumpThreshold + "'" +
            ", pumpsMotorEfficiencyCircPump='" + pumpsMotorEfficiencyCircPump + "'" +
            ", pumpsMotorEfficiencyCircPumpThreshold='" + pumpsMotorEfficiencyCircPumpThreshold + "'" +
            ", pumpsMotorEfficiencyHwCircPump='" + pumpsMotorEfficiencyHwCircPump + "'" +
            ", pumpsMotorEfficiencyHwCircPumpThreshold='" + pumpsMotorEfficiencyHwCircPumpThreshold + "'" +
            ", pumpsMotorEfficiencyCondCircPump='" + pumpsMotorEfficiencyCondCircPump + "'" +
            ", pumpsMotorEfficiencyCondCircPumpThreshold='" + pumpsMotorEfficiencyCondCircPumpThreshold + "'" +
            ", pumpsWaterFlowCircPump='" + pumpsWaterFlowCircPump + "'" +
            ", pumpsWaterFlowCircPumpThreshold='" + pumpsWaterFlowCircPumpThreshold + "'" +
            ", pumpsWaterFlowHwCircPump='" + pumpsWaterFlowHwCircPump + "'" +
            ", pumpsWaterFlowHwCircPumpThreshold='" + pumpsWaterFlowHwCircPumpThreshold + "'" +
            ", pumpsWaterFlowCondCircPump='" + pumpsWaterFlowCondCircPump + "'" +
            ", pumpsWaterFlowCondCircPumpThreshold='" + pumpsWaterFlowCondCircPumpThreshold + "'" +
            '}';
    }
}
