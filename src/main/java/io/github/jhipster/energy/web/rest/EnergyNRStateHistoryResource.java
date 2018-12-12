package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.service.EnergyNRStateHistoryService;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.energy.web.rest.util.PaginationUtil;
import io.github.jhipster.energy.service.dto.EnergyNRStateHistoryDTO;
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
 * REST controller for managing EnergyNRStateHistory.
 */
@RestController
@RequestMapping("/api")
public class EnergyNRStateHistoryResource {

    private final Logger log = LoggerFactory.getLogger(EnergyNRStateHistoryResource.class);

    private static final String ENTITY_NAME = "energyNRStateHistory";

    private final EnergyNRStateHistoryService energyNRStateHistoryService;

    public EnergyNRStateHistoryResource(EnergyNRStateHistoryService energyNRStateHistoryService) {
        this.energyNRStateHistoryService = energyNRStateHistoryService;
    }

    /**
     * POST  /energy-nr-state-histories : Create a new energyNRStateHistory.
     *
     * @param energyNRStateHistoryDTO the energyNRStateHistoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyNRStateHistoryDTO, or with status 400 (Bad Request) if the energyNRStateHistory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-nr-state-histories")
    @Timed
    public ResponseEntity<EnergyNRStateHistoryDTO> createEnergyNRStateHistory(@Valid @RequestBody EnergyNRStateHistoryDTO energyNRStateHistoryDTO) throws URISyntaxException {
        log.debug("REST request to save EnergyNRStateHistory : {}", energyNRStateHistoryDTO);
        if (energyNRStateHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new energyNRStateHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyNRStateHistoryDTO result = energyNRStateHistoryService.save(energyNRStateHistoryDTO);
        return ResponseEntity.created(new URI("/api/energy-nr-state-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-nr-state-histories : Updates an existing energyNRStateHistory.
     *
     * @param energyNRStateHistoryDTO the energyNRStateHistoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyNRStateHistoryDTO,
     * or with status 400 (Bad Request) if the energyNRStateHistoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the energyNRStateHistoryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-nr-state-histories")
    @Timed
    public ResponseEntity<EnergyNRStateHistoryDTO> updateEnergyNRStateHistory(@Valid @RequestBody EnergyNRStateHistoryDTO energyNRStateHistoryDTO) throws URISyntaxException {
        log.debug("REST request to update EnergyNRStateHistory : {}", energyNRStateHistoryDTO);
        if (energyNRStateHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyNRStateHistoryDTO result = energyNRStateHistoryService.save(energyNRStateHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyNRStateHistoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-nr-state-histories : get all the energyNRStateHistories.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of energyNRStateHistories in body
     */
    @GetMapping("/energy-nr-state-histories")
    @Timed
    public ResponseEntity<List<EnergyNRStateHistoryDTO>> getAllEnergyNRStateHistories(Pageable pageable) {
        log.debug("REST request to get a page of EnergyNRStateHistories");
        Page<EnergyNRStateHistoryDTO> page = energyNRStateHistoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/energy-nr-state-histories");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /energy-nr-state-histories/:id : get the "id" energyNRStateHistory.
     *
     * @param id the id of the energyNRStateHistoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyNRStateHistoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/energy-nr-state-histories/{id}")
    @Timed
    public ResponseEntity<EnergyNRStateHistoryDTO> getEnergyNRStateHistory(@PathVariable Long id) {
        log.debug("REST request to get EnergyNRStateHistory : {}", id);
        Optional<EnergyNRStateHistoryDTO> energyNRStateHistoryDTO = energyNRStateHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(energyNRStateHistoryDTO);
    }

    /**
     * DELETE  /energy-nr-state-histories/:id : delete the "id" energyNRStateHistory.
     *
     * @param id the id of the energyNRStateHistoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-nr-state-histories/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyNRStateHistory(@PathVariable Long id) {
        log.debug("REST request to delete EnergyNRStateHistory : {}", id);
        energyNRStateHistoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
