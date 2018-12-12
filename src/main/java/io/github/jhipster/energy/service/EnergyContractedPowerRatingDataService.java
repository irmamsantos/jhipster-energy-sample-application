package io.github.jhipster.energy.service;

import io.github.jhipster.energy.service.dto.EnergyContractedPowerRatingDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EnergyContractedPowerRatingData.
 */
public interface EnergyContractedPowerRatingDataService {

    /**
     * Save a energyContractedPowerRatingData.
     *
     * @param energyContractedPowerRatingDataDTO the entity to save
     * @return the persisted entity
     */
    EnergyContractedPowerRatingDataDTO save(EnergyContractedPowerRatingDataDTO energyContractedPowerRatingDataDTO);

    /**
     * Get all the energyContractedPowerRatingData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EnergyContractedPowerRatingDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" energyContractedPowerRatingData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnergyContractedPowerRatingDataDTO> findOne(Long id);

    /**
     * Delete the "id" energyContractedPowerRatingData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
