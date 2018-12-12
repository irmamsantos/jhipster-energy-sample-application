package io.github.jhipster.energy.repository;

import io.github.jhipster.energy.domain.EnergyElectricityData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnergyElectricityData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnergyElectricityDataRepository extends JpaRepository<EnergyElectricityData, Long> {

}
