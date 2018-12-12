package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.domain.EnergyFuelData;
import io.github.jhipster.energy.repository.EnergyFuelDataRepository;
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
 * REST controller for managing EnergyFuelData.
 */
@RestController
@RequestMapping("/api")
public class EnergyFuelDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyFuelDataResource.class);

    private static final String ENTITY_NAME = "energyFuelData";

    private final EnergyFuelDataRepository energyFuelDataRepository;

    public EnergyFuelDataResource(EnergyFuelDataRepository energyFuelDataRepository) {
        this.energyFuelDataRepository = energyFuelDataRepository;
    }

    /**
     * POST  /energy-fuel-data : Create a new energyFuelData.
     *
     * @param energyFuelData the energyFuelData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyFuelData, or with status 400 (Bad Request) if the energyFuelData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-fuel-data")
    @Timed
    public ResponseEntity<EnergyFuelData> createEnergyFuelData(@Valid @RequestBody EnergyFuelData energyFuelData) throws URISyntaxException {
        log.debug("REST request to save EnergyFuelData : {}", energyFuelData);
        if (energyFuelData.getId() != null) {
            throw new BadRequestAlertException("A new energyFuelData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyFuelData result = energyFuelDataRepository.save(energyFuelData);
        return ResponseEntity.created(new URI("/api/energy-fuel-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-fuel-data : Updates an existing energyFuelData.
     *
     * @param energyFuelData the energyFuelData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyFuelData,
     * or with status 400 (Bad Request) if the energyFuelData is not valid,
     * or with status 500 (Internal Server Error) if the energyFuelData couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-fuel-data")
    @Timed
    public ResponseEntity<EnergyFuelData> updateEnergyFuelData(@Valid @RequestBody EnergyFuelData energyFuelData) throws URISyntaxException {
        log.debug("REST request to update EnergyFuelData : {}", energyFuelData);
        if (energyFuelData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyFuelData result = energyFuelDataRepository.save(energyFuelData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyFuelData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-fuel-data : get all the energyFuelData.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of energyFuelData in body
     */
    @GetMapping("/energy-fuel-data")
    @Timed
    public List<EnergyFuelData> getAllEnergyFuelData() {
        log.debug("REST request to get all EnergyFuelData");
        return energyFuelDataRepository.findAll();
    }

    /**
     * GET  /energy-fuel-data/:id : get the "id" energyFuelData.
     *
     * @param id the id of the energyFuelData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyFuelData, or with status 404 (Not Found)
     */
    @GetMapping("/energy-fuel-data/{id}")
    @Timed
    public ResponseEntity<EnergyFuelData> getEnergyFuelData(@PathVariable Long id) {
        log.debug("REST request to get EnergyFuelData : {}", id);
        Optional<EnergyFuelData> energyFuelData = energyFuelDataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(energyFuelData);
    }

    /**
     * DELETE  /energy-fuel-data/:id : delete the "id" energyFuelData.
     *
     * @param id the id of the energyFuelData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-fuel-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyFuelData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyFuelData : {}", id);

        energyFuelDataRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
