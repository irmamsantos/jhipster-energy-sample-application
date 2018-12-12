package io.github.jhipster.energy.service.mapper;

import io.github.jhipster.energy.domain.*;
import io.github.jhipster.energy.service.dto.EnergyContractedPowerRatingDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EnergyContractedPowerRatingData and its DTO EnergyContractedPowerRatingDataDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnergyContractedPowerRatingDataMapper extends EntityMapper<EnergyContractedPowerRatingDataDTO, EnergyContractedPowerRatingData> {



    default EnergyContractedPowerRatingData fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnergyContractedPowerRatingData energyContractedPowerRatingData = new EnergyContractedPowerRatingData();
        energyContractedPowerRatingData.setId(id);
        return energyContractedPowerRatingData;
    }
}
