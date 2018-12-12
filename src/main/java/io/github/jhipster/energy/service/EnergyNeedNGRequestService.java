package io.github.jhipster.energy.service;

import io.github.jhipster.energy.service.dto.EnergyNeedNGRequestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EnergyNeedNGRequest.
 */
public interface EnergyNeedNGRequestService {

    /**
     * Save a energyNeedNGRequest.
     *
     * @param energyNeedNGRequestDTO the entity to save
     * @return the persisted entity
     */
    EnergyNeedNGRequestDTO save(EnergyNeedNGRequestDTO energyNeedNGRequestDTO);

    /**
     * Get all the energyNeedNGRequests.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EnergyNeedNGRequestDTO> findAll(Pageable pageable);


    /**
     * Get the "id" energyNeedNGRequest.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnergyNeedNGRequestDTO> findOne(Long id);

    /**
     * Delete the "id" energyNeedNGRequest.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
