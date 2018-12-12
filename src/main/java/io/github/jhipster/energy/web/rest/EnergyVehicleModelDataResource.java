package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.domain.EnergyVehicleModelData;
import io.github.jhipster.energy.repository.EnergyVehicleModelDataRepository;
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
 * REST controller for managing EnergyVehicleModelData.
 */
@RestController
@RequestMapping("/api")
public class EnergyVehicleModelDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyVehicleModelDataResource.class);

    private static final String ENTITY_NAME = "energyVehicleModelData";

    private final EnergyVehicleModelDataRepository energyVehicleModelDataRepository;

    public EnergyVehicleModelDataResource(EnergyVehicleModelDataRepository energyVehicleModelDataRepository) {
        this.energyVehicleModelDataRepository = energyVehicleModelDataRepository;
    }

    /**
     * POST  /energy-vehicle-model-data : Create a new energyVehicleModelData.
     *
     * @param energyVehicleModelData the energyVehicleModelData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyVehicleModelData, or with status 400 (Bad Request) if the energyVehicleModelData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-vehicle-model-data")
    @Timed
    public ResponseEntity<EnergyVehicleModelData> createEnergyVehicleModelData(@Valid @RequestBody EnergyVehicleModelData energyVehicleModelData) throws URISyntaxException {
        log.debug("REST request to save EnergyVehicleModelData : {}", energyVehicleModelData);
        if (energyVehicleModelData.getId() != null) {
            throw new BadRequestAlertException("A new energyVehicleModelData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyVehicleModelData result = energyVehicleModelDataRepository.save(energyVehicleModelData);
        return ResponseEntity.created(new URI("/api/energy-vehicle-model-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-vehicle-model-data : Updates an existing energyVehicleModelData.
     *
     * @param energyVehicleModelData the energyVehicleModelData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyVehicleModelData,
     * or with status 400 (Bad Request) if the energyVehicleModelData is not valid,
     * or with status 500 (Internal Server Error) if the energyVehicleModelData couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-vehicle-model-data")
    @Timed
    public ResponseEntity<EnergyVehicleModelData> updateEnergyVehicleModelData(@Valid @RequestBody EnergyVehicleModelData energyVehicleModelData) throws URISyntaxException {
        log.debug("REST request to update EnergyVehicleModelData : {}", energyVehicleModelData);
        if (energyVehicleModelData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyVehicleModelData result = energyVehicleModelDataRepository.save(energyVehicleModelData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyVehicleModelData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-vehicle-model-data : get all the energyVehicleModelData.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of energyVehicleModelData in body
     */
    @GetMapping("/energy-vehicle-model-data")
    @Timed
    public List<EnergyVehicleModelData> getAllEnergyVehicleModelData() {
        log.debug("REST request to get all EnergyVehicleModelData");
        return energyVehicleModelDataRepository.findAll();
    }

    /**
     * GET  /energy-vehicle-model-data/:id : get the "id" energyVehicleModelData.
     *
     * @param id the id of the energyVehicleModelData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyVehicleModelData, or with status 404 (Not Found)
     */
    @GetMapping("/energy-vehicle-model-data/{id}")
    @Timed
    public ResponseEntity<EnergyVehicleModelData> getEnergyVehicleModelData(@PathVariable Long id) {
        log.debug("REST request to get EnergyVehicleModelData : {}", id);
        Optional<EnergyVehicleModelData> energyVehicleModelData = energyVehicleModelDataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(energyVehicleModelData);
    }

    /**
     * DELETE  /energy-vehicle-model-data/:id : delete the "id" energyVehicleModelData.
     *
     * @param id the id of the energyVehicleModelData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-vehicle-model-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyVehicleModelData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyVehicleModelData : {}", id);

        energyVehicleModelDataRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
