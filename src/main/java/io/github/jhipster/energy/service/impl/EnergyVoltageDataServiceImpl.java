package io.github.jhipster.energy.service.impl;

import io.github.jhipster.energy.service.EnergyVoltageDataService;
import io.github.jhipster.energy.domain.EnergyVoltageData;
import io.github.jhipster.energy.repository.EnergyVoltageDataRepository;
import io.github.jhipster.energy.service.dto.EnergyVoltageDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyVoltageDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EnergyVoltageData.
 */
@Service
@Transactional
public class EnergyVoltageDataServiceImpl implements EnergyVoltageDataService {

    private final Logger log = LoggerFactory.getLogger(EnergyVoltageDataServiceImpl.class);

    private final EnergyVoltageDataRepository energyVoltageDataRepository;

    private final EnergyVoltageDataMapper energyVoltageDataMapper;

    public EnergyVoltageDataServiceImpl(EnergyVoltageDataRepository energyVoltageDataRepository, EnergyVoltageDataMapper energyVoltageDataMapper) {
        this.energyVoltageDataRepository = energyVoltageDataRepository;
        this.energyVoltageDataMapper = energyVoltageDataMapper;
    }

    /**
     * Save a energyVoltageData.
     *
     * @param energyVoltageDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnergyVoltageDataDTO save(EnergyVoltageDataDTO energyVoltageDataDTO) {
        log.debug("Request to save EnergyVoltageData : {}", energyVoltageDataDTO);

        EnergyVoltageData energyVoltageData = energyVoltageDataMapper.toEntity(energyVoltageDataDTO);
        energyVoltageData = energyVoltageDataRepository.save(energyVoltageData);
        return energyVoltageDataMapper.toDto(energyVoltageData);
    }

    /**
     * Get all the energyVoltageData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnergyVoltageDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnergyVoltageData");
        return energyVoltageDataRepository.findAll(pageable)
            .map(energyVoltageDataMapper::toDto);
    }


    /**
     * Get one energyVoltageData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnergyVoltageDataDTO> findOne(Long id) {
        log.debug("Request to get EnergyVoltageData : {}", id);
        return energyVoltageDataRepository.findById(id)
            .map(energyVoltageDataMapper::toDto);
    }

    /**
     * Delete the energyVoltageData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnergyVoltageData : {}", id);
        energyVoltageDataRepository.deleteById(id);
    }
}
