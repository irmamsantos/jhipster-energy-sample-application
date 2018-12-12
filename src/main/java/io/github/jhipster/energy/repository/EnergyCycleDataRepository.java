package io.github.jhipster.energy.repository;

import io.github.jhipster.energy.domain.EnergyCycleData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnergyCycleData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnergyCycleDataRepository extends JpaRepository<EnergyCycleData, Long> {

}
