package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.service.EnergyVoltageDataService;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.energy.web.rest.util.PaginationUtil;
import io.github.jhipster.energy.service.dto.EnergyVoltageDataDTO;
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
 * REST controller for managing EnergyVoltageData.
 */
@RestController
@RequestMapping("/api")
public class EnergyVoltageDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyVoltageDataResource.class);

    private static final String ENTITY_NAME = "energyVoltageData";

    private final EnergyVoltageDataService energyVoltageDataService;

    public EnergyVoltageDataResource(EnergyVoltageDataService energyVoltageDataService) {
        this.energyVoltageDataService = energyVoltageDataService;
    }

    /**
     * POST  /energy-voltage-data : Create a new energyVoltageData.
     *
     * @param energyVoltageDataDTO the energyVoltageDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyVoltageDataDTO, or with status 400 (Bad Request) if the energyVoltageData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-voltage-data")
    @Timed
    public ResponseEntity<EnergyVoltageDataDTO> createEnergyVoltageData(@Valid @RequestBody EnergyVoltageDataDTO energyVoltageDataDTO) throws URISyntaxException {
        log.debug("REST request to save EnergyVoltageData : {}", energyVoltageDataDTO);
        if (energyVoltageDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new energyVoltageData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyVoltageDataDTO result = energyVoltageDataService.save(energyVoltageDataDTO);
        return ResponseEntity.created(new URI("/api/energy-voltage-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-voltage-data : Updates an existing energyVoltageData.
     *
     * @param energyVoltageDataDTO the energyVoltageDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyVoltageDataDTO,
     * or with status 400 (Bad Request) if the energyVoltageDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the energyVoltageDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-voltage-data")
    @Timed
    public ResponseEntity<EnergyVoltageDataDTO> updateEnergyVoltageData(@Valid @RequestBody EnergyVoltageDataDTO energyVoltageDataDTO) throws URISyntaxException {
        log.debug("REST request to update EnergyVoltageData : {}", energyVoltageDataDTO);
        if (energyVoltageDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyVoltageDataDTO result = energyVoltageDataService.save(energyVoltageDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyVoltageDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-voltage-data : get all the energyVoltageData.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of energyVoltageData in body
     */
    @GetMapping("/energy-voltage-data")
    @Timed
    public ResponseEntity<List<EnergyVoltageDataDTO>> getAllEnergyVoltageData(Pageable pageable) {
        log.debug("REST request to get a page of EnergyVoltageData");
        Page<EnergyVoltageDataDTO> page = energyVoltageDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/energy-voltage-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /energy-voltage-data/:id : get the "id" energyVoltageData.
     *
     * @param id the id of the energyVoltageDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyVoltageDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/energy-voltage-data/{id}")
    @Timed
    public ResponseEntity<EnergyVoltageDataDTO> getEnergyVoltageData(@PathVariable Long id) {
        log.debug("REST request to get EnergyVoltageData : {}", id);
        Optional<EnergyVoltageDataDTO> energyVoltageDataDTO = energyVoltageDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(energyVoltageDataDTO);
    }

    /**
     * DELETE  /energy-voltage-data/:id : delete the "id" energyVoltageData.
     *
     * @param id the id of the energyVoltageDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-voltage-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyVoltageData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyVoltageData : {}", id);
        energyVoltageDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
