package io.github.jhipster.energy.repository;

import io.github.jhipster.energy.domain.EnergyNeedNGRequest;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnergyNeedNGRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnergyNeedNGRequestRepository extends JpaRepository<EnergyNeedNGRequest, Long> {

}
