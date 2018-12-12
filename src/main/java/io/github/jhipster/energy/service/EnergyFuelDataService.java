package io.github.jhipster.energy.service;

import io.github.jhipster.energy.service.dto.EnergyFuelDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EnergyFuelData.
 */
public interface EnergyFuelDataService {

    /**
     * Save a energyFuelData.
     *
     * @param energyFuelDataDTO the entity to save
     * @return the persisted entity
     */
    EnergyFuelDataDTO save(EnergyFuelDataDTO energyFuelDataDTO);

    /**
     * Get all the energyFuelData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EnergyFuelDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" energyFuelData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnergyFuelDataDTO> findOne(Long id);

    /**
     * Delete the "id" energyFuelData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
