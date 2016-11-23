package org.jhipster.com.repository.search;

import org.jhipster.com.domain.Padset;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Padset entity.
 */
public interface PadsetSearchRepository extends ElasticsearchRepository<Padset, Long> {
}
