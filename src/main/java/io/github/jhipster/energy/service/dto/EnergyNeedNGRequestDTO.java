package io.github.jhipster.energy.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EnergyNeedNGRequest entity.
 */
public class EnergyNeedNGRequestDTO implements Serializable {

    private Long id;

    @NotNull
    private String energyType;

    @NotNull
    private Instant updateDate;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userSACId) {
        this.userId = userSACId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnergyNeedNGRequestDTO energyNeedNGRequestDTO = (EnergyNeedNGRequestDTO) o;
        if (energyNeedNGRequestDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyNeedNGRequestDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyNeedNGRequestDTO{" +
            "id=" + getId() +
            ", energyType='" + getEnergyType() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
