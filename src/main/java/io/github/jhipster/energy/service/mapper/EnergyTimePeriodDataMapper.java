package io.github.jhipster.energy.service.mapper;

import io.github.jhipster.energy.domain.*;
import io.github.jhipster.energy.service.dto.EnergyTimePeriodDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EnergyTimePeriodData and its DTO EnergyTimePeriodDataDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnergyTimePeriodDataMapper extends EntityMapper<EnergyTimePeriodDataDTO, EnergyTimePeriodData> {



    default EnergyTimePeriodData fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnergyTimePeriodData energyTimePeriodData = new EnergyTimePeriodData();
        energyTimePeriodData.setId(id);
        return energyTimePeriodData;
    }
}
