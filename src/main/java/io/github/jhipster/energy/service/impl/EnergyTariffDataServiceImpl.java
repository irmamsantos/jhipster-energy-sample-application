package io.github.jhipster.energy.service.impl;

import io.github.jhipster.energy.service.EnergyTariffDataService;
import io.github.jhipster.energy.domain.EnergyTariffData;
import io.github.jhipster.energy.repository.EnergyTariffDataRepository;
import io.github.jhipster.energy.service.dto.EnergyTariffDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyTariffDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EnergyTariffData.
 */
@Service
@Transactional
public class EnergyTariffDataServiceImpl implements EnergyTariffDataService {

    private final Logger log = LoggerFactory.getLogger(EnergyTariffDataServiceImpl.class);

    private final EnergyTariffDataRepository energyTariffDataRepository;

    private final EnergyTariffDataMapper energyTariffDataMapper;

    public EnergyTariffDataServiceImpl(EnergyTariffDataRepository energyTariffDataRepository, EnergyTariffDataMapper energyTariffDataMapper) {
        this.energyTariffDataRepository = energyTariffDataRepository;
        this.energyTariffDataMapper = energyTariffDataMapper;
    }

    /**
     * Save a energyTariffData.
     *
     * @param energyTariffDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnergyTariffDataDTO save(EnergyTariffDataDTO energyTariffDataDTO) {
        log.debug("Request to save EnergyTariffData : {}", energyTariffDataDTO);

        EnergyTariffData energyTariffData = energyTariffDataMapper.toEntity(energyTariffDataDTO);
        energyTariffData = energyTariffDataRepository.save(energyTariffData);
        return energyTariffDataMapper.toDto(energyTariffData);
    }

    /**
     * Get all the energyTariffData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnergyTariffDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnergyTariffData");
        return energyTariffDataRepository.findAll(pageable)
            .map(energyTariffDataMapper::toDto);
    }


    /**
     * Get one energyTariffData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnergyTariffDataDTO> findOne(Long id) {
        log.debug("Request to get EnergyTariffData : {}", id);
        return energyTariffDataRepository.findById(id)
            .map(energyTariffDataMapper::toDto);
    }

    /**
     * Delete the energyTariffData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnergyTariffData : {}", id);
        energyTariffDataRepository.deleteById(id);
    }
}
