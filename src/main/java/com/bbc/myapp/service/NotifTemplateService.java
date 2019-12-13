package com.bbc.myapp.service;

import com.bbc.myapp.domain.NotifTemplate;
import com.bbc.myapp.repository.NotifTemplateRepository;
import com.bbc.myapp.service.dto.NotifTemplateDTO;
import com.bbc.myapp.service.mapper.NotifTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link NotifTemplate}.
 */
@Service
@Transactional
public class NotifTemplateService {

    private final Logger log = LoggerFactory.getLogger(NotifTemplateService.class);

    private final NotifTemplateRepository notifTemplateRepository;

    private final NotifTemplateMapper notifTemplateMapper;

    public NotifTemplateService(NotifTemplateRepository notifTemplateRepository, NotifTemplateMapper notifTemplateMapper) {
        this.notifTemplateRepository = notifTemplateRepository;
        this.notifTemplateMapper = notifTemplateMapper;
    }

    /**
     * Save a notifTemplate.
     *
     * @param notifTemplateDTO the entity to save.
     * @return the persisted entity.
     */
    public NotifTemplateDTO save(NotifTemplateDTO notifTemplateDTO) {
        log.debug("Request to save NotifTemplate : {}", notifTemplateDTO);
        NotifTemplate notifTemplate = notifTemplateMapper.toEntity(notifTemplateDTO);
        notifTemplate = notifTemplateRepository.save(notifTemplate);
        return notifTemplateMapper.toDto(notifTemplate);
    }

    /**
     * Get all the notifTemplates.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NotifTemplateDTO> findAll() {
        log.debug("Request to get all NotifTemplates");
        return notifTemplateRepository.findAll().stream()
            .map(notifTemplateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one notifTemplate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NotifTemplateDTO> findOne(Long id) {
        log.debug("Request to get NotifTemplate : {}", id);
        return notifTemplateRepository.findById(id)
            .map(notifTemplateMapper::toDto);
    }

    /**
     * Delete the notifTemplate by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NotifTemplate : {}", id);
        notifTemplateRepository.deleteById(id);
    }
}
