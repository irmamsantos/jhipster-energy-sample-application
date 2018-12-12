package io.github.jhipster.energy.service.impl;

import io.github.jhipster.energy.service.EnergyContractedPowerRatingDataService;
import io.github.jhipster.energy.domain.EnergyContractedPowerRatingData;
import io.github.jhipster.energy.repository.EnergyContractedPowerRatingDataRepository;
import io.github.jhipster.energy.service.dto.EnergyContractedPowerRatingDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyContractedPowerRatingDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EnergyContractedPowerRatingData.
 */
@Service
@Transactional
public class EnergyContractedPowerRatingDataServiceImpl implements EnergyContractedPowerRatingDataService {

    private final Logger log = LoggerFactory.getLogger(EnergyContractedPowerRatingDataServiceImpl.class);

    private final EnergyContractedPowerRatingDataRepository energyContractedPowerRatingDataRepository;

    private final EnergyContractedPowerRatingDataMapper energyContractedPowerRatingDataMapper;

    public EnergyContractedPowerRatingDataServiceImpl(EnergyContractedPowerRatingDataRepository energyContractedPowerRatingDataRepository, EnergyContractedPowerRatingDataMapper energyContractedPowerRatingDataMapper) {
        this.energyContractedPowerRatingDataRepository = energyContractedPowerRatingDataRepository;
        this.energyContractedPowerRatingDataMapper = energyContractedPowerRatingDataMapper;
    }

    /**
     * Save a energyContractedPowerRatingData.
     *
     * @param energyContractedPowerRatingDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnergyContractedPowerRatingDataDTO save(EnergyContractedPowerRatingDataDTO energyContractedPowerRatingDataDTO) {
        log.debug("Request to save EnergyContractedPowerRatingData : {}", energyContractedPowerRatingDataDTO);

        EnergyContractedPowerRatingData energyContractedPowerRatingData = energyContractedPowerRatingDataMapper.toEntity(energyContractedPowerRatingDataDTO);
        energyContractedPowerRatingData = energyContractedPowerRatingDataRepository.save(energyContractedPowerRatingData);
        return energyContractedPowerRatingDataMapper.toDto(energyContractedPowerRatingData);
    }

    /**
     * Get all the energyContractedPowerRatingData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnergyContractedPowerRatingDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnergyContractedPowerRatingData");
        return energyContractedPowerRatingDataRepository.findAll(pageable)
            .map(energyContractedPowerRatingDataMapper::toDto);
    }


    /**
     * Get one energyContractedPowerRatingData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnergyContractedPowerRatingDataDTO> findOne(Long id) {
        log.debug("Request to get EnergyContractedPowerRatingData : {}", id);
        return energyContractedPowerRatingDataRepository.findById(id)
            .map(energyContractedPowerRatingDataMapper::toDto);
    }

    /**
     * Delete the energyContractedPowerRatingData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnergyContractedPowerRatingData : {}", id);
        energyContractedPowerRatingDataRepository.deleteById(id);
    }
}
