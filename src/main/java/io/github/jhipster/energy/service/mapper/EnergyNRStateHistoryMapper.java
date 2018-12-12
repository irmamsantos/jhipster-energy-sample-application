package io.github.jhipster.energy.service.mapper;

import io.github.jhipster.energy.domain.*;
import io.github.jhipster.energy.service.dto.EnergyNRStateHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EnergyNRStateHistory and its DTO EnergyNRStateHistoryDTO.
 */
@Mapper(componentModel = "spring", uses = {EnergyNeedNGRequestMapper.class, UserSACMapper.class})
public interface EnergyNRStateHistoryMapper extends EntityMapper<EnergyNRStateHistoryDTO, EnergyNRStateHistory> {

    @Mapping(source = "needNGRequest.id", target = "needNGRequestId")
    @Mapping(source = "user.id", target = "userId")
    EnergyNRStateHistoryDTO toDto(EnergyNRStateHistory energyNRStateHistory);

    @Mapping(source = "needNGRequestId", target = "needNGRequest")
    @Mapping(source = "userId", target = "user")
    EnergyNRStateHistory toEntity(EnergyNRStateHistoryDTO energyNRStateHistoryDTO);

    default EnergyNRStateHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnergyNRStateHistory energyNRStateHistory = new EnergyNRStateHistory();
        energyNRStateHistory.setId(id);
        return energyNRStateHistory;
    }
}
