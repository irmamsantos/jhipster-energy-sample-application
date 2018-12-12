package io.github.jhipster.energy.service.mapper;

import io.github.jhipster.energy.domain.*;
import io.github.jhipster.energy.service.dto.EnergyNeedNGRequestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EnergyNeedNGRequest and its DTO EnergyNeedNGRequestDTO.
 */
@Mapper(componentModel = "spring", uses = {UserSACMapper.class})
public interface EnergyNeedNGRequestMapper extends EntityMapper<EnergyNeedNGRequestDTO, EnergyNeedNGRequest> {

    @Mapping(source = "user.id", target = "userId")
    EnergyNeedNGRequestDTO toDto(EnergyNeedNGRequest energyNeedNGRequest);

    @Mapping(source = "userId", target = "user")
    EnergyNeedNGRequest toEntity(EnergyNeedNGRequestDTO energyNeedNGRequestDTO);

    default EnergyNeedNGRequest fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnergyNeedNGRequest energyNeedNGRequest = new EnergyNeedNGRequest();
        energyNeedNGRequest.setId(id);
        return energyNeedNGRequest;
    }
}
