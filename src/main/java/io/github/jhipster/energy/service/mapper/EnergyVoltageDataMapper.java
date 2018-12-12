package io.github.jhipster.energy.service.mapper;

import io.github.jhipster.energy.domain.*;
import io.github.jhipster.energy.service.dto.EnergyVoltageDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EnergyVoltageData and its DTO EnergyVoltageDataDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnergyVoltageDataMapper extends EntityMapper<EnergyVoltageDataDTO, EnergyVoltageData> {



    default EnergyVoltageData fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnergyVoltageData energyVoltageData = new EnergyVoltageData();
        energyVoltageData.setId(id);
        return energyVoltageData;
    }
}
