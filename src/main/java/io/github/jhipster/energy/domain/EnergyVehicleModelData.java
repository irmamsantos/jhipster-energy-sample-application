package io.github.jhipster.energy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A EnergyVehicleModelData.
 */
@Entity
@Table(name = "energy_vehicle_model_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EnergyVehicleModelData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("models")
    private EnergyVehicleBrandData energyVehicleBrandData;

    @OneToMany(mappedBy = "energyVehicleModelData")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EnergyVehicleBrandData> energyVehicleBrandData = new HashSet<>();
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

    public EnergyVehicleModelData description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnergyVehicleBrandData getEnergyVehicleBrandData() {
        return energyVehicleBrandData;
    }

    public EnergyVehicleModelData energyVehicleBrandData(EnergyVehicleBrandData energyVehicleBrandData) {
        this.energyVehicleBrandData = energyVehicleBrandData;
        return this;
    }

    public void setEnergyVehicleBrandData(EnergyVehicleBrandData energyVehicleBrandData) {
        this.energyVehicleBrandData = energyVehicleBrandData;
    }

    public Set<EnergyVehicleBrandData> getEnergyVehicleBrandData() {
        return energyVehicleBrandData;
    }

    public EnergyVehicleModelData energyVehicleBrandData(Set<EnergyVehicleBrandData> energyVehicleBrandData) {
        this.energyVehicleBrandData = energyVehicleBrandData;
        return this;
    }

    public EnergyVehicleModelData addEnergyVehicleBrandData(EnergyVehicleBrandData energyVehicleBrandData) {
        this.energyVehicleBrandData.add(energyVehicleBrandData);
        energyVehicleBrandData.setEnergyVehicleModelData(this);
        return this;
    }

    public EnergyVehicleModelData removeEnergyVehicleBrandData(EnergyVehicleBrandData energyVehicleBrandData) {
        this.energyVehicleBrandData.remove(energyVehicleBrandData);
        energyVehicleBrandData.setEnergyVehicleModelData(null);
        return this;
    }

    public void setEnergyVehicleBrandData(Set<EnergyVehicleBrandData> energyVehicleBrandData) {
        this.energyVehicleBrandData = energyVehicleBrandData;
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
        EnergyVehicleModelData energyVehicleModelData = (EnergyVehicleModelData) o;
        if (energyVehicleModelData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyVehicleModelData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyVehicleModelData{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}