package io.github.jhipster.energy.service.mapper;

import io.github.jhipster.energy.domain.*;
import io.github.jhipster.energy.service.dto.EnergyElectricityDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EnergyElectricityData and its DTO EnergyElectricityDataDTO.
 */
@Mapper(componentModel = "spring", uses = {EnergyVoltageDataMapper.class, EnergyTariffDataMapper.class, EnergyCycleDataMapper.class, EnergyTimePeriodDataMapper.class})
public interface EnergyElectricityDataMapper extends EntityMapper<EnergyElectricityDataDTO, EnergyElectricityData> {

    @Mapping(source = "voltage.id", target = "voltageId")
    @Mapping(source = "tariff.id", target = "tariffId")
    @Mapping(source = "cycle.id", target = "cycleId")
    @Mapping(source = "timePeriod.id", target = "timePeriodId")
    EnergyElectricityDataDTO toDto(EnergyElectricityData energyElectricityData);

    @Mapping(source = "voltageId", target = "voltage")
    @Mapping(source = "tariffId", target = "tariff")
    @Mapping(source = "cycleId", target = "cycle")
    @Mapping(source = "timePeriodId", target = "timePeriod")
    EnergyElectricityData toEntity(EnergyElectricityDataDTO energyElectricityDataDTO);

    default EnergyElectricityData fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnergyElectricityData energyElectricityData = new EnergyElectricityData();
        energyElectricityData.setId(id);
        return energyElectricityData;
    }
}
