package org.jhipster.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.jhipster.com.domain.EquipmentSet;

import org.jhipster.com.repository.EquipmentSetRepository;
import org.jhipster.com.repository.search.EquipmentSetSearchRepository;
import org.jhipster.com.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
 * REST controller for managing EquipmentSet.
 */
@RestController
@RequestMapping("/api")
public class EquipmentSetResource {

    private final Logger log = LoggerFactory.getLogger(EquipmentSetResource.class);
        
    @Inject
    private EquipmentSetRepository equipmentSetRepository;

    @Inject
    private EquipmentSetSearchRepository equipmentSetSearchRepository;

    /**
     * POST  /equipment-sets : Create a new equipmentSet.
     *
     * @param equipmentSet the equipmentSet to create
     * @return the ResponseEntity with status 201 (Created) and with body the new equipmentSet, or with status 400 (Bad Request) if the equipmentSet has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/equipment-sets")
    @Timed
    public ResponseEntity<EquipmentSet> createEquipmentSet(@Valid @RequestBody EquipmentSet equipmentSet) throws URISyntaxException {
        log.debug("REST request to save EquipmentSet : {}", equipmentSet);
        if (equipmentSet.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("equipmentSet", "idexists", "A new equipmentSet cannot already have an ID")).body(null);
        }
        EquipmentSet result = equipmentSetRepository.save(equipmentSet);
        equipmentSetSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/equipment-sets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("equipmentSet", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /equipment-sets : Updates an existing equipmentSet.
     *
     * @param equipmentSet the equipmentSet to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated equipmentSet,
     * or with status 400 (Bad Request) if the equipmentSet is not valid,
     * or with status 500 (Internal Server Error) if the equipmentSet couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/equipment-sets")
    @Timed
    public ResponseEntity<EquipmentSet> updateEquipmentSet(@Valid @RequestBody EquipmentSet equipmentSet) throws URISyntaxException {
        log.debug("REST request to update EquipmentSet : {}", equipmentSet);
        if (equipmentSet.getId() == null) {
            return createEquipmentSet(equipmentSet);
        }
        EquipmentSet result = equipmentSetRepository.save(equipmentSet);
        equipmentSetSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("equipmentSet", equipmentSet.getId().toString()))
            .body(result);
    }

    /**
     * GET  /equipment-sets : get all the equipmentSets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of equipmentSets in body
     */
    @GetMapping("/equipment-sets")
    @Timed
    public List<EquipmentSet> getAllEquipmentSets() {
        log.debug("REST request to get all EquipmentSets");
        List<EquipmentSet> equipmentSets = equipmentSetRepository.findAll();
        return equipmentSets;
    }

    /**
     * GET  /equipment-sets/:id : get the "id" equipmentSet.
     *
     * @param id the id of the equipmentSet to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the equipmentSet, or with status 404 (Not Found)
     */
    @GetMapping("/equipment-sets/{id}")
    @Timed
    public ResponseEntity<EquipmentSet> getEquipmentSet(@PathVariable Long id) {
        log.debug("REST request to get EquipmentSet : {}", id);
        EquipmentSet equipmentSet = equipmentSetRepository.findOne(id);
        return Optional.ofNullable(equipmentSet)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /equipment-sets/:id : delete the "id" equipmentSet.
     *
     * @param id the id of the equipmentSet to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/equipment-sets/{id}")
    @Timed
    public ResponseEntity<Void> deleteEquipmentSet(@PathVariable Long id) {
        log.debug("REST request to delete EquipmentSet : {}", id);
        equipmentSetRepository.delete(id);
        equipmentSetSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("equipmentSet", id.toString())).build();
    }

    /**
     * SEARCH  /_search/equipment-sets?query=:query : search for the equipmentSet corresponding
     * to the query.
     *
     * @param query the query of the equipmentSet search 
     * @return the result of the search
     */
    @GetMapping("/_search/equipment-sets")
    @Timed
    public List<EquipmentSet> searchEquipmentSets(@RequestParam String query) {
        log.debug("REST request to search EquipmentSets for query {}", query);
        return StreamSupport
            .stream(equipmentSetSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }


}
