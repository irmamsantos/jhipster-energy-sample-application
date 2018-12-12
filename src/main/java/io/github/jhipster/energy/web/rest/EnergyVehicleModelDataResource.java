package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.service.EnergyVehicleModelDataService;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.energy.web.rest.util.PaginationUtil;
import io.github.jhipster.energy.service.dto.EnergyVehicleModelDataDTO;
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
 * REST controller for managing EnergyVehicleModelData.
 */
@RestController
@RequestMapping("/api")
public class EnergyVehicleModelDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyVehicleModelDataResource.class);

    private static final String ENTITY_NAME = "energyVehicleModelData";

    private final EnergyVehicleModelDataService energyVehicleModelDataService;

    public EnergyVehicleModelDataResource(EnergyVehicleModelDataService energyVehicleModelDataService) {
        this.energyVehicleModelDataService = energyVehicleModelDataService;
    }

    /**
     * POST  /energy-vehicle-model-data : Create a new energyVehicleModelData.
     *
     * @param energyVehicleModelDataDTO the energyVehicleModelDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyVehicleModelDataDTO, or with status 400 (Bad Request) if the energyVehicleModelData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-vehicle-model-data")
    @Timed
    public ResponseEntity<EnergyVehicleModelDataDTO> createEnergyVehicleModelData(@Valid @RequestBody EnergyVehicleModelDataDTO energyVehicleModelDataDTO) throws URISyntaxException {
        log.debug("REST request to save EnergyVehicleModelData : {}", energyVehicleModelDataDTO);
        if (energyVehicleModelDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new energyVehicleModelData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyVehicleModelDataDTO result = energyVehicleModelDataService.save(energyVehicleModelDataDTO);
        return ResponseEntity.created(new URI("/api/energy-vehicle-model-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-vehicle-model-data : Updates an existing energyVehicleModelData.
     *
     * @param energyVehicleModelDataDTO the energyVehicleModelDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyVehicleModelDataDTO,
     * or with status 400 (Bad Request) if the energyVehicleModelDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the energyVehicleModelDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-vehicle-model-data")
    @Timed
    public ResponseEntity<EnergyVehicleModelDataDTO> updateEnergyVehicleModelData(@Valid @RequestBody EnergyVehicleModelDataDTO energyVehicleModelDataDTO) throws URISyntaxException {
        log.debug("REST request to update EnergyVehicleModelData : {}", energyVehicleModelDataDTO);
        if (energyVehicleModelDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyVehicleModelDataDTO result = energyVehicleModelDataService.save(energyVehicleModelDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyVehicleModelDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-vehicle-model-data : get all the energyVehicleModelData.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of energyVehicleModelData in body
     */
    @GetMapping("/energy-vehicle-model-data")
    @Timed
    public ResponseEntity<List<EnergyVehicleModelDataDTO>> getAllEnergyVehicleModelData(Pageable pageable) {
        log.debug("REST request to get a page of EnergyVehicleModelData");
        Page<EnergyVehicleModelDataDTO> page = energyVehicleModelDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/energy-vehicle-model-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /energy-vehicle-model-data/:id : get the "id" energyVehicleModelData.
     *
     * @param id the id of the energyVehicleModelDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyVehicleModelDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/energy-vehicle-model-data/{id}")
    @Timed
    public ResponseEntity<EnergyVehicleModelDataDTO> getEnergyVehicleModelData(@PathVariable Long id) {
        log.debug("REST request to get EnergyVehicleModelData : {}", id);
        Optional<EnergyVehicleModelDataDTO> energyVehicleModelDataDTO = energyVehicleModelDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(energyVehicleModelDataDTO);
    }

    /**
     * DELETE  /energy-vehicle-model-data/:id : delete the "id" energyVehicleModelData.
     *
     * @param id the id of the energyVehicleModelDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-vehicle-model-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyVehicleModelData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyVehicleModelData : {}", id);
        energyVehicleModelDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
