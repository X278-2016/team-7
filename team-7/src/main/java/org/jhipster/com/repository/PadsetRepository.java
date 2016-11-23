package org.jhipster.com.repository;

import org.jhipster.com.domain.Padset;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Padset entity.
 */
@SuppressWarnings("unused")
public interface PadsetRepository extends JpaRepository<Padset,Long> {

}
