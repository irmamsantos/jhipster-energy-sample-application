package io.github.jhipster.energy.service.impl;

import io.github.jhipster.energy.service.EnergyNaturalGasDataService;
import io.github.jhipster.energy.domain.EnergyNaturalGasData;
import io.github.jhipster.energy.repository.EnergyNaturalGasDataRepository;
import io.github.jhipster.energy.service.dto.EnergyNaturalGasDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyNaturalGasDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EnergyNaturalGasData.
 */
@Service
@Transactional
public class EnergyNaturalGasDataServiceImpl implements EnergyNaturalGasDataService {

    private final Logger log = LoggerFactory.getLogger(EnergyNaturalGasDataServiceImpl.class);

    private final EnergyNaturalGasDataRepository energyNaturalGasDataRepository;

    private final EnergyNaturalGasDataMapper energyNaturalGasDataMapper;

    public EnergyNaturalGasDataServiceImpl(EnergyNaturalGasDataRepository energyNaturalGasDataRepository, EnergyNaturalGasDataMapper energyNaturalGasDataMapper) {
        this.energyNaturalGasDataRepository = energyNaturalGasDataRepository;
        this.energyNaturalGasDataMapper = energyNaturalGasDataMapper;
    }

    /**
     * Save a energyNaturalGasData.
     *
     * @param energyNaturalGasDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnergyNaturalGasDataDTO save(EnergyNaturalGasDataDTO energyNaturalGasDataDTO) {
        log.debug("Request to save EnergyNaturalGasData : {}", energyNaturalGasDataDTO);

        EnergyNaturalGasData energyNaturalGasData = energyNaturalGasDataMapper.toEntity(energyNaturalGasDataDTO);
        energyNaturalGasData = energyNaturalGasDataRepository.save(energyNaturalGasData);
        return energyNaturalGasDataMapper.toDto(energyNaturalGasData);
    }

    /**
     * Get all the energyNaturalGasData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnergyNaturalGasDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnergyNaturalGasData");
        return energyNaturalGasDataRepository.findAll(pageable)
            .map(energyNaturalGasDataMapper::toDto);
    }


    /**
     * Get one energyNaturalGasData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnergyNaturalGasDataDTO> findOne(Long id) {
        log.debug("Request to get EnergyNaturalGasData : {}", id);
        return energyNaturalGasDataRepository.findById(id)
            .map(energyNaturalGasDataMapper::toDto);
    }

    /**
     * Delete the energyNaturalGasData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnergyNaturalGasData : {}", id);
        energyNaturalGasDataRepository.deleteById(id);
    }
}
