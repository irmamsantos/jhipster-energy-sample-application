package io.github.jhipster.energy.service;

import io.github.jhipster.energy.service.dto.EnergyNRStateHistoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EnergyNRStateHistory.
 */
public interface EnergyNRStateHistoryService {

    /**
     * Save a energyNRStateHistory.
     *
     * @param energyNRStateHistoryDTO the entity to save
     * @return the persisted entity
     */
    EnergyNRStateHistoryDTO save(EnergyNRStateHistoryDTO energyNRStateHistoryDTO);

    /**
     * Get all the energyNRStateHistories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EnergyNRStateHistoryDTO> findAll(Pageable pageable);


    /**
     * Get the "id" energyNRStateHistory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnergyNRStateHistoryDTO> findOne(Long id);

    /**
     * Delete the "id" energyNRStateHistory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
