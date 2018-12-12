package io.github.jhipster.energy.service.mapper;

import io.github.jhipster.energy.domain.*;
import io.github.jhipster.energy.service.dto.UserSACDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserSAC and its DTO UserSACDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserSACMapper extends EntityMapper<UserSACDTO, UserSAC> {


    @Mapping(target = "needNGRequests", ignore = true)
    @Mapping(target = "stateHistories", ignore = true)
    UserSAC toEntity(UserSACDTO userSACDTO);

    default UserSAC fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserSAC userSAC = new UserSAC();
        userSAC.setId(id);
        return userSAC;
    }
}
