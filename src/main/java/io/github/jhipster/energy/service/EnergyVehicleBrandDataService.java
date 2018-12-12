package io.github.jhipster.energy.service;

import io.github.jhipster.energy.service.dto.EnergyVehicleBrandDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EnergyVehicleBrandData.
 */
public interface EnergyVehicleBrandDataService {

    /**
     * Save a energyVehicleBrandData.
     *
     * @param energyVehicleBrandDataDTO the entity to save
     * @return the persisted entity
     */
    EnergyVehicleBrandDataDTO save(EnergyVehicleBrandDataDTO energyVehicleBrandDataDTO);

    /**
     * Get all the energyVehicleBrandData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EnergyVehicleBrandDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" energyVehicleBrandData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnergyVehicleBrandDataDTO> findOne(Long id);

    /**
     * Delete the "id" energyVehicleBrandData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
