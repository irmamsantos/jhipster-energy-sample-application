package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.service.EnergyVehicleBrandDataService;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.energy.web.rest.util.PaginationUtil;
import io.github.jhipster.energy.service.dto.EnergyVehicleBrandDataDTO;
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
 * REST controller for managing EnergyVehicleBrandData.
 */
@RestController
@RequestMapping("/api")
public class EnergyVehicleBrandDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyVehicleBrandDataResource.class);

    private static final String ENTITY_NAME = "energyVehicleBrandData";

    private final EnergyVehicleBrandDataService energyVehicleBrandDataService;

    public EnergyVehicleBrandDataResource(EnergyVehicleBrandDataService energyVehicleBrandDataService) {
        this.energyVehicleBrandDataService = energyVehicleBrandDataService;
    }

    /**
     * POST  /energy-vehicle-brand-data : Create a new energyVehicleBrandData.
     *
     * @param energyVehicleBrandDataDTO the energyVehicleBrandDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyVehicleBrandDataDTO, or with status 400 (Bad Request) if the energyVehicleBrandData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-vehicle-brand-data")
    @Timed
    public ResponseEntity<EnergyVehicleBrandDataDTO> createEnergyVehicleBrandData(@Valid @RequestBody EnergyVehicleBrandDataDTO energyVehicleBrandDataDTO) throws URISyntaxException {
        log.debug("REST request to save EnergyVehicleBrandData : {}", energyVehicleBrandDataDTO);
        if (energyVehicleBrandDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new energyVehicleBrandData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyVehicleBrandDataDTO result = energyVehicleBrandDataService.save(energyVehicleBrandDataDTO);
        return ResponseEntity.created(new URI("/api/energy-vehicle-brand-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-vehicle-brand-data : Updates an existing energyVehicleBrandData.
     *
     * @param energyVehicleBrandDataDTO the energyVehicleBrandDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyVehicleBrandDataDTO,
     * or with status 400 (Bad Request) if the energyVehicleBrandDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the energyVehicleBrandDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-vehicle-brand-data")
    @Timed
    public ResponseEntity<EnergyVehicleBrandDataDTO> updateEnergyVehicleBrandData(@Valid @RequestBody EnergyVehicleBrandDataDTO energyVehicleBrandDataDTO) throws URISyntaxException {
        log.debug("REST request to update EnergyVehicleBrandData : {}", energyVehicleBrandDataDTO);
        if (energyVehicleBrandDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyVehicleBrandDataDTO result = energyVehicleBrandDataService.save(energyVehicleBrandDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyVehicleBrandDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-vehicle-brand-data : get all the energyVehicleBrandData.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of energyVehicleBrandData in body
     */
    @GetMapping("/energy-vehicle-brand-data")
    @Timed
    public ResponseEntity<List<EnergyVehicleBrandDataDTO>> getAllEnergyVehicleBrandData(Pageable pageable) {
        log.debug("REST request to get a page of EnergyVehicleBrandData");
        Page<EnergyVehicleBrandDataDTO> page = energyVehicleBrandDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/energy-vehicle-brand-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /energy-vehicle-brand-data/:id : get the "id" energyVehicleBrandData.
     *
     * @param id the id of the energyVehicleBrandDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyVehicleBrandDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/energy-vehicle-brand-data/{id}")
    @Timed
    public ResponseEntity<EnergyVehicleBrandDataDTO> getEnergyVehicleBrandData(@PathVariable Long id) {
        log.debug("REST request to get EnergyVehicleBrandData : {}", id);
        Optional<EnergyVehicleBrandDataDTO> energyVehicleBrandDataDTO = energyVehicleBrandDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(energyVehicleBrandDataDTO);
    }

    /**
     * DELETE  /energy-vehicle-brand-data/:id : delete the "id" energyVehicleBrandData.
     *
     * @param id the id of the energyVehicleBrandDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-vehicle-brand-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyVehicleBrandData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyVehicleBrandData : {}", id);
        energyVehicleBrandDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
