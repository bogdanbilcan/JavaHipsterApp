package com.bbc.myapp.service;

import com.bbc.myapp.domain.Dealer;
import com.bbc.myapp.repository.DealerRepository;
import com.bbc.myapp.service.dto.DealerDTO;
import com.bbc.myapp.service.mapper.DealerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Dealer}.
 */
@Service
@Transactional
public class DealerService {

    private final Logger log = LoggerFactory.getLogger(DealerService.class);

    private final DealerRepository dealerRepository;

    private final DealerMapper dealerMapper;

    public DealerService(DealerRepository dealerRepository, DealerMapper dealerMapper) {
        this.dealerRepository = dealerRepository;
        this.dealerMapper = dealerMapper;
    }

    /**
     * Save a dealer.
     *
     * @param dealerDTO the entity to save.
     * @return the persisted entity.
     */
    public DealerDTO save(DealerDTO dealerDTO) {
        log.debug("Request to save Dealer : {}", dealerDTO);
        Dealer dealer = dealerMapper.toEntity(dealerDTO);
        dealer = dealerRepository.save(dealer);
        return dealerMapper.toDto(dealer);
    }

    /**
     * Get all the dealers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DealerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dealers");
        return dealerRepository.findAll(pageable)
            .map(dealerMapper::toDto);
    }

    /**
     * Get all the dealers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DealerDTO> findAllWithEagerRelationships(Pageable pageable) {
        return dealerRepository.findAllWithEagerRelationships(pageable).map(dealerMapper::toDto);
    }
    

    /**
     * Get one dealer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DealerDTO> findOne(Long id) {
        log.debug("Request to get Dealer : {}", id);
        return dealerRepository.findOneWithEagerRelationships(id)
            .map(dealerMapper::toDto);
    }

    /**
     * Delete the dealer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Dealer : {}", id);
        dealerRepository.deleteById(id);
    }
}
