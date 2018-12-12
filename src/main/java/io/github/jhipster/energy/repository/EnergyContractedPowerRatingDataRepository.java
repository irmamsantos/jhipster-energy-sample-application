package io.github.jhipster.energy.repository;

import io.github.jhipster.energy.domain.EnergyContractedPowerRatingData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnergyContractedPowerRatingData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnergyContractedPowerRatingDataRepository extends JpaRepository<EnergyContractedPowerRatingData, Long> {

}
