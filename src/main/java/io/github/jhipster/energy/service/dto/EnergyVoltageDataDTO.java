package io.github.jhipster.energy.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EnergyVoltageData entity.
 */
public class EnergyVoltageDataDTO implements Serializable {

    private Long id;

    @NotNull
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnergyVoltageDataDTO energyVoltageDataDTO = (EnergyVoltageDataDTO) o;
        if (energyVoltageDataDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyVoltageDataDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyVoltageDataDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
