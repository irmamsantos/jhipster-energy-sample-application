package io.github.jhipster.energy.repository;

import io.github.jhipster.energy.domain.EnergyTimePeriodData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnergyTimePeriodData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnergyTimePeriodDataRepository extends JpaRepository<EnergyTimePeriodData, Long> {

}
