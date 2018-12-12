package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.domain.EnergyVoltageData;
import io.github.jhipster.energy.repository.EnergyVoltageDataRepository;
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
 * REST controller for managing EnergyVoltageData.
 */
@RestController
@RequestMapping("/api")
public class EnergyVoltageDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyVoltageDataResource.class);

    private static final String ENTITY_NAME = "energyVoltageData";

    private final EnergyVoltageDataRepository energyVoltageDataRepository;

    public EnergyVoltageDataResource(EnergyVoltageDataRepository energyVoltageDataRepository) {
        this.energyVoltageDataRepository = energyVoltageDataRepository;
    }

    /**
     * POST  /energy-voltage-data : Create a new energyVoltageData.
     *
     * @param energyVoltageData the energyVoltageData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyVoltageData, or with status 400 (Bad Request) if the energyVoltageData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-voltage-data")
    @Timed
    public ResponseEntity<EnergyVoltageData> createEnergyVoltageData(@Valid @RequestBody EnergyVoltageData energyVoltageData) throws URISyntaxException {
        log.debug("REST request to save EnergyVoltageData : {}", energyVoltageData);
        if (energyVoltageData.getId() != null) {
            throw new BadRequestAlertException("A new energyVoltageData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyVoltageData result = energyVoltageDataRepository.save(energyVoltageData);
        return ResponseEntity.created(new URI("/api/energy-voltage-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-voltage-data : Updates an existing energyVoltageData.
     *
     * @param energyVoltageData the energyVoltageData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyVoltageData,
     * or with status 400 (Bad Request) if the energyVoltageData is not valid,
     * or with status 500 (Internal Server Error) if the energyVoltageData couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-voltage-data")
    @Timed
    public ResponseEntity<EnergyVoltageData> updateEnergyVoltageData(@Valid @RequestBody EnergyVoltageData energyVoltageData) throws URISyntaxException {
        log.debug("REST request to update EnergyVoltageData : {}", energyVoltageData);
        if (energyVoltageData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyVoltageData result = energyVoltageDataRepository.save(energyVoltageData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyVoltageData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-voltage-data : get all the energyVoltageData.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of energyVoltageData in body
     */
    @GetMapping("/energy-voltage-data")
    @Timed
    public List<EnergyVoltageData> getAllEnergyVoltageData() {
        log.debug("REST request to get all EnergyVoltageData");
        return energyVoltageDataRepository.findAll();
    }

    /**
     * GET  /energy-voltage-data/:id : get the "id" energyVoltageData.
     *
     * @param id the id of the energyVoltageData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyVoltageData, or with status 404 (Not Found)
     */
    @GetMapping("/energy-voltage-data/{id}")
    @Timed
    public ResponseEntity<EnergyVoltageData> getEnergyVoltageData(@PathVariable Long id) {
        log.debug("REST request to get EnergyVoltageData : {}", id);
        Optional<EnergyVoltageData> energyVoltageData = energyVoltageDataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(energyVoltageData);
    }

    /**
     * DELETE  /energy-voltage-data/:id : delete the "id" energyVoltageData.
     *
     * @param id the id of the energyVoltageData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-voltage-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyVoltageData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyVoltageData : {}", id);

        energyVoltageDataRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
