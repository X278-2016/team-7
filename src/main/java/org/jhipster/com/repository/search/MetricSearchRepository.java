package org.jhipster.com.repository.search;

import org.jhipster.com.domain.Metric;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Metric entity.
 */
public interface MetricSearchRepository extends ElasticsearchRepository<Metric, Long> {
}
