package io.github.jhipster.energy.service;

import io.github.jhipster.energy.service.dto.UserSACDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing UserSAC.
 */
public interface UserSACService {

    /**
     * Save a userSAC.
     *
     * @param userSACDTO the entity to save
     * @return the persisted entity
     */
    UserSACDTO save(UserSACDTO userSACDTO);

    /**
     * Get all the userSACS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserSACDTO> findAll(Pageable pageable);


    /**
     * Get the "id" userSAC.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UserSACDTO> findOne(Long id);

    /**
     * Delete the "id" userSAC.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
