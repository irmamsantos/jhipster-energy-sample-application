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
 * A EnergyTimePeriodData.
 */
@Entity
@Table(name = "energy_time_period_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EnergyTimePeriodData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "energyTimePeriodData")
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

    public EnergyTimePeriodData description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<EnergyElectricityData> getEnergyElectricityData() {
        return energyElectricityData;
    }

    public EnergyTimePeriodData energyElectricityData(Set<EnergyElectricityData> energyElectricityData) {
        this.energyElectricityData = energyElectricityData;
        return this;
    }

    public EnergyTimePeriodData addEnergyElectricityData(EnergyElectricityData energyElectricityData) {
        this.energyElectricityData.add(energyElectricityData);
        energyElectricityData.setEnergyTimePeriodData(this);
        return this;
    }

    public EnergyTimePeriodData removeEnergyElectricityData(EnergyElectricityData energyElectricityData) {
        this.energyElectricityData.remove(energyElectricityData);
        energyElectricityData.setEnergyTimePeriodData(null);
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
        EnergyTimePeriodData energyTimePeriodData = (EnergyTimePeriodData) o;
        if (energyTimePeriodData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyTimePeriodData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyTimePeriodData{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
