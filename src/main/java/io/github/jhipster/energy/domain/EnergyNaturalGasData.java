package io.github.jhipster.energy.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EnergyNaturalGasData.
 */
@Entity
@Table(name = "energy_natural_gas_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EnergyNaturalGasData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "pressure", nullable = false)
    private String pressure;

    @NotNull
    @Column(name = "echelon", nullable = false)
    private String echelon;

    @NotNull
    @Column(name = "pressure_description", nullable = false)
    private String pressureDescription;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPressure() {
        return pressure;
    }

    public EnergyNaturalGasData pressure(String pressure) {
        this.pressure = pressure;
        return this;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getEchelon() {
        return echelon;
    }

    public EnergyNaturalGasData echelon(String echelon) {
        this.echelon = echelon;
        return this;
    }

    public void setEchelon(String echelon) {
        this.echelon = echelon;
    }

    public String getPressureDescription() {
        return pressureDescription;
    }

    public EnergyNaturalGasData pressureDescription(String pressureDescription) {
        this.pressureDescription = pressureDescription;
        return this;
    }

    public void setPressureDescription(String pressureDescription) {
        this.pressureDescription = pressureDescription;
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
        EnergyNaturalGasData energyNaturalGasData = (EnergyNaturalGasData) o;
        if (energyNaturalGasData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyNaturalGasData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyNaturalGasData{" +
            "id=" + getId() +
            ", pressure='" + getPressure() + "'" +
            ", echelon='" + getEchelon() + "'" +
            ", pressureDescription='" + getPressureDescription() + "'" +
            "}";
    }
}
