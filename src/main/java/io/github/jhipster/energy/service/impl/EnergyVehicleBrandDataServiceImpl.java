package io.github.jhipster.energy.service.impl;

import io.github.jhipster.energy.service.EnergyVehicleBrandDataService;
import io.github.jhipster.energy.domain.EnergyVehicleBrandData;
import io.github.jhipster.energy.repository.EnergyVehicleBrandDataRepository;
import io.github.jhipster.energy.service.dto.EnergyVehicleBrandDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyVehicleBrandDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EnergyVehicleBrandData.
 */
@Service
@Transactional
public class EnergyVehicleBrandDataServiceImpl implements EnergyVehicleBrandDataService {

    private final Logger log = LoggerFactory.getLogger(EnergyVehicleBrandDataServiceImpl.class);

    private final EnergyVehicleBrandDataRepository energyVehicleBrandDataRepository;

    private final EnergyVehicleBrandDataMapper energyVehicleBrandDataMapper;

    public EnergyVehicleBrandDataServiceImpl(EnergyVehicleBrandDataRepository energyVehicleBrandDataRepository, EnergyVehicleBrandDataMapper energyVehicleBrandDataMapper) {
        this.energyVehicleBrandDataRepository = energyVehicleBrandDataRepository;
        this.energyVehicleBrandDataMapper = energyVehicleBrandDataMapper;
    }

    /**
     * Save a energyVehicleBrandData.
     *
     * @param energyVehicleBrandDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnergyVehicleBrandDataDTO save(EnergyVehicleBrandDataDTO energyVehicleBrandDataDTO) {
        log.debug("Request to save EnergyVehicleBrandData : {}", energyVehicleBrandDataDTO);

        EnergyVehicleBrandData energyVehicleBrandData = energyVehicleBrandDataMapper.toEntity(energyVehicleBrandDataDTO);
        energyVehicleBrandData = energyVehicleBrandDataRepository.save(energyVehicleBrandData);
        return energyVehicleBrandDataMapper.toDto(energyVehicleBrandData);
    }

    /**
     * Get all the energyVehicleBrandData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnergyVehicleBrandDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnergyVehicleBrandData");
        return energyVehicleBrandDataRepository.findAll(pageable)
            .map(energyVehicleBrandDataMapper::toDto);
    }


    /**
     * Get one energyVehicleBrandData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnergyVehicleBrandDataDTO> findOne(Long id) {
        log.debug("Request to get EnergyVehicleBrandData : {}", id);
        return energyVehicleBrandDataRepository.findById(id)
            .map(energyVehicleBrandDataMapper::toDto);
    }

    /**
     * Delete the energyVehicleBrandData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnergyVehicleBrandData : {}", id);
        energyVehicleBrandDataRepository.deleteById(id);
    }
}
