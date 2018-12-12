package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.service.EnergyContractedPowerRatingDataService;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.energy.web.rest.util.PaginationUtil;
import io.github.jhipster.energy.service.dto.EnergyContractedPowerRatingDataDTO;
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
 * REST controller for managing EnergyContractedPowerRatingData.
 */
@RestController
@RequestMapping("/api")
public class EnergyContractedPowerRatingDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyContractedPowerRatingDataResource.class);

    private static final String ENTITY_NAME = "energyContractedPowerRatingData";

    private final EnergyContractedPowerRatingDataService energyContractedPowerRatingDataService;

    public EnergyContractedPowerRatingDataResource(EnergyContractedPowerRatingDataService energyContractedPowerRatingDataService) {
        this.energyContractedPowerRatingDataService = energyContractedPowerRatingDataService;
    }

    /**
     * POST  /energy-contracted-power-rating-data : Create a new energyContractedPowerRatingData.
     *
     * @param energyContractedPowerRatingDataDTO the energyContractedPowerRatingDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyContractedPowerRatingDataDTO, or with status 400 (Bad Request) if the energyContractedPowerRatingData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-contracted-power-rating-data")
    @Timed
    public ResponseEntity<EnergyContractedPowerRatingDataDTO> createEnergyContractedPowerRatingData(@Valid @RequestBody EnergyContractedPowerRatingDataDTO energyContractedPowerRatingDataDTO) throws URISyntaxException {
        log.debug("REST request to save EnergyContractedPowerRatingData : {}", energyContractedPowerRatingDataDTO);
        if (energyContractedPowerRatingDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new energyContractedPowerRatingData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyContractedPowerRatingDataDTO result = energyContractedPowerRatingDataService.save(energyContractedPowerRatingDataDTO);
        return ResponseEntity.created(new URI("/api/energy-contracted-power-rating-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-contracted-power-rating-data : Updates an existing energyContractedPowerRatingData.
     *
     * @param energyContractedPowerRatingDataDTO the energyContractedPowerRatingDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyContractedPowerRatingDataDTO,
     * or with status 400 (Bad Request) if the energyContractedPowerRatingDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the energyContractedPowerRatingDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-contracted-power-rating-data")
    @Timed
    public ResponseEntity<EnergyContractedPowerRatingDataDTO> updateEnergyContractedPowerRatingData(@Valid @RequestBody EnergyContractedPowerRatingDataDTO energyContractedPowerRatingDataDTO) throws URISyntaxException {
        log.debug("REST request to update EnergyContractedPowerRatingData : {}", energyContractedPowerRatingDataDTO);
        if (energyContractedPowerRatingDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyContractedPowerRatingDataDTO result = energyContractedPowerRatingDataService.save(energyContractedPowerRatingDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyContractedPowerRatingDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-contracted-power-rating-data : get all the energyContractedPowerRatingData.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of energyContractedPowerRatingData in body
     */
    @GetMapping("/energy-contracted-power-rating-data")
    @Timed
    public ResponseEntity<List<EnergyContractedPowerRatingDataDTO>> getAllEnergyContractedPowerRatingData(Pageable pageable) {
        log.debug("REST request to get a page of EnergyContractedPowerRatingData");
        Page<EnergyContractedPowerRatingDataDTO> page = energyContractedPowerRatingDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/energy-contracted-power-rating-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /energy-contracted-power-rating-data/:id : get the "id" energyContractedPowerRatingData.
     *
     * @param id the id of the energyContractedPowerRatingDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyContractedPowerRatingDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/energy-contracted-power-rating-data/{id}")
    @Timed
    public ResponseEntity<EnergyContractedPowerRatingDataDTO> getEnergyContractedPowerRatingData(@PathVariable Long id) {
        log.debug("REST request to get EnergyContractedPowerRatingData : {}", id);
        Optional<EnergyContractedPowerRatingDataDTO> energyContractedPowerRatingDataDTO = energyContractedPowerRatingDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(energyContractedPowerRatingDataDTO);
    }

    /**
     * DELETE  /energy-contracted-power-rating-data/:id : delete the "id" energyContractedPowerRatingData.
     *
     * @param id the id of the energyContractedPowerRatingDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-contracted-power-rating-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyContractedPowerRatingData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyContractedPowerRatingData : {}", id);
        energyContractedPowerRatingDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
