package io.github.jhipster.energy.repository;

import io.github.jhipster.energy.domain.EnergyVehicleModelData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnergyVehicleModelData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnergyVehicleModelDataRepository extends JpaRepository<EnergyVehicleModelData, Long> {

}
