package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.domain.EnergyElectricityData;
import io.github.jhipster.energy.repository.EnergyElectricityDataRepository;
import io.github.jhipster.energy.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.energy.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final EnergyElectricityDataRepository energyElectricityDataRepository;

    public EnergyElectricityDataResource(EnergyElectricityDataRepository energyElectricityDataRepository) {
        this.energyElectricityDataRepository = energyElectricityDataRepository;
    }

    /**
     * POST  /energy-electricity-data : Create a new energyElectricityData.
     *
     * @param energyElectricityData the energyElectricityData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyElectricityData, or with status 400 (Bad Request) if the energyElectricityData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-electricity-data")
    @Timed
    public ResponseEntity<EnergyElectricityData> createEnergyElectricityData(@RequestBody EnergyElectricityData energyElectricityData) throws URISyntaxException {
        log.debug("REST request to save EnergyElectricityData : {}", energyElectricityData);
        if (energyElectricityData.getId() != null) {
            throw new BadRequestAlertException("A new energyElectricityData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyElectricityData result = energyElectricityDataRepository.save(energyElectricityData);
        return ResponseEntity.created(new URI("/api/energy-electricity-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-electricity-data : Updates an existing energyElectricityData.
     *
     * @param energyElectricityData the energyElectricityData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyElectricityData,
     * or with status 400 (Bad Request) if the energyElectricityData is not valid,
     * or with status 500 (Internal Server Error) if the energyElectricityData couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-electricity-data")
    @Timed
    public ResponseEntity<EnergyElectricityData> updateEnergyElectricityData(@RequestBody EnergyElectricityData energyElectricityData) throws URISyntaxException {
        log.debug("REST request to update EnergyElectricityData : {}", energyElectricityData);
        if (energyElectricityData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyElectricityData result = energyElectricityDataRepository.save(energyElectricityData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyElectricityData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-electricity-data : get all the energyElectricityData.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of energyElectricityData in body
     */
    @GetMapping("/energy-electricity-data")
    @Timed
    public List<EnergyElectricityData> getAllEnergyElectricityData() {
        log.debug("REST request to get all EnergyElectricityData");
        return energyElectricityDataRepository.findAll();
    }

    /**
     * GET  /energy-electricity-data/:id : get the "id" energyElectricityData.
     *
     * @param id the id of the energyElectricityData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyElectricityData, or with status 404 (Not Found)
     */
    @GetMapping("/energy-electricity-data/{id}")
    @Timed
    public ResponseEntity<EnergyElectricityData> getEnergyElectricityData(@PathVariable Long id) {
        log.debug("REST request to get EnergyElectricityData : {}", id);
        Optional<EnergyElectricityData> energyElectricityData = energyElectricityDataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(energyElectricityData);
    }

    /**
     * DELETE  /energy-electricity-data/:id : delete the "id" energyElectricityData.
     *
     * @param id the id of the energyElectricityData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-electricity-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyElectricityData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyElectricityData : {}", id);

        energyElectricityDataRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
