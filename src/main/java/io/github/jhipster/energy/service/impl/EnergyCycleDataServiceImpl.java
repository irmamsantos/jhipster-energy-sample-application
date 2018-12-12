package io.github.jhipster.energy.service.impl;

import io.github.jhipster.energy.service.EnergyCycleDataService;
import io.github.jhipster.energy.domain.EnergyCycleData;
import io.github.jhipster.energy.repository.EnergyCycleDataRepository;
import io.github.jhipster.energy.service.dto.EnergyCycleDataDTO;
import io.github.jhipster.energy.service.mapper.EnergyCycleDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EnergyCycleData.
 */
@Service
@Transactional
public class EnergyCycleDataServiceImpl implements EnergyCycleDataService {

    private final Logger log = LoggerFactory.getLogger(EnergyCycleDataServiceImpl.class);

    private final EnergyCycleDataRepository energyCycleDataRepository;

    private final EnergyCycleDataMapper energyCycleDataMapper;

    public EnergyCycleDataServiceImpl(EnergyCycleDataRepository energyCycleDataRepository, EnergyCycleDataMapper energyCycleDataMapper) {
        this.energyCycleDataRepository = energyCycleDataRepository;
        this.energyCycleDataMapper = energyCycleDataMapper;
    }

    /**
     * Save a energyCycleData.
     *
     * @param energyCycleDataDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnergyCycleDataDTO save(EnergyCycleDataDTO energyCycleDataDTO) {
        log.debug("Request to save EnergyCycleData : {}", energyCycleDataDTO);

        EnergyCycleData energyCycleData = energyCycleDataMapper.toEntity(energyCycleDataDTO);
        energyCycleData = energyCycleDataRepository.save(energyCycleData);
        return energyCycleDataMapper.toDto(energyCycleData);
    }

    /**
     * Get all the energyCycleData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnergyCycleDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnergyCycleData");
        return energyCycleDataRepository.findAll(pageable)
            .map(energyCycleDataMapper::toDto);
    }


    /**
     * Get one energyCycleData by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnergyCycleDataDTO> findOne(Long id) {
        log.debug("Request to get EnergyCycleData : {}", id);
        return energyCycleDataRepository.findById(id)
            .map(energyCycleDataMapper::toDto);
    }

    /**
     * Delete the energyCycleData by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnergyCycleData : {}", id);
        energyCycleDataRepository.deleteById(id);
    }
}
