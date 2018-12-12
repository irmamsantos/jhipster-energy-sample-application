package io.github.jhipster.energy.service.impl;

import io.github.jhipster.energy.service.EnergyFuelDataService;
import io.github.jhipster.energy.domain.EnergyFuelData;
import io.github.jhipster.energy.repository.EnergyFuelDataRepository;
import io.github.jhipster.energy.service.dto.EnergyFuelDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyFuelDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EnergyFuelData.
 */
@Service
@Transactional
public class EnergyFuelDataServiceImpl implements EnergyFuelDataService {

    private final Logger log = LoggerFactory.getLogger(EnergyFuelDataServiceImpl.class);

    private final EnergyFuelDataRepository energyFuelDataRepository;

    private final EnergyFuelDataMapper energyFuelDataMapper;

    public EnergyFuelDataServiceImpl(EnergyFuelDataRepository energyFuelDataRepository, EnergyFuelDataMapper energyFuelDataMapper) {
        this.energyFuelDataRepository = energyFuelDataRepository;
        this.energyFuelDataMapper = energyFuelDataMapper;
    }

    /**
     * Save a energyFuelData.
     *
     * @param energyFuelDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnergyFuelDataDTO save(EnergyFuelDataDTO energyFuelDataDTO) {
        log.debug("Request to save EnergyFuelData : {}", energyFuelDataDTO);

        EnergyFuelData energyFuelData = energyFuelDataMapper.toEntity(energyFuelDataDTO);
        energyFuelData = energyFuelDataRepository.save(energyFuelData);
        return energyFuelDataMapper.toDto(energyFuelData);
    }

    /**
     * Get all the energyFuelData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnergyFuelDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnergyFuelData");
        return energyFuelDataRepository.findAll(pageable)
            .map(energyFuelDataMapper::toDto);
    }


    /**
     * Get one energyFuelData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnergyFuelDataDTO> findOne(Long id) {
        log.debug("Request to get EnergyFuelData : {}", id);
        return energyFuelDataRepository.findById(id)
            .map(energyFuelDataMapper::toDto);
    }

    /**
     * Delete the energyFuelData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnergyFuelData : {}", id);
        energyFuelDataRepository.deleteById(id);
    }
}
