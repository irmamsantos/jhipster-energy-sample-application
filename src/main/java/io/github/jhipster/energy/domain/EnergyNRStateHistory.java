package io.github.jhipster.energy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A EnergyNRStateHistory.
 */
@Entity
@Table(name = "energy_nr_state_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EnergyNRStateHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "energy_type", nullable = false)
    private String energyType;

    @NotNull
    @Column(name = "justification", nullable = false)
    private String justification;

    @NotNull
    @Column(name = "update_date", nullable = false)
    private Instant updateDate;

    @Column(name = "state_value")
    private String stateValue;

    @ManyToOne
    @JsonIgnoreProperties("")
    private EnergyNeedNGRequest needNGRequest;

    @ManyToOne
    @JsonIgnoreProperties("stateHistories")
    private UserSAC user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnergyType() {
        return energyType;
    }

    public EnergyNRStateHistory energyType(String energyType) {
        this.energyType = energyType;
        return this;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public String getJustification() {
        return justification;
    }

    public EnergyNRStateHistory justification(String justification) {
        this.justification = justification;
        return this;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public EnergyNRStateHistory updateDate(Instant updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getStateValue() {
        return stateValue;
    }

    public EnergyNRStateHistory stateValue(String stateValue) {
        this.stateValue = stateValue;
        return this;
    }

    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
    }

    public EnergyNeedNGRequest getNeedNGRequest() {
        return needNGRequest;
    }

    public EnergyNRStateHistory needNGRequest(EnergyNeedNGRequest energyNeedNGRequest) {
        this.needNGRequest = energyNeedNGRequest;
        return this;
    }

    public void setNeedNGRequest(EnergyNeedNGRequest energyNeedNGRequest) {
        this.needNGRequest = energyNeedNGRequest;
    }

    public UserSAC getUser() {
        return user;
    }

    public EnergyNRStateHistory user(UserSAC userSAC) {
        this.user = userSAC;
        return this;
    }

    public void setUser(UserSAC userSAC) {
        this.user = userSAC;
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
        EnergyNRStateHistory energyNRStateHistory = (EnergyNRStateHistory) o;
        if (energyNRStateHistory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyNRStateHistory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyNRStateHistory{" +
            "id=" + getId() +
            ", energyType='" + getEnergyType() + "'" +
            ", justification='" + getJustification() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", stateValue='" + getStateValue() + "'" +
            "}";
    }
}
