package com.bbc.myapp.service;

import com.bbc.myapp.domain.Stoc;
import com.bbc.myapp.repository.StocRepository;
import com.bbc.myapp.service.dto.StocDTO;
import com.bbc.myapp.service.mapper.StocMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Stoc}.
 */
@Service
@Transactional
public class StocService {

    private final Logger log = LoggerFactory.getLogger(StocService.class);

    private final StocRepository stocRepository;

    private final StocMapper stocMapper;

    public StocService(StocRepository stocRepository, StocMapper stocMapper) {
        this.stocRepository = stocRepository;
        this.stocMapper = stocMapper;
    }

    /**
     * Save a stoc.
     *
     * @param stocDTO the entity to save.
     * @return the persisted entity.
     */
    public StocDTO save(StocDTO stocDTO) {
        log.debug("Request to save Stoc : {}", stocDTO);
        Stoc stoc = stocMapper.toEntity(stocDTO);
        stoc = stocRepository.save(stoc);
        return stocMapper.toDto(stoc);
    }

    /**
     * Get all the stocs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<StocDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Stocs");
        return stocRepository.findAll(pageable)
            .map(stocMapper::toDto);
    }


    /**
     * Get one stoc by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StocDTO> findOne(Long id) {
        log.debug("Request to get Stoc : {}", id);
        return stocRepository.findById(id)
            .map(stocMapper::toDto);
    }

    /**
     * Delete the stoc by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Stoc : {}", id);
        stocRepository.deleteById(id);
    }
}
