package io.github.jhipster.energy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A EnergyVoltageData.
 */
@Entity
@Table(name = "energy_voltage_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EnergyVoltageData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "energyVoltageData")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EnergyElectricityData> energyElectricityData = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public EnergyVoltageData description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<EnergyElectricityData> getEnergyElectricityData() {
        return energyElectricityData;
    }

    public EnergyVoltageData energyElectricityData(Set<EnergyElectricityData> energyElectricityData) {
        this.energyElectricityData = energyElectricityData;
        return this;
    }

    public EnergyVoltageData addEnergyElectricityData(EnergyElectricityData energyElectricityData) {
        this.energyElectricityData.add(energyElectricityData);
        energyElectricityData.setEnergyVoltageData(this);
        return this;
    }

    public EnergyVoltageData removeEnergyElectricityData(EnergyElectricityData energyElectricityData) {
        this.energyElectricityData.remove(energyElectricityData);
        energyElectricityData.setEnergyVoltageData(null);
        return this;
    }

    public void setEnergyElectricityData(Set<EnergyElectricityData> energyElectricityData) {
        this.energyElectricityData = energyElectricityData;
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
        EnergyVoltageData energyVoltageData = (EnergyVoltageData) o;
        if (energyVoltageData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyVoltageData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyVoltageData{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
