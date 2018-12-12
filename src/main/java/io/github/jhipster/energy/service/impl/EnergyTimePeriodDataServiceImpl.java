package io.github.jhipster.energy.service.impl;

import io.github.jhipster.energy.service.EnergyTimePeriodDataService;
import io.github.jhipster.energy.domain.EnergyTimePeriodData;
import io.github.jhipster.energy.repository.EnergyTimePeriodDataRepository;
import io.github.jhipster.energy.service.dto.EnergyTimePeriodDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyTimePeriodDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EnergyTimePeriodData.
 */
@Service
@Transactional
public class EnergyTimePeriodDataServiceImpl implements EnergyTimePeriodDataService {

    private final Logger log = LoggerFactory.getLogger(EnergyTimePeriodDataServiceImpl.class);

    private final EnergyTimePeriodDataRepository energyTimePeriodDataRepository;

    private final EnergyTimePeriodDataMapper energyTimePeriodDataMapper;

    public EnergyTimePeriodDataServiceImpl(EnergyTimePeriodDataRepository energyTimePeriodDataRepository, EnergyTimePeriodDataMapper energyTimePeriodDataMapper) {
        this.energyTimePeriodDataRepository = energyTimePeriodDataRepository;
        this.energyTimePeriodDataMapper = energyTimePeriodDataMapper;
    }

    /**
     * Save a energyTimePeriodData.
     *
     * @param energyTimePeriodDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnergyTimePeriodDataDTO save(EnergyTimePeriodDataDTO energyTimePeriodDataDTO) {
        log.debug("Request to save EnergyTimePeriodData : {}", energyTimePeriodDataDTO);

        EnergyTimePeriodData energyTimePeriodData = energyTimePeriodDataMapper.toEntity(energyTimePeriodDataDTO);
        energyTimePeriodData = energyTimePeriodDataRepository.save(energyTimePeriodData);
        return energyTimePeriodDataMapper.toDto(energyTimePeriodData);
    }

    /**
     * Get all the energyTimePeriodData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnergyTimePeriodDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnergyTimePeriodData");
        return energyTimePeriodDataRepository.findAll(pageable)
            .map(energyTimePeriodDataMapper::toDto);
    }


    /**
     * Get one energyTimePeriodData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnergyTimePeriodDataDTO> findOne(Long id) {
        log.debug("Request to get EnergyTimePeriodData : {}", id);
        return energyTimePeriodDataRepository.findById(id)
            .map(energyTimePeriodDataMapper::toDto);
    }

    /**
     * Delete the energyTimePeriodData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnergyTimePeriodData : {}", id);
        energyTimePeriodDataRepository.deleteById(id);
    }
}
