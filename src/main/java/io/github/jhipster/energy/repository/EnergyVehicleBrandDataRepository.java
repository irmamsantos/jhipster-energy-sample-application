package io.github.jhipster.energy.repository;

import io.github.jhipster.energy.domain.EnergyVehicleBrandData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnergyVehicleBrandData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnergyVehicleBrandDataRepository extends JpaRepository<EnergyVehicleBrandData, Long> {

}
