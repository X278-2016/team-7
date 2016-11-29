package org.jhipster.com.service;

import com.codahale.metrics.annotation.Timed;
import org.jhipster.com.domain.*;
import org.jhipster.com.repository.*;
import org.jhipster.com.repository.search.*;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.lang.reflect.Method;
import java.util.List;

@Service
public class ElasticsearchIndexService {

    private final Logger log = LoggerFactory.getLogger(ElasticsearchIndexService.class);

    @Inject
    private EquipmentSetRepository equipmentSetRepository;

    @Inject
    private EquipmentSetSearchRepository equipmentSetSearchRepository;

    @Inject
    private MetricRepository metricRepository;

    @Inject
    private MetricSearchRepository metricSearchRepository;

    @Inject
    private PadSetRepository padSetRepository;

    @Inject
    private PadSetSearchRepository padSetSearchRepository;

    @Inject
    private ThresholdRepository thresholdRepository;

    @Inject
    private ThresholdSearchRepository thresholdSearchRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserSearchRepository userSearchRepository;

    @Inject
    private ElasticsearchTemplate elasticsearchTemplate;

    @Async
    @Timed
    public void reindexAll() {
        reindexForClass(EquipmentSet.class, equipmentSetRepository, equipmentSetSearchRepository);
        reindexForClass(Metric.class, metricRepository, metricSearchRepository);
        reindexForClass(PadSet.class, padSetRepository, padSetSearchRepository);
        reindexForClass(Threshold.class, thresholdRepository, thresholdSearchRepository);
        reindexForClass(User.class, userRepository, userSearchRepository);

        log.info("Elasticsearch: Successfully performed reindexing");
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    private <T> void reindexForClass(Class<T> entityClass, JpaRepository<T, Long> jpaRepository,
                                                          ElasticsearchRepository<T, Long> elasticsearchRepository) {
        elasticsearchTemplate.deleteIndex(entityClass);
        try {
            elasticsearchTemplate.createIndex(entityClass);
        } catch (IndexAlreadyExistsException e) {
            // Do nothing. Index was already concurrently recreated by some other service.
        }
        elasticsearchTemplate.putMapping(entityClass);
        if (jpaRepository.count() > 0) {
            try {
                Method m = jpaRepository.getClass().getMethod("findAllWithEagerRelationships");
                elasticsearchRepository.save((List<T>) m.invoke(jpaRepository));
            } catch (Exception e) {
                elasticsearchRepository.save(jpaRepository.findAll());
            }
        }
        log.info("Elasticsearch: Indexed all rows for " + entityClass.getSimpleName());
    }
}
