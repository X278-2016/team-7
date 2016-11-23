package org.jhipster.com.repository.search;

import org.jhipster.com.domain.Equipmentset;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Equipmentset entity.
 */
public interface EquipmentsetSearchRepository extends ElasticsearchRepository<Equipmentset, Long> {
}
