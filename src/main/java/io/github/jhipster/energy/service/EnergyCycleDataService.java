package io.github.jhipster.energy.service;

import io.github.jhipster.energy.service.dto.EnergyCycleDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EnergyCycleData.
 */
public interface EnergyCycleDataService {

    /**
     * Save a energyCycleData.
     *
     * @param energyCycleDataDTO the entity to save
     * @return the persisted entity
     */
    EnergyCycleDataDTO save(EnergyCycleDataDTO energyCycleDataDTO);

    /**
     * Get all the energyCycleData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EnergyCycleDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" energyCycleData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnergyCycleDataDTO> findOne(Long id);

    /**
     * Delete the "id" energyCycleData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
