package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.service.EnergyFuelDataService;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.energy.web.rest.util.PaginationUtil;
import io.github.jhipster.energy.service.dto.EnergyFuelDataDTO;
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
 * REST controller for managing EnergyFuelData.
 */
@RestController
@RequestMapping("/api")
public class EnergyFuelDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyFuelDataResource.class);

    private static final String ENTITY_NAME = "energyFuelData";

    private final EnergyFuelDataService energyFuelDataService;

    public EnergyFuelDataResource(EnergyFuelDataService energyFuelDataService) {
        this.energyFuelDataService = energyFuelDataService;
    }

    /**
     * POST  /energy-fuel-data : Create a new energyFuelData.
     *
     * @param energyFuelDataDTO the energyFuelDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyFuelDataDTO, or with status 400 (Bad Request) if the energyFuelData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-fuel-data")
    @Timed
    public ResponseEntity<EnergyFuelDataDTO> createEnergyFuelData(@Valid @RequestBody EnergyFuelDataDTO energyFuelDataDTO) throws URISyntaxException {
        log.debug("REST request to save EnergyFuelData : {}", energyFuelDataDTO);
        if (energyFuelDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new energyFuelData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyFuelDataDTO result = energyFuelDataService.save(energyFuelDataDTO);
        return ResponseEntity.created(new URI("/api/energy-fuel-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-fuel-data : Updates an existing energyFuelData.
     *
     * @param energyFuelDataDTO the energyFuelDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyFuelDataDTO,
     * or with status 400 (Bad Request) if the energyFuelDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the energyFuelDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-fuel-data")
    @Timed
    public ResponseEntity<EnergyFuelDataDTO> updateEnergyFuelData(@Valid @RequestBody EnergyFuelDataDTO energyFuelDataDTO) throws URISyntaxException {
        log.debug("REST request to update EnergyFuelData : {}", energyFuelDataDTO);
        if (energyFuelDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyFuelDataDTO result = energyFuelDataService.save(energyFuelDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyFuelDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-fuel-data : get all the energyFuelData.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of energyFuelData in body
     */
    @GetMapping("/energy-fuel-data")
    @Timed
    public ResponseEntity<List<EnergyFuelDataDTO>> getAllEnergyFuelData(Pageable pageable) {
        log.debug("REST request to get a page of EnergyFuelData");
        Page<EnergyFuelDataDTO> page = energyFuelDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/energy-fuel-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /energy-fuel-data/:id : get the "id" energyFuelData.
     *
     * @param id the id of the energyFuelDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyFuelDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/energy-fuel-data/{id}")
    @Timed
    public ResponseEntity<EnergyFuelDataDTO> getEnergyFuelData(@PathVariable Long id) {
        log.debug("REST request to get EnergyFuelData : {}", id);
        Optional<EnergyFuelDataDTO> energyFuelDataDTO = energyFuelDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(energyFuelDataDTO);
    }

    /**
     * DELETE  /energy-fuel-data/:id : delete the "id" energyFuelData.
     *
     * @param id the id of the energyFuelDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-fuel-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyFuelData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyFuelData : {}", id);
        energyFuelDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
