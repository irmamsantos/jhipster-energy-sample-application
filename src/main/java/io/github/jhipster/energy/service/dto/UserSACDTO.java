package io.github.jhipster.energy.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UserSAC entity.
 */
public class UserSACDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer userSACId;

    @NotNull
    private String userName;

    @NotNull
    private String userEmail;

    @NotNull
    private Instant updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserSACId() {
        return userSACId;
    }

    public void setUserSACId(Integer userSACId) {
        this.userSACId = userSACId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserSACDTO userSACDTO = (UserSACDTO) o;
        if (userSACDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userSACDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserSACDTO{" +
            "id=" + getId() +
            ", userSACId=" + getUserSACId() +
            ", userName='" + getUserName() + "'" +
            ", userEmail='" + getUserEmail() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            "}";
    }
}
