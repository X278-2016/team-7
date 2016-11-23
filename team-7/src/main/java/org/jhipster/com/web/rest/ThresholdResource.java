package org.jhipster.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.jhipster.com.domain.Threshold;

import org.jhipster.com.repository.ThresholdRepository;
import org.jhipster.com.repository.search.ThresholdSearchRepository;
import org.jhipster.com.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Threshold.
 */
@RestController
@RequestMapping("/api")
public class ThresholdResource {

    private final Logger log = LoggerFactory.getLogger(ThresholdResource.class);
        
    @Inject
    private ThresholdRepository thresholdRepository;

    @Inject
    private ThresholdSearchRepository thresholdSearchRepository;

    /**
     * POST  /thresholds : Create a new threshold.
     *
     * @param threshold the threshold to create
     * @return the ResponseEntity with status 201 (Created) and with body the new threshold, or with status 400 (Bad Request) if the threshold has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/thresholds",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Threshold> createThreshold(@Valid @RequestBody Threshold threshold) throws URISyntaxException {
        log.debug("REST request to save Threshold : {}", threshold);
        if (threshold.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("threshold", "idexists", "A new threshold cannot already have an ID")).body(null);
        }
        Threshold result = thresholdRepository.save(threshold);
        thresholdSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/thresholds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("threshold", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /thresholds : Updates an existing threshold.
     *
     * @param threshold the threshold to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated threshold,
     * or with status 400 (Bad Request) if the threshold is not valid,
     * or with status 500 (Internal Server Error) if the threshold couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/thresholds",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Threshold> updateThreshold(@Valid @RequestBody Threshold threshold) throws URISyntaxException {
        log.debug("REST request to update Threshold : {}", threshold);
        if (threshold.getId() == null) {
            return createThreshold(threshold);
        }
        Threshold result = thresholdRepository.save(threshold);
        thresholdSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("threshold", threshold.getId().toString()))
            .body(result);
    }

    /**
     * GET  /thresholds : get all the thresholds.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of thresholds in body
     */
    @RequestMapping(value = "/thresholds",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Threshold> getAllThresholds() {
        log.debug("REST request to get all Thresholds");
        List<Threshold> thresholds = thresholdRepository.findAll();
        return thresholds;
    }

    /**
     * GET  /thresholds/:id : get the "id" threshold.
     *
     * @param id the id of the threshold to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the threshold, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/thresholds/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Threshold> getThreshold(@PathVariable Long id) {
        log.debug("REST request to get Threshold : {}", id);
        Threshold threshold = thresholdRepository.findOne(id);
        return Optional.ofNullable(threshold)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /thresholds/:id : delete the "id" threshold.
     *
     * @param id the id of the threshold to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/thresholds/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteThreshold(@PathVariable Long id) {
        log.debug("REST request to delete Threshold : {}", id);
        thresholdRepository.delete(id);
        thresholdSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("threshold", id.toString())).build();
    }

    /**
     * SEARCH  /_search/thresholds?query=:query : search for the threshold corresponding
     * to the query.
     *
     * @param query the query of the threshold search 
     * @return the result of the search
     */
    @RequestMapping(value = "/_search/thresholds",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Threshold> searchThresholds(@RequestParam String query) {
        log.debug("REST request to search Thresholds for query {}", query);
        return StreamSupport
            .stream(thresholdSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }


}
