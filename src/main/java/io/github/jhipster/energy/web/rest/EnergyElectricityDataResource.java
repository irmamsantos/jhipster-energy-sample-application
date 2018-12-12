package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.service.EnergyElectricityDataService;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.energy.web.rest.util.PaginationUtil;
import io.github.jhipster.energy.service.dto.EnergyElectricityDataDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing EnergyElectricityData.
 */
@RestController
@RequestMapping("/api")
public class EnergyElectricityDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyElectricityDataResource.class);

    private static final String ENTITY_NAME = "energyElectricityData";

    private final EnergyElectricityDataService energyElectricityDataService;

    public EnergyElectricityDataResource(EnergyElectricityDataService energyElectricityDataService) {
        this.energyElectricityDataService = energyElectricityDataService;
    }

    /**
     * POST  /energy-electricity-data : Create a new energyElectricityData.
     *
     * @param energyElectricityDataDTO the energyElectricityDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyElectricityDataDTO, or with status 400 (Bad Request) if the energyElectricityData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-electricity-data")
    @Timed
    public ResponseEntity<EnergyElectricityDataDTO> createEnergyElectricityData(@RequestBody EnergyElectricityDataDTO energyElectricityDataDTO) throws URISyntaxException {
        log.debug("REST request to save EnergyElectricityData : {}", energyElectricityDataDTO);
        if (energyElectricityDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new energyElectricityData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyElectricityDataDTO result = energyElectricityDataService.save(energyElectricityDataDTO);
        return ResponseEntity.created(new URI("/api/energy-electricity-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-electricity-data : Updates an existing energyElectricityData.
     *
     * @param energyElectricityDataDTO the energyElectricityDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyElectricityDataDTO,
     * or with status 400 (Bad Request) if the energyElectricityDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the energyElectricityDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-electricity-data")
    @Timed
    public ResponseEntity<EnergyElectricityDataDTO> updateEnergyElectricityData(@RequestBody EnergyElectricityDataDTO energyElectricityDataDTO) throws URISyntaxException {
        log.debug("REST request to update EnergyElectricityData : {}", energyElectricityDataDTO);
        if (energyElectricityDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyElectricityDataDTO result = energyElectricityDataService.save(energyElectricityDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyElectricityDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-electricity-data : get all the energyElectricityData.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of energyElectricityData in body
     */
    @GetMapping("/energy-electricity-data")
    @Timed
    public ResponseEntity<List<EnergyElectricityDataDTO>> getAllEnergyElectricityData(Pageable pageable) {
        log.debug("REST request to get a page of EnergyElectricityData");
        Page<EnergyElectricityDataDTO> page = energyElectricityDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/energy-electricity-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /energy-electricity-data/:id : get the "id" energyElectricityData.
     *
     * @param id the id of the energyElectricityDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyElectricityDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/energy-electricity-data/{id}")
    @Timed
    public ResponseEntity<EnergyElectricityDataDTO> getEnergyElectricityData(@PathVariable Long id) {
        log.debug("REST request to get EnergyElectricityData : {}", id);
        Optional<EnergyElectricityDataDTO> energyElectricityDataDTO = energyElectricityDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(energyElectricityDataDTO);
    }

    /**
     * DELETE  /energy-electricity-data/:id : delete the "id" energyElectricityData.
     *
     * @param id the id of the energyElectricityDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-electricity-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyElectricityData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyElectricityData : {}", id);
        energyElectricityDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
