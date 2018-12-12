package io.github.jhipster.energy.service.mapper;

import io.github.jhipster.energy.domain.*;
import io.github.jhipster.energy.service.dto.EnergyCycleDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EnergyCycleData and its DTO EnergyCycleDataDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnergyCycleDataMapper extends EntityMapper<EnergyCycleDataDTO, EnergyCycleData> {



    default EnergyCycleData fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnergyCycleData energyCycleData = new EnergyCycleData();
        energyCycleData.setId(id);
        return energyCycleData;
    }
}
