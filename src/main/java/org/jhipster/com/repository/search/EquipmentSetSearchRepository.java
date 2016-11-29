package org.jhipster.com.repository.search;

import org.jhipster.com.domain.EquipmentSet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the EquipmentSet entity.
 */
public interface EquipmentSetSearchRepository extends ElasticsearchRepository<EquipmentSet, Long> {
}
