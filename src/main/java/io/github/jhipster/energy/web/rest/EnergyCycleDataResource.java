package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.service.EnergyCycleDataService;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.energy.web.rest.util.PaginationUtil;
import io.github.jhipster.energy.service.dto.EnergyCycleDataDTO;
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
 * REST controller for managing EnergyCycleData.
 */
@RestController
@RequestMapping("/api")
public class EnergyCycleDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyCycleDataResource.class);

    private static final String ENTITY_NAME = "energyCycleData";

    private final EnergyCycleDataService energyCycleDataService;

    public EnergyCycleDataResource(EnergyCycleDataService energyCycleDataService) {
        this.energyCycleDataService = energyCycleDataService;
    }

    /**
     * POST  /energy-cycle-data : Create a new energyCycleData.
     *
     * @param energyCycleDataDTO the energyCycleDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyCycleDataDTO, or with status 400 (Bad Request) if the energyCycleData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-cycle-data")
    @Timed
    public ResponseEntity<EnergyCycleDataDTO> createEnergyCycleData(@Valid @RequestBody EnergyCycleDataDTO energyCycleDataDTO) throws URISyntaxException {
        log.debug("REST request to save EnergyCycleData : {}", energyCycleDataDTO);
        if (energyCycleDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new energyCycleData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyCycleDataDTO result = energyCycleDataService.save(energyCycleDataDTO);
        return ResponseEntity.created(new URI("/api/energy-cycle-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-cycle-data : Updates an existing energyCycleData.
     *
     * @param energyCycleDataDTO the energyCycleDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyCycleDataDTO,
     * or with status 400 (Bad Request) if the energyCycleDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the energyCycleDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-cycle-data")
    @Timed
    public ResponseEntity<EnergyCycleDataDTO> updateEnergyCycleData(@Valid @RequestBody EnergyCycleDataDTO energyCycleDataDTO) throws URISyntaxException {
        log.debug("REST request to update EnergyCycleData : {}", energyCycleDataDTO);
        if (energyCycleDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyCycleDataDTO result = energyCycleDataService.save(energyCycleDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyCycleDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-cycle-data : get all the energyCycleData.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of energyCycleData in body
     */
    @GetMapping("/energy-cycle-data")
    @Timed
    public ResponseEntity<List<EnergyCycleDataDTO>> getAllEnergyCycleData(Pageable pageable) {
        log.debug("REST request to get a page of EnergyCycleData");
        Page<EnergyCycleDataDTO> page = energyCycleDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/energy-cycle-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /energy-cycle-data/:id : get the "id" energyCycleData.
     *
     * @param id the id of the energyCycleDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyCycleDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/energy-cycle-data/{id}")
    @Timed
    public ResponseEntity<EnergyCycleDataDTO> getEnergyCycleData(@PathVariable Long id) {
        log.debug("REST request to get EnergyCycleData : {}", id);
        Optional<EnergyCycleDataDTO> energyCycleDataDTO = energyCycleDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(energyCycleDataDTO);
    }

    /**
     * DELETE  /energy-cycle-data/:id : delete the "id" energyCycleData.
     *
     * @param id the id of the energyCycleDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-cycle-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyCycleData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyCycleData : {}", id);
        energyCycleDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
