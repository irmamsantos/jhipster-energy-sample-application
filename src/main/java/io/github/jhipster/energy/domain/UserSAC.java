package io.github.jhipster.energy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A UserSAC.
 */
@Entity
@Table(name = "user_sac")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserSAC implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_sac_id", nullable = false)
    private Integer userSACId;

    @NotNull
    @Column(name = "user_name", nullable = false)
    private String userName;

    @NotNull
    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @NotNull
    @Column(name = "update_date", nullable = false)
    private Instant updateDate;

    @OneToMany(mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EnergyNeedNGRequest> needNGRequests = new HashSet<>();
    @OneToMany(mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EnergyNRStateHistory> stateHistories = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserSACId() {
        return userSACId;
    }

    public UserSAC userSACId(Integer userSACId) {
        this.userSACId = userSACId;
        return this;
    }

    public void setUserSACId(Integer userSACId) {
        this.userSACId = userSACId;
    }

    public String getUserName() {
        return userName;
    }

    public UserSAC userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserSAC userEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public UserSAC updateDate(Instant updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public Set<EnergyNeedNGRequest> getNeedNGRequests() {
        return needNGRequests;
    }

    public UserSAC needNGRequests(Set<EnergyNeedNGRequest> energyNeedNGRequests) {
        this.needNGRequests = energyNeedNGRequests;
        return this;
    }

    public UserSAC addNeedNGRequest(EnergyNeedNGRequest energyNeedNGRequest) {
        this.needNGRequests.add(energyNeedNGRequest);
        energyNeedNGRequest.setUser(this);
        return this;
    }

    public UserSAC removeNeedNGRequest(EnergyNeedNGRequest energyNeedNGRequest) {
        this.needNGRequests.remove(energyNeedNGRequest);
        energyNeedNGRequest.setUser(null);
        return this;
    }

    public void setNeedNGRequests(Set<EnergyNeedNGRequest> energyNeedNGRequests) {
        this.needNGRequests = energyNeedNGRequests;
    }

    public Set<EnergyNRStateHistory> getStateHistories() {
        return stateHistories;
    }

    public UserSAC stateHistories(Set<EnergyNRStateHistory> energyNRStateHistories) {
        this.stateHistories = energyNRStateHistories;
        return this;
    }

    public UserSAC addStateHistory(EnergyNRStateHistory energyNRStateHistory) {
        this.stateHistories.add(energyNRStateHistory);
        energyNRStateHistory.setUser(this);
        return this;
    }

    public UserSAC removeStateHistory(EnergyNRStateHistory energyNRStateHistory) {
        this.stateHistories.remove(energyNRStateHistory);
        energyNRStateHistory.setUser(null);
        return this;
    }

    public void setStateHistories(Set<EnergyNRStateHistory> energyNRStateHistories) {
        this.stateHistories = energyNRStateHistories;
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
        UserSAC userSAC = (UserSAC) o;
        if (userSAC.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userSAC.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserSAC{" +
            "id=" + getId() +
            ", userSACId=" + getUserSACId() +
            ", userName='" + getUserName() + "'" +
            ", userEmail='" + getUserEmail() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            "}";
    }
}
