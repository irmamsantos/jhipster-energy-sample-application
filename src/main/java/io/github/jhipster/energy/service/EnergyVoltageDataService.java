package io.github.jhipster.energy.service;

import io.github.jhipster.energy.service.dto.EnergyVoltageDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EnergyVoltageData.
 */
public interface EnergyVoltageDataService {

    /**
     * Save a energyVoltageData.
     *
     * @param energyVoltageDataDTO the entity to save
     * @return the persisted entity
     */
    EnergyVoltageDataDTO save(EnergyVoltageDataDTO energyVoltageDataDTO);

    /**
     * Get all the energyVoltageData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EnergyVoltageDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" energyVoltageData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnergyVoltageDataDTO> findOne(Long id);

    /**
     * Delete the "id" energyVoltageData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
