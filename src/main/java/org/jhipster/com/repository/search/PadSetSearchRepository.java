package org.jhipster.com.repository.search;

import org.jhipster.com.domain.PadSet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the PadSet entity.
 */
public interface PadSetSearchRepository extends ElasticsearchRepository<PadSet, Long> {
}
