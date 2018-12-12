package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.domain.EnergyVehicleBrandData;
import io.github.jhipster.energy.repository.EnergyVehicleBrandDataRepository;
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
 * REST controller for managing EnergyVehicleBrandData.
 */
@RestController
@RequestMapping("/api")
public class EnergyVehicleBrandDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyVehicleBrandDataResource.class);

    private static final String ENTITY_NAME = "energyVehicleBrandData";

    private final EnergyVehicleBrandDataRepository energyVehicleBrandDataRepository;

    public EnergyVehicleBrandDataResource(EnergyVehicleBrandDataRepository energyVehicleBrandDataRepository) {
        this.energyVehicleBrandDataRepository = energyVehicleBrandDataRepository;
    }

    /**
     * POST  /energy-vehicle-brand-data : Create a new energyVehicleBrandData.
     *
     * @param energyVehicleBrandData the energyVehicleBrandData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyVehicleBrandData, or with status 400 (Bad Request) if the energyVehicleBrandData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-vehicle-brand-data")
    @Timed
    public ResponseEntity<EnergyVehicleBrandData> createEnergyVehicleBrandData(@Valid @RequestBody EnergyVehicleBrandData energyVehicleBrandData) throws URISyntaxException {
        log.debug("REST request to save EnergyVehicleBrandData : {}", energyVehicleBrandData);
        if (energyVehicleBrandData.getId() != null) {
            throw new BadRequestAlertException("A new energyVehicleBrandData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyVehicleBrandData result = energyVehicleBrandDataRepository.save(energyVehicleBrandData);
        return ResponseEntity.created(new URI("/api/energy-vehicle-brand-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-vehicle-brand-data : Updates an existing energyVehicleBrandData.
     *
     * @param energyVehicleBrandData the energyVehicleBrandData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyVehicleBrandData,
     * or with status 400 (Bad Request) if the energyVehicleBrandData is not valid,
     * or with status 500 (Internal Server Error) if the energyVehicleBrandData couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-vehicle-brand-data")
    @Timed
    public ResponseEntity<EnergyVehicleBrandData> updateEnergyVehicleBrandData(@Valid @RequestBody EnergyVehicleBrandData energyVehicleBrandData) throws URISyntaxException {
        log.debug("REST request to update EnergyVehicleBrandData : {}", energyVehicleBrandData);
        if (energyVehicleBrandData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyVehicleBrandData result = energyVehicleBrandDataRepository.save(energyVehicleBrandData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyVehicleBrandData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-vehicle-brand-data : get all the energyVehicleBrandData.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of energyVehicleBrandData in body
     */
    @GetMapping("/energy-vehicle-brand-data")
    @Timed
    public List<EnergyVehicleBrandData> getAllEnergyVehicleBrandData() {
        log.debug("REST request to get all EnergyVehicleBrandData");
        return energyVehicleBrandDataRepository.findAll();
    }

    /**
     * GET  /energy-vehicle-brand-data/:id : get the "id" energyVehicleBrandData.
     *
     * @param id the id of the energyVehicleBrandData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyVehicleBrandData, or with status 404 (Not Found)
     */
    @GetMapping("/energy-vehicle-brand-data/{id}")
    @Timed
    public ResponseEntity<EnergyVehicleBrandData> getEnergyVehicleBrandData(@PathVariable Long id) {
        log.debug("REST request to get EnergyVehicleBrandData : {}", id);
        Optional<EnergyVehicleBrandData> energyVehicleBrandData = energyVehicleBrandDataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(energyVehicleBrandData);
    }

    /**
     * DELETE  /energy-vehicle-brand-data/:id : delete the "id" energyVehicleBrandData.
     *
     * @param id the id of the energyVehicleBrandData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-vehicle-brand-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyVehicleBrandData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyVehicleBrandData : {}", id);

        energyVehicleBrandDataRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
