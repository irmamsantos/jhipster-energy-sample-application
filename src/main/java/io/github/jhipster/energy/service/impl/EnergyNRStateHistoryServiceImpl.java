package io.github.jhipster.energy.service.impl;

import io.github.jhipster.energy.service.EnergyNRStateHistoryService;
import io.github.jhipster.energy.domain.EnergyNRStateHistory;
import io.github.jhipster.energy.repository.EnergyNRStateHistoryRepository;
import io.github.jhipster.energy.service.dto.EnergyNRStateHistoryDTO;
import io.github.jhipster.energy.service.mapper.EnergyNRStateHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EnergyNRStateHistory.
 */
@Service
@Transactional
public class EnergyNRStateHistoryServiceImpl implements EnergyNRStateHistoryService {

    private final Logger log = LoggerFactory.getLogger(EnergyNRStateHistoryServiceImpl.class);

    private final EnergyNRStateHistoryRepository energyNRStateHistoryRepository;

    private final EnergyNRStateHistoryMapper energyNRStateHistoryMapper;

    public EnergyNRStateHistoryServiceImpl(EnergyNRStateHistoryRepository energyNRStateHistoryRepository, EnergyNRStateHistoryMapper energyNRStateHistoryMapper) {
        this.energyNRStateHistoryRepository = energyNRStateHistoryRepository;
        this.energyNRStateHistoryMapper = energyNRStateHistoryMapper;
    }

    /**
     * Save a energyNRStateHistory.
     *
     * @param energyNRStateHistoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnergyNRStateHistoryDTO save(EnergyNRStateHistoryDTO energyNRStateHistoryDTO) {
        log.debug("Request to save EnergyNRStateHistory : {}", energyNRStateHistoryDTO);

        EnergyNRStateHistory energyNRStateHistory = energyNRStateHistoryMapper.toEntity(energyNRStateHistoryDTO);
        energyNRStateHistory = energyNRStateHistoryRepository.save(energyNRStateHistory);
        return energyNRStateHistoryMapper.toDto(energyNRStateHistory);
    }

    /**
     * Get all the energyNRStateHistories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnergyNRStateHistoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnergyNRStateHistories");
        return energyNRStateHistoryRepository.findAll(pageable)
            .map(energyNRStateHistoryMapper::toDto);
    }


    /**
     * Get one energyNRStateHistory by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnergyNRStateHistoryDTO> findOne(Long id) {
        log.debug("Request to get EnergyNRStateHistory : {}", id);
        return energyNRStateHistoryRepository.findById(id)
            .map(energyNRStateHistoryMapper::toDto);
    }

    /**
     * Delete the energyNRStateHistory by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnergyNRStateHistory : {}", id);
        energyNRStateHistoryRepository.deleteById(id);
    }
}
