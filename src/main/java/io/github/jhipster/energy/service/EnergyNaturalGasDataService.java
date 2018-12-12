package io.github.jhipster.energy.service;

import io.github.jhipster.energy.service.dto.EnergyNaturalGasDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EnergyNaturalGasData.
 */
public interface EnergyNaturalGasDataService {

    /**
     * Save a energyNaturalGasData.
     *
     * @param energyNaturalGasDataDTO the entity to save
     * @return the persisted entity
     */
    EnergyNaturalGasDataDTO save(EnergyNaturalGasDataDTO energyNaturalGasDataDTO);

    /**
     * Get all the energyNaturalGasData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EnergyNaturalGasDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" energyNaturalGasData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnergyNaturalGasDataDTO> findOne(Long id);

    /**
     * Delete the "id" energyNaturalGasData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
