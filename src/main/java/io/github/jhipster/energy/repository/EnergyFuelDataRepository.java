package io.github.jhipster.energy.repository;

import io.github.jhipster.energy.domain.EnergyFuelData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnergyFuelData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnergyFuelDataRepository extends JpaRepository<EnergyFuelData, Long> {

}
