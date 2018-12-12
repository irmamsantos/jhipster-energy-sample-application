package io.github.jhipster.energy.service.mapper;

import io.github.jhipster.energy.domain.*;
import io.github.jhipster.energy.service.dto.EnergyVehicleBrandDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EnergyVehicleBrandData and its DTO EnergyVehicleBrandDataDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnergyVehicleBrandDataMapper extends EntityMapper<EnergyVehicleBrandDataDTO, EnergyVehicleBrandData> {


    @Mapping(target = "models", ignore = true)
    EnergyVehicleBrandData toEntity(EnergyVehicleBrandDataDTO energyVehicleBrandDataDTO);

    default EnergyVehicleBrandData fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnergyVehicleBrandData energyVehicleBrandData = new EnergyVehicleBrandData();
        energyVehicleBrandData.setId(id);
        return energyVehicleBrandData;
    }
}
