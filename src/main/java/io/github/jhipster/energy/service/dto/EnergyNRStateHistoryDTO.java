package io.github.jhipster.energy.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EnergyNRStateHistory entity.
 */
public class EnergyNRStateHistoryDTO implements Serializable {

    private Long id;

    @NotNull
    private String energyType;

    @NotNull
    private String justification;

    @NotNull
    private Instant updateDate;

    private String stateValue;

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

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getStateValue() {
        return stateValue;
    }

    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
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

        EnergyNRStateHistoryDTO energyNRStateHistoryDTO = (EnergyNRStateHistoryDTO) o;
        if (energyNRStateHistoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyNRStateHistoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyNRStateHistoryDTO{" +
            "id=" + getId() +
            ", energyType='" + getEnergyType() + "'" +
            ", justification='" + getJustification() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", stateValue='" + getStateValue() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
