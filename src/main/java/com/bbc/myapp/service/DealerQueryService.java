package com.bbc.myapp.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.bbc.myapp.domain.Dealer;
import com.bbc.myapp.domain.*; // for static metamodels
import com.bbc.myapp.repository.DealerRepository;
import com.bbc.myapp.service.dto.DealerCriteria;
import com.bbc.myapp.service.dto.DealerDTO;
import com.bbc.myapp.service.mapper.DealerMapper;

/**
 * Service for executing complex queries for {@link Dealer} entities in the database.
 * The main input is a {@link DealerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DealerDTO} or a {@link Page} of {@link DealerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DealerQueryService extends QueryService<Dealer> {

    private final Logger log = LoggerFactory.getLogger(DealerQueryService.class);

    private final DealerRepository dealerRepository;

    private final DealerMapper dealerMapper;

    public DealerQueryService(DealerRepository dealerRepository, DealerMapper dealerMapper) {
        this.dealerRepository = dealerRepository;
        this.dealerMapper = dealerMapper;
    }

    /**
     * Return a {@link List} of {@link DealerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DealerDTO> findByCriteria(DealerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Dealer> specification = createSpecification(criteria);
        return dealerMapper.toDto(dealerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DealerDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DealerDTO> findByCriteria(DealerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Dealer> specification = createSpecification(criteria);
        return dealerRepository.findAll(specification, page)
            .map(dealerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DealerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Dealer> specification = createSpecification(criteria);
        return dealerRepository.count(specification);
    }

    /**
     * Function to convert {@link DealerCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Dealer> createSpecification(DealerCriteria criteria) {
        Specification<Dealer> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Dealer_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Dealer_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Dealer_.description));
            }
            if (criteria.getTipAutovehicule() != null) {
                specification = specification.and(buildSpecification(criteria.getTipAutovehicule(), Dealer_.tipAutovehicule));
            }
            if (criteria.getDealerId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDealerId(), Dealer_.dealerId));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getUserId(),
                    root -> root.join(Dealer_.users, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getStocId() != null) {
                specification = specification.and(buildSpecification(criteria.getStocId(),
                    root -> root.join(Dealer_.stoc, JoinType.LEFT).get(Stoc_.id)));
            }
            if (criteria.getPortofoliuId() != null) {
                specification = specification.and(buildSpecification(criteria.getPortofoliuId(),
                    root -> root.join(Dealer_.portofoliu, JoinType.LEFT).get(Portofoliu_.id)));
            }
        }
        return specification;
    }
}
