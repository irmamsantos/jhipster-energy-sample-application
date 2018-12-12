package io.github.jhipster.energy.service.mapper;

import io.github.jhipster.energy.domain.*;
import io.github.jhipster.energy.service.dto.EnergyVehicleModelDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EnergyVehicleModelData and its DTO EnergyVehicleModelDataDTO.
 */
@Mapper(componentModel = "spring", uses = {EnergyVehicleBrandDataMapper.class})
public interface EnergyVehicleModelDataMapper extends EntityMapper<EnergyVehicleModelDataDTO, EnergyVehicleModelData> {

    @Mapping(source = "energyVehicleBrandData.id", target = "energyVehicleBrandDataId")
    @Mapping(source = "brand.id", target = "brandId")
    EnergyVehicleModelDataDTO toDto(EnergyVehicleModelData energyVehicleModelData);

    @Mapping(source = "energyVehicleBrandDataId", target = "energyVehicleBrandData")
    @Mapping(source = "brandId", target = "brand")
    EnergyVehicleModelData toEntity(EnergyVehicleModelDataDTO energyVehicleModelDataDTO);

    default EnergyVehicleModelData fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnergyVehicleModelData energyVehicleModelData = new EnergyVehicleModelData();
        energyVehicleModelData.setId(id);
        return energyVehicleModelData;
    }
}
