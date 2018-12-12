package io.github.jhipster.energy.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EnergyNaturalGasData entity.
 */
public class EnergyNaturalGasDataDTO implements Serializable {

    private Long id;

    @NotNull
    private String pressure;

    @NotNull
    private String echelon;

    @NotNull
    private String pressureDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getEchelon() {
        return echelon;
    }

    public void setEchelon(String echelon) {
        this.echelon = echelon;
    }

    public String getPressureDescription() {
        return pressureDescription;
    }

    public void setPressureDescription(String pressureDescription) {
        this.pressureDescription = pressureDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnergyNaturalGasDataDTO energyNaturalGasDataDTO = (EnergyNaturalGasDataDTO) o;
        if (energyNaturalGasDataDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyNaturalGasDataDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyNaturalGasDataDTO{" +
            "id=" + getId() +
            ", pressure='" + getPressure() + "'" +
            ", echelon='" + getEchelon() + "'" +
            ", pressureDescription='" + getPressureDescription() + "'" +
            "}";
    }
}
