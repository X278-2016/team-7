package org.jhipster.com.repository;

import org.jhipster.com.domain.Threshold;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Threshold entity.
 */
@SuppressWarnings("unused")
public interface ThresholdRepository extends JpaRepository<Threshold,Long> {

}
