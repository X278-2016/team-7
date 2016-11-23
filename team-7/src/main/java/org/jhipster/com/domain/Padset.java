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

    @Column(name = "cooling_coils_nominal_total_capacity")
    private Double coolingCoilsNominalTotalCapacity;

    @Column(name = "heating_coils_nominal_total_capacity_reheat_coil_zone_1")
    private Double heatingCoilsNominalTotalCapacityReheatCoilZone1;

    @Column(name = "heating_coils_nominal_total_capacity_reheat_coil_zone_2")
    private Double heatingCoilsNominalTotalCapacityReheatCoilZone2;

    @Column(name = "heating_coils_nominal_total_capacity_reheat_coil_zone_3")
    private Double heatingCoilsNominalTotalCapacityReheatCoilZone3;

    @Column(name = "fan_max_air_flow_rate")
    private Double fanMaxAirFlowRate;

    @Column(name = "fan_rated_electric_power")
    private Double fanRatedElectricPower;

    @Column(name = "fan_rated_power_per_max_air_flow_rate")
    private Double fanRatedPowerPerMaxAirFlowRate;

    @Column(name = "fan_motor_heat_in_air_fraction")
    private Double fanMotorHeatInAirFraction;

    @Column(name = "fan_total_efficiency")
    private Double fanTotalEfficiency;

    @Column(name = "pumps_power_per_water_flow_rate_circ_pump")
    private Double pumpsPowerPerWaterFlowRateCircPump;

    @Column(name = "pumps_power_per_water_flow_rate_hw_circ_pump")
    private Double pumpsPowerPerWaterFlowRateHwCircPump;

    @Column(name = "pumps_power_per_water_flow_rate_cond_circ_pump")
    private Double pumpsPowerPerWaterFlowRateCondCircPump;

    @Column(name = "pumps_head_circ_pump")
    private Double pumpsHeadCircPump;

    @Column(name = "pumps_head_hw_circ_pump")
    private Double pumpsHeadHwCircPump;

    @Column(name = "pumps_head_cond_circ_pump")
    private Double pumpsHeadCondCircPump;

    @Column(name = "pumps_electric_power_circ_pump")
    private Double pumpsElectricPowerCircPump;

    @Column(name = "pumps_electric_power_hw_circ_pump")
    private Double pumpsElectricPowerHwCircPump;

    @Column(name = "pumps_electric_power_cond_circ_pump")
    private Double pumpsElectricPowerCondCircPump;

    @Column(name = "pumps_motor_efficiency_circ_pump")
    private Double pumpsMotorEfficiencyCircPump;

    @Column(name = "pumps_motor_efficiency_hw_circ_pump")
    private Double pumpsMotorEfficiencyHwCircPump;

    @Column(name = "pumps_motor_efficiency_cond_circ_pump")
    private Double pumpsMotorEfficiencyCondCircPump;

    @Column(name = "pumps_water_flow_circ_pump")
    private Double pumpsWaterFlowCircPump;

    @Column(name = "pumps_water_flow_hw_circ_pump")
    private Double pumpsWaterFlowHwCircPump;

    @Column(name = "pumps_water_flow_cond_circ_pump")
    private Double pumpsWaterFlowCondCircPump;

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
            ", coolingCoilsNominalTotalCapacity='" + coolingCoilsNominalTotalCapacity + "'" +
            ", heatingCoilsNominalTotalCapacityReheatCoilZone1='" + heatingCoilsNominalTotalCapacityReheatCoilZone1 + "'" +
            ", heatingCoilsNominalTotalCapacityReheatCoilZone2='" + heatingCoilsNominalTotalCapacityReheatCoilZone2 + "'" +
            ", heatingCoilsNominalTotalCapacityReheatCoilZone3='" + heatingCoilsNominalTotalCapacityReheatCoilZone3 + "'" +
            ", fanMaxAirFlowRate='" + fanMaxAirFlowRate + "'" +
            ", fanRatedElectricPower='" + fanRatedElectricPower + "'" +
            ", fanRatedPowerPerMaxAirFlowRate='" + fanRatedPowerPerMaxAirFlowRate + "'" +
            ", fanMotorHeatInAirFraction='" + fanMotorHeatInAirFraction + "'" +
            ", fanTotalEfficiency='" + fanTotalEfficiency + "'" +
            ", pumpsPowerPerWaterFlowRateCircPump='" + pumpsPowerPerWaterFlowRateCircPump + "'" +
            ", pumpsPowerPerWaterFlowRateHwCircPump='" + pumpsPowerPerWaterFlowRateHwCircPump + "'" +
            ", pumpsPowerPerWaterFlowRateCondCircPump='" + pumpsPowerPerWaterFlowRateCondCircPump + "'" +
            ", pumpsHeadCircPump='" + pumpsHeadCircPump + "'" +
            ", pumpsHeadHwCircPump='" + pumpsHeadHwCircPump + "'" +
            ", pumpsHeadCondCircPump='" + pumpsHeadCondCircPump + "'" +
            ", pumpsElectricPowerCircPump='" + pumpsElectricPowerCircPump + "'" +
            ", pumpsElectricPowerHwCircPump='" + pumpsElectricPowerHwCircPump + "'" +
            ", pumpsElectricPowerCondCircPump='" + pumpsElectricPowerCondCircPump + "'" +
            ", pumpsMotorEfficiencyCircPump='" + pumpsMotorEfficiencyCircPump + "'" +
            ", pumpsMotorEfficiencyHwCircPump='" + pumpsMotorEfficiencyHwCircPump + "'" +
            ", pumpsMotorEfficiencyCondCircPump='" + pumpsMotorEfficiencyCondCircPump + "'" +
            ", pumpsWaterFlowCircPump='" + pumpsWaterFlowCircPump + "'" +
            ", pumpsWaterFlowHwCircPump='" + pumpsWaterFlowHwCircPump + "'" +
            ", pumpsWaterFlowCondCircPump='" + pumpsWaterFlowCondCircPump + "'" +
            '}';
    }
}
