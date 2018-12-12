package io.github.jhipster.energy.service.impl;

import io.github.jhipster.energy.service.EnergyElectricityDataService;
import io.github.jhipster.energy.domain.EnergyElectricityData;
import io.github.jhipster.energy.repository.EnergyElectricityDataRepository;
import io.github.jhipster.energy.service.dto.EnergyElectricityDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyElectricityDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EnergyElectricityData.
 */
@Service
@Transactional
public class EnergyElectricityDataServiceImpl implements EnergyElectricityDataService {

    private final Logger log = LoggerFactory.getLogger(EnergyElectricityDataServiceImpl.class);

    private final EnergyElectricityDataRepository energyElectricityDataRepository;

    private final EnergyElectricityDataMapper energyElectricityDataMapper;

    public EnergyElectricityDataServiceImpl(EnergyElectricityDataRepository energyElectricityDataRepository, EnergyElectricityDataMapper energyElectricityDataMapper) {
        this.energyElectricityDataRepository = energyElectricityDataRepository;
        this.energyElectricityDataMapper = energyElectricityDataMapper;
    }

    /**
     * Save a energyElectricityData.
     *
     * @param energyElectricityDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnergyElectricityDataDTO save(EnergyElectricityDataDTO energyElectricityDataDTO) {
        log.debug("Request to save EnergyElectricityData : {}", energyElectricityDataDTO);

        EnergyElectricityData energyElectricityData = energyElectricityDataMapper.toEntity(energyElectricityDataDTO);
        energyElectricityData = energyElectricityDataRepository.save(energyElectricityData);
        return energyElectricityDataMapper.toDto(energyElectricityData);
    }

    /**
     * Get all the energyElectricityData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnergyElectricityDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnergyElectricityData");
        return energyElectricityDataRepository.findAll(pageable)
            .map(energyElectricityDataMapper::toDto);
    }


    /**
     * Get one energyElectricityData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnergyElectricityDataDTO> findOne(Long id) {
        log.debug("Request to get EnergyElectricityData : {}", id);
        return energyElectricityDataRepository.findById(id)
            .map(energyElectricityDataMapper::toDto);
    }

    /**
     * Delete the energyElectricityData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnergyElectricityData : {}", id);
        energyElectricityDataRepository.deleteById(id);
    }
}
