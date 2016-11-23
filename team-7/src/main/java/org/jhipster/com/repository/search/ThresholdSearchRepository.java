package org.jhipster.com.repository.search;

import org.jhipster.com.domain.Threshold;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Threshold entity.
 */
public interface ThresholdSearchRepository extends ElasticsearchRepository<Threshold, Long> {
}
