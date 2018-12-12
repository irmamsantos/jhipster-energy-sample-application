package io.github.jhipster.energy.service.mapper;

import io.github.jhipster.energy.domain.*;
import io.github.jhipster.energy.service.dto.EnergyFuelDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EnergyFuelData and its DTO EnergyFuelDataDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnergyFuelDataMapper extends EntityMapper<EnergyFuelDataDTO, EnergyFuelData> {



    default EnergyFuelData fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnergyFuelData energyFuelData = new EnergyFuelData();
        energyFuelData.setId(id);
        return energyFuelData;
    }
}
