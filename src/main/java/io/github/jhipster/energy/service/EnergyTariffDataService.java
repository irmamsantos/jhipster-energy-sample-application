package io.github.jhipster.energy.service;

import io.github.jhipster.energy.service.dto.EnergyTariffDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EnergyTariffData.
 */
public interface EnergyTariffDataService {

    /**
     * Save a energyTariffData.
     *
     * @param energyTariffDataDTO the entity to save
     * @return the persisted entity
     */
    EnergyTariffDataDTO save(EnergyTariffDataDTO energyTariffDataDTO);

    /**
     * Get all the energyTariffData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EnergyTariffDataDTO> findAll(Pageable pageable);


    /**
     * Get the "id" energyTariffData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EnergyTariffDataDTO> findOne(Long id);

    /**
     * Delete the "id" energyTariffData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
