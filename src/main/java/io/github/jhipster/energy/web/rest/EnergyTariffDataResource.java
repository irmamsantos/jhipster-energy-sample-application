package io.github.jhipster.energy.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.energy.domain.EnergyTariffData;
import io.github.jhipster.energy.repository.EnergyTariffDataRepository;
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
 * REST controller for managing EnergyTariffData.
 */
@RestController
@RequestMapping("/api")
public class EnergyTariffDataResource {

    private final Logger log = LoggerFactory.getLogger(EnergyTariffDataResource.class);

    private static final String ENTITY_NAME = "energyTariffData";

    private final EnergyTariffDataRepository energyTariffDataRepository;

    public EnergyTariffDataResource(EnergyTariffDataRepository energyTariffDataRepository) {
        this.energyTariffDataRepository = energyTariffDataRepository;
    }

    /**
     * POST  /energy-tariff-data : Create a new energyTariffData.
     *
     * @param energyTariffData the energyTariffData to create
     * @return the ResponseEntity with status 201 (Created) and with body the new energyTariffData, or with status 400 (Bad Request) if the energyTariffData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/energy-tariff-data")
    @Timed
    public ResponseEntity<EnergyTariffData> createEnergyTariffData(@Valid @RequestBody EnergyTariffData energyTariffData) throws URISyntaxException {
        log.debug("REST request to save EnergyTariffData : {}", energyTariffData);
        if (energyTariffData.getId() != null) {
            throw new BadRequestAlertException("A new energyTariffData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnergyTariffData result = energyTariffDataRepository.save(energyTariffData);
        return ResponseEntity.created(new URI("/api/energy-tariff-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /energy-tariff-data : Updates an existing energyTariffData.
     *
     * @param energyTariffData the energyTariffData to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated energyTariffData,
     * or with status 400 (Bad Request) if the energyTariffData is not valid,
     * or with status 500 (Internal Server Error) if the energyTariffData couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/energy-tariff-data")
    @Timed
    public ResponseEntity<EnergyTariffData> updateEnergyTariffData(@Valid @RequestBody EnergyTariffData energyTariffData) throws URISyntaxException {
        log.debug("REST request to update EnergyTariffData : {}", energyTariffData);
        if (energyTariffData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnergyTariffData result = energyTariffDataRepository.save(energyTariffData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, energyTariffData.getId().toString()))
            .body(result);
    }

    /**
     * GET  /energy-tariff-data : get all the energyTariffData.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of energyTariffData in body
     */
    @GetMapping("/energy-tariff-data")
    @Timed
    public List<EnergyTariffData> getAllEnergyTariffData() {
        log.debug("REST request to get all EnergyTariffData");
        return energyTariffDataRepository.findAll();
    }

    /**
     * GET  /energy-tariff-data/:id : get the "id" energyTariffData.
     *
     * @param id the id of the energyTariffData to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the energyTariffData, or with status 404 (Not Found)
     */
    @GetMapping("/energy-tariff-data/{id}")
    @Timed
    public ResponseEntity<EnergyTariffData> getEnergyTariffData(@PathVariable Long id) {
        log.debug("REST request to get EnergyTariffData : {}", id);
        Optional<EnergyTariffData> energyTariffData = energyTariffDataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(energyTariffData);
    }

    /**
     * DELETE  /energy-tariff-data/:id : delete the "id" energyTariffData.
     *
     * @param id the id of the energyTariffData to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/energy-tariff-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEnergyTariffData(@PathVariable Long id) {
        log.debug("REST request to delete EnergyTariffData : {}", id);

        energyTariffDataRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
