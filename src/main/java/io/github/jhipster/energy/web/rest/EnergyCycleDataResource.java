package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.domain.EnergyCycleData;
import io.github.jhipster.energy.repository.EnergyCycleDataRepository;
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
 * REST controller for managing EnergyCycleData.
 */
@RestController
@RequestMapping("/api")
public class EnergyCycleDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyCycleDataResource.class);

    private static final String ENTITY_NAME = "energyCycleData";

    private final EnergyCycleDataRepository energyCycleDataRepository;

    public EnergyCycleDataResource(EnergyCycleDataRepository energyCycleDataRepository) {
        this.energyCycleDataRepository = energyCycleDataRepository;
    }

    /**
     * POST  /energy-cycle-data : Create a new energyCycleData.
     *
     * @param energyCycleData the energyCycleData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyCycleData, or with status 400 (Bad Request) if the energyCycleData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-cycle-data")
    @Timed
    public ResponseEntity<EnergyCycleData> createEnergyCycleData(@Valid @RequestBody EnergyCycleData energyCycleData) throws URISyntaxException {
        log.debug("REST request to save EnergyCycleData : {}", energyCycleData);
        if (energyCycleData.getId() != null) {
            throw new BadRequestAlertException("A new energyCycleData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyCycleData result = energyCycleDataRepository.save(energyCycleData);
        return ResponseEntity.created(new URI("/api/energy-cycle-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-cycle-data : Updates an existing energyCycleData.
     *
     * @param energyCycleData the energyCycleData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyCycleData,
     * or with status 400 (Bad Request) if the energyCycleData is not valid,
     * or with status 500 (Internal Server Error) if the energyCycleData couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-cycle-data")
    @Timed
    public ResponseEntity<EnergyCycleData> updateEnergyCycleData(@Valid @RequestBody EnergyCycleData energyCycleData) throws URISyntaxException {
        log.debug("REST request to update EnergyCycleData : {}", energyCycleData);
        if (energyCycleData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyCycleData result = energyCycleDataRepository.save(energyCycleData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyCycleData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-cycle-data : get all the energyCycleData.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of energyCycleData in body
     */
    @GetMapping("/energy-cycle-data")
    @Timed
    public List<EnergyCycleData> getAllEnergyCycleData() {
        log.debug("REST request to get all EnergyCycleData");
        return energyCycleDataRepository.findAll();
    }

    /**
     * GET  /energy-cycle-data/:id : get the "id" energyCycleData.
     *
     * @param id the id of the energyCycleData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyCycleData, or with status 404 (Not Found)
     */
    @GetMapping("/energy-cycle-data/{id}")
    @Timed
    public ResponseEntity<EnergyCycleData> getEnergyCycleData(@PathVariable Long id) {
        log.debug("REST request to get EnergyCycleData : {}", id);
        Optional<EnergyCycleData> energyCycleData = energyCycleDataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(energyCycleData);
    }

    /**
     * DELETE  /energy-cycle-data/:id : delete the "id" energyCycleData.
     *
     * @param id the id of the energyCycleData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-cycle-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyCycleData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyCycleData : {}", id);

        energyCycleDataRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
