package io.github.jhipster.energy.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EnergyTariffData entity.
 */
public class EnergyTariffDataDTO implements Serializable {

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

        EnergyTariffDataDTO energyTariffDataDTO = (EnergyTariffDataDTO) o;
        if (energyTariffDataDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyTariffDataDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyTariffDataDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
