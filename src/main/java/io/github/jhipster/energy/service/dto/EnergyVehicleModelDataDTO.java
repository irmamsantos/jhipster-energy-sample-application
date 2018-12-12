package io.github.jhipster.energy.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EnergyVehicleModelData entity.
 */
public class EnergyVehicleModelDataDTO implements Serializable {

    private Long id;

    @NotNull
    private String description;

    private Long energyVehicleBrandDataId;

    private Long brandId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEnergyVehicleBrandDataId() {
        return energyVehicleBrandDataId;
    }

    public void setEnergyVehicleBrandDataId(Long energyVehicleBrandDataId) {
        this.energyVehicleBrandDataId = energyVehicleBrandDataId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long energyVehicleBrandDataId) {
        this.brandId = energyVehicleBrandDataId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnergyVehicleModelDataDTO energyVehicleModelDataDTO = (EnergyVehicleModelDataDTO) o;
        if (energyVehicleModelDataDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyVehicleModelDataDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyVehicleModelDataDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", energyVehicleBrandData=" + getEnergyVehicleBrandDataId() +
            ", brand=" + getBrandId() +
            "}";
    }
}
