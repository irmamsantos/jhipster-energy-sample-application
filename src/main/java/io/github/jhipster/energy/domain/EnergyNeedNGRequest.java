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
 * A EnergyNeedNGRequest.
 */
@Entity
@Table(name = "energy_need_ng_request")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EnergyNeedNGRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "energy_type", nullable = false)
    private String energyType;

    @NotNull
    @Column(name = "update_date", nullable = false)
    private Instant updateDate;

    @ManyToOne
    @JsonIgnoreProperties("needNGRequests")
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

    public EnergyNeedNGRequest energyType(String energyType) {
        this.energyType = energyType;
        return this;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public EnergyNeedNGRequest updateDate(Instant updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public UserSAC getUser() {
        return user;
    }

    public EnergyNeedNGRequest user(UserSAC userSAC) {
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
        EnergyNeedNGRequest energyNeedNGRequest = (EnergyNeedNGRequest) o;
        if (energyNeedNGRequest.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), energyNeedNGRequest.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EnergyNeedNGRequest{" +
            "id=" + getId() +
            ", energyType='" + getEnergyType() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            "}";
    }
}
