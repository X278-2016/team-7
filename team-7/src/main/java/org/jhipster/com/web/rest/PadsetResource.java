package org.jhipster.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.jhipster.com.domain.Padset;

import org.jhipster.com.repository.PadsetRepository;
import org.jhipster.com.repository.search.PadsetSearchRepository;
import org.jhipster.com.web.rest.util.HeaderUtil;
import org.json.JSONObject;
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
import org.jhipster.com.domain.*;
import java.io.File;

/**
 * REST controller for managing Padset.
 */
@RestController
@RequestMapping("/api")
public class PadsetResource {

    private final Logger log = LoggerFactory.getLogger(PadsetResource.class);
        
    @Inject
    private PadsetRepository padsetRepository;

    @Inject
    private PadsetSearchRepository padsetSearchRepository;

    /**
     * POST  /padsets : Create a new padset.
     *
     * @param padset the padset to create
     * @return the ResponseEntity with status 201 (Created) and with body the new padset, or with status 400 (Bad Request) if the padset has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/padsets",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Padset> createPadset(@Valid @RequestBody Padset padset) throws URISyntaxException {
        log.debug("REST request to save Padset : {}", padset);
        if (padset.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("padset", "idexists", "A new padset cannot already have an ID")).body(null);
        }
        Padset result = padsetRepository.save(padset);
        padsetSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/padsets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("padset", result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /padsets : Create a new padset.
     *
     * @param padset the padset to create
     * @return the ResponseEntity with status 201 (Created) and with body the new padset, or with status 400 (Bad Request) if the padset has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/create/newpadset/",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Padset> createPadsetWithJSON(@Valid @RequestBody JSONObject json) throws URISyntaxException {
    	 System.out.println("Creating Padset from URL");
   	     PadsetDeserializer mPadsetDeserializer = new PadsetDeserializer();
   	     Padset padset = mPadsetDeserializer.getPadsetFromJSON(new File(json.toString()));
         return createPadset( padset);
    }

    
    
    
    
    /**
     * PUT  /padsets : Updates an existing padset.
     *
     * @param padset the padset to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated padset,
     * or with status 400 (Bad Request) if the padset is not valid,
     * or with status 500 (Internal Server Error) if the padset couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/padsets",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Padset> updatePadset(@Valid @RequestBody Padset padset) throws URISyntaxException {
        log.debug("REST request to update Padset : {}", padset);
        if (padset.getId() == null) {
            return createPadset(padset);
        }
        Padset result = padsetRepository.save(padset);
        padsetSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("padset", padset.getId().toString()))
            .body(result);
    }

    /**
     * GET  /padsets : get all the padsets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of padsets in body
     */
    @RequestMapping(value = "/padsets",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Padset> getAllPadsets() {
        log.debug("REST request to get all Padsets");
        List<Padset> padsets = padsetRepository.findAll();
        return padsets;
    }

    /**
     * GET  /padsets/:id : get the "id" padset.
     *
     * @param id the id of the padset to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the padset, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/padsets/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Padset> getPadset(@PathVariable Long id) {
        log.debug("REST request to get Padset : {}", id);
        Padset padset = padsetRepository.findOne(id);
        return Optional.ofNullable(padset)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /padsets/:id : delete the "id" padset.
     *
     * @param id the id of the padset to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/padsets/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deletePadset(@PathVariable Long id) {
        log.debug("REST request to delete Padset : {}", id);
        padsetRepository.delete(id);
        padsetSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("padset", id.toString())).build();
    }

    /**
     * SEARCH  /_search/padsets?query=:query : search for the padset corresponding
     * to the query.
     *
     * @param query the query of the padset search 
     * @return the result of the search
     */
    @RequestMapping(value = "/_search/padsets",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Padset> searchPadsets(@RequestParam String query) {
        log.debug("REST request to search Padsets for query {}", query);
        return StreamSupport
            .stream(padsetSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
    
    
    /**
     * POST /create/padsets
     * 
     * 
     */
      @RequestMapping(value = "/create/padsets",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
        @Timed
        public ResponseEntity<Padset> createPadsetFromURL() throws URISyntaxException {
    	  log.debug("Creating Padset from URL");
    	  System.out.println("Creating Padset from URL");
    	  PadsetDeserializer mPadsetDeserializer = new PadsetDeserializer();
  		  File testFile = new File("mockData.json");
  		  Padset padset = mPadsetDeserializer.getPadsetFromJSON(testFile);
          return createPadset( padset);
        }
      
      /**
       * POST /create/padsets
       * 
       * 
       */
        @RequestMapping(value = "/create/padsets/{id}",
              method = RequestMethod.GET,
              produces = MediaType.APPLICATION_JSON_VALUE)
          @Timed
    public ResponseEntity<Padset> updatePadsetFromURL( @PathVariable Long id) throws URISyntaxException {
      	  log.debug("Update Padset from URL");
      	  System.out.println("Update Padset from URL");
      	  PadsetDeserializer mPadsetDeserializer = new PadsetDeserializer();
          File testFile = new File("mockData.json");
    	  Padset padset = mPadsetDeserializer.getPadsetFromJSON(testFile);
    	  padset.setId(id);
    	  return updatePadset(padset);
    }


}
