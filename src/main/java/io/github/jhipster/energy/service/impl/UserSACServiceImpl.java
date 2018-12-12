package io.github.jhipster.energy.service.impl;

import io.github.jhipster.energy.service.UserSACService;
import io.github.jhipster.energy.domain.UserSAC;
import io.github.jhipster.energy.repository.UserSACRepository;
import io.github.jhipster.energy.service.dto.UserSACDTO;
import io.github.jhipster.energy.service.mapper.UserSACMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing UserSAC.
 */
@Service
@Transactional
public class UserSACServiceImpl implements UserSACService {

    private final Logger log = LoggerFactory.getLogger(UserSACServiceImpl.class);

    private final UserSACRepository userSACRepository;

    private final UserSACMapper userSACMapper;

    public UserSACServiceImpl(UserSACRepository userSACRepository, UserSACMapper userSACMapper) {
        this.userSACRepository = userSACRepository;
        this.userSACMapper = userSACMapper;
    }

    /**
     * Save a userSAC.
     *
     * @param userSACDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserSACDTO save(UserSACDTO userSACDTO) {
        log.debug("Request to save UserSAC : {}", userSACDTO);

        UserSAC userSAC = userSACMapper.toEntity(userSACDTO);
        userSAC = userSACRepository.save(userSAC);
        return userSACMapper.toDto(userSAC);
    }

    /**
     * Get all the userSACS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserSACDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserSACS");
        return userSACRepository.findAll(pageable)
            .map(userSACMapper::toDto);
    }


    /**
     * Get one userSAC by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserSACDTO> findOne(Long id) {
        log.debug("Request to get UserSAC : {}", id);
        return userSACRepository.findById(id)
            .map(userSACMapper::toDto);
    }

    /**
     * Delete the userSAC by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserSAC : {}", id);
        userSACRepository.deleteById(id);
    }
}
