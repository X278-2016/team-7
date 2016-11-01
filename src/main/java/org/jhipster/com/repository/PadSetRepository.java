package org.jhipster.com.repository;

import org.jhipster.com.domain.PadSet;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PadSet entity.
 */
@SuppressWarnings("unused")
public interface PadSetRepository extends JpaRepository<PadSet,Long> {

}
