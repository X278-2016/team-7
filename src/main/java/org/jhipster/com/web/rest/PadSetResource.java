package org.jhipster.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.jhipster.com.domain.PadSet;

import org.jhipster.com.repository.PadSetRepository;
import org.jhipster.com.repository.search.PadSetSearchRepository;
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
 * REST controller for managing PadSet.
 */
@RestController
@RequestMapping("/api")
public class PadSetResource {

    private final Logger log = LoggerFactory.getLogger(PadSetResource.class);
        
    @Inject
    private PadSetRepository padSetRepository;

    @Inject
    private PadSetSearchRepository padSetSearchRepository;

    /**
     * POST  /pad-sets : Create a new padSet.
     *
     * @param padSet the padSet to create
     * @return the ResponseEntity with status 201 (Created) and with body the new padSet, or with status 400 (Bad Request) if the padSet has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/pad-sets",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PadSet> createPadSet(@Valid @RequestBody PadSet padSet) throws URISyntaxException {
        log.debug("REST request to save PadSet : {}", padSet);
        if (padSet.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("padSet", "idexists", "A new padSet cannot already have an ID")).body(null);
        }
        PadSet result = padSetRepository.save(padSet);
        padSetSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/pad-sets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("padSet", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pad-sets : Updates an existing padSet.
     *
     * @param padSet the padSet to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated padSet,
     * or with status 400 (Bad Request) if the padSet is not valid,
     * or with status 500 (Internal Server Error) if the padSet couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/pad-sets",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PadSet> updatePadSet(@Valid @RequestBody PadSet padSet) throws URISyntaxException {
        log.debug("REST request to update PadSet : {}", padSet);
        if (padSet.getId() == null) {
            return createPadSet(padSet);
        }
        PadSet result = padSetRepository.save(padSet);
        padSetSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("padSet", padSet.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pad-sets : get all the padSets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of padSets in body
     */
    @RequestMapping(value = "/pad-sets",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<PadSet> getAllPadSets() {
        log.debug("REST request to get all PadSets");
        List<PadSet> padSets = padSetRepository.findAll();
        return padSets;
    }

    /**
     * GET  /pad-sets/:id : get the "id" padSet.
     *
     * @param id the id of the padSet to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the padSet, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/pad-sets/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PadSet> getPadSet(@PathVariable Long id) {
        log.debug("REST request to get PadSet : {}", id);
        PadSet padSet = padSetRepository.findOne(id);
        return Optional.ofNullable(padSet)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /pad-sets/:id : delete the "id" padSet.
     *
     * @param id the id of the padSet to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/pad-sets/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deletePadSet(@PathVariable Long id) {
        log.debug("REST request to delete PadSet : {}", id);
        padSetRepository.delete(id);
        padSetSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("padSet", id.toString())).build();
    }

    /**
     * SEARCH  /_search/pad-sets?query=:query : search for the padSet corresponding
     * to the query.
     *
     * @param query the query of the padSet search 
     * @return the result of the search
     */
    @RequestMapping(value = "/_search/pad-sets",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<PadSet> searchPadSets(@RequestParam String query) {
        log.debug("REST request to search PadSets for query {}", query);
        return StreamSupport
            .stream(padSetSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }


}
