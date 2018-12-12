package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.service.EnergyTimePeriodDataService;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.energy.web.rest.util.PaginationUtil;
import io.github.jhipster.energy.service.dto.EnergyTimePeriodDataDTO;
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
 * REST controller for managing EnergyTimePeriodData.
 */
@RestController
@RequestMapping("/api")
public class EnergyTimePeriodDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyTimePeriodDataResource.class);

    private static final String ENTITY_NAME = "energyTimePeriodData";

    private final EnergyTimePeriodDataService energyTimePeriodDataService;

    public EnergyTimePeriodDataResource(EnergyTimePeriodDataService energyTimePeriodDataService) {
        this.energyTimePeriodDataService = energyTimePeriodDataService;
    }

    /**
     * POST  /energy-time-period-data : Create a new energyTimePeriodData.
     *
     * @param energyTimePeriodDataDTO the energyTimePeriodDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyTimePeriodDataDTO, or with status 400 (Bad Request) if the energyTimePeriodData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-time-period-data")
    @Timed
    public ResponseEntity<EnergyTimePeriodDataDTO> createEnergyTimePeriodData(@Valid @RequestBody EnergyTimePeriodDataDTO energyTimePeriodDataDTO) throws URISyntaxException {
        log.debug("REST request to save EnergyTimePeriodData : {}", energyTimePeriodDataDTO);
        if (energyTimePeriodDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new energyTimePeriodData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyTimePeriodDataDTO result = energyTimePeriodDataService.save(energyTimePeriodDataDTO);
        return ResponseEntity.created(new URI("/api/energy-time-period-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-time-period-data : Updates an existing energyTimePeriodData.
     *
     * @param energyTimePeriodDataDTO the energyTimePeriodDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyTimePeriodDataDTO,
     * or with status 400 (Bad Request) if the energyTimePeriodDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the energyTimePeriodDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-time-period-data")
    @Timed
    public ResponseEntity<EnergyTimePeriodDataDTO> updateEnergyTimePeriodData(@Valid @RequestBody EnergyTimePeriodDataDTO energyTimePeriodDataDTO) throws URISyntaxException {
        log.debug("REST request to update EnergyTimePeriodData : {}", energyTimePeriodDataDTO);
        if (energyTimePeriodDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyTimePeriodDataDTO result = energyTimePeriodDataService.save(energyTimePeriodDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyTimePeriodDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-time-period-data : get all the energyTimePeriodData.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of energyTimePeriodData in body
     */
    @GetMapping("/energy-time-period-data")
    @Timed
    public ResponseEntity<List<EnergyTimePeriodDataDTO>> getAllEnergyTimePeriodData(Pageable pageable) {
        log.debug("REST request to get a page of EnergyTimePeriodData");
        Page<EnergyTimePeriodDataDTO> page = energyTimePeriodDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/energy-time-period-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /energy-time-period-data/:id : get the "id" energyTimePeriodData.
     *
     * @param id the id of the energyTimePeriodDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyTimePeriodDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/energy-time-period-data/{id}")
    @Timed
    public ResponseEntity<EnergyTimePeriodDataDTO> getEnergyTimePeriodData(@PathVariable Long id) {
        log.debug("REST request to get EnergyTimePeriodData : {}", id);
        Optional<EnergyTimePeriodDataDTO> energyTimePeriodDataDTO = energyTimePeriodDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(energyTimePeriodDataDTO);
    }

    /**
     * DELETE  /energy-time-period-data/:id : delete the "id" energyTimePeriodData.
     *
     * @param id the id of the energyTimePeriodDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-time-period-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyTimePeriodData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyTimePeriodData : {}", id);
        energyTimePeriodDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
