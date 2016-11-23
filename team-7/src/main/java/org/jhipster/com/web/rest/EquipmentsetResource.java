package org.jhipster.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.jhipster.com.domain.Equipmentset;

import org.jhipster.com.repository.EquipmentsetRepository;
import org.jhipster.com.repository.search.EquipmentsetSearchRepository;
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
 * REST controller for managing Equipmentset.
 */
@RestController
@RequestMapping("/api")
public class EquipmentsetResource {

    private final Logger log = LoggerFactory.getLogger(EquipmentsetResource.class);
        
    @Inject
    private EquipmentsetRepository equipmentsetRepository;

    @Inject
    private EquipmentsetSearchRepository equipmentsetSearchRepository;

    /**
     * POST  /equipmentsets : Create a new equipmentset.
     *
     * @param equipmentset the equipmentset to create
     * @return the ResponseEntity with status 201 (Created) and with body the new equipmentset, or with status 400 (Bad Request) if the equipmentset has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/equipmentsets",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Equipmentset> createEquipmentset(@Valid @RequestBody Equipmentset equipmentset) throws URISyntaxException {
        log.debug("REST request to save Equipmentset : {}", equipmentset);
        if (equipmentset.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("equipmentset", "idexists", "A new equipmentset cannot already have an ID")).body(null);
        }
        Equipmentset result = equipmentsetRepository.save(equipmentset);
        equipmentsetSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/equipmentsets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("equipmentset", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /equipmentsets : Updates an existing equipmentset.
     *
     * @param equipmentset the equipmentset to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated equipmentset,
     * or with status 400 (Bad Request) if the equipmentset is not valid,
     * or with status 500 (Internal Server Error) if the equipmentset couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/equipmentsets",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Equipmentset> updateEquipmentset(@Valid @RequestBody Equipmentset equipmentset) throws URISyntaxException {
        log.debug("REST request to update Equipmentset : {}", equipmentset);
        if (equipmentset.getId() == null) {
            return createEquipmentset(equipmentset);
        }
        Equipmentset result = equipmentsetRepository.save(equipmentset);
        equipmentsetSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("equipmentset", equipmentset.getId().toString()))
            .body(result);
    }

    /**
     * GET  /equipmentsets : get all the equipmentsets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of equipmentsets in body
     */
    @RequestMapping(value = "/equipmentsets",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Equipmentset> getAllEquipmentsets() {
        log.debug("REST request to get all Equipmentsets");
        List<Equipmentset> equipmentsets = equipmentsetRepository.findAll();
        return equipmentsets;
    }

    /**
     * GET  /equipmentsets/:id : get the "id" equipmentset.
     *
     * @param id the id of the equipmentset to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the equipmentset, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/equipmentsets/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Equipmentset> getEquipmentset(@PathVariable Long id) {
        log.debug("REST request to get Equipmentset : {}", id);
        Equipmentset equipmentset = equipmentsetRepository.findOne(id);
        return Optional.ofNullable(equipmentset)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /equipmentsets/:id : delete the "id" equipmentset.
     *
     * @param id the id of the equipmentset to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/equipmentsets/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteEquipmentset(@PathVariable Long id) {
        log.debug("REST request to delete Equipmentset : {}", id);
        equipmentsetRepository.delete(id);
        equipmentsetSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("equipmentset", id.toString())).build();
    }

    /**
     * SEARCH  /_search/equipmentsets?query=:query : search for the equipmentset corresponding
     * to the query.
     *
     * @param query the query of the equipmentset search 
     * @return the result of the search
     */
    @RequestMapping(value = "/_search/equipmentsets",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Equipmentset> searchEquipmentsets(@RequestParam String query) {
        log.debug("REST request to search Equipmentsets for query {}", query);
        return StreamSupport
            .stream(equipmentsetSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }


}
