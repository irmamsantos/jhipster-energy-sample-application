package io.github.jhipster.energy.repository;

import io.github.jhipster.energy.domain.UserSAC;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UserSAC entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserSACRepository extends JpaRepository<UserSAC, Long> {

}
