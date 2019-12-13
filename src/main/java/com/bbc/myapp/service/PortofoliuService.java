package com.bbc.myapp.service;

import com.bbc.myapp.domain.Portofoliu;
import com.bbc.myapp.repository.PortofoliuRepository;
import com.bbc.myapp.service.dto.PortofoliuDTO;
import com.bbc.myapp.service.mapper.PortofoliuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Portofoliu}.
 */
@Service
@Transactional
public class PortofoliuService {

    private final Logger log = LoggerFactory.getLogger(PortofoliuService.class);

    private final PortofoliuRepository portofoliuRepository;

    private final PortofoliuMapper portofoliuMapper;

    public PortofoliuService(PortofoliuRepository portofoliuRepository, PortofoliuMapper portofoliuMapper) {
        this.portofoliuRepository = portofoliuRepository;
        this.portofoliuMapper = portofoliuMapper;
    }

    /**
     * Save a portofoliu.
     *
     * @param portofoliuDTO the entity to save.
     * @return the persisted entity.
     */
    public PortofoliuDTO save(PortofoliuDTO portofoliuDTO) {
        log.debug("Request to save Portofoliu : {}", portofoliuDTO);
        Portofoliu portofoliu = portofoliuMapper.toEntity(portofoliuDTO);
        portofoliu = portofoliuRepository.save(portofoliu);
        return portofoliuMapper.toDto(portofoliu);
    }

    /**
     * Get all the portofolius.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PortofoliuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Portofolius");
        return portofoliuRepository.findAll(pageable)
            .map(portofoliuMapper::toDto);
    }


    /**
     * Get one portofoliu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PortofoliuDTO> findOne(Long id) {
        log.debug("Request to get Portofoliu : {}", id);
        return portofoliuRepository.findById(id)
            .map(portofoliuMapper::toDto);
    }

    /**
     * Delete the portofoliu by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Portofoliu : {}", id);
        portofoliuRepository.deleteById(id);
    }
}
