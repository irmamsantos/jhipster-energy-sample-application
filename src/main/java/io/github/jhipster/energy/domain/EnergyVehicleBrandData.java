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
 * A EnergyVehicleBrandData.
 */
@Entity
@Table(name = "energy_vehicle_brand_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EnergyVehicleBrandData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "energyVehicleBrandData")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EnergyVehicleModelData> models = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("energyVehicleBrandData")
    private EnergyVehicleModelData energyVehicleModelData;

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

    public EnergyVehicleBrandData description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<EnergyVehicleModelData> getModels() {
        return models;
    }

    public EnergyVehicleBrandData models(Set<EnergyVehicleModelData> energyVehicleModelData) {
        this.models = energyVehicleModelData;
        return this;
    }

    public EnergyVehicleBrandData addModel(EnergyVehicleModelData energyVehicleModelData) {
        this.models.add(energyVehicleModelData);
        energyVehicleModelData.setEnergyVehicleBrandData(this);
        return this;
    }

    public EnergyVehicleBrandData removeModel(EnergyVehicleModelData energyVehicleModelData) {
        this.models.remove(energyVehicleModelData);
        energyVehicleModelData.setEnergyVehicleBrandData(null);
        return this;
    }

    public void setModels(Set<EnergyVehicleModelData> energyVehicleModelData) {
        this.models = energyVehicleModelData;
    }

    public EnergyVehicleModelData getEnergyVehicleModelData() {
        return energyVehicleModelData;
    }

    public EnergyVehicleBrandData energyVehicleModelData(EnergyVehicleModelData energyVehicleModelData) {
        this.energyVehicleModelData = energyVehicleModelData;
        return this;
    }

    public void setEnergyVehicleModelData(EnergyVehicleModelData energyVehicleModelData) {
        this.energyVehicleModelData = energyVehicleModelData;
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
        EnergyVehicleBrandData energyVehicleBrandData = (EnergyVehicleBrandData) o;
        if (energyVehicleBrandData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyVehicleBrandData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyVehicleBrandData{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
