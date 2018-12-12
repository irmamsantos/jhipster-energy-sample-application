package io.github.jhipster.energy.service.impl;

import io.github.jhipster.energy.service.EnergyVehicleModelDataService;
import io.github.jhipster.energy.domain.EnergyVehicleModelData;
import io.github.jhipster.energy.repository.EnergyVehicleModelDataRepository;
import io.github.jhipster.energy.service.dto.EnergyVehicleModelDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyVehicleModelDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EnergyVehicleModelData.
 */
@Service
@Transactional
public class EnergyVehicleModelDataServiceImpl implements EnergyVehicleModelDataService {

    private final Logger log = LoggerFactory.getLogger(EnergyVehicleModelDataServiceImpl.class);

    private final EnergyVehicleModelDataRepository energyVehicleModelDataRepository;

    private final EnergyVehicleModelDataMapper energyVehicleModelDataMapper;

    public EnergyVehicleModelDataServiceImpl(EnergyVehicleModelDataRepository energyVehicleModelDataRepository, EnergyVehicleModelDataMapper energyVehicleModelDataMapper) {
        this.energyVehicleModelDataRepository = energyVehicleModelDataRepository;
        this.energyVehicleModelDataMapper = energyVehicleModelDataMapper;
    }

    /**
     * Save a energyVehicleModelData.
     *
     * @param energyVehicleModelDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnergyVehicleModelDataDTO save(EnergyVehicleModelDataDTO energyVehicleModelDataDTO) {
        log.debug("Request to save EnergyVehicleModelData : {}", energyVehicleModelDataDTO);

        EnergyVehicleModelData energyVehicleModelData = energyVehicleModelDataMapper.toEntity(energyVehicleModelDataDTO);
        energyVehicleModelData = energyVehicleModelDataRepository.save(energyVehicleModelData);
        return energyVehicleModelDataMapper.toDto(energyVehicleModelData);
    }

    /**
     * Get all the energyVehicleModelData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnergyVehicleModelDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnergyVehicleModelData");
        return energyVehicleModelDataRepository.findAll(pageable)
            .map(energyVehicleModelDataMapper::toDto);
    }


    /**
     * Get one energyVehicleModelData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnergyVehicleModelDataDTO> findOne(Long id) {
        log.debug("Request to get EnergyVehicleModelData : {}", id);
        return energyVehicleModelDataRepository.findById(id)
            .map(energyVehicleModelDataMapper::toDto);
    }

    /**
     * Delete the energyVehicleModelData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnergyVehicleModelData : {}", id);
        energyVehicleModelDataRepository.deleteById(id);
    }
}
