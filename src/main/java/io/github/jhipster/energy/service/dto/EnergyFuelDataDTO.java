package io.github.jhipster.energy.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EnergyFuelData entity.
 */
public class EnergyFuelDataDTO implements Serializable {

    private Long id;

    @NotNull
    private String type;

    @NotNull
    private String product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnergyFuelDataDTO energyFuelDataDTO = (EnergyFuelDataDTO) o;
        if (energyFuelDataDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyFuelDataDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyFuelDataDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", product='" + getProduct() + "'" +
            "}";
    }
}
