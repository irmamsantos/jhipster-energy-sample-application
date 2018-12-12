package io.github.jhipster.energy.service.mapper;

import io.github.jhipster.energy.domain.*;
import io.github.jhipster.energy.service.dto.EnergyNaturalGasDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EnergyNaturalGasData and its DTO EnergyNaturalGasDataDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnergyNaturalGasDataMapper extends EntityMapper<EnergyNaturalGasDataDTO, EnergyNaturalGasData> {



    default EnergyNaturalGasData fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnergyNaturalGasData energyNaturalGasData = new EnergyNaturalGasData();
        energyNaturalGasData.setId(id);
        return energyNaturalGasData;
    }
}
