package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.domain.EnergyContractedPowerRatingData;
import io.github.jhipster.energy.repository.EnergyContractedPowerRatingDataRepository;
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
 * REST controller for managing EnergyContractedPowerRatingData.
 */
@RestController
@RequestMapping("/api")
public class EnergyContractedPowerRatingDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyContractedPowerRatingDataResource.class);

    private static final String ENTITY_NAME = "energyContractedPowerRatingData";

    private final EnergyContractedPowerRatingDataRepository energyContractedPowerRatingDataRepository;

    public EnergyContractedPowerRatingDataResource(EnergyContractedPowerRatingDataRepository energyContractedPowerRatingDataRepository) {
        this.energyContractedPowerRatingDataRepository = energyContractedPowerRatingDataRepository;
    }

    /**
     * POST  /energy-contracted-power-rating-data : Create a new energyContractedPowerRatingData.
     *
     * @param energyContractedPowerRatingData the energyContractedPowerRatingData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyContractedPowerRatingData, or with status 400 (Bad Request) if the energyContractedPowerRatingData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-contracted-power-rating-data")
    @Timed
    public ResponseEntity<EnergyContractedPowerRatingData> createEnergyContractedPowerRatingData(@Valid @RequestBody EnergyContractedPowerRatingData energyContractedPowerRatingData) throws URISyntaxException {
        log.debug("REST request to save EnergyContractedPowerRatingData : {}", energyContractedPowerRatingData);
        if (energyContractedPowerRatingData.getId() != null) {
            throw new BadRequestAlertException("A new energyContractedPowerRatingData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyContractedPowerRatingData result = energyContractedPowerRatingDataRepository.save(energyContractedPowerRatingData);
        return ResponseEntity.created(new URI("/api/energy-contracted-power-rating-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-contracted-power-rating-data : Updates an existing energyContractedPowerRatingData.
     *
     * @param energyContractedPowerRatingData the energyContractedPowerRatingData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyContractedPowerRatingData,
     * or with status 400 (Bad Request) if the energyContractedPowerRatingData is not valid,
     * or with status 500 (Internal Server Error) if the energyContractedPowerRatingData couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-contracted-power-rating-data")
    @Timed
    public ResponseEntity<EnergyContractedPowerRatingData> updateEnergyContractedPowerRatingData(@Valid @RequestBody EnergyContractedPowerRatingData energyContractedPowerRatingData) throws URISyntaxException {
        log.debug("REST request to update EnergyContractedPowerRatingData : {}", energyContractedPowerRatingData);
        if (energyContractedPowerRatingData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyContractedPowerRatingData result = energyContractedPowerRatingDataRepository.save(energyContractedPowerRatingData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyContractedPowerRatingData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-contracted-power-rating-data : get all the energyContractedPowerRatingData.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of energyContractedPowerRatingData in body
     */
    @GetMapping("/energy-contracted-power-rating-data")
    @Timed
    public List<EnergyContractedPowerRatingData> getAllEnergyContractedPowerRatingData() {
        log.debug("REST request to get all EnergyContractedPowerRatingData");
        return energyContractedPowerRatingDataRepository.findAll();
    }

    /**
     * GET  /energy-contracted-power-rating-data/:id : get the "id" energyContractedPowerRatingData.
     *
     * @param id the id of the energyContractedPowerRatingData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyContractedPowerRatingData, or with status 404 (Not Found)
     */
    @GetMapping("/energy-contracted-power-rating-data/{id}")
    @Timed
    public ResponseEntity<EnergyContractedPowerRatingData> getEnergyContractedPowerRatingData(@PathVariable Long id) {
        log.debug("REST request to get EnergyContractedPowerRatingData : {}", id);
        Optional<EnergyContractedPowerRatingData> energyContractedPowerRatingData = energyContractedPowerRatingDataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(energyContractedPowerRatingData);
    }

    /**
     * DELETE  /energy-contracted-power-rating-data/:id : delete the "id" energyContractedPowerRatingData.
     *
     * @param id the id of the energyContractedPowerRatingData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-contracted-power-rating-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyContractedPowerRatingData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyContractedPowerRatingData : {}", id);

        energyContractedPowerRatingDataRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
