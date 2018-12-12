package io.github.jhipster.energy.service.mapper;

import io.github.jhipster.energy.domain.*;
import io.github.jhipster.energy.service.dto.EnergyTariffDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EnergyTariffData and its DTO EnergyTariffDataDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnergyTariffDataMapper extends EntityMapper<EnergyTariffDataDTO, EnergyTariffData> {



    default EnergyTariffData fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnergyTariffData energyTariffData = new EnergyTariffData();
        energyTariffData.setId(id);
        return energyTariffData;
    }
}
