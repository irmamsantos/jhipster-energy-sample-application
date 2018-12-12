package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.service.EnergyNeedNGRequestService;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.energy.web.rest.util.PaginationUtil;
import io.github.jhipster.energy.service.dto.EnergyNeedNGRequestDTO;
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
 * REST controller for managing EnergyNeedNGRequest.
 */
@RestController
@RequestMapping("/api")
public class EnergyNeedNGRequestResource {

    private final Logger log = LoggerFactory.getLogger(EnergyNeedNGRequestResource.class);

    private static final String ENTITY_NAME = "energyNeedNGRequest";

    private final EnergyNeedNGRequestService energyNeedNGRequestService;

    public EnergyNeedNGRequestResource(EnergyNeedNGRequestService energyNeedNGRequestService) {
        this.energyNeedNGRequestService = energyNeedNGRequestService;
    }

    /**
     * POST  /energy-need-ng-requests : Create a new energyNeedNGRequest.
     *
     * @param energyNeedNGRequestDTO the energyNeedNGRequestDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyNeedNGRequestDTO, or with status 400 (Bad Request) if the energyNeedNGRequest has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-need-ng-requests")
    @Timed
    public ResponseEntity<EnergyNeedNGRequestDTO> createEnergyNeedNGRequest(@Valid @RequestBody EnergyNeedNGRequestDTO energyNeedNGRequestDTO) throws URISyntaxException {
        log.debug("REST request to save EnergyNeedNGRequest : {}", energyNeedNGRequestDTO);
        if (energyNeedNGRequestDTO.getId() != null) {
            throw new BadRequestAlertException("A new energyNeedNGRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyNeedNGRequestDTO result = energyNeedNGRequestService.save(energyNeedNGRequestDTO);
        return ResponseEntity.created(new URI("/api/energy-need-ng-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-need-ng-requests : Updates an existing energyNeedNGRequest.
     *
     * @param energyNeedNGRequestDTO the energyNeedNGRequestDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyNeedNGRequestDTO,
     * or with status 400 (Bad Request) if the energyNeedNGRequestDTO is not valid,
     * or with status 500 (Internal Server Error) if the energyNeedNGRequestDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-need-ng-requests")
    @Timed
    public ResponseEntity<EnergyNeedNGRequestDTO> updateEnergyNeedNGRequest(@Valid @RequestBody EnergyNeedNGRequestDTO energyNeedNGRequestDTO) throws URISyntaxException {
        log.debug("REST request to update EnergyNeedNGRequest : {}", energyNeedNGRequestDTO);
        if (energyNeedNGRequestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyNeedNGRequestDTO result = energyNeedNGRequestService.save(energyNeedNGRequestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyNeedNGRequestDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-need-ng-requests : get all the energyNeedNGRequests.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of energyNeedNGRequests in body
     */
    @GetMapping("/energy-need-ng-requests")
    @Timed
    public ResponseEntity<List<EnergyNeedNGRequestDTO>> getAllEnergyNeedNGRequests(Pageable pageable) {
        log.debug("REST request to get a page of EnergyNeedNGRequests");
        Page<EnergyNeedNGRequestDTO> page = energyNeedNGRequestService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/energy-need-ng-requests");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /energy-need-ng-requests/:id : get the "id" energyNeedNGRequest.
     *
     * @param id the id of the energyNeedNGRequestDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyNeedNGRequestDTO, or with status 404 (Not Found)
     */
    @GetMapping("/energy-need-ng-requests/{id}")
    @Timed
    public ResponseEntity<EnergyNeedNGRequestDTO> getEnergyNeedNGRequest(@PathVariable Long id) {
        log.debug("REST request to get EnergyNeedNGRequest : {}", id);
        Optional<EnergyNeedNGRequestDTO> energyNeedNGRequestDTO = energyNeedNGRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(energyNeedNGRequestDTO);
    }

    /**
     * DELETE  /energy-need-ng-requests/:id : delete the "id" energyNeedNGRequest.
     *
     * @param id the id of the energyNeedNGRequestDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-need-ng-requests/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyNeedNGRequest(@PathVariable Long id) {
        log.debug("REST request to delete EnergyNeedNGRequest : {}", id);
        energyNeedNGRequestService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
