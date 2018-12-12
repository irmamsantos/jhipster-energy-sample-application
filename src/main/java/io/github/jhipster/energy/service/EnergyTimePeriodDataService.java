package io.github.jhipster.energy.service;

import io.github.jhipster.energy.service.dto.EnergyTimePeriodDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EnergyTimePeriodData.
 */
public interface EnergyTimePeriodDataService {

    /**
     * Save a energyTimePeriodData.
     *
     * @param energyTimePeriodDataDTO the entity to save
     * @return the persisted entity
     */
    EnergyTimePeriodDataDTO save(EnergyTimePeriodDataDTO energyTimePeriodDataDTO);

    /**
     * Get all the energyTimePeriodData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EnergyTimePeriodDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" energyTimePeriodData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnergyTimePeriodDataDTO> findOne(Long id);

    /**
     * Delete the "id" energyTimePeriodData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
