package io.github.jhipster.energy.repository;

import io.github.jhipster.energy.domain.EnergyNRStateHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnergyNRStateHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnergyNRStateHistoryRepository extends JpaRepository<EnergyNRStateHistory, Long> {

}
