package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.service.EnergyTariffDataService;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.energy.web.rest.util.PaginationUtil;
import io.github.jhipster.energy.service.dto.EnergyTariffDataDTO;
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
 * REST controller for managing EnergyTariffData.
 */
@RestController
@RequestMapping("/api")
public class EnergyTariffDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyTariffDataResource.class);

    private static final String ENTITY_NAME = "energyTariffData";

    private final EnergyTariffDataService energyTariffDataService;

    public EnergyTariffDataResource(EnergyTariffDataService energyTariffDataService) {
        this.energyTariffDataService = energyTariffDataService;
    }

    /**
     * POST  /energy-tariff-data : Create a new energyTariffData.
     *
     * @param energyTariffDataDTO the energyTariffDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyTariffDataDTO, or with status 400 (Bad Request) if the energyTariffData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-tariff-data")
    @Timed
    public ResponseEntity<EnergyTariffDataDTO> createEnergyTariffData(@Valid @RequestBody EnergyTariffDataDTO energyTariffDataDTO) throws URISyntaxException {
        log.debug("REST request to save EnergyTariffData : {}", energyTariffDataDTO);
        if (energyTariffDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new energyTariffData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyTariffDataDTO result = energyTariffDataService.save(energyTariffDataDTO);
        return ResponseEntity.created(new URI("/api/energy-tariff-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-tariff-data : Updates an existing energyTariffData.
     *
     * @param energyTariffDataDTO the energyTariffDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyTariffDataDTO,
     * or with status 400 (Bad Request) if the energyTariffDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the energyTariffDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-tariff-data")
    @Timed
    public ResponseEntity<EnergyTariffDataDTO> updateEnergyTariffData(@Valid @RequestBody EnergyTariffDataDTO energyTariffDataDTO) throws URISyntaxException {
        log.debug("REST request to update EnergyTariffData : {}", energyTariffDataDTO);
        if (energyTariffDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyTariffDataDTO result = energyTariffDataService.save(energyTariffDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyTariffDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-tariff-data : get all the energyTariffData.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of energyTariffData in body
     */
    @GetMapping("/energy-tariff-data")
    @Timed
    public ResponseEntity<List<EnergyTariffDataDTO>> getAllEnergyTariffData(Pageable pageable) {
        log.debug("REST request to get a page of EnergyTariffData");
        Page<EnergyTariffDataDTO> page = energyTariffDataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/energy-tariff-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /energy-tariff-data/:id : get the "id" energyTariffData.
     *
     * @param id the id of the energyTariffDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyTariffDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/energy-tariff-data/{id}")
    @Timed
    public ResponseEntity<EnergyTariffDataDTO> getEnergyTariffData(@PathVariable Long id) {
        log.debug("REST request to get EnergyTariffData : {}", id);
        Optional<EnergyTariffDataDTO> energyTariffDataDTO = energyTariffDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(energyTariffDataDTO);
    }

    /**
     * DELETE  /energy-tariff-data/:id : delete the "id" energyTariffData.
     *
     * @param id the id of the energyTariffDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-tariff-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyTariffData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyTariffData : {}", id);
        energyTariffDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
