package com.bbc.myapp.web.rest;

import com.bbc.myapp.service.PortofoliuService;
import com.bbc.myapp.web.rest.errors.BadRequestAlertException;
import com.bbc.myapp.service.dto.PortofoliuDTO;
import com.bbc.myapp.service.dto.PortofoliuCriteria;
import com.bbc.myapp.service.PortofoliuQueryService;

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
 * REST controller for managing {@link com.bbc.myapp.domain.Portofoliu}.
 */
@RestController
@RequestMapping("/api")
public class PortofoliuResource {

    private final Logger log = LoggerFactory.getLogger(PortofoliuResource.class);

    private final PortofoliuService portofoliuService;

    private final PortofoliuQueryService portofoliuQueryService;

    public PortofoliuResource(PortofoliuService portofoliuService, PortofoliuQueryService portofoliuQueryService) {
        this.portofoliuService = portofoliuService;
        this.portofoliuQueryService = portofoliuQueryService;
    }

    /**
     * {@code GET  /portofolius} : get all the portofolius.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of portofolius in body.
     */
    @GetMapping("/portofolius")
    public ResponseEntity<List<PortofoliuDTO>> getAllPortofolius(PortofoliuCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Portofolius by criteria: {}", criteria);
        Page<PortofoliuDTO> page = portofoliuQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /portofolius/count} : count all the portofolius.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/portofolius/count")
    public ResponseEntity<Long> countPortofolius(PortofoliuCriteria criteria) {
        log.debug("REST request to count Portofolius by criteria: {}", criteria);
        return ResponseEntity.ok().body(portofoliuQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /portofolius/:id} : get the "id" portofoliu.
     *
     * @param id the id of the portofoliuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the portofoliuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/portofolius/{id}")
    public ResponseEntity<PortofoliuDTO> getPortofoliu(@PathVariable Long id) {
        log.debug("REST request to get Portofoliu : {}", id);
        Optional<PortofoliuDTO> portofoliuDTO = portofoliuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(portofoliuDTO);
    }
}
