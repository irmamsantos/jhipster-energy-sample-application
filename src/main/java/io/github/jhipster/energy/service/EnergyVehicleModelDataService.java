package io.github.jhipster.energy.service;

import io.github.jhipster.energy.service.dto.EnergyVehicleModelDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EnergyVehicleModelData.
 */
public interface EnergyVehicleModelDataService {

    /**
     * Save a energyVehicleModelData.
     *
     * @param energyVehicleModelDataDTO the entity to save
     * @return the persisted entity
     */
    EnergyVehicleModelDataDTO save(EnergyVehicleModelDataDTO energyVehicleModelDataDTO);

    /**
     * Get all the energyVehicleModelData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EnergyVehicleModelDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" energyVehicleModelData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnergyVehicleModelDataDTO> findOne(Long id);

    /**
     * Delete the "id" energyVehicleModelData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
