package io.github.jhipster.energy.repository;

import io.github.jhipster.energy.domain.EnergyTariffData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnergyTariffData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnergyTariffDataRepository extends JpaRepository<EnergyTariffData, Long> {

}
