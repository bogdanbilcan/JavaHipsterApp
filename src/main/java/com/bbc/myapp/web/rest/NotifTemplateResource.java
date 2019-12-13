package com.bbc.myapp.web.rest;

import com.bbc.myapp.service.NotifTemplateService;
import com.bbc.myapp.web.rest.errors.BadRequestAlertException;
import com.bbc.myapp.service.dto.NotifTemplateDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.bbc.myapp.domain.NotifTemplate}.
 */
@RestController
@RequestMapping("/api")
public class NotifTemplateResource {

    private final Logger log = LoggerFactory.getLogger(NotifTemplateResource.class);

    private static final String ENTITY_NAME = "notifTemplate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotifTemplateService notifTemplateService;

    public NotifTemplateResource(NotifTemplateService notifTemplateService) {
        this.notifTemplateService = notifTemplateService;
    }

    /**
     * {@code POST  /notif-templates} : Create a new notifTemplate.
     *
     * @param notifTemplateDTO the notifTemplateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notifTemplateDTO, or with status {@code 400 (Bad Request)} if the notifTemplate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notif-templates")
    public ResponseEntity<NotifTemplateDTO> createNotifTemplate(@Valid @RequestBody NotifTemplateDTO notifTemplateDTO) throws URISyntaxException {
        log.debug("REST request to save NotifTemplate : {}", notifTemplateDTO);
        if (notifTemplateDTO.getId() != null) {
            throw new BadRequestAlertException("A new notifTemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotifTemplateDTO result = notifTemplateService.save(notifTemplateDTO);
        return ResponseEntity.created(new URI("/api/notif-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notif-templates} : Updates an existing notifTemplate.
     *
     * @param notifTemplateDTO the notifTemplateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notifTemplateDTO,
     * or with status {@code 400 (Bad Request)} if the notifTemplateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notifTemplateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notif-templates")
    public ResponseEntity<NotifTemplateDTO> updateNotifTemplate(@Valid @RequestBody NotifTemplateDTO notifTemplateDTO) throws URISyntaxException {
        log.debug("REST request to update NotifTemplate : {}", notifTemplateDTO);
        if (notifTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotifTemplateDTO result = notifTemplateService.save(notifTemplateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, notifTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /notif-templates} : get all the notifTemplates.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notifTemplates in body.
     */
    @GetMapping("/notif-templates")
    public List<NotifTemplateDTO> getAllNotifTemplates() {
        log.debug("REST request to get all NotifTemplates");
        return notifTemplateService.findAll();
    }

    /**
     * {@code GET  /notif-templates/:id} : get the "id" notifTemplate.
     *
     * @param id the id of the notifTemplateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notifTemplateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notif-templates/{id}")
    public ResponseEntity<NotifTemplateDTO> getNotifTemplate(@PathVariable Long id) {
        log.debug("REST request to get NotifTemplate : {}", id);
        Optional<NotifTemplateDTO> notifTemplateDTO = notifTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notifTemplateDTO);
    }

    /**
     * {@code DELETE  /notif-templates/:id} : delete the "id" notifTemplate.
     *
     * @param id the id of the notifTemplateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notif-templates/{id}")
    public ResponseEntity<Void> deleteNotifTemplate(@PathVariable Long id) {
        log.debug("REST request to delete NotifTemplate : {}", id);
        notifTemplateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
