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
    @JsonIgnoreProperties("")
    private EnergyVoltageData voltage;

    @ManyToOne
    @JsonIgnoreProperties("")
    private EnergyTariffData tariff;

    @ManyToOne
    @JsonIgnoreProperties("")
    private EnergyCycleData cycle;

    @ManyToOne
    @JsonIgnoreProperties("")
    private EnergyTimePeriodData timePeriod;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnergyVoltageData getVoltage() {
        return voltage;
    }

    public EnergyElectricityData voltage(EnergyVoltageData energyVoltageData) {
        this.voltage = energyVoltageData;
        return this;
    }

    public void setVoltage(EnergyVoltageData energyVoltageData) {
        this.voltage = energyVoltageData;
    }

    public EnergyTariffData getTariff() {
        return tariff;
    }

    public EnergyElectricityData tariff(EnergyTariffData energyTariffData) {
        this.tariff = energyTariffData;
        return this;
    }

    public void setTariff(EnergyTariffData energyTariffData) {
        this.tariff = energyTariffData;
    }

    public EnergyCycleData getCycle() {
        return cycle;
    }

    public EnergyElectricityData cycle(EnergyCycleData energyCycleData) {
        this.cycle = energyCycleData;
        return this;
    }

    public void setCycle(EnergyCycleData energyCycleData) {
        this.cycle = energyCycleData;
    }

    public EnergyTimePeriodData getTimePeriod() {
        return timePeriod;
    }

    public EnergyElectricityData timePeriod(EnergyTimePeriodData energyTimePeriodData) {
        this.timePeriod = energyTimePeriodData;
        return this;
    }

    public void setTimePeriod(EnergyTimePeriodData energyTimePeriodData) {
        this.timePeriod = energyTimePeriodData;
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
