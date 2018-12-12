package io.github.jhipster.energy.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EnergyElectricityData entity.
 */
public class EnergyElectricityDataDTO implements Serializable {

    private Long id;

    private Long voltageId;

    private Long tariffId;

    private Long cycleId;

    private Long timePeriodId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoltageId() {
        return voltageId;
    }

    public void setVoltageId(Long energyVoltageDataId) {
        this.voltageId = energyVoltageDataId;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long energyTariffDataId) {
        this.tariffId = energyTariffDataId;
    }

    public Long getCycleId() {
        return cycleId;
    }

    public void setCycleId(Long energyCycleDataId) {
        this.cycleId = energyCycleDataId;
    }

    public Long getTimePeriodId() {
        return timePeriodId;
    }

    public void setTimePeriodId(Long energyTimePeriodDataId) {
        this.timePeriodId = energyTimePeriodDataId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnergyElectricityDataDTO energyElectricityDataDTO = (EnergyElectricityDataDTO) o;
        if (energyElectricityDataDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyElectricityDataDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyElectricityDataDTO{" +
            "id=" + getId() +
            ", voltage=" + getVoltageId() +
            ", tariff=" + getTariffId() +
            ", cycle=" + getCycleId() +
            ", timePeriod=" + getTimePeriodId() +
            "}";
    }
}
