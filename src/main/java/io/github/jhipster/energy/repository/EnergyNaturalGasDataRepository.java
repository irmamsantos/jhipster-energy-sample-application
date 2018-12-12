package io.github.jhipster.energy.repository;

import io.github.jhipster.energy.domain.EnergyNaturalGasData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnergyNaturalGasData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnergyNaturalGasDataRepository extends JpaRepository<EnergyNaturalGasData, Long> {

}
