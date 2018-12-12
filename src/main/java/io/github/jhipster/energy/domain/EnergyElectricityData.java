package io.github.jhipster.energy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EnergyElectricityData.
 */
@Entity
@Table(name = "energy_electricity_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EnergyElectricityData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("energyElectricityData")
    private EnergyVoltageData energyVoltageData;

    @ManyToOne
    @JsonIgnoreProperties("energyElectricityData")
    private EnergyTariffData energyTariffData;

    @ManyToOne
    @JsonIgnoreProperties("energyElectricityData")
    private EnergyCycleData energyCycleData;

    @ManyToOne
    @JsonIgnoreProperties("energyElectricityData")
    private EnergyTimePeriodData energyTimePeriodData;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnergyVoltageData getEnergyVoltageData() {
        return energyVoltageData;
    }

    public EnergyElectricityData energyVoltageData(EnergyVoltageData energyVoltageData) {
        this.energyVoltageData = energyVoltageData;
        return this;
    }

    public void setEnergyVoltageData(EnergyVoltageData energyVoltageData) {
        this.energyVoltageData = energyVoltageData;
    }

    public EnergyTariffData getEnergyTariffData() {
        return energyTariffData;
    }

    public EnergyElectricityData energyTariffData(EnergyTariffData energyTariffData) {
        this.energyTariffData = energyTariffData;
        return this;
    }

    public void setEnergyTariffData(EnergyTariffData energyTariffData) {
        this.energyTariffData = energyTariffData;
    }

    public EnergyCycleData getEnergyCycleData() {
        return energyCycleData;
    }

    public EnergyElectricityData energyCycleData(EnergyCycleData energyCycleData) {
        this.energyCycleData = energyCycleData;
        return this;
    }

    public void setEnergyCycleData(EnergyCycleData energyCycleData) {
        this.energyCycleData = energyCycleData;
    }

    public EnergyTimePeriodData getEnergyTimePeriodData() {
        return energyTimePeriodData;
    }

    public EnergyElectricityData energyTimePeriodData(EnergyTimePeriodData energyTimePeriodData) {
        this.energyTimePeriodData = energyTimePeriodData;
        return this;
    }

    public void setEnergyTimePeriodData(EnergyTimePeriodData energyTimePeriodData) {
        this.energyTimePeriodData = energyTimePeriodData;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EnergyElectricityData energyElectricityData = (EnergyElectricityData) o;
        if (energyElectricityData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyElectricityData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyElectricityData{" +
            "id=" + getId() +
            "}";
    }
}
