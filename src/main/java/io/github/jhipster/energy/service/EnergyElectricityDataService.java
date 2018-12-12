package io.github.jhipster.energy.service;

import io.github.jhipster.energy.service.dto.EnergyElectricityDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EnergyElectricityData.
 */
public interface EnergyElectricityDataService {

    /**
     * Save a energyElectricityData.
     *
     * @param energyElectricityDataDTO the entity to save
     * @return the persisted entity
     */
    EnergyElectricityDataDTO save(EnergyElectricityDataDTO energyElectricityDataDTO);

    /**
     * Get all the energyElectricityData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EnergyElectricityDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" energyElectricityData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnergyElectricityDataDTO> findOne(Long id);

    /**
     * Delete the "id" energyElectricityData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
