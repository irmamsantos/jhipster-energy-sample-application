package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.service.EnergyNaturalGasDataService;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.energy.web.rest.util.PaginationUtil;
import io.github.jhipster.energy.service.dto.EnergyNaturalGasDataDTO;
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
 * REST controller for managing EnergyNaturalGasData.
 */
@RestController
@RequestMapping("/api")
public class EnergyNaturalGasDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyNaturalGasDataResource.class);

    private static final String ENTITY_NAME = "energyNaturalGasData";

    private final EnergyNaturalGasDataService energyNaturalGasDataService;

    public EnergyNaturalGasDataResource(EnergyNaturalGasDataService energyNaturalGasDataService) {
        this.energyNaturalGasDataService = energyNaturalGasDataService;
    }

    /**
     * POST  /energy-natural-gas-data : Create a new energyNaturalGasData.
     *
     * @param energyNaturalGasDataDTO the energyNaturalGasDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyNaturalGasDataDTO, or with status 400 (Bad Request) if the energyNaturalGasData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-natural-gas-data")
    @Timed
    public ResponseEntity<EnergyNaturalGasDataDTO> createEnergyNaturalGasData(@Valid @RequestBody EnergyNaturalGasDataDTO energyNaturalGasDataDTO) throws URISyntaxException {
        log.debug("REST request to save EnergyNaturalGasData : {}", energyNaturalGasDataDTO);
        if (energyNaturalGasDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new energyNaturalGasData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyNaturalGasDataDTO result = energyNaturalGasDataService.save(energyNaturalGasDataDTO);
        return ResponseEntity.created(new URI("/api/energy-natural-gas-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-natural-gas-data : Updates an existing energyNaturalGasData.
     *
     * @param energyNaturalGasDataDTO the energyNaturalGasDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyNaturalGasDataDTO,
     * or with status 400 (Bad Request) if the energyNaturalGasDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the energyNaturalGasDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-natural-gas-data")
    @Timed
    public ResponseEntity<EnergyNaturalGasDataDTO> updateEnergyNaturalGasData(@Valid @RequestBody EnergyNaturalGasDataDTO energyNaturalGasDataDTO) throws URISyntaxException {
        log.debug("REST request to update EnergyNaturalGasData : {}", energyNaturalGasDataDTO);
        if (energyNaturalGasDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyNaturalGasDataDTO result = energyNaturalGasDataService.save(energyNaturalGasDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyNaturalGasDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-natural-gas-data : get all the energyNaturalGasData.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of energyNaturalGasData in body
     */
    @GetMapping("/energy-natural-gas-data")
    @Timed
    public ResponseEntity<List<EnergyNaturalGasDataDTO>> getAllEnergyNaturalGasData(Pageable pageable) {
        log.debug("REST request to get a page of EnergyNaturalGasData");
        Page<EnergyNaturalGasDataDTO> page = energyNaturalGasDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/energy-natural-gas-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /energy-natural-gas-data/:id : get the "id" energyNaturalGasData.
     *
     * @param id the id of the energyNaturalGasDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyNaturalGasDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/energy-natural-gas-data/{id}")
    @Timed
    public ResponseEntity<EnergyNaturalGasDataDTO> getEnergyNaturalGasData(@PathVariable Long id) {
        log.debug("REST request to get EnergyNaturalGasData : {}", id);
        Optional<EnergyNaturalGasDataDTO> energyNaturalGasDataDTO = energyNaturalGasDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(energyNaturalGasDataDTO);
    }

    /**
     * DELETE  /energy-natural-gas-data/:id : delete the "id" energyNaturalGasData.
     *
     * @param id the id of the energyNaturalGasDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-natural-gas-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyNaturalGasData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyNaturalGasData : {}", id);
        energyNaturalGasDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
