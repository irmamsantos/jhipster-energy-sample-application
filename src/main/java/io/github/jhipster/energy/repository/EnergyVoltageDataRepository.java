package io.github.jhipster.energy.repository;

import io.github.jhipster.energy.domain.EnergyVoltageData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnergyVoltageData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnergyVoltageDataRepository extends JpaRepository<EnergyVoltageData, Long> {

}
