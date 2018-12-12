package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.service.UserSACService;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.energy.web.rest.util.PaginationUtil;
import io.github.jhipster.energy.service.dto.UserSACDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserSAC.
 */
@RestController
@RequestMapping("/api")
public class UserSACResource {

    private final Logger log = LoggerFactory.getLogger(UserSACResource.class);

    private static final String ENTITY_NAME = "userSAC";

    private final UserSACService userSACService;

    public UserSACResource(UserSACService userSACService) {
        this.userSACService = userSACService;
    }

    /**
     * POST  /user-sacs : Create a new userSAC.
     *
     * @param userSACDTO the userSACDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userSACDTO, or with status 400 (Bad Request) if the userSAC has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-sacs")
    @Timed
    public ResponseEntity<UserSACDTO> createUserSAC(@Valid @RequestBody UserSACDTO userSACDTO) throws URISyntaxException {
        log.debug("REST request to save UserSAC : {}", userSACDTO);
        if (userSACDTO.getId() != null) {
            throw new BadRequestAlertException("A new userSAC cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserSACDTO result = userSACService.save(userSACDTO);
        return ResponseEntity.created(new URI("/api/user-sacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-sacs : Updates an existing userSAC.
     *
     * @param userSACDTO the userSACDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userSACDTO,
     * or with status 400 (Bad Request) if the userSACDTO is not valid,
     * or with status 500 (Internal Server Error) if the userSACDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-sacs")
    @Timed
    public ResponseEntity<UserSACDTO> updateUserSAC(@Valid @RequestBody UserSACDTO userSACDTO) throws URISyntaxException {
        log.debug("REST request to update UserSAC : {}", userSACDTO);
        if (userSACDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserSACDTO result = userSACService.save(userSACDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userSACDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-sacs : get all the userSACS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userSACS in body
     */
    @GetMapping("/user-sacs")
    @Timed
    public ResponseEntity<List<UserSACDTO>> getAllUserSACS(Pageable pageable) {
        log.debug("REST request to get a page of UserSACS");
        Page<UserSACDTO> page = userSACService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-sacs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /user-sacs/:id : get the "id" userSAC.
     *
     * @param id the id of the userSACDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userSACDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-sacs/{id}")
    @Timed
    public ResponseEntity<UserSACDTO> getUserSAC(@PathVariable Long id) {
        log.debug("REST request to get UserSAC : {}", id);
        Optional<UserSACDTO> userSACDTO = userSACService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userSACDTO);
    }

    /**
     * DELETE  /user-sacs/:id : delete the "id" userSAC.
     *
     * @param id the id of the userSACDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-sacs/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserSAC(@PathVariable Long id) {
        log.debug("REST request to delete UserSAC : {}", id);
        userSACService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
