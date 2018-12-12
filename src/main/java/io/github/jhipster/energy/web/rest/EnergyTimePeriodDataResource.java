package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.domain.EnergyTimePeriodData;
import io.github.jhipster.energy.repository.EnergyTimePeriodDataRepository;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final EnergyTimePeriodDataRepository energyTimePeriodDataRepository;

    public EnergyTimePeriodDataResource(EnergyTimePeriodDataRepository energyTimePeriodDataRepository) {
        this.energyTimePeriodDataRepository = energyTimePeriodDataRepository;
    }

    /**
     * POST  /energy-time-period-data : Create a new energyTimePeriodData.
     *
     * @param energyTimePeriodData the energyTimePeriodData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyTimePeriodData, or with status 400 (Bad Request) if the energyTimePeriodData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-time-period-data")
    @Timed
    public ResponseEntity<EnergyTimePeriodData> createEnergyTimePeriodData(@Valid @RequestBody EnergyTimePeriodData energyTimePeriodData) throws URISyntaxException {
        log.debug("REST request to save EnergyTimePeriodData : {}", energyTimePeriodData);
        if (energyTimePeriodData.getId() != null) {
            throw new BadRequestAlertException("A new energyTimePeriodData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyTimePeriodData result = energyTimePeriodDataRepository.save(energyTimePeriodData);
        return ResponseEntity.created(new URI("/api/energy-time-period-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-time-period-data : Updates an existing energyTimePeriodData.
     *
     * @param energyTimePeriodData the energyTimePeriodData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyTimePeriodData,
     * or with status 400 (Bad Request) if the energyTimePeriodData is not valid,
     * or with status 500 (Internal Server Error) if the energyTimePeriodData couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-time-period-data")
    @Timed
    public ResponseEntity<EnergyTimePeriodData> updateEnergyTimePeriodData(@Valid @RequestBody EnergyTimePeriodData energyTimePeriodData) throws URISyntaxException {
        log.debug("REST request to update EnergyTimePeriodData : {}", energyTimePeriodData);
        if (energyTimePeriodData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyTimePeriodData result = energyTimePeriodDataRepository.save(energyTimePeriodData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyTimePeriodData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-time-period-data : get all the energyTimePeriodData.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of energyTimePeriodData in body
     */
    @GetMapping("/energy-time-period-data")
    @Timed
    public List<EnergyTimePeriodData> getAllEnergyTimePeriodData() {
        log.debug("REST request to get all EnergyTimePeriodData");
        return energyTimePeriodDataRepository.findAll();
    }

    /**
     * GET  /energy-time-period-data/:id : get the "id" energyTimePeriodData.
     *
     * @param id the id of the energyTimePeriodData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyTimePeriodData, or with status 404 (Not Found)
     */
    @GetMapping("/energy-time-period-data/{id}")
    @Timed
    public ResponseEntity<EnergyTimePeriodData> getEnergyTimePeriodData(@PathVariable Long id) {
        log.debug("REST request to get EnergyTimePeriodData : {}", id);
        Optional<EnergyTimePeriodData> energyTimePeriodData = energyTimePeriodDataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(energyTimePeriodData);
    }

    /**
     * DELETE  /energy-time-period-data/:id : delete the "id" energyTimePeriodData.
     *
     * @param id the id of the energyTimePeriodData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-time-period-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyTimePeriodData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyTimePeriodData : {}", id);

        energyTimePeriodDataRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
