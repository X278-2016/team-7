package org.jhipster.com.repository;

import org.jhipster.com.domain.Metric;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Metric entity.
 */
@SuppressWarnings("unused")
public interface MetricRepository extends JpaRepository<Metric,Long> {

}
