package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.domain.EnergyNaturalGasData;
import io.github.jhipster.energy.repository.EnergyNaturalGasDataRepository;
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
 * REST controller for managing EnergyNaturalGasData.
 */
@RestController
@RequestMapping("/api")
public class EnergyNaturalGasDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyNaturalGasDataResource.class);

    private static final String ENTITY_NAME = "energyNaturalGasData";

    private final EnergyNaturalGasDataRepository energyNaturalGasDataRepository;

    public EnergyNaturalGasDataResource(EnergyNaturalGasDataRepository energyNaturalGasDataRepository) {
        this.energyNaturalGasDataRepository = energyNaturalGasDataRepository;
    }

    /**
     * POST  /energy-natural-gas-data : Create a new energyNaturalGasData.
     *
     * @param energyNaturalGasData the energyNaturalGasData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyNaturalGasData, or with status 400 (Bad Request) if the energyNaturalGasData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-natural-gas-data")
    @Timed
    public ResponseEntity<EnergyNaturalGasData> createEnergyNaturalGasData(@Valid @RequestBody EnergyNaturalGasData energyNaturalGasData) throws URISyntaxException {
        log.debug("REST request to save EnergyNaturalGasData : {}", energyNaturalGasData);
        if (energyNaturalGasData.getId() != null) {
            throw new BadRequestAlertException("A new energyNaturalGasData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyNaturalGasData result = energyNaturalGasDataRepository.save(energyNaturalGasData);
        return ResponseEntity.created(new URI("/api/energy-natural-gas-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-natural-gas-data : Updates an existing energyNaturalGasData.
     *
     * @param energyNaturalGasData the energyNaturalGasData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyNaturalGasData,
     * or with status 400 (Bad Request) if the energyNaturalGasData is not valid,
     * or with status 500 (Internal Server Error) if the energyNaturalGasData couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-natural-gas-data")
    @Timed
    public ResponseEntity<EnergyNaturalGasData> updateEnergyNaturalGasData(@Valid @RequestBody EnergyNaturalGasData energyNaturalGasData) throws URISyntaxException {
        log.debug("REST request to update EnergyNaturalGasData : {}", energyNaturalGasData);
        if (energyNaturalGasData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyNaturalGasData result = energyNaturalGasDataRepository.save(energyNaturalGasData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyNaturalGasData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-natural-gas-data : get all the energyNaturalGasData.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of energyNaturalGasData in body
     */
    @GetMapping("/energy-natural-gas-data")
    @Timed
    public List<EnergyNaturalGasData> getAllEnergyNaturalGasData() {
        log.debug("REST request to get all EnergyNaturalGasData");
        return energyNaturalGasDataRepository.findAll();
    }

    /**
     * GET  /energy-natural-gas-data/:id : get the "id" energyNaturalGasData.
     *
     * @param id the id of the energyNaturalGasData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyNaturalGasData, or with status 404 (Not Found)
     */
    @GetMapping("/energy-natural-gas-data/{id}")
    @Timed
    public ResponseEntity<EnergyNaturalGasData> getEnergyNaturalGasData(@PathVariable Long id) {
        log.debug("REST request to get EnergyNaturalGasData : {}", id);
        Optional<EnergyNaturalGasData> energyNaturalGasData = energyNaturalGasDataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(energyNaturalGasData);
    }

    /**
     * DELETE  /energy-natural-gas-data/:id : delete the "id" energyNaturalGasData.
     *
     * @param id the id of the energyNaturalGasData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-natural-gas-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyNaturalGasData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyNaturalGasData : {}", id);

        energyNaturalGasDataRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
