package com.bbc.myapp.web.rest;

import com.bbc.myapp.service.StocService;
import com.bbc.myapp.web.rest.errors.BadRequestAlertException;
import com.bbc.myapp.service.dto.StocDTO;
import com.bbc.myapp.service.dto.StocCriteria;
import com.bbc.myapp.service.StocQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.bbc.myapp.domain.Stoc}.
 */
@RestController
@RequestMapping("/api")
public class StocResource {

    private final Logger log = LoggerFactory.getLogger(StocResource.class);

    private final StocService stocService;

    private final StocQueryService stocQueryService;

    public StocResource(StocService stocService, StocQueryService stocQueryService) {
        this.stocService = stocService;
        this.stocQueryService = stocQueryService;
    }

    /**
     * {@code GET  /stocs} : get all the stocs.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stocs in body.
     */
    @GetMapping("/stocs")
    public ResponseEntity<List<StocDTO>> getAllStocs(StocCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Stocs by criteria: {}", criteria);
        Page<StocDTO> page = stocQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /stocs/count} : count all the stocs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/stocs/count")
    public ResponseEntity<Long> countStocs(StocCriteria criteria) {
        log.debug("REST request to count Stocs by criteria: {}", criteria);
        return ResponseEntity.ok().body(stocQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /stocs/:id} : get the "id" stoc.
     *
     * @param id the id of the stocDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stocDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stocs/{id}")
    public ResponseEntity<StocDTO> getStoc(@PathVariable Long id) {
        log.debug("REST request to get Stoc : {}", id);
        Optional<StocDTO> stocDTO = stocService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stocDTO);
    }
}
