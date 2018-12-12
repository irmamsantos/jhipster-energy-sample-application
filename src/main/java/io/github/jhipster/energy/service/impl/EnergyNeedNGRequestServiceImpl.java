package io.github.jhipster.energy.service.impl;

import io.github.jhipster.energy.service.EnergyNeedNGRequestService;
import io.github.jhipster.energy.domain.EnergyNeedNGRequest;
import io.github.jhipster.energy.repository.EnergyNeedNGRequestRepository;
import io.github.jhipster.energy.service.dto.EnergyNeedNGRequestDTO;
import io.github.jhipster.energy.service.mapper.EnergyNeedNGRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EnergyNeedNGRequest.
 */
@Service
@Transactional
public class EnergyNeedNGRequestServiceImpl implements EnergyNeedNGRequestService {

    private final Logger log = LoggerFactory.getLogger(EnergyNeedNGRequestServiceImpl.class);

    private final EnergyNeedNGRequestRepository energyNeedNGRequestRepository;

    private final EnergyNeedNGRequestMapper energyNeedNGRequestMapper;

    public EnergyNeedNGRequestServiceImpl(EnergyNeedNGRequestRepository energyNeedNGRequestRepository, EnergyNeedNGRequestMapper energyNeedNGRequestMapper) {
        this.energyNeedNGRequestRepository = energyNeedNGRequestRepository;
        this.energyNeedNGRequestMapper = energyNeedNGRequestMapper;
    }

    /**
     * Save a energyNeedNGRequest.
     *
     * @param energyNeedNGRequestDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EnergyNeedNGRequestDTO save(EnergyNeedNGRequestDTO energyNeedNGRequestDTO) {
        log.debug("Request to save EnergyNeedNGRequest : {}", energyNeedNGRequestDTO);

        EnergyNeedNGRequest energyNeedNGRequest = energyNeedNGRequestMapper.toEntity(energyNeedNGRequestDTO);
        energyNeedNGRequest = energyNeedNGRequestRepository.save(energyNeedNGRequest);
        return energyNeedNGRequestMapper.toDto(energyNeedNGRequest);
    }

    /**
     * Get all the energyNeedNGRequests.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnergyNeedNGRequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnergyNeedNGRequests");
        return energyNeedNGRequestRepository.findAll(pageable)
            .map(energyNeedNGRequestMapper::toDto);
    }


    /**
     * Get one energyNeedNGRequest by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnergyNeedNGRequestDTO> findOne(Long id) {
        log.debug("Request to get EnergyNeedNGRequest : {}", id);
        return energyNeedNGRequestRepository.findById(id)
            .map(energyNeedNGRequestMapper::toDto);
    }

    /**
     * Delete the energyNeedNGRequest by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnergyNeedNGRequest : {}", id);
        energyNeedNGRequestRepository.deleteById(id);
    }
}
